#include <iostream>
#include <algorithm>
using namespace std ;
#define MAX 1930
#define CNT 4
struct NODE
{
  int width ;
  int length ;
};
struct ANSWER
{
  int width ;
  int length ;
  int area ;
};
NODE rect[CNT] ;
ANSWER ans[MAX] ;
int perm[CNT] ;
int num ;
void Swap( int& a , int& b )
{
  int temp = a ;
  a = b ;
  b = temp ;
}
int Max( int a , int b )
{
  return a > b ? a : b ;
}
int Max( int a , int b , int c )
{
  int k = Max( a , b ) ;
  return k > c ? k : c ;
}
int Max( int a , int b , int c , int d )
{
  int k = Max( a , b , c ) ;
  return k > d ? k : d ;
}
void Func_1( int index )
{
  if ( index == CNT )
    {
                ans[num].width = rect[perm[0]].width + rect[perm[1]].width +
                  rect[perm[2]].width + rect[perm[3]].width ;
                ans[num].length = Max( rect[perm[0]].length , rect[perm[1]].length ,
                                       rect[perm[2]].length , rect[perm[3]].length ) ;
                ans[num].area = ans[num].length * ans[num].width ;
                num++ ;
                return ;
    }
  Func_1( index+1 );
  Swap( rect[perm[index]].length , rect[perm[index]].width ) ;
  Func_1( index+1 ) ;
  Swap( rect[perm[index]].length , rect[perm[index]].width ) ;
}
void Func_2( int index )
{
  if ( index == CNT )
    {
      ans[num].width = Max( rect[perm[0]].width + rect[perm[1]].width +
                            rect[perm[2]].width  , rect[perm[3]].width ) ;
      ans[num].length = Max( rect[perm[0]].length , rect[perm[1]].length ,
                             rect[perm[2]].length ) + rect[perm[3]].length ;
      ans[num].area = ans[num].length * ans[num].width ;
      num++ ;
      return ;
    }
  Func_2( index+1 );
  Swap( rect[perm[index]].length , rect[perm[index]].width ) ;
  Func_2( index+1 ) ;
  Swap( rect[perm[index]].length , rect[perm[index]].width ) ;
}
void Func_3( int index )
{
  if ( index == CNT )
    {
      ans[num].width = Max( rect[perm[1]].width + rect[perm[2]].width ,
                            rect[perm[3]].width ) + rect[perm[0]].width ;
      ans[num].length = Max( rect[perm[0]].length ,
                             rect[perm[3]].length + Max( rect[perm[1]].length , rect[perm[2]].length ) ) ;
      ans[num].area = ans[num].length * ans[num].width ;
      num++ ;
      return ;
    }
  Func_3( index+1 );
  Swap( rect[perm[index]].length , rect[perm[index]].width ) ;
  Func_3( index+1 ) ;
  Swap( rect[perm[index]].length , rect[perm[index]].width ) ;
}
void Func_4( int index )
{
  if ( index == CNT )
    {
      ans[num].width = Max( rect[perm[3]].width , rect[perm[2]].width ) +
        rect[perm[1]].width + rect[perm[0]].width ;
      ans[num].length = Max( rect[perm[0]].length , rect[perm[1]].length ,
                             rect[perm[3]].length + rect[perm[2]].length  ) ;
      ans[num].area = ans[num].length * ans[num].width ;
      num++ ;
      return ;
    }
  Func_4( index+1 );
  Swap( rect[perm[index]].length , rect[perm[index]].width ) ;
  Func_4( index+1 ) ;
  Swap( rect[perm[index]].length , rect[perm[index]].width ) ;
}
void Func_6( int index )
{
  if ( index == CNT )
    {
      ans[num].width = Max( rect[perm[0]].width + rect[perm[3]].width ,
                            rect[perm[1]].width + rect[perm[2]].width ,
                            rect[perm[0]].width + rect[perm[2]].width );
      ans[num].length = Max( rect[perm[0]].length + rect[perm[1]].length ,
                             rect[perm[3]].length + rect[perm[2]].length ,
                             rect[perm[1]].length + rect[perm[3]].length );
      ans[num].area = ans[num].length * ans[num].width ;
      num++ ;
      return ;
    }
  Func_6( index+1 );
  Swap( rect[perm[index]].length , rect[perm[index]].width ) ;
  Func_6( index+1 ) ;
  Swap( rect[perm[index]].length , rect[perm[index]].width ) ;
}
int cmp1( const void* a , const void* b )  // sort based on the area
{
  return (*(ANSWER*)a).area - (*(ANSWER*)b).area ;
}
int cmp2( const void* a , const void* b )    // sort based on the length
{
  return ( *(ANSWER*)a ).length - ( *(ANSWER*)b ).length ;
}
int main()
{
  // freopen( "e:\\in.txt" , "r" , stdin ) ;
  int i , k = 0 ;
  for ( i = 0 ; i < 4 ; i++ )
    {
      cin >> rect[i].width >> rect[i].length ;
      perm[i] = i ;
    }
  num = 1 ;
   do
     {
       // k++ ;
       Func_1( 0 ) ;
       Func_2( 0 ) ;
       Func_3( 0 ) ;
       Func_4( 0 ) ;
       Func_6( 0 ) ;
       /*  for ( i = 0 ; i < 4 ; i++ )
             {
             cout << perm[i] << "  " ;
                     }
                     cout << endl ;*/
     }while ( next_permutation( perm , perm+4 ) ) ;
   // cout << k << endl ;
   qsort( &ans[1] , num-1 , sizeof( ans[1] ) , cmp1 ) ;
   int sum = 0 ;
   int temp = ans[1].area ;
   for ( i = 1 ; i < num ; i++ )
     {
       if ( ans[i].area == temp )
         {
           if ( ans[i].length > ans[i].width )
             {
               Swap( ans[i].length , ans[i].width ) ;
             }
           sum++ ;
           //    cout << ans[i].length << " " << ans[i].width << endl ;
         }
       else
         break ;
     }
   qsort( &ans[1] , sum , sizeof( ans[1] ) , cmp2 ) ;
   cout << temp <<  endl ;
   for ( i = 1 ; i <= sum ; i++ )
     {
       if ( i == 1 || ans[i].length != ans[i-1].length || ans[i].width != ans[i-1].width )
         {
           cout << ans[i].length << " " << ans[i].width << endl ;
         }
     }
   system( "pause" ) ;
   return 0 ;
}
