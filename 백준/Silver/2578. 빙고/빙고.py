import sys

board = [list(map(int, sys.stdin.readline().split())) for _ in range(5)]
next_number = [num for _ in range(5) for num in map(int, sys.stdin.readline().split())]
position = {}
call_counter = 0

def check_bingo(counter):
    global bingo_counter
    if counter == 5:
        bingo_counter += 1

for i in range(5):
    for j in range(5):
        num = board[i][j]
        position[num] = (i,j)

for num in next_number:
    call_counter += 1
    # 호명한 숫자 제거
    num_position = position[num]
    board[num_position[0]][num_position[1]] = -1

    bingo_counter = 0
    # 가로 빙고 검사
    for col in range(5):
        check_bingo(board[col].count(-1))

    # 세로 빙고 검사
    for i in range(5):
        tmp_counter = 0
        for j in range(5):
            if board[j][i] == -1:
                tmp_counter += 1
        check_bingo(tmp_counter)
    # 대각선 빙고 검사 (하향 대각선, 상승 대각선 동시 검사)
    decline_tmp_counter = 0
    rise_tmp_counter = 0
    for i in range(5):
        if board[i][i] == -1:
            decline_tmp_counter += 1
        if board[i][4-i] == -1:
            rise_tmp_counter += 1

    check_bingo(decline_tmp_counter)
    check_bingo(rise_tmp_counter)

    if bingo_counter > 2:
        print(call_counter)
        break
