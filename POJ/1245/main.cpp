 #include<stdio.h>
 #include<string.h>
 #include<math.h>
 #include<stdlib.h>
 
 struct rank {
     char name[12];
     int sol, tot, g, p[7], ind;
 };
 rank ranks[25];
 
 int comp(const void *c, const void *d)
 {
     rank *a = (rank *)c;
     rank *b = (rank *)d;
     if(a->sol > b->sol) return -1;
     else if(a->sol < b->sol) return 1;
     else {
         if(a->tot < b->tot) return -1;
         else if(a->tot > b->tot) return 1;
         else {
             if(a->g < b->g) return -1;
             else if(a->g > b->g) return 1;
             else {
                 return strcmp(a->name, b->name);
             }
         }
     }
 
 }
 
 int main()
 {
 //    freopen("data.in", "r", stdin);
 //    freopen("data.out", "w", stdout);
     int n = 0, cnt = 1;
     while(scanf("%d", &n)) {
         if(!n) break;
         for(int i = 0; i < n; i++) {
             double t = 0;
             ranks[i].sol = 0, ranks[i].tot = 0, ranks[i].g = 0;
             scanf("%s", &ranks[i].name);
             for(int j = 0; j < 7; j++) {
                 scanf("%d", &ranks[i].p[j]);
                 if(ranks[i].p[j]) {
                     ranks[i].sol++;
                     ranks[i].tot += ranks[i].p[j];
                     t += log((double)ranks[i].p[j]);
                 }
             }
             if(ranks[i].sol > 0) {
                 ranks[i].g = exp(t/ranks[i].sol) + 0.5;
             }
         }
         qsort(ranks, n, sizeof(rank), comp);
         printf("CONTEST %d\n", cnt);
         cnt++;
         for(int i = 0; i < n; i++) {
             if(i < 9) {
                 printf("0");
             }
             if(i > 0 && ranks[i].sol == ranks[i-1].sol && ranks[i].tot == ranks[i-1].tot && ranks[i].g == ranks[i-1].g) {
                 ranks[i].ind = ranks[i-1].ind;
             }
             else {
                 ranks[i].ind = i + 1;
             }
             printf("%d %-10s %d %4d %3d %3d %3d %3d %3d %3d %3d %3d\n", ranks[i].ind, ranks[i].name, ranks[i].sol, ranks[i].tot, ranks[i].g,
                    ranks[i].p[0], ranks[i].p[1], ranks[i].p[2], ranks[i].p[3], ranks[i].p[4], ranks[i].p[5], ranks[i].p[6]);
 
         }
 
     }
     return 0;
 }
