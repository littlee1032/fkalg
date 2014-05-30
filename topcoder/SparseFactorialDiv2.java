public class SparseFactorialDiv2 {
    private long getCount(long number, long div) {
        long[] remains = new long[(int)div];
        for (long i = 0; i * i < number; i++) {
            if (remains[(int)((i*i) % div)] == 0)
                remains[(int)((i*i) % div)] = i*i;
        }
        long ret = number / div;
        for (int i = 1; i < div; i++) {
            if (remains[i] > 0) {
                ret += (number - remains[i]) / div;
            }
        }
        return ret;
    }
    
    public long getCount(long lo,long hi, long divisor) {
        return getCount(hi, divisor) - getCount(lo - 1, divisor);
    }
}
