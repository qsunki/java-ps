package graphsearch;

import java.io.*;
import java.util.*;

public class BOJ_4179_불 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        char[][] maze0 = new char[r][];
        for (int i = 0; i < r; i++) {
            maze0[i] = br.readLine().toCharArray();
        }
        Maze maze = new Maze(maze0);
        int ans = maze.exitTime();
        if (ans == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(ans);
        }

    }

    static class Maze {
        static final int[] dx = {1, -1, 0, 0};
        static final int[] dy = {0, 0, -1, 1};
        char[][] maze0;
        Queue<State> fires = new ArrayDeque<>();
        Queue<State> players = new ArrayDeque<>();
        Queue<State> next = new ArrayDeque<>();

        Maze(char[][] maze0) {
            this.maze0 = maze0;
            for (int i = 0; i < maze0.length; i++) {
                for (int j = 0; j < maze0[0].length; j++) {
                    if (maze0[i][j] == 'J') {
                        players.offer(new State(i, j));
                    } else if (maze0[i][j] == 'F') {
                        fires.offer(new State(i, j));
                    }
                }
            }
        }

        int exitTime() {
            Queue<State> tmp;
            int time = 1;
            while (true) {
                while (!fires.isEmpty()) {
                    State cur = fires.poll();
                    for (int i = 0; i < 4; i++) {
                        int nx = cur.x + dx[i];
                        int ny = cur.y + dy[i];
                        if (isNotValid(nx, ny) || maze0[nx][ny] == '#' || maze0[nx][ny] == 'F') {
                            continue;
                        }
                        next.offer(new State(nx, ny));
                        maze0[nx][ny] = 'F';
                    }
                }
                tmp = fires;
                fires = next;
                next = tmp;
                while (!players.isEmpty()) {
                    State cur = players.poll();
                    if (isEdge(cur.x, cur.y)) {
                        return time;
                    }
                    for (int i = 0; i < 4; i++) {
                        int nx = cur.x + dx[i];
                        int ny = cur.y + dy[i];
                        if (isNotValid(nx, ny) || maze0[nx][ny] != '.') {
                            continue;
                        }
                        next.offer(new State(nx, ny));
                        maze0[nx][ny] = 'J';
                    }
                }
                tmp = players;
                players = next;
                next = tmp;
                if (players.isEmpty()) {
                    return -1;
                }
                ++time;
            }
        }

        boolean isEdge(int x, int y) {
            return x == 0 || y == 0 || x == maze0.length - 1 || y == maze0[0].length - 1;
        }

        boolean isNotValid(int x, int y) {
            return x < 0 || x >= maze0.length || y < 0 || y >= maze0[0].length;
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
