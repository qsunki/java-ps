package dp;

import java.io.*;
import java.util.*;

public class BOJ_12852_1로만들기2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] cnts;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        cnts = new int[n + 1];
        parents = new int[n + 1];
        bfs(n);
        trace(n);
        System.out.println(cnts[1]);
        System.out.println(sb);
    }

    static void bfs(int n) {
        if (n == 1) {
            return;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(n);
        cnts[n] = 0;
        parents[n] = -1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int next;
            if (cur % 3 == 0) {
                next = cur / 3;
                if (parents[next] == 0) {
                    cnts[next] = cnts[cur] + 1;
                    parents[next] = cur;
                    if (next == 1) {
                        return;
                    }
                    queue.add(next);
                }
            }
            if (cur % 2 == 0) {
                next = cur / 2;
                if (parents[next] == 0) {
                    cnts[next] = cnts[cur] + 1;
                    parents[next] = cur;
                    if (next == 1) {
                        return;
                    }
                    queue.add(next);
                }
            }
            // minus 1
            next = cur - 1;
            if (parents[next] == 0) {
                cnts[next] = cnts[cur] + 1;
                parents[next] = cur;
                if (next == 1) {
                    return;
                }
                queue.add(next);
            }
        }
    }

    static void trace(int target) {
        int next = 1;
        sb.insert(0, ' ').insert(0, next);
        while (next != target) {
            next = parents[next];
            sb.insert(0, ' ').insert(0, next);
        }
    }

}
