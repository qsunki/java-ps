import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int[] nums;
    static int ansFirst;
    static int ansSecond;
    static int ansThird;
    static long ansSumAbs = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = nextInt();
        }
        Arrays.sort(nums);
        outer:
        for (int i = 0; i < n - 2; i++) {
            int first = i;
            int second = i + 1;
            int third = n - 1;
            while (second < third) {
                long sum = ((long) nums[first]) + nums[second] + nums[third];
                long sumAbs = Math.abs(sum);
                if (sumAbs < ansSumAbs) {
                    ansFirst = first;
                    ansSecond = second;
                    ansThird = third;
                    ansSumAbs = sumAbs;
                }
                if (sum == 0) {
                    break outer;
                }
                if (sum > 0) {
                    --third;
                } else {
                    ++second;
                }
            }
        }
        System.out.println(nums[ansFirst] + " " + nums[ansSecond] + " " + nums[ansThird]);
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
}
