n = int(input())
voter_list = [int(input()) for _ in range(n)]
count = 0

if n == 1:
    print(0)

else:
    max_voter = voter_list[1]
    max_index = 1
    for i in range(1, len(voter_list)):
        if max_voter < voter_list[i]:
            max_voter = voter_list[i]
            max_index = i

    while(voter_list[0] <= max_voter):
        voter_list[max_index] -= 1
        voter_list[0] += 1
        count += 1

        max_voter = voter_list[1]
        max_index = 1
        for i in range(1, len(voter_list)):
            if max_voter < voter_list[i]:
                max_voter = voter_list[i]
                max_index = i

    print(count)