package graphsearch;

import java.io.*;
import java.util.*;

public class BOJ_2178_미로탐색 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] maze0 = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                maze0[i][j] = chars[j] - '0';
            }
        }
        Maze maze = new Maze(maze0);
        System.out.println(maze.calculateMinCostToExit());
    }

    static class Maze {
        static final int[] dx = {1, -1, 0, 0};
        static final int[] dy = {0, 0, -1, 1};
        int[][] maze0;

        Maze(int[][] maze0) {
            this.maze0 = maze0;
        }

        int calculateMinCostToExit() {
            boolean[][] visited = new boolean[maze0.length][maze0[0].length];
            Queue<State> queue = new ArrayDeque<>();
            queue.offer(new State(0, 0, 1));
            visited[0][0] = true;
            while (!queue.isEmpty()) {
                State cur = queue.poll();
                if (cur.x == maze0.length - 1 && cur.y == maze0[0].length - 1) {
                    return cur.cnt;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (isNotValid(nx, ny) || visited[nx][ny] || maze0[nx][ny] == 0) {
                        continue;
                    }
                    queue.offer(new State(nx, ny, cur.cnt + 1));
                    visited[nx][ny] = true;
                }
            }
            return -1;
        }

        boolean isNotValid(int x, int y) {
            return x < 0 || x >= maze0.length || y < 0 || y >= maze0[0].length;
        }

        static class State {
            int x, y, cnt;

            State(int x, int y, int cnt) {
                this.x = x;
                this.y = y;
                this.cnt = cnt;
            }
        }
    }
}
