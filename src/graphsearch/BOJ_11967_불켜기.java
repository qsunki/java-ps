package graphsearch;

import java.io.*;
import java.util.*;

public class BOJ_11967_불켜기 {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();

        NodeList[][] grid = new NodeList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = new NodeList();
            }
        }
        for (int i = 0; i < m; i++) {
            int x = nextInt() - 1;
            int y = nextInt() - 1;
            int a = nextInt() - 1;
            int b = nextInt() - 1;
            grid[x][y].addNeighbor(new Node(a, b));
        }

        int ans = bfs(grid, n);
        System.out.println(ans);
    }

    static int bfs(NodeList[][] grid, int n) {
        boolean[][] visited = new boolean[n][n];
        boolean[][] canGo = new boolean[n][n];
        Queue<Node> queue = new ArrayDeque<>();
        visited[0][0] = true;
        canGo[0][0] = true;
        queue.add(new Node(0, 0));
        int lightOnCount = 1;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
//            System.out.println("x: " + (poll.x + 1) + " y: " + (poll.y + 1));
            for (Node neighbor : grid[poll.x][poll.y].getNeighbors()) {
                if (canGo[neighbor.x][neighbor.y] || visited[neighbor.x][neighbor.y]) {
                    continue;
                }
                canGo[neighbor.x][neighbor.y] = true;
                ++lightOnCount;
                if (visitedNearBy(visited, neighbor.x, neighbor.y, n)) {
                    queue.add(new Node(neighbor.x, neighbor.y));
                    visited[neighbor.x][neighbor.y] = true;
//                    System.out.println("light x: " + (neighbor.x + 1) + " light y: " + (neighbor.y + 1));
                }
            }
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (isNotValid(nx, ny, n) || visited[nx][ny] || !canGo[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                queue.add(new Node(nx, ny));
            }
        }
        return lightOnCount;
    }

    private static boolean visitedNearBy(boolean[][] visited, int x, int y, int n) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isNotValid(nx, ny, n)) {
                continue;
            }
            if (visited[nx][ny]) {
                return true;
            }
        }
        return false;
    }

    static boolean isNotValid(int x, int y, int n) {
        return x < 0 || x >= n || y < 0 || y >= n;
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

    static class NodeList {
        List<Node> neighbors = new ArrayList<>();

        void addNeighbor(Node node) {
            neighbors.add(node);
        }

        List<Node> getNeighbors() {
            return neighbors;
        }

    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
