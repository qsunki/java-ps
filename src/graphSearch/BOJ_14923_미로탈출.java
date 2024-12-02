package graphSearch;

import java.io.*;
import java.util.*;

public class BOJ_14923_미로탈출 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m;
    static int hx, hy, ex, ey;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        int[][] board = new int[n][m];
        hx = nextInt() - 1;
        hy = nextInt() - 1;
        ex = nextInt() - 1;
        ey = nextInt() - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = nextInt();
            }
        }
        int ans = bfs(board);
        System.out.println(ans);
    }

    static int bfs(int[][] board) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int[][][] visitedWithDistance = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visitedWithDistance[i][j][0] = Integer.MAX_VALUE;
                visitedWithDistance[i][j][1] = Integer.MAX_VALUE;
            }
        }
        Queue<Node> queue = new ArrayDeque<>();
        visitedWithDistance[hx][hy][0] = 0;
        queue.offer(new Node(hx, hy, 0, true));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int nDistance = cur.distance + 1;
            for (int i = 0; i < 4; ++i) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isNotValid(nx, ny) || nDistance >= visitedWithDistance[nx][ny][0]) {
                    continue;
                }
                if (board[nx][ny] == 1) {
                    if (cur.canBreakWall) {
                        if (isExit(nx, ny)) {
                            return nDistance;
                        }
                        queue.offer(new Node(nx, ny, nDistance, false));
                        visitedWithDistance[nx][ny][1] = nDistance;
                    }
                    continue;
                }
                if (!cur.canBreakWall) {
                    if (nDistance >= visitedWithDistance[nx][ny][1]) {
                        continue;
                    }
                    if (isExit(nx, ny)) {
                        return nDistance;
                    }
                    queue.offer(new Node(nx, ny, nDistance, false));
                    visitedWithDistance[nx][ny][1] = nDistance;
                    continue;
                }
                if (isExit(nx, ny)) {
                    return nDistance;
                }
                queue.offer(new Node(nx, ny, nDistance, true));
                visitedWithDistance[nx][ny][0] = nDistance;
            }
        }
        return -1;
    }

    static boolean isExit(int x, int y) {
        return x == ex && y == ey;
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

    static class Node {
        int x;
        int y;
        int distance;
        boolean canBreakWall;

        Node(int x, int y, int distance, boolean canBreakWall) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.canBreakWall = canBreakWall;
        }
    }
}
