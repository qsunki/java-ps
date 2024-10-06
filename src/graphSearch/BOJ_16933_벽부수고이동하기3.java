package graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 이동거리 1부터 시작
 * 현재 이동거리가 홀수일 때 벽 부수고 이동가능(낮)
 * 상태: 위치(x, y), 이동거리, 부술 수 있는 벽 수
 */
public class BOJ_16933_벽부수고이동하기3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m, k;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static char[][] grid;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        k = nextInt();
        grid = new char[n][];
        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
        }
        int ans = bfs();
        System.out.println(ans);
    }

    static int bfs() {
        if (n == 1 && m == 1) {
            return 1;
        }
        int[][][] minVisited = new int[n][m][2]; //거리, 부수기 가능한 횟수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                minVisited[i][j][0] = Integer.MAX_VALUE;
            }
        }
        Queue<Node> q = new ArrayDeque<>();
        minVisited[0][0][0] = 1;
        minVisited[0][0][1] = k;
        q.add(new Node(0, 0, 1, k));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nextMovedCnt = cur.movedCnt + 1;
                if (isNotValid(nx, ny)) {
                    continue;
                }
                if (minVisited[nx][ny][0] <= nextMovedCnt && minVisited[nx][ny][1] >= cur.canBreak) {
                    continue;
                }
                if (grid[nx][ny] == '0') {
                    if (nx == n - 1 && ny == m - 1) {
                        return nextMovedCnt;
                    }
                    minVisited[nx][ny][0] = nextMovedCnt;
                    minVisited[nx][ny][1] = cur.canBreak;
                    q.add(new Node(nx, ny, nextMovedCnt, cur.canBreak));
                    continue;
                }
                if (grid[nx][ny] == '1' && cur.canBreak > 0) {
                    if (nextMovedCnt % 2 == 0) {
                        minVisited[nx][ny][0] = nextMovedCnt;
                        minVisited[nx][ny][1] = cur.canBreak - 1;
                        q.add(new Node(nx, ny, nextMovedCnt, cur.canBreak - 1));
                    } else {
                        q.add(new Node(cur.x, cur.y, nextMovedCnt, cur.canBreak));
                    }
                }
            }
        }
        return -1;
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
        int x, y;
        int movedCnt;
        int canBreak;

        Node(int x, int y, int movedCnt, int canBreak) {
            this.x = x;
            this.y = y;
            this.movedCnt = movedCnt;
            this.canBreak = canBreak;
        }
    }
}
