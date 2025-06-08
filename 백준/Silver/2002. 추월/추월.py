n = int(input())
enterance_seq = [input() for _ in range(n)]
exit_seq = [input() for _ in range(n)]
result = 0

index_table = {}
index_counter = 0

for num in enterance_seq:
    if num not in index_table:
        index_table[num] = index_counter
        index_counter += 1

for i in range(n):
    cur = index_table[exit_seq[i]]
    for j in range(i+1,n):
        if index_table[exit_seq[j]] < cur:
            result += 1
            break

print(result)