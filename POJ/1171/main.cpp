#include<iostream>
#include <algorithm>
using namespace std;

int n,m;

int s[26]={2,5,4,4,1,6,5,5,1,7,6,3,5,2,3,5,7,2,1,2,4,6,6,7,5,7};
int t[26],tp[26];
struct node
{
  char a[8];
  int s;
}d[40010];
char str[10],a[10];
int cmp(node A,node B)
{
  return A.s<B.s;
}

int bfind(int l,int r,int key)
{
  if (d[l].s>key) return l-1;
  else if (d[r].s<=key) return r;

  int mid;
  while (l<=r)
    {
      mid=(l+r)>>1;
      if (d[mid].s>key) r=mid-1;
      else l=mid+1;
    }
  return r;
}

int check(char f[],char e[])
{
  for (int i=0;i<26;i++) tp[i]=t[i];
  for (int i=0;f[i];i++) if (--tp[f[i]-'a']<0) return 0;
  for (int i=0;e[i];i++) if (--tp[e[i]-'a']<0) return 0;
  return 1;
}
int main()
{
  while (scanf("%s",str)!=EOF)
    {
      memset(t,0,sizeof(t));
      int Max=0;
      for (int i=0;str[i];i++) { t[str[i]-'a']++; Max+=s[str[i]-'a'];}
      int i,ans=0;
      m=0;
      while (scanf("%s",a) && a[0]!='.')
        {
          for (i=0;i<26;i++) tp[i]=t[i];
          int sum=0;
          for (i=0;a[i];i++)
            {
              if (--tp[a[i]-'a']<0) break;
              sum+=s[a[i]-'a'];
            }
          if (a[i]) continue;
          ans=max(ans,sum);
          strcpy(d[m].a,a);
          d[m++].s=sum;
        }


      sort(d,d+m,cmp);
      for (int i=0;i<m;i++)
        {
          int pos=bfind(0,m-1,Max-d[i].s);
          for (int j=pos;j>=0;j--)
            if (d[j].s+d[i].s>ans ){
              if (check(d[j].a,d[i].a)) ans=d[j].s+d[i].s;
            }
            else break;
        }
      printf("%d\n",ans);

    }
  return 0;
}
