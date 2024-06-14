import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int[] nums;
    static int ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        nums = new int[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            nums[i] = nextInt();
        }
        permutation(0, new int[n]);
        System.out.println(ans);
    }

    static void permutation(int cnt, int[] perm) {
        if (cnt == n) {
            countLines(perm);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                perm[cnt] = nums[i];
                permutation(cnt + 1, perm);
                visited[i] = false;
            }
        }
    }

    static void countLines(int[] perm) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int j = i;
            while (sum < 50) {
                sum += perm[j];
                j = (j + 1) % n;
                if (sum == 50) {
                    ++cnt;
                }
            }
        }
        ans = Math.max(ans, cnt / 2);
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
