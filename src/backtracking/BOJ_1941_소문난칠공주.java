package backtracking;

import java.io.*;
import java.util.*;

public class BOJ_1941_소문난칠공주 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static char[][] grid;
    static boolean[][] visited = new boolean[5][5];
    static Queue<Point> queue = new ArrayDeque<>();
    static int ans = 0;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        grid = new char[5][];
        for (int i = 0; i < 5; ++i) {
            grid[i] = br.readLine().toCharArray();
        }
        boolean[][] selected = new boolean[5][5];
        backtrack(0, 0, 0, 0, selected);
        System.out.println(ans);
    }

    static void backtrack(int x, int y, int totalCnt, int yCnt, boolean[][] selected) {
        if (yCnt >= 4) {
            return;
        }
        if (totalCnt == 7) {
            if (!isConnected(x, y - 1, selected)) {
                return;
            }
            ++ans;
            return;
        }
        if (y == 5) {
            y = 0;
            ++x;
        }
        if (x == 5) {
            return;
        }

        backtrack(x, y + 1, totalCnt, yCnt, selected);

        yCnt = grid[x][y] == 'Y' ? yCnt + 1 : yCnt;
        selected[x][y] = true;
        backtrack(x, y + 1, totalCnt + 1, yCnt, selected);
        selected[x][y] = false;
    }

    static boolean isConnected(int sx, int sy, boolean[][] selected) {
        int selectedCnt = 1;
        queue.offer(new Point(sx, sy));
        visited[sx][sy] = true;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || visited[nx][ny]) {
                    continue;
                }
                if (!selected[nx][ny]) {
                    continue;
                }
                ++selectedCnt;
                queue.offer(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                visited[i][j] = false;
            }
        }
        return selectedCnt == 7;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
