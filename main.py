import os

inpt = ""
finish = 0
rawTeams = ""
teams = ""

while finish == 0:
    rawTeams = input("Paste in the raw teams. '?' for format.\n")

    if rawTeams == "?":
        print("Format from https://www.thebluealliance.com/api/v3/event/2020mosl/teams/keys:")
        print("[\n    \"frc1234\",\n    \"frc5678\",\n    \"frc9012\"\n]")
        continue

    while "]" not in teams:
        teams += input("")

    while inpt not in ["txt", "print"]:
        print("Do you want the converted teams as .'txt's or 'print'ed?")
        inpt = input("")

        if inpt not in ["txt", "print"]:
            print("Please put .'txt' or 'print'")

    #Converts
    teams = teams.replace("\"", "").replace("frc", "").replace("[", "").replace("]", "").replace(" ","").replace(",", "\n").splitlines()
    for t in range(len(teams)):
        teams[t] = str(t+1)+":"+teams[t]

    if inpt == "txt":
        file = open("ScoutingTeams Documents/"+"ScoutingTeams.txt", "w+")
        for t in teams:
            file.write(str(t)+"\n")
        file.close()

        print("Files created! Be sure to rename each file to 'ScoutingTeams.txt' when sending to the tablet")
        print("The files are located at "+os.path.dirname(os.path.realpath(__file__)))

    elif inpt == "print":
        for t in teams:
            print(t)

    print("\n"*3)