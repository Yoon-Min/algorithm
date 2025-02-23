from sys import stdin
from math import gcd
input = stdin.readline

mod = 1_000_000_007
ans = 0

# 거듭제곱
def multi(x,y):
    if y == 1: return x
    if y%2 : return x * multi(x,y-1) % mod
    t = multi(x, y // 2)
    return t * t % mod

for _ in range(int(input())):
    n,m = map(int,input().split())
    # 기약분수 만들기
    g = gcd(n,m)
    n //= g
    m //= g
	
    # 모듈러 역원 구해서 더하기
    ans += m * multi(n,mod-2) % mod
    ans %= mod

print(ans)