package implementation;

import java.io.*;
import java.util.*;

public class BOJ_14890_경사로 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        outer:
        for (int i = 0; i < n; ++i) {
            int before = map[i][0];
            int sameHeightCnt = 1;
            for (int j = 1; j < n; ++j) {
                if (map[i][j] - before == 1 && sameHeightCnt >= l) {
                    sameHeightCnt = 0;
                } else if (map[i][j] - before == -1) {
                    sameHeightCnt = 1;
                    while (j + 1 < n && sameHeightCnt < l && map[i][j] == map[i][j + 1]) {
                        ++j;
                        ++sameHeightCnt;
                    }
                    if (sameHeightCnt == l) {
                        sameHeightCnt = -1;
                    } else {
                        continue outer;
                    }
                } else if (before != map[i][j]) {
                    continue outer;
                }
                before = map[i][j];
                ++sameHeightCnt;
            }
            ++ans;
//            System.out.println("row = " + i);
        }
//        System.out.println(ans);
        outer:
        for (int j = 0; j < n; ++j) {
            int before = map[0][j];
            int sameHeightCnt = 1;
            for (int i = 1; i < n; ++i) {
                if (map[i][j] - before == 1 && sameHeightCnt >= l) {
                    sameHeightCnt = 0;
                } else if (map[i][j] - before == -1) {
                    sameHeightCnt = 1;
                    while (i + 1 < n && sameHeightCnt < l && map[i][j] == map[i + 1][j]) {
                        ++i;
                        ++sameHeightCnt;
                    }
                    if (sameHeightCnt == l) {
                        sameHeightCnt = -1;
                    } else {
                        continue outer;
                    }
                } else if (before != map[i][j]) {
                    continue outer;
                }
                before = map[i][j];
                ++sameHeightCnt;
            }
            ++ans;
        }
        System.out.println(ans);
    }
}
