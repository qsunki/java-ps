package graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 개선할 점: 남들보다 3~4배 시간이 더 걸린다.
 * 생각해보면 역을 기준으로 bfs를 실행하고 있는데
 * 종점이 포함된 튜브를 방문했다면 역을 탐색할 필요가 없다.
 * 하이퍼튜브가 edge처럼 보이지만 이를 node라고 생각하고
 * 시점이 포함된 튜브에서 종점이 포함된 튜브를 찾는 방식으로 bfs를 실행하면
 * 남들만큼 빨라질 듯!
 */
public class BOJ_5214_환승 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int k = nextInt();
        int m = nextInt();
        List<List<Edge>> edgesPerNode = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            edgesPerNode.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            Edge edge = new Edge(k);
            for (int j = 1; j <= k; j++) {
                int u = nextInt();
                edge.addNode(u);
                edgesPerNode.get(u).add(edge);
            }
        }
        System.out.println(bfs(n, edgesPerNode));
    }

    static int bfs(int n, List<List<Edge>> edgesPerNode) {
        if (n == 1) {
            return 1;
        }
        boolean[] visited = new boolean[n + 1];
        Queue<Node> q = new ArrayDeque<>();
        visited[1] = true;
        q.add(new Node(1, 1));
        while (!q.isEmpty()) {
            Node cur = q.poll();
//            System.out.println("current node: " + cur.u);
            int nCnt = cur.cnt + 1;
            for (Edge edge : edgesPerNode.get(cur.u)) {
                for (Integer neighbor : edge.nodes) {
//                    System.out.println("neighbor: " + neighbor);
                    if (visited[neighbor]) {
                        continue;
                    }
                    if (neighbor == n) {
                        return nCnt;
                    }
                    visited[neighbor] = true;
                    q.add(new Node(neighbor, nCnt));
                }
            }
        }
        return -1;
    }

    static class Node {
        int u;
        int cnt;

        Node(int u, int cnt) {
            this.u = u;
            this.cnt = cnt;
        }
    }

    static class Edge {
        int size;
        List<Integer> nodes;

        Edge(int size) {
            this.size = size;
            nodes = new ArrayList<>(size);
        }

        void addNode(int node) {
            nodes.add(node);
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
}
