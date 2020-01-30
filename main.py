import os
import requests

finish = 0

print("This is able to get all matches, but will currently only pull qualifiers as those")
print("are what will be scouted and I am currently unable to find practice match logs")
while finish == 0:
    inpt = ""
    rawTeams = ""
    teams = ""

    rawData = input("\nPaste in a URL or full text from https://www.thebluealliance.com/api/v3/event/MATCHID/matches\n")

    if rawData[0] == "[":
        while True:
            line = input("")
            if line == "]":
                break
            teams += line
    else:
        try:
            teams = str(requests.get(rawData).text)
            if "Invalid endpoint" in teams:
                print("Unable to access the webpage (It's buggy)")
                continue
        except:
            print("Error with formatting, please put a valid URL or raw text")
            continue

    print(teams)

    # Conversion:
    teams = teams \
        .replace("\n", "") \
        .replace(" ", "")\
        .replace("{", "")\
        .replace("}", "")\
        .replace("\"", "")\
        .replace(",frc", ".frc")\
        .replace(",", "\n") \
        .splitlines()

    temp = []

    for t in teams: #Only gets team keys
        if len(t) < 9:
            continue
        if (t[:9] == "team_keys") or (t[:13] == "comp_level:qm") or (t[:12] == "match_number"):
            temp.append(t)

    teams = []
    for i in range(len(temp)-2):
        if "qm" in temp[i+2]:
            teams.append(temp[i+3][13:]+":")  # Match number
            teams.append(temp[i])  # Blue alliance
            teams.append(temp[i+1])  # Red alliance

    # By now all qualifier alliances have been added to teams

    temp = []

    for t in range(int(len(teams)/3)):
        temp.append(teams[t*3] + teams[t*3 + 1] + teams[t*3 + 2])

    teams = temp

    for t in range(len(teams)):
        teams[t] = teams[t]\
            .replace("team_keys:[", "")\
            .replace("frc", "")\
            .replace("]", ".")\
            .replace(".", ",")
        teams[t] = teams[t][0:len(teams[t])-1]  # Remove the last comma

    temp = []

    for t in teams:
        temp.append(t.replace(",", "\n").replace(":", ":\n").splitlines())

    teams = temp

    # :Conversion

    # print(teams)  # base teams conversion

    scoutingTeams = ["", "", "", "", "", "", ""]  # 1 match number and 6 teams per match

    for t in range(len(teams)):
        temp = ""
        for i in range(len(teams[t])-1):
            scoutingTeams[i] = scoutingTeams[i] + "." + teams[t][i] + teams[t][i+1] + "\n"

    # print(scoutingTeams)  # full scouting files

    for s in range(len(scoutingTeams)-1):
        file = open("ScoutingTeams Documents/" + "ScoutingTeams"+str(s+1)+".txt", "w+")
        file.write(str(scoutingTeams[s]))
        file.close()

    print("\n\nFiles created! Be sure to rename each file to 'ScoutingTeams.txt' when sending to the tablet")
    print("Blue alliance teams: 1-3, Red alliance teams: 4-6")
    print("The files are located at "+os.path.dirname(os.path.realpath(__file__)))

    finish = 1
