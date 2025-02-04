package graphsearch;

import java.io.*;
import java.util.*;

public class BOJ_7569_토마토 {
    static final int[] dx = {1, -1, 0, 0, 0, 0};
    static final int[] dy = {0, 0, 1, -1, 0, 0};
    static final int[] dz = {0, 0, 0, 0, 1, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int h;
    static int[][][] tomatoes;
    static Queue<State> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        tomatoes = new int[n][m][h];
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    tomatoes[i][j][k] = Integer.parseInt(st.nextToken());
                    if (tomatoes[i][j][k] == 1) {
                        queue.add(new State(i, j, k, 0));
                    }
                }
            }
        }
        int day = bfs();
        boolean allRipen = check();
        if (!allRipen) {
            System.out.println(-1);
            return;
        }
        System.out.println(day);
    }

    static int bfs() {
        int day = 0;
        while (!queue.isEmpty()) {
            State cur = queue.poll();
            day = cur.day;
            for (int i = 0; i < 6; ++i) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nz = cur.z + dz[i];
                if (isNotValid(nx, ny, nz) || tomatoes[nx][ny][nz] != 0) {
                    continue;
                }
                queue.add(new State(nx, ny, nz, cur.day + 1));
                tomatoes[nx][ny][nz] = 1;
            }
        }
        return day;
    }

    static boolean isNotValid(int x, int y, int z) {
        return x < 0 || x >= n || y < 0 || y >= m || z < 0 || z >= h;
    }

    static boolean check() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < h; k++) {
                    if (tomatoes[i][j][k] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static class State {
        int x, y, z, day;

        State(int x, int y, int z, int day) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }
}
