#include<iostream>

using namespace std;
const int INF=1000000000;
int king_move[8][2]={{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
int knight_move[8][2]={{-2,-1},{-2,1},{-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,1}};
int king_map[64][64];
int knight_map[64][64];

//判定位置i是否合法0<=i<=63
bool ok(int x, int y)
{
  if(x>=0 && x<8 && y>=0 && y<8) return true;
  else return false;
}

//根据数字表示的位置获得坐标
void getXY(int p, int &x, int &y)
{
  x=p%8;
  y=p/8;
}

//根据坐标获得数字表示的位置
int getPosition(int x, int y)
{
  return x+y*8;
}

//初始化 king_map和 knight_map
void init()
{
  for(int i=0; i<64; ++i)
    {
      for(int j=0; j<64; ++j)
        {
          king_map[i][j]=INF;
          knight_map[i][j]=INF;
        }

      king_map[i][i]=0;
      knight_map[i][i]=0;
      int x, tx, y, ty;
      int next;
      getXY(i, x, y);
      for(int j=0; j<8; ++j)
        {
          tx = x+king_move[j][0];
          ty = y+king_move[j][1];
          if(ok(tx,ty))
            {
              next=getPosition(tx,ty);
              king_map[i][next]=1;
            }

          tx = x+knight_move[j][0];
          ty = y+knight_move[j][1];
          if(ok(tx, ty))
            {
              next=getPosition(tx,ty);
              knight_map[i][next]=1;
            }
        }
    }
}

//利用floyd算法计算任意两点间的最短距离
void floyd()
{
  for(int k=0; k<64; ++k)
    for(int i=0; i<64; ++i)
      for(int j=0; j<64; ++j)
        {
          if(king_map[i][k] + king_map[k][j] < king_map[i][j])
            king_map[i][j] = king_map[i][k] + king_map[k][j];
          if(knight_map[i][k] + knight_map[k][j] < knight_map[i][j])
            knight_map[i][j] = knight_map[i][k] + knight_map[k][j];
        }

}

int main()
{
  string s;
  int p_num, s_size, min_move, sum;
  int position[64];

  init();
  floyd();

  cin>>s;
  s_size = s.size();
  p_num = 0;
  //将字符串表示的位置转换为 0-63表示的位置
  for(int i=0; i<s_size; i += 2)
    {
      position[p_num++] = s[i]-'A' + (s[i+1]-'1')*8;
    }


  min_move = INF;
  for(int dst=0; dst<64; ++dst) //dst代表最终的位置
    for(int m=0; m<64; ++m)  //m代表国王与骑士相遇的位置
      for(int k=1; k<p_num; ++k)  //k代表与国王相遇的骑士
        {
          sum=0;
          //计算所有骑士到dst需要的移动步数
          for(int i=1; i<p_num; ++i) sum += knight_map[position[i]][dst];
          //计算国王到m的步数
          sum += king_map[position[0]][m];
          //计算骑士k在m处遇到国王后再到dst的步数
          sum +=  knight_map[position[k]][m] + knight_map[m][dst];
          //减去骑士k多算的一次值
          sum -=  knight_map[position[k]][dst];

          if(sum < min_move) min_move=sum;
        }

  //输出结果
  cout<<min_move<<endl;

  return 0;
}
