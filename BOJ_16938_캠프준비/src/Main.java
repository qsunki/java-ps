import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, l, r, x;
    static int[] nums;
    static int ans;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        l = nextInt();
        r = nextInt();
        x = nextInt();
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = nextInt();
        }
        dfs(0, Integer.MAX_VALUE, 0, 0);
        System.out.println(ans);
    }

    static void dfs(int idx, int easiest, int hardest, int sum) {
        if (sum > r) {
            return;
        }
        if (idx == n) {
            if (sum < l) {
                return;
            }
            if (hardest - easiest < x) {
                return;
            }
            ++ans;
            return;
        }
        dfs(idx + 1, Math.min(easiest, nums[idx]), Math.max(hardest, nums[idx]), sum + nums[idx]);
        dfs(idx + 1, easiest, hardest, sum);
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
