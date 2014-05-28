#include <cstdio>
#include <iostream>
#include <cstring>
#include <queue>

using namespace std;

const int MAXX=10005;

int dp[MAXX];
int sumt[MAXX], sumf[MAXX], que[MAXX];
int n, s, head, rear;

int SubDP(int j, int k){
  return dp[j] - dp[k];
}

int SubT(int j, int k){
  return sumt[j] - sumt[k];
}

int GetDp(int i, int j){
  return (sumt[i] - sumt[j] + s) * sumf[i] + dp[j];
}

void DP(){
  int i;
  dp[n+1] = sumt[n+1] = sumf[n+1] = 0; //这里为边界条件
  for(i = n; i >= 1; i--) sumt[i] += sumt[i+1];//sumt[i]表示从i到n的所有工作所用时间和
  for(i = n; i >= 1; i--) sumf[i] += sumf[i+1];//sumf[i]表示从i到n的所有工作花费系数的和
  head = 0;
  rear = -1;
  que[++rear]=n+1;

  for(i = n; i >= 1; i--){   //这里的等号我之所以加上是因为上面分析了其实等于的时候也可以去点head下面道理相同
    while(head < rear && SubDP(que[head+1],que[head]) <= SubT(que[head + 1], que[head]) * sumf[i]) head++;
    dp[i] = GetDp(i, que[head]);
    while(head < rear && SubDP(i, que[rear]) * SubT(que[rear], que[rear - 1])<= SubDP(que[rear], que[rear - 1]) * SubT(i, que[rear])) rear--;
    que[++rear] = i;
  }
  printf("%d\n", dp[1]);
}

int main(){
  while(scanf("%d%d", &n, &s)!=EOF){
    for(int i=1;i<=n;i++){
      scanf("%d%d", sumt+i, sumf+i);
    }
    DP();
  }
  return 0;
}
