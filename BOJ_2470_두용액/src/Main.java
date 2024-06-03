import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int[] nums;
    static int ansLeft;
    static int ansRight;
    static int ansSumAbs;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = nextInt();
        }
        Arrays.sort(nums);
        int left = ansLeft = 0;
        int right = ansRight = n - 1;
        ansSumAbs = Math.abs(nums[ansLeft] + nums[ansRight]);
        while (left < right) {
            int sum = nums[left] + nums[right];
            int sumAbs = Math.abs(sum);
            if (ansSumAbs > sumAbs) {
                ansSumAbs = sumAbs;
                ansLeft = left;
                ansRight = right;
            }
            if (sum == 0) {
                break;
            }
            if (sum > 0) {
                --right;
            } else {
                ++left;
            }
        }
        System.out.println(nums[ansLeft] + " " + nums[ansRight]);
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
