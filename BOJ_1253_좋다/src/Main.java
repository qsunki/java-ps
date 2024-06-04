import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int[] nums;
    static int ans;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = nextInt();
        }
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (isGood(i)) {
                ++ans;
            }
        }
        System.out.println(ans);
    }

    static boolean isGood(int target) {
        int left = 0;
        int right = n - 1;

        while (true) {
            if (left == target) {
                ++left;
            }
            if (right == target) {
                --right;
            }
            if (left >= right) {
                break;
            }
            int sum = nums[left] + nums[right];
            if (sum == nums[target]) {
                return true;
            }
            if (sum < nums[target]) {
                ++left;
            } else {
                --right;
            }
        }
        return false;
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
