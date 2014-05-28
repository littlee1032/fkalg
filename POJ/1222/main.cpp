#include <string.h>
#include <algorithm>
using namespace std;
#define MOD 2
#define MAX 35


int n,m,x[MAX];
int arr[MAX][MAX];
int mat[MAX][MAX];
int dir[4][2] = {{1,0},{-1,0},{0,1},{0,-1}};


void Initial() {

  int i,j,k,row,col;


  n = m = 5 * 6;
  memset(mat,0,sizeof(mat));
  for (i = 0; i < 5; ++i)
    for (j = 0; j < 6; ++j) {

      row = i * 6 + j;
      mat[row][row] = 1;
      mat[row][m] = arr[i][j];
      for (k = 0; k < 4; ++k) {

        int ii = dir[k][0] + i;
        int jj = dir[k][1] + j;
        if (ii >= 0 && ii < 5
            && jj >=0 && jj < 6)
          mat[row][ii*6+jj] = 1;
      }
    }
}
int Gcd(int x,int y) {

  int r = x % y;
  while (r) {

    x = y,y = r;
    r = x % y;
  }
  return y;
}
int Lcm(int x,int y) {

  return x / Gcd(x,y) * y;
}
void Gauss_AC() {

  int i,j,row,max_r,col;
  int ta,tb,temp,LCM;


  row = col = 0;
  for (; row < n && col < m; ++row,++col) {

    max_r = row;
    for (j = row + 1; j < n; ++j)
      if (mat[j][col]) {

        max_r = j;
        break;
      }
    if (mat[max_r][col] == 0) {

      row--;
      continue;
    }


    if (row != max_r)
      for (j = col; j <= m; ++j)
        swap(mat[row][j],mat[max_r][j]);
    for (i = row + 1; i < n; ++i)   //将每一行的首元素消成0，故要用row行去异或i行
      if (mat[i][col]) for (j = col; j <= m; ++j)
                         mat[i][j] ^= mat[row][j];
  }


  for (i = m - 1; i >= 0; --i) {

    x[i] = mat[i][m];               //mat[i][i]为1，那么mat[i][m]和其他列的和就必须为0故用异或，如果异或为1则x[i]为1
    for (j = i + 1; j < m; ++j)
      x[i] ^= (mat[i][j] && x[j]);
  }
}


int main()
{
  int i,j,k,t,cas = 0;


  scanf("%d",&t);
  while (t--) {

    for (i = 0; i < 5; ++i)
      for (j = 0; j < 6; ++j)
        scanf("%d",&arr[i][j]);


    Initial();
    Gauss_AC();
    printf("PUZZLE #%d\n",++cas);
    for (i = 0; i < m; ++i)
      printf("%d%c",x[i],(i%6)==5?'\n':' ');
  }
}
