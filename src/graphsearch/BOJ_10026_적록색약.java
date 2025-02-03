package graphsearch;

import java.io.*;
import java.util.*;

public class BOJ_10026_적록색약 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        Paint paint = new Paint(n);
        for (int i = 0; i < n; i++) {
            paint.setLine(i, br.readLine().toCharArray());
        }
        System.out.println(paint.areaCnt1() + " " + paint.areaCnt2());
    }

    static class Paint {
        static final int[] dx = {1, -1, 0, 0};
        static final int[] dy = {0, 0, 1, -1};

        int n;
        char[][] paint0;

        Paint(int n) {
            this.n = n;
            paint0 = new char[n][];
        }

        void setLine(int i, char[] line) {
            paint0[i] = line;
        }

        int areaCnt1() {
            int cnt = 0;
            boolean[][] visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) {
                        continue;
                    }
                    bfs1(i, j, visited);
                    ++cnt;
                }
            }
            return cnt;
        }

        int areaCnt2() {
            int cnt = 0;
            boolean[][] visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) {
                        continue;
                    }
                    if (paint0[i][j] == 'B') {
                        bfs1(i, j, visited);
                    } else {
                        bfsRG(i, j, visited);
                    }
                    ++cnt;
                }
            }
            return cnt;
        }

        void bfs1(int x, int y, boolean[][] visited) {
            char target = paint0[x][y];
            Queue<State> queue = new ArrayDeque<>();
            visited[x][y] = true;
            queue.offer(new State(x, y));
            while (!queue.isEmpty()) {
                State cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (isNotValid(nx, ny) || visited[nx][ny] || paint0[nx][ny] != target) {
                        continue;
                    }
                    queue.offer(new State(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        void bfsRG(int x, int y, boolean[][] visited) {
            Queue<State> queue = new ArrayDeque<>();
            visited[x][y] = true;
            queue.offer(new State(x, y));
            while (!queue.isEmpty()) {
                State cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (isNotValid(nx, ny) || visited[nx][ny] || paint0[nx][ny] == 'B') {
                        continue;
                    }
                    queue.offer(new State(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        boolean isNotValid(int x, int y) {
            return x < 0 || x >= n || y < 0 || y >= n;
        }

        static class State {
            int x, y;

            State(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
}
