(w,h) = map(int,input().split())
store = int(input())
storePos = [list(map(int,input().split())) for _ in range(store)]
policePos = list(map(int,input().split()))

minDistSum = 0

def getDist(curLoc, targetLoc, dir):
    if dir in (1,4):
        return curLoc - targetLoc
    elif dir in (2,3):
        return targetLoc - curLoc

def getTargetLoc(curDir):
    if curDir in (1,4):
        return 0
    elif curDir == 2:
        return w
    else:
        return h


for pos in storePos:
    curPolicePos = [policePos[0],policePos[1]]
    storeDir = pos[0]
    storeLoc = pos[1]
    policeDir = curPolicePos[0]
    policeLoc = curPolicePos[1]

    tmpSum = 0

    if policeDir == storeDir:
        tmpSum = abs(policeLoc - storeLoc)
        minDistSum += min(tmpSum, (2*w+2*h) - tmpSum)
        continue

    while(policeDir != storeDir):
        tmpSum += getDist(policeLoc, getTargetLoc(policeDir), policeDir)

        if policeDir == 1:
            policeLoc = 0
            policeDir = 3
        elif policeDir == 2:
            policeLoc = h
            policeDir = 4
        elif policeDir == 3:
            policeLoc = 0
            policeDir = 2
        else:
            policeLoc = w
            policeDir = 1
        
    tmpSum += getDist(policeLoc, storeLoc, policeDir)
    minDistSum += min(tmpSum, (2*w+2*h) - tmpSum)

print(minDistSum)