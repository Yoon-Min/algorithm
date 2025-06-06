def getFirstHigherPosition(arr, s, comp):
    for i in range(s, len(arr)):
        if comp <= arr[i][1]:
            return i
    return -1

n = int(input())
arr = [list(map(int,input().split(" "))) for _ in range(n)]
arr.sort(key = lambda x: x[0])

ptr = 0
area = 0

while(ptr < len(arr)-1):
    next = getFirstHigherPosition(arr, ptr+1, arr[ptr][1])
    h = -1
    
    if next == -1:
        max = ptr+1
        for i in range(ptr+1, len(arr)):
            if arr[max][1] <= arr[i][1]:
                max = i
        next = max
        h = arr[next][1]
        area += arr[ptr][1] - h
    else:
        h = arr[ptr][1]

    w = arr[next][0] - arr[ptr][0]
    
    area += w * h
    ptr = next

area += arr[-1][1]
print(area)

