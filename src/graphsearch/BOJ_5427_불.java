package graphsearch;

import java.io.*;
import java.util.*;

public class BOJ_5427_불 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static char[][] building = new char[1001][];
    static Queue<int[]> external = new ArrayDeque<>();
    static Queue<int[]> fire = new ArrayDeque<>();
    static Queue<int[]> player = new ArrayDeque<>();


    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            player.clear();
            fire.clear();
            external.clear();
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            for (int i = 0; i < h; i++) {
                building[i] = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if (building[i][j] == '*') {
                        fire.offer(new int[]{i, j});
                    } else if (building[i][j] == '@') {
                        player.offer(new int[]{i, j});
                    }
                }
            }
            int ans = escape(w, h);
            sb.append(ans == -1 ? "IMPOSSIBLE" : ans).append('\n');
        }
        System.out.println(sb);
    }

    static int escape(int w, int h) {
        int time = 1;
        Queue<int[]> tmp;
        while (!player.isEmpty()) {
            while (!fire.isEmpty()) {
                int[] cur = fire.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    if (isOut(nx, ny, w, h) || building[nx][ny] == '#' || building[nx][ny] == '*') {
                        continue;
                    }
                    external.offer(new int[]{nx, ny});
                    building[nx][ny] = '*';
                }
            }
            tmp = fire;
            fire = external;
            external = tmp;
            while (!player.isEmpty()) {
                int[] cur = player.poll();
                if (isEdge(cur[0], cur[1], w, h)) {
                    return time;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    if (isOut(nx, ny, w, h) || building[nx][ny] != '.') {
                        continue;
                    }
                    external.offer(new int[]{nx, ny});
                    building[nx][ny] = '@';
                }
            }
            tmp = player;
            player = external;
            external = tmp;
            ++time;
        }
        return -1;
    }

    static boolean isEdge(int x, int y, int w, int h) {
        return x == 0 || y == 0 || x == h - 1 || y == w - 1;
    }

    static boolean isOut(int x, int y, int w, int h) {
        return x < 0 || x >= h || y < 0 || y >= w;
    }
}
