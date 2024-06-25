import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int[][] grid;
    static int babyShark = 2;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int locX;
    static int locY;
    static int ans;
    static int eatCnt;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = nextInt();
                if (grid[i][j] == 9) {
                    locX = i;
                    locY = j;
                    grid[i][j] = 0;
                }
            }
        }
        while (true) {
            int time = bfs();
            if (time == -1) {
                break;
            }
            ans += time;
        }
        System.out.println(ans);
    }

    static int bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][n];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(locX, locY, 0));
        visited[locX][locY] = true;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int nt = cur.t + 1;
            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if (isNotValid(nx, ny) || visited[nx][ny] || grid[nx][ny] > babyShark) {
                    continue;
                }
                if (grid[nx][ny] != 0 && grid[nx][ny] < babyShark) {
                    if (pq.isEmpty() || pq.peek().t == nt) {
                        pq.add(new Node(nx, ny, nt));
                    } else {
                        return eat(pq.peek());
                    }
                }
                q.add(new Node(nx, ny, nt));
                visited[nx][ny] = true;
            }
        }
        if (pq.isEmpty()) {
            return -1;
        }
        return eat(pq.peek());
    }

    private static int eat(Node node) {
        grid[node.x][node.y] = 0;
        ++eatCnt;
        if (eatCnt == babyShark) {
            eatCnt = 0;
            ++babyShark;
        }
        locX = node.x;
        locY = node.y;
        return node.t;
    }

    static boolean isNotValid(int x, int y) {
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

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int t;

        Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }

        @Override
        public int compareTo(Node o) {
            int compare = Integer.compare(x, o.x);
            if (compare == 0) {
                compare = Integer.compare(y, o.y);
            }
            return compare;
        }
    }
}
