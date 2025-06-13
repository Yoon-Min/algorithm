from collections import deque

d_vector = [(0,1),(1,0),(0,-1),(-1,0)]
counter = 1

(c,r) = map(int, input().split())
k = int(input())
map = [[0 for _ in range(r)] for _ in range(c)]

col_range = range(c)
row_range = range(r)

q = deque()

q.append((0,0,0))

while(len(q) != 0 and counter <= c*r):
    cur = q.popleft()
    next_dir = cur[-1]

    map[cur[0]][cur[1]] = counter

    if map[cur[0]][cur[1]] == k:
        print(f"{cur[0]+1} {cur[1]+1}")
        break

    counter += 1

    nx = cur[1] + d_vector[next_dir][1]
    ny = cur[0] + d_vector[next_dir][0]
    
    if nx not in row_range or ny not in col_range or map[ny][nx] != 0:
        next_dir = (next_dir+1) % 4

        nx = cur[1] + d_vector[next_dir][1]
        ny = cur[0] + d_vector[next_dir][0]

        if next_dir == 0:
            col_range = range(col_range.start+1, col_range.stop-1)
            row_range = range(row_range.start+1, row_range.stop-1)

    q.append((ny,nx,next_dir))

if counter > c*r:
    print(0)