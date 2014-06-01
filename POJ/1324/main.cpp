/*
  用C++提交，
  368K188MS
  犀利啊
*/
#include<cstdio>
#include<cstring>
#include<queue>
#include<map>
using namespace std;
int n,m,l,len;
bool gap[21][21];
int mat[21][21];
int dx[4]= {0,1,0,-1};
int dy[4]= {1,0,-1,0};
int x[10],y[10];
struct Snack
{
  int state;
  int step;
  Snack() {}
  Snack(int sta,int ste)
  {
    state=sta;
    step=ste;
  }
  bool operator < (const Snack& a)  const
  {
    return mat[(state>>len)&31][((state>>5)>>len)&31]+step
      > mat[(a.state>>len)&31][((a.state>>5)>>len)&31]+a.step;
  }
};

int front,rear;
int inputSnack()
{
  int state=0,ll=0;
  scanf("%d%d",&x[0],&y[0]);
  x[0]--,y[0]--;
  for(int i=1; i<l; ++i)
    {
      scanf("%d%d",&x[i],&y[i]);
      x[i]--,y[i]--;//[i-1]----->[i]
      if(x[i-1]==x[i])//就是这一点与上一点在同一个横坐标上
        {
          if(y[i]>y[i-1]) state=state | (0<<ll);//UP
          else state=state | (2<<ll);//DOWN
        }
      else
        {
          if(x[i]>x[i-1]) state=state | (1<<ll);//RIGHT
          else state=state | (3<<ll);//LEFT
        }
      ll+=2;
    }
  state |=(x[0]<<len);
  state |=((y[0]<<5)<<len);
  int m;//读入图中
  scanf("%d",&m);
  int x,y;
  for(int i=0; i<m; ++i)
    {
      scanf("%d%d",&x,&y);
      x--;
      y--;
      gap[x][y]=1;
    }
  return state;
}

int bfs(int s)
{
  priority_queue < Snack > q ;
  int i , state , step  ,rr , cc , k ;
  map < int , bool > hash ;
  q.push ( Snack ( s , 0 ) ) ;
  hash[s] = true ;
  while ( !q.empty () )
    {
      s = q.top().state , step = q.top().step , q.pop() ;
      x[0] = ((s>>(len))&31) ;
      y[0] = (((s>>5)>>((len)))&31) ;
      if ( (s>>(len)) == 0 )
        {
          return step ;
        }
      for ( i = 1 ; i < l ; i++ )
        {
          k = ((s>>((i-1)<<1))&3) ;
          x[i] = dx[k] + x[i - 1] ;
          y[i] = dy[k] + y[i - 1] ;
        }
      for ( i = 0 ; i < 4 ; i++ )
        {
          rr = x[0] - dx[i] ;
          cc = y[0] - dy[i] ;
          if ( rr < 0 || rr >= n || cc < 0 || cc >= m || gap[rr][cc] == 1 )
            {
              continue ;
            }
          for ( k = 0 ; k < l ; k++ )
            {
              if ( rr == x[k] && cc == y[k] )
                {
                  break ;
                }
            }
          if ( k  < l )
            {
              continue ;
            }
          if ( rr == 0 && cc == 0 )
            {
              return step + 1 ;
            }
          state = ((((s&((1<<((l-2)<<1))-1))<<2)|i)|(rr<<(len))|(((cc<<5)<<(len)))) ;
          if ( hash.find ( state ) == hash.end ()  )
            {
              hash[state] = true ;
              q.push ( Snack ( state , step + 1 ) ) ;
            }
        }
    }
  return -1 ;
}
void search(int s)
{
  for(int i=0; i<n; ++i)
    for(int j=0; j<m; ++j)
      {
        mat[i][j]=1<<25;
      }
  int q[410];
  int front=0,rear=1;
  int x,y,step,r,c;//全部压缩到一个整数里
  mat[0][0]=0;
  q[front]=s;
  while(front<rear)
    {
      s=q[front++];
      x=(s&31);
      y=((s>>5)&31);
      step=(s>>10);
      for(int i=0; i<4; i++)
        {
          r=x+dx[i];
          c=y+dy[i];
          if(r>=0&&r<n&&c>=0&&c<m&&gap[r][c]==0&&mat[r][c]>mat[x][y]+1)
            {
              mat[r][c]=mat[x][y]+1;
              q[rear++]=(r|(c<<5)|((step+1)<<10));
            }
        }
    }
}
int main()
{
  int cas=0;
  while(scanf("%d%d%d",&n,&m,&l)!=EOF)
    {
      if(n==0&&m==0&&l==0)  break;
      memset(gap,false,sizeof(gap));
      len=(l-1)<<1;
      int s=inputSnack();
      search(0);
      if(mat[x[0]][y[0]]==1<<25)
        {
          printf("Case %d: -1\n",++cas);
          continue;
        }
      int steps=bfs(s);//s储存着蛇头的信息
      printf("Case %d: %d\n",++cas,steps);
    }
  return 0;
}
