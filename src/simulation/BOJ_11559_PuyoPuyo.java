package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11559_PuyoPuyo {
    static final int N = 12;
    static final int M = 6;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static char[][] map = new char[N][];
    static int ans;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        while (remove()) {
            ++ans;
            drop();
        }
        System.out.println(ans);
    }

    static boolean remove() {
        boolean removed = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != '.') {
                    removed |= bfs(i, j);
                }
            }
        }
        return removed;
    }

    static boolean bfs(int x, int y) {
        Set<Node> visited = new HashSet<>();
        char target = map[x][y];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited.add(new Node(x, y));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                Node e = new Node(nx, ny);
                if (isNotValid(nx, ny) || map[nx][ny] != target || visited.contains(e)) {
                    continue;
                }
                visited.add(e);
                q.add(e);
            }
        }
        if (visited.size() >= 4) {
            for (Node node : visited) {
                map[node.x][node.y] = '.';
            }
            return true;
        }
        return false;
    }

    static boolean isNotValid(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= M;
    }

    static void drop() {
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != '.') {
                    dropSingle(i, j);
                }
            }
        }
    }

    static void dropSingle(int x, int y) {
        while (x + 1 < N && map[x + 1][y] == '.') {
            map[x + 1][y] = map[x][y];
            map[x][y] = '.';
            ++x;
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

    static class Node {
        int x, y;

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
}
