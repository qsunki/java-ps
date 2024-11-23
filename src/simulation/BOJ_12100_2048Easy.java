package simulation;

import java.io.*;
import java.util.*;

/**
 * 개선할 점: command 후 변경 없을 시 가지치기
 * merge, compact 중복로직 제거(90, 180, 270 배열 회전이용)
 */
public class BOJ_12100_2048Easy {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        Game game = new Game(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                game.board.set(i, j, nextInt());
            }
        }
        int ans = getMaxBlockInTimes(game, 5);
        System.out.println(ans);
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

    static int getMaxBlockInTimes(Game game, int times) {
        if (times == 0) {
            return game.getMaxBlock();
        }
        int max = game.getMaxBlock();
        for (Game.Direction direction : Game.Direction.values()) {
            game.command(direction);
//            System.out.println(direction + " times: " + times);
//            game.printBoard();
            max = Math.max(max, getMaxBlockInTimes(game, times - 1));
            game.undo();
        }
        return max;
    }

    static class Game {
        static final int EMPTY = 0;
        int size;
        Board board;
        Deque<Board> history = new ArrayDeque<>();

        Game(int size) {
            this.size = size;
            board = new Board(size);
        }

        void command(Direction direction) {
            Board newBoard = new Board(size);
            merge(direction, newBoard);
            compact(direction, newBoard);
            history.push(board);
            board = newBoard;
        }

        void merge(Direction direction, Board newBoard) {
            switch (direction) {
                case LEFT:
                    mergeLeft(newBoard);
                    break;
                case RIGHT:
                    mergeRight(newBoard);
                    break;
                case UP:
                    mergeUp(newBoard);
                    break;
                case DOWN:
                    mergeDown(newBoard);
                    break;
            }
        }

        void mergeLeft(Board newBoard) {
            for (int i = 0; i < size; i++) {
                int ahead = 0;
                int behind = 1;
                while (behind < size) {
                    if (board.get(i, ahead) == EMPTY) {
                        ++ahead;
                        behind = ahead + 1;
                        continue;
                    }
                    if (board.get(i, behind) == EMPTY) {
                        ++behind;
                        continue;
                    }
                    int leftValue = board.get(i, ahead);
                    int rightValue = board.get(i, behind);
                    if (leftValue == rightValue) {
                        newBoard.set(i, ahead, leftValue * 2);
                        ahead = behind + 1;
                        behind = ahead + 1;
                    } else {
                        newBoard.set(i, ahead, leftValue);
                        ahead = behind;
                        behind = ahead + 1;
//                        newBoard.set(i, behind, rightValue);
                    }
                }
                if (ahead < size) {
                    newBoard.set(i, ahead, board.get(i, ahead));
                }
            }
        }

        void mergeRight(Board newBoard) {
            for (int i = 0; i < size; i++) {
                int ahead = size - 1;
                int behind = size - 2;
                while (behind >= 0) {
                    if (board.get(i, ahead) == EMPTY) {
                        --ahead;
                        behind = ahead - 1;
                        continue;
                    }
                    if (board.get(i, behind) == EMPTY) {
                        --behind;
                        continue;
                    }
                    int leftValue = board.get(i, ahead);
                    int rightValue = board.get(i, behind);
                    if (leftValue == rightValue) {
                        newBoard.set(i, ahead, leftValue * 2);
                        ahead = behind - 1;
                        behind = ahead - 1;
                    } else {
                        newBoard.set(i, ahead, leftValue);
                        ahead = behind;
                        behind = ahead - 1;
                    }
                }
                if (ahead >= 0) {
                    newBoard.set(i, ahead, board.get(i, ahead));
                }
            }
        }

        void mergeUp(Board newBoard) {
            for (int i = 0; i < size; i++) {
                int ahead = 0;
                int behind = 1;
                while (behind < size) {
                    if (board.get(ahead, i) == EMPTY) {
                        ++ahead;
                        behind = ahead + 1;
                        continue;
                    }
                    if (board.get(behind, i) == EMPTY) {
                        ++behind;
                        continue;
                    }
                    int leftValue = board.get(ahead, i);
                    int rightValue = board.get(behind, i);
                    if (leftValue == rightValue) {
                        newBoard.set(ahead, i, leftValue * 2);
                        ahead = behind + 1;
                        behind = ahead + 1;
                    } else {
                        newBoard.set(ahead, i, leftValue);
                        ahead = behind;
                        behind = ahead + 1;
                    }

                }
                if (ahead < size) {
                    newBoard.set(ahead, i, board.get(ahead, i));
                }
            }
        }

        void mergeDown(Board newBoard) {
            for (int i = 0; i < size; i++) {
                int ahead = size - 1;
                int behind = size - 2;
                while (behind >= 0) {
                    if (board.get(ahead, i) == EMPTY) {
                        --ahead;
                        behind = ahead - 1;
                        continue;
                    }
                    if (board.get(behind, i) == EMPTY) {
                        --behind;
                        continue;
                    }
                    int leftValue = board.get(ahead, i);
                    int rightValue = board.get(behind, i);
                    if (leftValue == rightValue) {
                        newBoard.set(ahead, i, leftValue * 2);
                        ahead = behind - 1;
                        behind = ahead - 1;
                    } else {
                        newBoard.set(ahead, i, leftValue);
                        ahead = behind;
                        behind = ahead - 1;
                    }
                }
                if (ahead >= 0) {
                    newBoard.set(ahead, i, board.get(ahead, i));
                }
            }
        }

        void compact(Direction direction, Board newBoard) {
            switch (direction) {
                case LEFT:
                    compactLeft(newBoard);
                    break;
                case RIGHT:
                    compactRight(newBoard);
                    break;
                case UP:
                    compactUp(newBoard);
                    break;
                case DOWN:
                    compactDown(newBoard);
                    break;
            }
        }

        void compactLeft(Board newBoard) {
            for (int i = 0; i < size; i++) {
                for (int j = 1; j < size; j++) {
                    int k = j - 1;
                    while (k >= 0 && newBoard.get(i, k) == EMPTY) {
                        k--;
                    }
                    if (k + 1 != j) {
                        newBoard.set(i, k + 1, newBoard.get(i, j));
                        newBoard.set(i, j, 0);
                    }
                }
            }
        }

        void compactRight(Board newBoard) {
            for (int i = 0; i < size; i++) {
                for (int j = size - 2; j >= 0; j--) {
                    int k = j + 1;
                    while (k < size && newBoard.get(i, k) == EMPTY) {
                        k++;
                    }
                    if (k - 1 != j) {
                        newBoard.set(i, k - 1, newBoard.get(i, j));
                        newBoard.set(i, j, 0);
                    }
                }
            }
        }

        void compactUp(Board newBoard) {
            for (int i = 0; i < size; i++) {
                for (int j = 1; j < size; j++) {
                    int k = j - 1;
                    while (k >= 0 && newBoard.get(k, i) == EMPTY) {
                        k--;
                    }
                    if (k + 1 != j) {
                        newBoard.set(k + 1, i, newBoard.get(j, i));
                        newBoard.set(j, i, 0);
                    }
                }
            }
        }

        void compactDown(Board newBoard) {
            for (int i = 0; i < size; i++) {
                for (int j = size - 2; j >= 0; j--) {
                    int k = j + 1;
                    while (k < size && newBoard.get(k, i) == EMPTY) {
                        k++;
                    }
                    if (k - 1 != j) {
                        newBoard.set(k - 1, i, newBoard.get(j, i));
                        newBoard.set(j, i, 0);
                    }
                }
            }
        }

        void undo() {
            if (!history.isEmpty()) {
                board = history.pop();
            }
        }

        int getMaxBlock() {
            return board.getMaxBlock();
        }

        void printBoard() {
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    System.out.print(board.get(i, j) + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        enum Direction {
            LEFT(0, -1), RIGHT(0, 1), UP(-1, 0), DOWN(1, 0);
            final int dx, dy;

            Direction(int dx, int dy) {
                this.dx = dx;
                this.dy = dy;
            }
        }

        static class Board {
            int n;
            int maxValue;
            int[][] board0;

            Board(int n) {
                this.n = n;
                board0 = new int[n][n];
            }

            int get(int x, int y) {
                return board0[x][y];
            }

            void set(int x, int y, int value) {
                board0[x][y] = value;
                maxValue = Math.max(maxValue, value);
            }

            int getMaxBlock() {
                return maxValue;
            }
        }
    }
}
