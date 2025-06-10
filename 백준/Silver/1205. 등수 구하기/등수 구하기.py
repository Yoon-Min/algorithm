(n, score, p) = map(int, input().split())
rank_score = [-1 for _ in range(p)]
if n > 0:
    for i, s in enumerate(list(map(int, input().split()))):
        rank_score[i] = s

i = 0
while(i < p and score <= rank_score[i]):
    i += 1

if i == p:
    print(-1)
elif rank_score[i] == -1 and score < rank_score[-1]: # 가장 최하위 점수라 마지막에 추가
    print(i+1)
else: # 중간에 추가
    for rank in range(len(rank_score)):
        if rank_score[rank] <= score:
            print(rank+1)
            break