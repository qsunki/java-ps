import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class WeightedGraphExample {
    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 9);
        graph.addEdge(1, 3, 13);

        int[] distance = new int[10];
        graph.dijkstra(1, distance);
        for (int d : distance) {
            System.out.println(d);
        }

    }

    static class Edge implements Comparable<Edge> {
        int u;
        int w;

        Edge(int u, int w) {
            this.u = u;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return o.w - w;
        }
    }

    static class Graph {
        int n;
        List<List<Edge>> adjList;

        Graph(int n) {
            this.n = n;
            adjList = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v, int w) {
            adjList.get(u).add(new Edge(v, w));
            adjList.get(v).add(new Edge(u, w));
        }

        void dijkstra(int start, int[] distance) {
            Arrays.fill(distance, Integer.MAX_VALUE);
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.offer(new Edge(start, 0));
            distance[start] = 0;

            while (!pq.isEmpty()) {
                Edge cur = pq.poll();

                if (cur.w > distance[cur.u]) {
                    continue;
                }

                for (Edge neighbor : adjList.get(cur.u)) {
                    int newDistance = distance[cur.u] + neighbor.w;
                    if (newDistance < distance[neighbor.u]) {
                        distance[neighbor.u] = newDistance;
                        pq.offer(new Edge(neighbor.u, newDistance));
                    }
                }
            }
        }

        void floydWarshall() {

        }
    }
}
