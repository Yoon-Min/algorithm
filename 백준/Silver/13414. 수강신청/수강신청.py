from collections import deque

(k,l) = map(int, input().split())
reg = [input() for _ in range(l)]

q = deque()
waiting = {}

for student_num in reg:
    if student_num in waiting:
        q[waiting[student_num]] = -1

    q.append(student_num)
    waiting[student_num] = len(q)-1

for student_num in q:
    if student_num != -1:
        k -= 1
        print(student_num)
        if k == 0:
            break

