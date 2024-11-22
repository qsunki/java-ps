package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499_주사위굴리기 {
    static final int EAST = 1;
    static final int WEST = 2;
    static final int NORTH = 3;
    static final int SOUTH = 4;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, m, x, y, k;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        x = nextInt();
        y = nextInt();
        k = nextInt();
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = nextInt();
            }
        }
        Dice dice = new Dice();
        while (k-- > 0) {
            int direct = nextInt();
            int nx = nextX(direct);
            int ny = nextY(direct);
            if (isNotValid(nx, ny)) {
                continue;
            }
            x = nx;
            y = ny;
            dice.move(direct);
            writeBottomValue(dice);
            sb.append(dice.top).append("\n");
        }
        System.out.println(sb);
    }

    static int nextX(int direct) {
        switch (direct) {
            case NORTH:
                return x - 1;
            case SOUTH:
                return x + 1;
            default:
                return x;
        }
    }

    static int nextY(int direct) {
        switch (direct) {
            case EAST:
                return y + 1;
            case WEST:
                return y - 1;
            default:
                return y;
        }
    }

    static boolean isNotValid(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    static void writeBottomValue(Dice dice) {
        if (board[x][y] == 0) {
            board[x][y] = dice.bottom;
        } else {
            dice.bottom = board[x][y];
            board[x][y] = 0;
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

    static class Dice {
        int bottom;
        int top;
        int left;
        int right;
        int up;
        int down;

        void move(int direct) {
            switch (direct) {
                case EAST:
                    moveEast();
                    break;
                case WEST:
                    moveWest();
                    break;
                case NORTH:
                    moveNorth();
                    break;
                case SOUTH:
                    moveSouth();
                    break;
            }
        }

        void moveEast() {
            int tmp = bottom;
            bottom = right;
            right = top;
            top = left;
            left = tmp;
        }

        void moveWest() {
            int tmp = bottom;
            bottom = left;
            left = top;
            top = right;
            right = tmp;
        }

        void moveNorth() {
            int tmp = bottom;
            bottom = up;
            up = top;
            top = down;
            down = tmp;
        }

        void moveSouth() {
            int tmp = bottom;
            bottom = down;
            down = top;
            top = up;
            up = tmp;
        }
    }
}
