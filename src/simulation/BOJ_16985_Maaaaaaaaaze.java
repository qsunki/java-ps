package simulation;

import java.io.*;
import java.util.*;

public class BOJ_16985_Maaaaaaaaaze {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int ans = Integer.MAX_VALUE;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        int[][][] input = new int[5][5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    input[i][j][k] = nextInt();
                }
            }
        }
        permutation(0, input, new int[5][][], new boolean[5]);
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }

    }

    static void permutation(int cnt, int[][][] input, int[][][] perm, boolean[] visited) {
        if (cnt == 5) {
            for (Angle angle0 : Angle.values()) {
                for (Angle angle1 : Angle.values()) {
                    for (Angle angle2 : Angle.values()) {
                        for (Angle angle3 : Angle.values()) {
                            for (Angle angle4 : Angle.values()) {
                                Maze maze = new Maze();
                                maze.setFloor(0, perm[0], angle0);
                                maze.setFloor(1, perm[1], angle1);
                                maze.setFloor(2, perm[2], angle2);
                                maze.setFloor(3, perm[3], angle3);
                                maze.setFloor(4, perm[4], angle4);
                                int moveCnt = bfs(maze);
                                if (moveCnt != -1) {
                                    ans = Math.min(ans, moveCnt);
                                }
                            }
                        }
                    }
                }
            }
            return;
        }
        for (int i = 0; i < 5; i++) {
            if (!visited[i]) {
                perm[cnt] = input[i];
                visited[i] = true;
                permutation(cnt + 1, input, perm, visited);
                visited[i] = false;
            }
        }
    }

    static int bfs(Maze maze) {
        if (maze.get(0, 0, 0) == 0) {
            return -1;
        }
        boolean[][][] visited = new boolean[5][5][5];
        Queue<Location> q = new ArrayDeque<>();
        visited[0][0][0] = true;
        q.offer(new Location(0, 0, 0, 0));
        while (!q.isEmpty()) {
            Location loc = q.poll();
            int nCnt = loc.cnt + 1;
            if (ans != -1 && nCnt >= ans) {
                return -1;
            }
            for (int i = 0; i < 6; i++) {
                int nx = loc.x + dx[i];
                int ny = loc.y + dy[i];
                int nz = loc.z + dz[i];
                if (isNotValid(nx, ny, nz) || visited[nx][ny][nz]) {
                    continue;
                }
                if (maze.get(nx, ny, nz) == 0) {
                    continue;
                }
                if (nx == 4 && ny == 4 && nz == 4) {
                    return nCnt;
                }
                visited[nx][ny][nz] = true;
                q.offer(new Location(nx, ny, nz, nCnt));
            }
        }
        return -1;
    }

    static boolean isNotValid(int x, int y, int z) {
        return x < 0 || x >= 5 || y < 0 || y >= 5 || z < 0 || z >= 5;
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

    enum Angle {
        ANGLE_0, ANGLE_90, ANGLE_180, ANGLE_270;
    }

    static class Location {
        int x;
        int y;
        int z;
        int cnt;

        Location(int x, int y, int z, int cnt) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.cnt = cnt;
        }
    }

    static class Maze {
        Floor[] floors;

        Maze() {
            floors = new Floor[5];
        }

        void setFloor(int idx, int[][] grid, Angle angle) {
            floors[idx] = new Floor(angle, grid);
        }

        int get(int x, int y, int z) {
            return floors[x].get(y, z);
        }
    }

    static class Floor {
        Angle angle;
        int[][] grid;

        Floor(Angle angle, int[][] grid) {
            this.angle = angle;
            this.grid = grid;
        }

        int get(int x, int y) {
            switch (angle) {
                case ANGLE_90:
                    return grid[5 - y - 1][x];
                case ANGLE_180:
                    return grid[5 - x - 1][5 - y - 1];
                case ANGLE_270:
                    return grid[y][5 - x - 1];
                default:
                    return grid[x][y];
            }
        }
    }
}
