import sys

n = int(sys.stdin.readline())
house = list(map(int, sys.stdin.readline().split()))
house.sort()

min_dist_sum = 0
min_pointer = house[0]
pointer = min_pointer
for next in house:
    min_dist_sum += next - pointer

prev_dist_sum = min_dist_sum
for i in range(1, len(house)):
    next = house[i]
    next_step = next - pointer
    next_dist_sum = prev_dist_sum + (next_step * i) - (next_step * (len(house)-1-i)) - next_step
    
    if next_dist_sum < min_dist_sum:
        min_dist_sum = next_dist_sum
        min_pointer = next

    pointer = next
    prev_dist_sum = next_dist_sum

print(min_pointer)

