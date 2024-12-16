package graphSearch;

import java.io.*;
import java.util.*;

/**
 * <a href="https://oh2279.tistory.com/123">https://oh2279.tistory.com/123</a>
 */
public class BOJ_17071_숨바꼭질5 {

    static final int MAX = 500000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[][] visited = new boolean[MAX + 1][2];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (n == k) {
            System.out.println(0);
            return;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        visited[n][0] = true;

        int time = 0;
        while (!queue.isEmpty()) {
            time++;

            int sisterPos = k + time * (time + 1) / 2;
            if (sisterPos > MAX) {
                break;
            }

            int parity = time % 2;

            if (visited[sisterPos][parity]) {
                System.out.println(time);
                return;
            }

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();

                int[] nextPositions = {cur - 1, cur + 1, cur * 2};

                for (int next : nextPositions) {
                    if (next < 0 || next > MAX) {
                        continue;
                    }

                    if (!visited[next][parity]) {
                        if (next == sisterPos) {
                            System.out.println(time);
                            return;
                        }
                        visited[next][parity] = true;
                        queue.offer(next);
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
