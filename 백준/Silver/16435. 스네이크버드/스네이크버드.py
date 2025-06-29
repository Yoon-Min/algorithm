import sys
import heapq

n,l = map(int, sys.stdin.readline().split())
h = list(map(int, sys.stdin.readline().split()))
h.sort()

i = 0
while i < len(h) and h[i] <= l: 
    l += 1
    i += 1

print(l)