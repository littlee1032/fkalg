#include <iostream>
#include <cstdio>
#include <memory.h>
using namespace std;
const int maxn = 12;
const int maxm = 32;
const int mod = 11380;
int dp[maxn][maxn][maxn][maxm];
bool vis[maxn][maxn][maxn][maxm];
int a,b,c,d;

int dfs(int l1,int l2,int l3,int dep)
{
  if(l1 == 0 && l2 == 0 && l3 == 0)
    {
      vis[l1][l2][l3][dep] = true;
      return dp[l1][l2][l3][dep] = 1;
    }
  if(dep == 0)
    {
      vis[l1][l2][l3][dep] = true;
      return dp[l1][l2][l3][dep] = 0;
    }
  if(vis[l1][l2][l3][dep])
    {
      return dp[l1][l2][l3][dep];
    }
  int res = 0;
  for(int i=0;i<=l3;i++)
    {
      if(i)
        {
          res += (dfs(0,0,i-1,dep-1) * dfs(l1,l2,l3-i,dep)) % mod;
          res %= mod;
        }
      for(int j=0;j<=l2;j++)
        {
          if(j)
            {
              res += (dfs(0,j-1,i,dep-1) * dfs(l1,l2-j,l3-i,dep)) % mod;
              res %= mod;
            }
          for(int k=1;k<=l1;k++)
            {
              res += (dfs(k-1,j,i,dep-1) * dfs(l1-k,l2-j,l3-i,dep)) % mod;
              res %= mod;
            }
        }
    }
  vis[l1][l2][l3][dep] = true;
  return dp[l1][l2][l3][dep] = res;
}

int main()
{
  memset(vis,false,sizeof(vis));
  while(~scanf("%d %d %d %d",&a,&b,&c,&d))
    {
      dfs(a,b,c,d);
      if(d) dfs(a,b,c,d-1);
      int ans;
      if(d == 0) ans = dp[a][b][c][d];
      else ans = ((dp[a][b][c][d] - dp[a][b][c][d-1]) % mod + mod) % mod;
      printf("%d\n",ans);
    }
  return 0;
}
