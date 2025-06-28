import sys
import heapq

n,m = map(int, sys.stdin.readline().split())
card = list(map(int, sys.stdin.readline().split()))
heapq.heapify(card)

for _ in range(m):
    min_first = heapq.heappop(card)
    min_second = heapq.heappop(card)
    heapq.heappush(card, min_first + min_second)
    heapq.heappush(card, min_first + min_second)

print(sum(card))