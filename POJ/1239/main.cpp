#include <iostream>
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int maxn = 90;

char s[maxn];
int len, dpforward[maxn], dpbackward[maxn];


bool cmp(int s1, int t1, int s2, int t2);

int main()
{
  while(true)
    {
      scanf("%s", s);
      len = strlen(s);
      if(len == 1 && s[0] == '0')
        break;
      memset(dpforward, 0, sizeof(dpforward));
      memset(dpbackward, 0, sizeof(dpbackward));
      for(int i = 1; i < len; i++)
        {
          for(int j = 0; j < i; j++)
            {
              if(cmp(dpforward[j], j, j+1, i))
                dpforward[i] = max(dpforward[i], j+1);
            }
        }


      int tlen = dpforward[len-1];
      dpbackward[tlen]  = len - 1;
      for(int i = tlen - 1; s[i] == '0'; i--)
        dpbackward[i] = len - 1;
      for(int i = tlen - 1; i >= 0; i--)
        {
          for(int j = i; j <= tlen - 1; j++)
            {
              if(cmp(i, j, j + 1, dpbackward[j+1]))
                dpbackward[i] = max(dpbackward[i], j);
            }
        }


      int pos = dpbackward[0], i = 0;
      while(i < len)
        {
          while(i <= pos && i < len)
            printf("%c", s[i++]);
          if(i < len)
            printf(",");
          pos = dpbackward[pos + 1];
        }
      printf("\n");
    }

  return 0;
}

bool cmp(int s1, int t1, int s2, int t2)
{
  while(s[s1] == '0' && s1 <= t1)
    s1++;
  while(s[s2] == '0' && s2 <= t2)
    s2++;
  if(s1 > t1 && s2 > t2)
    return true;

  if((t1 - s1) > (t2 - s2))
    return false;
  else if((t1 - s1) < (t2 - s2))
    return true;
  else
    {
      for(int i = s1, j = s2; i <= t1; i++, j++)
        {
          if(s[i] > s[j])
            return false;
          else if(s[i] < s[j])
            return true;
        }
    }
  return false;
}
