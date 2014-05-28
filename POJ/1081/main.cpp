#include <stdio.h>
#include <inttypes.h>

uint64_t map[64];
int N;
int bit_cnt[256];

__inline int calc_cnt(uint64_t val)
{
    return bit_cnt[((char *)&val)[0]] + 
        bit_cnt[((char *)&val)[1]] + 
        bit_cnt[((char *)&val)[2]] + 
        bit_cnt[((char *)&val)[3]] + 
        bit_cnt[((char *)&val)[4]] + 
        bit_cnt[((char *)&val)[5]] + 
        bit_cnt[((char *)&val)[6]] + 
        bit_cnt[((char *)&val)[7]];
}

__inline int min(int a, int b)
{
    return a < b ? a : b;
}

__inline int max(int a, int b)
{
    return a < b ? b : a;
}

int main()
{
    int i, j, k, l, r, arr[64], min_val;
    uint64_t mask;

    for (i = 0; i < 256; i++) {
        k = 0;
        for (j = i; j; j &= j - 1)
            k++;
        bit_cnt[i] = k;
    }

    while (scanf("%d%d", &j, &k) != EOF) {
        while (k--) {
            scanf("%d", &i);
            map[j] |= (uint64_t)1 << i;
        }
        if (j > N)
            N = j;
    }
    for (i = 1; i <= N; i++)
        map[i] |= (uint64_t)1 << i;

    min_val = N;
    for (i = 1; i <= N/2; i++)
        arr[i] = i;
    while (1) {
        mask = 0;
        for (i = 1; i <= N/2; i++)
            mask |= (uint64_t)1 << arr[i];
        l = r = N;
        for (i = 1; i <= N; i++) {
            if (mask & ((uint64_t)1 << i)) 
                l = min(calc_cnt(map[i] & mask), l);
            else
                r = min(calc_cnt(map[i] & ~mask), r);
        }
        i = max(N/2 - l, N - N/2 - r);
        if (i < min_val)
            min_val = i;
        for (i = N/2; i >= 1 && arr[i] == N + i - N/2; i--);
        if (!i)
            break;
        arr[i]++;
        for (j = 1; j + i <= N/2; j++)
            arr[j + i] = arr[i] + j;
    }
    printf("%d\n", min_val);
    
    return 0;
}
