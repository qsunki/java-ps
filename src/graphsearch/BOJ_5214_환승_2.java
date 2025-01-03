package graphsearch;

import java.io.*;
import java.util.*;

/**
 * 방문체크를 역(station)기준이 아닌 hypertube기준으로 체크
 */
public class BOJ_5214_환승_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int k = nextInt();
        int m = nextInt();
        List<List<HyperTube>> tubesPerStation = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            tubesPerStation.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            HyperTube edge = new HyperTube(k, i);
            for (int j = 1; j <= k; j++) {
                int u = nextInt();
                edge.addNode(u);
                tubesPerStation.get(u).add(edge);
            }
        }
        System.out.println(bfs(n, m, tubesPerStation));
    }

    static int bfs(int n, int m, List<List<HyperTube>> tubesPerStation) {
        if (n == 1) {
            return 1;
        }
        boolean[] visited = new boolean[m];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(1, 1));
        while (!q.isEmpty()) {
            Node cur = q.poll();
//            System.out.println("current node: " + cur.u);
            int nCnt = cur.cnt + 1;
            for (HyperTube tube : tubesPerStation.get(cur.u)) {
                if (visited[tube.idx]) {
                    continue;
                }
                for (Integer station : tube.stations) {
//                    System.out.println("station: " + station);
                    if (station == n) {
                        return nCnt;
                    }
                    q.add(new Node(station, nCnt));
                }
                visited[tube.idx] = true;
            }
        }
        return -1;
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

    static class Node {
        int u;
        int cnt;

        Node(int u, int cnt) {
            this.u = u;
            this.cnt = cnt;
        }
    }

    static class HyperTube {
        int size;
        int idx;
        List<Integer> stations;

        HyperTube(int size, int idx) {
            this.size = size;
            this.idx = idx;
            stations = new ArrayList<>(size);
        }

        void addNode(int node) {
            stations.add(node);
        }
    }
}
