(a,p) = map(int, input().split())
arr = [a]
idx = {a : 0}

while(True):
    cur = str(arr[-1])
    next = 0
    for digit in cur:
        next += pow(int(digit), p)

    if next in idx:
        print(idx[next])
        break

    arr.append(next)
    idx[next] = len(arr)-1

