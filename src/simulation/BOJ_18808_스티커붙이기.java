package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18808_스티커붙이기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m, k;
    static int r, c;
    static int[][] notebook;
    static int[][] sticker = new int[11][11];


    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        k = nextInt();
        notebook = new int[n][m];
        while (k-- > 0) {
            r = nextInt();
            c = nextInt();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    sticker[i][j] = nextInt();
                }
            }
            int rotateCnt = 4;
            findAttachable:
            while (rotateCnt-- > 0) {
                int rr = rotateCnt % 2 == 1 ? r : c;
                int rc = rotateCnt % 2 == 1 ? c : r;
                int row = n - rr + 1;
                int col = m - rc + 1;
                if (row <= 0 || col <= 0) {
                    continue;
                }
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (attachable(i, j, rotateCnt)) {
                            attach(i, j, rotateCnt);
                            break findAttachable;
                        }
                    }
                }
            }
        }
        int ans = countAttachedBlock();
        System.out.println(ans);
    }


    static boolean attachable(int x, int y, int rotateCnt) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int ri = rotateXWithCnt(i, j, rotateCnt);
                int rj = rotateYWithCnt(i, j, rotateCnt);
                if (notebook[x + ri][y + rj] == 1 && sticker[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static void attach(int x, int y, int rotateCnt) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int ri = rotateXWithCnt(i, j, rotateCnt);
                int rj = rotateYWithCnt(i, j, rotateCnt);
                if (sticker[i][j] == 1) {
                    notebook[x + ri][y + rj] = sticker[i][j];
                }
            }
        }
    }

    static int countAttachedBlock() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (notebook[i][j] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // cnt == 4: 0도 ... cnt == 0: 270도
    static int rotateXWithCnt(int x, int y, int cnt) {
        if (cnt == 3) {
            return x;
        }
        if (cnt == 2) {
            return y;
        }
        if (cnt == 1) {
            return r - x - 1;
        }
        return c - y - 1;
    }

    static int rotateYWithCnt(int x, int y, int cnt) {
        if (cnt == 3) {
            return y;
        }
        if (cnt == 2) {
            return r - x - 1;
        }
        if (cnt == 1) {
            return c - y - 1;
        }
        return x;
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
