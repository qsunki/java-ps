package graphsearch;

import java.io.*;
import java.util.*;

public class BOJ_7562_나이트의이동 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            int ans = bfs(startX, startY, endX, endY, n);
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }

    private static int bfs(int startX, int startY, int endX, int endY, int n) {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> external = new ArrayDeque<>();
        Queue<int[]> internal = new ArrayDeque<>();
        Queue<int[]> tmp;
        external.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
        int time = 0;
        while (!external.isEmpty()) {
            tmp = internal;
            internal = external;
            external = tmp;
            while (!internal.isEmpty()) {
                int[] cur = internal.poll();
                int x = cur[0];
                int y = cur[1];
                if (x == endX && y == endY) {
                    return time;
                }
                for (int i = 0; i < 8; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (isOut(nx, ny, n) || visited[nx][ny]) {
                        continue;
                    }
                    external.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
            ++time;
        }
        return time;
    }

    static boolean isOut(int x, int y, int n) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}
