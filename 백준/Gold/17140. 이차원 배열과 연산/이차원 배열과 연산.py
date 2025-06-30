import sys
from collections import Counter
from itertools import chain

r,c,k = map(int, sys.stdin.readline().split())
m = [list(map(int, sys.stdin.readline().split())) for _ in range(3)]

round = 0
total_col = len(m)
total_row = len(m[0])

while round < 101:

    if r <= total_col and c <= total_row and m[r-1][c-1] == k:
        break

    new_m = []

    if total_col >= total_row:
        max_len = 0
        for inner in m:
            inner = [e for e in inner if e != 0]
            counter_list = list(dict(Counter(inner)).items())
            cal_inner_by_r = sorted(counter_list, key = lambda x: (x[1], x[0]))
            r_inner = list(chain.from_iterable(cal_inner_by_r))
            new_m.append(r_inner)
            max_len = max(max_len, len(r_inner))

        for inner in new_m:
            if len(inner) < max_len:
                for _ in range(max_len - len(inner)):
                    inner.append(0)

    else:
        transposed = [list(inner) for inner in list(zip(*m))]
        max_len = 0

        for inner in transposed:
            inner = [e for e in inner if e != 0]
            counter_list = list(dict(Counter(inner)).items())
            cal_inner_by_c = sorted(counter_list, key = lambda x: (x[1],x[0]))
            c_inner = list(chain.from_iterable(cal_inner_by_c))
            new_m.append(c_inner)
            max_len = max(max_len, len(c_inner))
            
        for inner in new_m:
            if len(inner) < max_len:
                for _ in range(max_len - len(inner)):
                    inner.append(0)

        new_m = [list(inner) for inner in list(zip(*new_m))]

    m.clear()
    for inner in new_m:
        while len(inner) > 100:
            inner.pop()
        m.append(inner)

    round += 1
    total_col = len(m)
    total_row = len(m[0])

if round == 101:
    round = -1

print(round)
