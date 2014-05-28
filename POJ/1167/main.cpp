#include <stdio.h>
// bus x means the bus arrived at x minute
// bus count
int n;
// bus info
int time[60];
// current used bus
int now[60];
// save which bus is first of a bus route
// if bus x is, first[x]++
int first[60];
// the begin bus's time
int begin;
// the last bus's time
int last;
// the min of bus route
int min;
// current used bus route
int temp;
int counter;
// if choose the route, is OK?
bool isOK(int first, int inv) {
  for (int i = first; i < 60; i += inv) {
    if (time[i] - now[i] <= 0)
      return false;
  }
  return true;
}
// one_of_p_or_n is +-1
void mark(int first, int inv, int one_of_p_or_n) {
  for (int i = first; i < 60; i += inv) {
    now[i] += one_of_p_or_n;
  }
}
// try every bus
// x is the current bus
void find(int x) {
  counter++;
  if (x == 60) {
    // search end
    if (temp < min) {
      min = temp;
    }
    return;
  }
  if (temp > min) {
    // cut
    return;
  }
  if (time[x] - now[x] <= 0) {
    // x minute, no bus
    int i;
    for (i = x + 1; i < 60; i++) {
      // try valid x
      if (time[i] - now[i] > 0) {
        break;
      }
    }
    find(i);
    return;
  }
  // search the first bus of the bus route that x in
  // only the front half is possible
  for (int i = begin; 2 * i < x; i++) {
    if (first[i] > 0) {
      if (isOK(x, x - i)) {
        // if interval{x-i} is valid, try it
        first[i]--;
        mark(x, x - i, 1);
        find(x);
        mark(x, x - i, -1);
        first[i]++;
      }
    }
  }
  // if x is first
  // |    x   |        last
  // |--------|--------|
  // so 2*x < last
  if ((x << 1) < last) {
    first[x]++;
    now[x]++;
    temp++;
    find(x);
    first[x]--;
    now[x]--;
    temp--;
  }
}
int main() {
  scanf("%d", &n);
  if (!n) {
    printf("0");
    return 0;
  }
  scanf("%d", &temp);
  time[temp]++;
  begin = temp;
  for (int i = 1; i < n; i++) {
    scanf("%d", &temp);
    time[temp]++;
  }
  last = temp;
  min = n / 2;
  temp = 0;
  find(begin);
  printf("%d\n", min);
  printf("%d\n", counter);
  return 0;
}
