import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static Set<Node> curWalls = new HashSet<>();
    static Set<Node> nextWalls = new HashSet<>();
    static char[][] board = new char[8][];

    static int[] dr = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        for (int r = 0; r < 8; ++r) {
            board[r] = next().toCharArray();
            for (int c = 0; c < 8; ++c) {
                if (board[r][c] == '#') {
                    curWalls.add(new Node(r , c));
                    nextWalls.add(new Node(r , c));
                }
            }
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(7, 0));
        int ans = bfs(q);
        System.out.println(ans);
    }

    static int bfs(Queue<Node> q) {
        Queue<Node> nextQ = new ArrayDeque<>();
        moveWalls(nextWalls);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 9; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (isNotValid(nr, nc)) {
                    continue;
                }
                Node next = new Node(nr, nc);
                if (curWalls.contains(next) || nextWalls.contains(next)) {
                    continue;
                }
                if (nr == 0 && nc == 7) {
                    return 1;
                }
                nextQ.add(next);
            }
        }
        if (nextQ.isEmpty()) {
            return 0;
        }
        moveWalls(curWalls);
        return bfs(nextQ);
    }

    private static void moveWalls(Set<Node> walls) {
        Set<Node> newSet = new HashSet<>();
        for (Node wall : walls) {
            ++wall.r;
            if (wall.r != 8) {
                newSet.add(wall);
            }
        }
        walls.clear();
        walls.addAll(newSet);
    }

    static boolean isNotValid(int r, int c) {
        return r < 0 || r >= 8 || c < 0 || c >= 8;
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
        int r;
        int c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return r == node.r && c == node.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}
