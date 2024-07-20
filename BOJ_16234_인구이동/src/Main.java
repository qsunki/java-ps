import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, l, r;
    static int[][] grid;
    static int ans;
    static Set<Node> listOfOpened = new HashSet<>();
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        n = nextInt();
        l = nextInt();
        r = nextInt();
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = nextInt();
            }
        }
        while (true) {
            boolean[][][] visited = new boolean[n][n][4];
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (!visited[i][j][k]) {
                            bfs(i, j, visited);
                            if (!listOfOpened.isEmpty()) {
                                flag = true;
                                int sumOfOpened = 0;
                                for (Node node : listOfOpened) {
                                    sumOfOpened += grid[node.x][node.y];
                                }
                                int afterValue = sumOfOpened / listOfOpened.size();
                                for (Node node : listOfOpened) {
                                    grid[node.x][node.y] = afterValue;
                                }
                                listOfOpened.clear();
                            }
                        }
                    }
                }
            }
            if (!flag) {
                break;
            }
            ++ans;
        }
        System.out.println(ans);
    }

    static void bfs(int startX, int startY, boolean[][][] visited) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(startX, startY));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isNotValid(nx, ny) || visited[nx][ny][(i + 2) % 4]) {
                    continue;
                }
                int diff = Math.abs(grid[cur.x][cur.y] - grid[nx][ny]);
                if (l <= diff && diff <= r) {
                    listOfOpened.add(cur);
                    listOfOpened.add(new Node(nx, ny));
                    q.offer(new Node(nx, ny));
                }
                visited[cur.x][cur.y][i] = true;
                visited[nx][ny][(i + 2) % 4] = true;
            }
        }
    }

    static boolean isNotValid(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }

    static class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
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
}
