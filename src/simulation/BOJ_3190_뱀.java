package simulation;

import java.io.*;
import java.util.*;

public class BOJ_3190_뱀 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        Game game = new Game(n);
        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            game.setApple(x, y);
        }
        int l = Integer.parseInt(br.readLine());
        int ans = 0;
        for (int i = 0; i < l; ++i) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char turn = st.nextToken().charAt(0);
            int result = game.command(time, turn);
            if (result != 0) {
                System.out.println(result);
                return;
            }
        }
        while (true) {
            int result = game.command(10000, 'X');
            if (result != 0) {
                ans = result;
                break;
            }
        }
        System.out.println(ans);
    }

    static class Game {
        static final int APPLE = 2;
        static final int SNAKE = 1;
        static final int EMPTY = 0;
        int size;
        int[][] board;
        int currentTime;
        Snake snake;


        Game(int size) {
            this.size = size;
            board = new int[size][size];
            snake = new Snake(size, board);
        }

        void setApple(int x, int y) {
            board[x][y] = APPLE;
        }

        int command(int time, char turn) {
            for (int i = currentTime; i < time; ++i) {
                ++currentTime;
//                System.out.print("currentTime: " + currentTime + " ");
                boolean go = snake.go();
                if (!go) {
                    return currentTime;
                }
            }
            snake.turn(turn);
            return 0;
        }


        static class Snake {

            static final int UP = 0;
            static final int DOWN = 1;
            static final int LEFT = 2;
            static final int RIGHT = 3;
            static final int[] dx = {-1, 1, 0, 0};
            static final int[] dy = {0, 0, -1, 1};

            int boardSize;
            int[][] board;
            Queue<Point> body = new ArrayDeque<>();
            int curDirection = RIGHT;
            Point head;

            Snake(int boardSize, int[][] board) {
                this.boardSize = boardSize;
                this.board = board;
                head = new Point(0, 0);
                body.add(head);
            }

            boolean go() {
//                System.out.println("curDirection: " + curDirection);
                int nx = head.x + dx[curDirection];
                int ny = head.y + dy[curDirection];
                if (isNotValid(nx, ny)) {
                    return false;
                }
                if (board[nx][ny] == SNAKE) {
                    return false;
                }
                if (board[nx][ny] != APPLE) {
                    Point tail = body.poll();
                    board[tail.x][tail.y] = EMPTY;
                }
                board[nx][ny] = SNAKE;
                head = new Point(nx, ny);
                body.offer(head);
//                System.out.println(head);
                return true;
            }

            boolean isNotValid(int x, int y) {
                return x < 0 || x >= boardSize || y < 0 || y >= boardSize;
            }

            void turn(char turn) {
                if (turn == 'D') {
                    if (curDirection == UP) {
                        curDirection = RIGHT;
                    } else if (curDirection == DOWN) {
                        curDirection = LEFT;
                    } else if (curDirection == LEFT) {
                        curDirection = UP;
                    } else {
                        curDirection = DOWN;
                    }
                } else {
                    if (curDirection == UP) {
                        curDirection = LEFT;
                    } else if (curDirection == DOWN) {
                        curDirection = RIGHT;
                    } else if (curDirection == LEFT) {
                        curDirection = DOWN;
                    } else {
                        curDirection = UP;
                    }
                }
            }
        }

        static class Point {
            int x;
            int y;

            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public String toString() {
                return "Point{" +
                        "x=" + x +
                        ", y=" + y +
                        '}';
            }
        }
    }
}
