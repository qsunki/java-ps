package graphsearch;

import java.io.*;
import java.util.*;

public class BOJ_7576_토마토 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        Storage storage = new Storage(n, m);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                storage.set(i, j, Integer.parseInt(st.nextToken()));
            }
        }
        System.out.println(storage.calculateMinRipeDate());
    }

    static class Storage {
        static final int[] dx = {1, -1, 0, 0};
        static final int[] dy = {0, 0, -1, 1};
        int height, width;
        int[][] storage0;
        Queue<State> ripenTomatoes;

        Storage(int height, int width) {
            this.height = height;
            this.width = width;
            storage0 = new int[height][width];
            ripenTomatoes = new ArrayDeque<>();
        }

        void set(int x, int y, int tomato) {
            storage0[x][y] = tomato;
            if (tomato == 1) {
                ripenTomatoes.add(new State(x, y, 0));
            }
        }

        int calculateMinRipeDate() {
            int day = 0;
            while (!ripenTomatoes.isEmpty()) {
                State state = ripenTomatoes.poll();
                day = state.day;
                for (int i = 0; i < 4; ++i) {
                    int nx = state.x + dx[i];
                    int ny = state.y + dy[i];
                    if (isNotValid(nx, ny) || storage0[nx][ny] != 0) {
                        continue;
                    }
                    ripenTomatoes.add(new State(nx, ny, state.day + 1));
                    storage0[nx][ny] = 1;
                }
            }
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (storage0[i][j] == 0) {
                        return -1;
                    }
                }
            }
            return day;
        }

        boolean isNotValid(int x, int y) {
            return x < 0 || x >= height || y < 0 || y >= width;
        }

        static class State {
            int x, y, day;

            State(int x, int y, int day) {
                this.x = x;
                this.y = y;
                this.day = day;
            }
        }


    }
}
