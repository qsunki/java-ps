package graphsearch;

import java.io.*;
import java.util.*;

public class BOJ_16920_확장게임 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();
        int p = nextInt();
        int[] s = new int[p + 1];
        for (int i = 1; i <= p; i++) {
            s[i] = nextInt();
        }
        Game game = new Game(n, m, p, s);

        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < m; ++j) {
                game.initBoard(i, j, chars[j]);
            }
        }
        while (!game.isEnd()) {
            for (int i = 1; i <= p; i++) {
                game.go(i);
            }
        }
        System.out.println(game.getResult());
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

    static class Game {
        static final int[] dx = {-1, 1, 0, 0};
        static final int[] dy = {0, 0, -1, 1};
        int n, m, p;
        int[] s;
        int[] areaInfos;
        boolean end;
        char[][] board;
        List<Queue<Node>> castlesQueueByNumber;

        Game(int n, int m, int p, int[] s) {
            this.n = n;
            this.m = m;
            this.p = p;
            this.s = s;
            areaInfos = new int[p + 1];
            board = new char[n][m];
            castlesQueueByNumber = new ArrayList<>(p + 1);
            for (int i = 0; i <= p; i++) {
                castlesQueueByNumber.add(new ArrayDeque<>());
            }
        }

        void initBoard(int x, int y, char value) {
            board[x][y] = value;
            if (value != '.' && value != '#') {
                int castle = value - '0';
                areaInfos[castle]++;
                castlesQueueByNumber.get(castle).add(new Node(x, y, 0));
            }
        }

        boolean isEnd() {
            return end;
        }

        void go(int x) {
            if (x == 1) {
                end = true;
            }
            int extendedCnt = extend(x);
            if (extendedCnt != 0) {
                end = false;
            }
        }

        int extend(int x) {
            //bfs
            int count = 0;
            Queue<Node> queue = castlesQueueByNumber.get(x);
            while (!queue.isEmpty() && queue.peek().step < s[x]) {
                Node polled = queue.poll();
                int nStep = polled.step + 1;
                for (int i = 0; i < 4; ++i) {
                    int nx = polled.x + dx[i];
                    int ny = polled.y + dy[i];
                    if (isNotValid(nx, ny)) {
                        continue;
                    }
                    if (board[nx][ny] != '.') {
                        continue;
                    }
                    board[nx][ny] = (char) (x + '0');
                    queue.add(new Node(nx, ny, nStep));
                    ++count;
                }
            }
            //reset step
            for (Node node : castlesQueueByNumber.get(x)) {
                node.step = 0;
            }
            areaInfos[x] += count;
            return count;
        }

        private boolean isNotValid(int nx, int ny) {
            return nx < 0 || nx >= n || ny < 0 || ny >= m;
        }

        String getResult() {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= p; ++i) {
                sb.append(areaInfos[i]).append(" ");
            }
            return sb.toString();
        }

        void printBoard() {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
        }

        static class Node {
            int x, y;
            int step;

            Node(int x, int y, int step) {
                this.x = x;
                this.y = y;
                this.step = step;
            }
        }

    }
}
