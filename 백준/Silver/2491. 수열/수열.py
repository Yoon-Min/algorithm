n = int(input())
arr = list(map(int, input().split()))

max_len = 1
max_len_desc = 1

l = 0
r = 1
while(r < len(arr)):
    if arr[r-1] <= arr[r]:
        max_len = max(max_len, r-l+1)
        r += 1
    else:
        l = r
        r += 1

l = 0
r = 1
while(r < len(arr)):
    if arr[r-1] >= arr[r]:
        max_len_desc = max(max_len_desc, r-l+1)
        r += 1
    else:
        l = r
        r += 1
    
print(max(max_len, max_len_desc))