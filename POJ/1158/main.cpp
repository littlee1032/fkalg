// poj 1158 TRAFFIC LIGHTS (Dijkstra算法的应用)
/*
  其实最短路的裸题几乎不会出现，要学的是最优路径的性质的应用，本题虽然加了个时间限制，
  但如果能看穿并证明其仍旧符合最优路径性质，那问题就迎刃而解了。
*/
#include <iostream>
using namespace std;
const int inf = 1<<26;
int n,m;
struct node
{
  int color,r,ti[2];
}p[310];

int con[310][310],mark[310],wt[310];

//计算t时刻k点的状态和距离变换到下一状态所需的时间
int state(int k,int t,int &r)
{
  if (t<p[k].r) { r=p[k].r-t; return p[k].color;}
  t=(t-p[k].r)%(p[k].ti[0]+p[k].ti[1]);
  int c=p[k].color^1;
  if (t<p[k].ti[c]) { r=p[k].ti[c]-t; return c;}
  else { r=p[k].ti[c^1]-t; return c^1; }
}

//计算在t时从from点到to点所需等待的时间
int wait(int from,int to,int t)
{
  int r1,r2;
  int c1=state(from,t,r1),c2=state(to,t,r2);
  if (c1==c2) return 0;
  if (r1!=r2) return r1<r2?r1:r2;
  if (p[from].ti[c1^1]<p[to].ti[c2^1]) return r1+p[from].ti[c1^1];
  else if (p[from].ti[c1^1]>p[to].ti[c2^1]) return r2+p[to].ti[c2^1];
  else return -1;

}
//传统Dijkstra算法
int dijk(int s,int t)
{
  for (int i=0;i<=n;i++) wt[i]=inf;
  memset(mark,0,sizeof(mark));
  mark[s]=1;
  wt[s]=0;
  int Min=-1;
  for (int v=s;Min;v=Min)
    {
      Min=0;
      for (int i=1;i<=n;i++)
        if (!mark[i] && con[v][i])
          {
            int tm=wait(v,i,wt[v]);
            if (tm==-1) continue;
            else {
              wt[i]=min(wt[i],wt[v]+tm+con[v][i]);
              if (wt[i]<wt[Min]) Min=i;
            }
          }

      mark[Min]=1;
    }

  if (wt[t]>=inf) return 0;
  return wt[t];
}
int main()
{
  int s,t,a,b,c;
  while (scanf("%d%d",&s,&t)!=EOF)
    {
      scanf("%d%d",&n,&m);
      char op[2];
      for (int i=1;i<=n;i++)
        {
          scanf("%s%d%d%d",op,&p[i].r,&p[i].ti[0],&p[i].ti[1]);
          if (op[0]=='B') p[i].color=0;
          else p[i].color=1;
        }
      memset(con,0,sizeof(con));
      while (m--) {
        scanf("%d%d%d",&a,&b,&c);
        con[a][b]=con[b][a]=c;
      }
      printf("%d\n",dijk(s,t));

    }
  return 0;
}
