import sys

n,m = map(int, sys.stdin.readline().split())
j = int(sys.stdin.readline())
apple_pos = [int(sys.stdin.readline())-1 for _ in range(j)]

b_l = 0
b_r = m-1
count = 0

for next_apple in apple_pos:

    if b_l <= next_apple <= b_r:
        continue

    if next_apple < b_l:
        count += b_l - next_apple
        b_r -= b_l - next_apple
        b_l = next_apple
    else:
        count += next_apple - b_r
        b_l += next_apple - b_r
        b_r = next_apple

print(count)