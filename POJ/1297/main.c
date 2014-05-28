#include <iostream>
#define minv(a, b) ((a) <= (b) ? (a) : (b))
#define MAX_L 100
#define MAX_N 100000
using namespace std;
double minVal[2][MAX_N + 2];
int listNum, goodsNum;
int list[MAX_L + 2];
struct good
{
  int id;
  double price;
}goods[MAX_N + 1];
int minTotal;
void init(int i)
{
  minTotal = INT_MAX;
  for(int j = 0; j <= goodsNum; j++)
    minVal[i][j] = INT_MAX;
}
void dpSolve()
{
  int i, j;
  //first good in the list
  init(0);
  for(j = 1; j <= goodsNum; j++)
    {
      if(goods[j].id == list[1])
        minVal[0][j] = minv(minVal[0][j - 1], goods[j].price);
      else
        minVal[0][j] = minVal[0][j - 1];
    }
  int lastPos = 0, curPos;
  for(i = 2; i <= listNum; i++)
    {
      curPos = 1 - lastPos;
      init(curPos);
      for(j = i; j <= goodsNum; j++)
        {
          minVal[curPos][j] = INT_MAX;
          if(goods[j].id == list[i])
            minVal[curPos][j] = minv(minVal[curPos][j - 1], minVal[lastPos][j - 1] + goods[j].price);
          //on not buy
          else
            minVal[curPos][j] = minVal[curPos][j - 1];
        }
      lastPos = curPos;
    }
}
int main()
{
  int i;
  while(scanf("%d%d", &listNum, &goodsNum) && (listNum + goodsNum) != 0)
    {
      for(i = 1; i <= listNum; i++)
        scanf("%d", &list[i]);
      for(i = 1; i <= goodsNum; i++)
        scanf("%d%lf", &goods[i].id, &goods[i].price);
      dpSolve();
      if(minVal[(listNum - 1) % 2][goodsNum] == (double)INT_MAX)
        printf("Impossible\n");
      else
        printf("%.2f\n", minVal[(listNum - 1) % 2][goodsNum]);
    }
  return 0;
}
