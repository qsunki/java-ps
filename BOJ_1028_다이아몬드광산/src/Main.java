import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int r, c;
    static char[][] grid;
    static int ans;

    public static void main(String[] args) throws IOException {
        r = nextInt();
        c = nextInt();
        grid = new char[r][];
        for (int i = 0; i < r; i++) {
            grid[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                diamond(i, j, 0);
            }
        }
        System.out.println(ans);
    }

    static void diamond(int x, int y, int step) {
        //다이아몬드 상단 절반 검증
        if (notInRange(x + step, y - step)
                || notInRange(x + step, y + step)
                || grid[x + step][y - step] != '1'
                || grid[x + step][y + step] != '1') {
            return;
        }
        diamond(x, y, step + 1);
        //다이아몬드 하단 절반 검증 & ans 업데이트
        if (ans <= step) {
            x = x + 2 * step;
            for (int i = 0; i < step; i++) {
                if (notInRange(x - i, y - i) ||
                        notInRange(x - i, y + i)
                        || grid[x - i][y - i] != '1'
                        || grid[x - i][y + i] != '1') {
                    return;
                }
            }
            ans = step + 1;
        }
    }

    static boolean notInRange(int x, int y) {
        return x < 0 || x >= r || y < 0 || y >= c;
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
