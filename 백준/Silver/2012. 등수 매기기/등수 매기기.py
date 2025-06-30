import sys

n = int(sys.stdin.readline().strip())
exp_score = [int(sys.stdin.readline().strip()) for _ in range(n)]
exp_score.sort()

next_rank = 1
sum = 0
for i in range(len(exp_score)):
    sum += abs(exp_score[i] - next_rank)
    next_rank += 1

print(sum)

