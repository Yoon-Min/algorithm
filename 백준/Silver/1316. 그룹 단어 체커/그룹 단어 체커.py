#그룹 단어 체커

n=int(input())
SUM=0
for i in range(n):
    d={}
    result=True
    s=input()

    for i in range(len(s)):
        if s[i] not in d:
            d[s[i]]=i
        else:
            if i-d[s[i]]!=1:
                result=False
                break
            else:
                d[s[i]]=i
    
    if result==True: SUM+=1

print(SUM)
    
        
