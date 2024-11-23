package shortestPath;

import java.io.*;
import java.util.*;

public class BOJ_22865_가장먼곳 {
    static final int MAX = Integer.MAX_VALUE;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n;
    static int a, b, c;
    static int m;
    static int[] distanceA;
    static int[] distanceB;
    static int[] distanceC;
    static List<List<Node>> graph;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        a = nextInt();
        b = nextInt();
        c = nextInt();
        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<Node>());
        }
        m = nextInt();
        for (int i = 0; i < m; ++i) {
            int u = nextInt();
            int v = nextInt();
            int w = nextInt();
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        distanceA = new int[n + 1];
        distanceB = new int[n + 1];
        distanceC = new int[n + 1];

        dijkstra(a, distanceA);
        dijkstra(b, distanceB);
        dijkstra(c, distanceC);

        int farthest = 1;
        int farthestDistance = 0;

        for (int i = 1; i <= n; ++i) {
            if (farthestDistance < distanceA[i] && farthestDistance < distanceB[i] && farthestDistance < distanceC[i]) {
                farthestDistance = Math.min(distanceB[i], distanceA[i]);
                farthestDistance = Math.min(farthestDistance, distanceC[i]);
                farthest = i;
            }
        }
        System.out.println(farthest);

    }

    private static void dijkstra(int x, int[] distance) {
        Arrays.fill(distance, MAX);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(x, 0));
        distance[x] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (distance[node.v] > node.w) {
                continue;
            }
            for (Node neighbor : graph.get(node.v)) {
                int newDistance = node.w + neighbor.w;
                if (distance[neighbor.v] <= newDistance) {
                    continue;
                }
                distance[neighbor.v] = newDistance;
                pq.offer(new Node(neighbor.v, newDistance));
            }
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

    static class Node implements Comparable<Node> {
        int v, w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(w, o.w);
        }
    }
}
