import sys

n = int(input())
score = [int(input()) for _ in range(n)]

last = score[-1]
counter = 0

for i in range(n-2, -1, -1):
    if last <= score[i]:
        counter += score[i] - (last-1)
        last -= 1
    else:
        last = score[i]

print(counter)