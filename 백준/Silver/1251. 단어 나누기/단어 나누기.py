str = input()
result = 'z'*50

for i in range(len(str)):
    for j in range(i+1, len(str)):
        left = str[:i+1]
        mid = str[i+1:j+1]
        right = str[j+1:]

        if not left or not mid or not right:
            continue

        tmp_str = left[::-1] + mid[::-1] + right[::-1]
        
        if tmp_str < result:
            result = tmp_str

print(result)