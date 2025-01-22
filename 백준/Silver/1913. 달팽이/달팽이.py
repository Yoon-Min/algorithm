import sys

n = int(sys.stdin.readline())
k = int(sys.stdin.readline())
direction_vector = [(1,0), (0,1), (-1,0), (0,-1)]
is_visited = [ [False]*n for _ in range(n)]
result = [[1]*n for _ in range(n)]
k_c = (0,0)
is_visited[0][0] = True

cur = (0,0)
next_num = n*n
vector_index = 0

while True:
    result[cur[0]][cur[1]] = next_num
    if next_num == k:
        k_c = cur

    if next_num == 1:
        for l in result:
            print(' '.join(map(str,l)))
        print(k_c[0]+1, k_c[1]+1)
        exit()

    v = direction_vector[vector_index]
    next_vector_index = vector_index
    next_i = cur[0] + v[0]
    next_j = cur[1] + v[1]

    if next_i not in range(0, n) or next_j not in range(0, n) or is_visited[next_i][next_j]:
        next_vector_index = (vector_index+1)%4
        v = direction_vector[next_vector_index]
        next_i = cur[0] + v[0]
        next_j = cur[1] + v[1]

    is_visited[next_i][next_j] = True
    cur = (next_i,next_j)
    vector_index = next_vector_index
    next_num -= 1







