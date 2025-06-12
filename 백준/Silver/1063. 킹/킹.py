(k, s, n) = input().split()
c = [input() for _ in range(int(n))]

v = {
    "R" : (0,1),
    "L" : (0,-1),
    "B" : (-1,0),
    "T" : (1,0),
    "RT" : (1,1),
    "LT" : (1,-1),
    "RB" : (-1,1),
    "LB" : (-1,-1)
}

k = (ord(k[1]) - 49, ord(k[0]) - 65)
s = (ord(s[1]) - 49, ord(s[0]) - 65)

for next in c:
    nx = v[next][1] + k[1]
    ny = v[next][0] + k[0]

    if nx not in range(8) or ny not in range(8):
        continue
    
    if ny == s[0] and nx == s[1]:
        s_nx = v[next][1] + s[1]
        s_ny = v[next][0] + s[0]
        
        if s_nx not in range(8) or s_ny not in range(8):
            continue

        s = (s_ny, s_nx)

    k = (ny,nx)
    
print(f"{chr(k[1]+65)}{chr(k[0]+49)}")
print(f"{chr(s[1]+65)}{chr(s[0]+49)}")