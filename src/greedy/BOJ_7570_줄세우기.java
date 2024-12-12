package greedy;

import java.io.*;
import java.util.*;

public class BOJ_7570_줄세우기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    /**
     * 안움직여도 되는 것 갯수 찾기
     * 5 2 4 1 3 -> 2, 3 이 안 움직여도 됨
     */
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[n + 1];
        int[] noMoveCnt = new int[n + 1];
        int noMoveMax = 1;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            int num = Integer.parseInt(st.nextToken());
            visited[num] = true;
            if (visited[num - 1]) {
                noMoveCnt[num - 1] = noMoveCnt[num - 1] + 1;
                noMoveCnt[num] = noMoveCnt[num - 1];
                noMoveMax = Math.max(noMoveMax, noMoveCnt[num]);
            } else {
                noMoveCnt[num] = 1;
            }
        }
        System.out.println(n - noMoveMax);
    }

}
