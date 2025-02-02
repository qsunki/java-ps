package graphsearch;

import java.io.*;
import java.util.*;

public class BOJ_1012_유기농배추 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            Farm farm = new Farm(col, row);
            for (int i = 0; i < k; ++i) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                farm.setCabbage(x, y);
            }
            sb.append(farm.getMinWorm()).append('\n');
        }
        System.out.println(sb);
    }

    static class Farm {
        static final int[] dx = {1, -1, 0, 0};
        static final int[] dy = {0, 0, 1, -1};
        int[][] field;

        Farm(int row, int col) {
            field = new int[row][col];
        }

        void setCabbage(int x, int y) {
            field[x][y] = 1;
        }

        int getMinWorm() {
            int cnt = 0;
            boolean[][] visited = new boolean[field.length][field[0].length];
            for (int i = 0; i < field.length; ++i) {
                for (int j = 0; j < field[0].length; ++j) {
                    if (field[i][j] == 1 && !visited[i][j]) {
                        bfs(i, j, visited);
                        ++cnt;
                    }
                }
            }
            return cnt;
        }

        void bfs(int x, int y, boolean[][] visited) {
            Queue<State> queue = new ArrayDeque<>();
            queue.offer(new State(x, y));
            visited[x][y] = true;
            while (!queue.isEmpty()) {
                State cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (isNotValid(nx, ny) || visited[nx][ny] || field[nx][ny] == 0) {
                        continue;
                    }
                    queue.offer(new State(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        boolean isNotValid(int x, int y) {
            return x < 0 || x >= field.length || y < 0 || y >= field[0].length;
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
