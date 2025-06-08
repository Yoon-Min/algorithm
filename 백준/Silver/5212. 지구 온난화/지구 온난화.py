d_vector = [
    (0,1),
    (1,0),
    (0,-1),
    (-1,0)
]

(r,c) = map(int, input().split())
map = [list(input()) for _ in range(r)]
removal = []

for i in range(r):
    for j in range(c):
        if map[i][j] == 'X':
            sea = 0
            for v in d_vector:
                nx = v[1] + j
                ny = v[0] + i
                if nx not in range(c) or ny not in range(r) or map[ny][nx] == '.':
                    sea += 1
            
            if sea >= 3:
                removal.append((i,j))

for pos in removal:
    map[pos[0]][pos[1]] = '.'

horizontal_top = -1
horizontal_bottom = -1
vertical_left = -1
vertical_right = -1

for i in range(r):
    if horizontal_top == -1 and 'X' in map[i]:
        horizontal_top = i
    
    if horizontal_bottom == -1 and 'X' in map[r-1-i]:
        horizontal_bottom = r-1-i

for i in range(c):
    for j in range(r):
        if vertical_left == -1 and map[j][i] == 'X':
            vertical_left = i
        
        if vertical_right == -1 and map[j][c-1-i] == 'X':
            vertical_right = c-1-i

for i in range(horizontal_top, horizontal_bottom+1):
    for j in range(vertical_left, vertical_right+1):
        print(map[i][j], end="")
    print()