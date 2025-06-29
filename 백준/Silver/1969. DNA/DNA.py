import sys
from collections import Counter

n,m = map(int, sys.stdin.readline().split())
dna = [sys.stdin.readline().strip() for _ in range(n)]
result = []
hamming_dist = 0

transposed = list(zip(*dna))

for row in transposed:
    d = dict(Counter(row))
    max_key = max(d, key = d.get)
    
    for c in row:
        if d[c] == d[max_key] and c < max_key:
            max_key = c
    
    result.append(max_key)
    hamming_dist += len(row) - d[max_key]

print("".join(result))
print(hamming_dist)
