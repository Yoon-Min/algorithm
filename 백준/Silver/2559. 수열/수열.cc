#include<iostream>
using namespace std;

int temperature;
int cumculativeSum[100001] = {0,};

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N, K;
    cin >> N >> K;

    for (int i = 1; i <= N; i++){
        cin >> temperature;
        cumculativeSum[i] = cumculativeSum[i-1] + temperature;    
    }

    int currScopeSum;
    int maxScopeSum = -200000000;
    for (int i = K; i <= N; i++){
        currScopeSum = cumculativeSum[i] - cumculativeSum[i-K];
        if(currScopeSum > maxScopeSum) maxScopeSum = currScopeSum;
    }
    
    cout << maxScopeSum;

    return 0;
}