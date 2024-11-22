package prefixSum;

import java.io.*;
import java.util.*;

public class BOJ_10986_나머지합 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();
        NumList numList = new NumList(n);
        for (int i = 0; i < n; i++) {
            numList.set(i, nextInt(), m);
        }
        System.out.println(numList.getAns());
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static long comb2(long n) {
        if (n < 2) {
            return 0;
        }
        return n * (n - 1) / 2;
    }

    static class NumList {
        int[] nums;
        long[] sums;
        Map<Integer, Integer> remainCnt;

        public NumList(int n) {
            nums = new int[n];
            sums = new long[n];
            remainCnt = new HashMap<>();
        }

        void set(int idx, int value, int mod) {
            nums[idx] = value;
            if (idx == 0) {
                sums[idx] = value;
            } else {
                sums[idx] = sums[idx - 1] + value;
            }
            int remain = (int) (sums[idx] % mod);
            int cnt = remainCnt.getOrDefault(remain, 0);
            remainCnt.put(remain, cnt + 1);
        }

        long getAns() {
            long ans = remainCnt.getOrDefault(0, 0);
            for (Integer value : remainCnt.values()) {
                ans += comb2(value);
            }
            return ans;
        }
    }
}
