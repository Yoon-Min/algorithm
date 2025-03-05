#include<iostream>
#include<vector>
#include<queue>
using namespace std;

int MovingCount(queue<int> &, int);
int isExist[200001]={0,};
int main(){
    int N,M;
    queue<int> q;
    cin >> N >> M;

    if(N==M){ cout << 0;}
    else{
        q.push(N-1);
        q.push(N+1);
        q.push(N*2);
        isExist[N-1]=1;
        isExist[N+1]=1;
        isExist[N*2]=1;
        cout << MovingCount(q,M);
    }
}

int MovingCount(queue<int> &q,int M)
{
    int count=1;
    while(!q.empty()){
        int curr_level_node=q.size();
        while (curr_level_node!=0)
        {
            int node=q.front();
            if(node==M){
                return count;
            }
            q.pop();
            curr_level_node--;
            int add=node+1;
            int sub=node-1;
            int mul=node*2;
            if(isExist[add]==0){
                isExist[add]=1;
                q.push(add);
            }
            if(isExist[sub]==0){
                isExist[sub]=1;
                q.push(sub);
            }
            if(mul<=200000 && isExist[mul]==0){
                isExist[mul]=1;
                q.push(mul);
            }
        }
        count++;
    }
    return 0;
}