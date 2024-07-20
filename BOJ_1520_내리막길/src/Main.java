import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m;
    static int[][] grid;
    static int[][] dp;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        grid = new int[n][m];
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = nextInt();
            }
        }
        System.out.println(dp(n - 1, m - 1));
    }

    static int dp(int x, int y) {
        if (x == 0 && y == 0) {
            return 1;
        }
        if (dp[x][y] == -1) {
            return 0;
        }
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isNotValid(nx, ny) || grid[x][y] >= grid[nx][ny]) {
                continue;
            }
            dp[x][y] += dp(nx, ny);
        }
        if (dp[x][y] == 0) {
            dp[x][y] = -1;
            return 0;
        }
        return dp[x][y];
    }

    static boolean isNotValid(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
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
