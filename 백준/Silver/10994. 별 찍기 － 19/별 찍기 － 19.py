import sys

n = int(sys.stdin.readline())
length = 4*(n-1) + 1
cur_col = 0
l = 0
r = length
result = [[' ']*length for _ in range(length)]

for _ in range(n):
    for i in range(l+1, r-1):
        result[i][l] = '*'
        result[i][-1-l] = '*'
        result[length-i][0] = '*'
        result[length-i][-1] ='*'
    for j in range(l, r):
        result[l][j] = '*'
        result[length-1-l][j] = '*'

    cur_col += 2
    l += 2
    r -= 2


for inner in result:
    print(''.join(inner))


