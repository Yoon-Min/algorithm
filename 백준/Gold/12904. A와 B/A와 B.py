import sys

s = sys.stdin.readline().strip()
t = sys.stdin.readline().strip()

while len(s) < len(t):
    if t[-1] == 'A':
        t = t[:len(t)-1]
    else:
        t = t[:len(t)-1][::-1]

if s == t:
    print(1)
else:
    print(0)


    