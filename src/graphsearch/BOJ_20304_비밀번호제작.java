package graphsearch;

import java.io.*;
import java.util.*;

public class BOJ_20304_비밀번호제작 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        Queue<Node> queue = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        int[] minDistances = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int password = Integer.parseInt(st.nextToken());
            queue.offer(new Node(password, 0));
            minDistances[password] = 0;
        }
        Arrays.fill(minDistances, Integer.MAX_VALUE);
        int maxBit = Integer.toBinaryString(n).length();
        bfs(queue, n, minDistances, maxBit);
        int max = 0;
        for (int i = 0; i <= n; i++) {
            max = Math.max(max, minDistances[i]);
        }

        System.out.println(max);
    }

    static void bfs(Queue<Node> queue, int n, int[] minDistances, int maxBit) {
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int nDistance = cur.distance + 1;
            for (int i = 0; i <= maxBit; ++i) {
                int neighbor = cur.value ^ (1 << i);
                if (neighbor > n || nDistance >= minDistances[neighbor]) {
                    continue;
                }
                minDistances[neighbor] = nDistance;
                queue.offer(new Node(neighbor, nDistance));
            }
        }
    }

    static class Node {
        int value;
        int distance;

        public Node(int value, int distance) {
            this.value = value;
            this.distance = distance;
        }
    }

}
