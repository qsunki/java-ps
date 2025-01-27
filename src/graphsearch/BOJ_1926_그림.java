package graphsearch;

import java.io.*;
import java.util.*;

public class BOJ_1926_그림 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] paper = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DrawingPaper drawingPaper = new DrawingPaper(paper);
        System.out.println(drawingPaper.countOfDrawings + "\n" + drawingPaper.maxSizeOfDrawings);
    }

    static class DrawingPaper {
        static final int[] dx = {0, 0, 1, -1};
        static final int[] dy = {1, -1, 0, 0};

        final int[][] paper;
        int countOfDrawings;
        int maxSizeOfDrawings;

        DrawingPaper(int[][] paper) {
            this.paper = paper;
            boolean[][] visited = new boolean[paper.length][paper[0].length];
            for (int i = 0; i < paper.length; i++) {
                for (int j = 0; j < paper[0].length; j++) {
                    if (!visited[i][j] && paper[i][j] == 1) {
                        int size = bfs(i, j, visited);
                        maxSizeOfDrawings = Math.max(maxSizeOfDrawings, size);
                        ++countOfDrawings;
                    }
                }
            }
        }

        int bfs(int x, int y, boolean[][] visited) {
            int size = 1;
            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{x, y});
            visited[x][y] = true;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    if (isNotValid(nx, ny) || visited[nx][ny] || paper[nx][ny] == 0) {
                        continue;
                    }
                    q.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    ++size;
                }
            }
            return size;
        }

        boolean isNotValid(int x, int y) {
            return x < 0 || x >= paper.length || y < 0 || y >= paper[0].length;
        }
    }
}
