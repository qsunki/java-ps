import java.util.*;

public class GraphSearch {
    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(1, 6);
        graph.addEdge(6, 7);
        graph.addEdge(1, 8);
//        graph.dfsStack(1);
//        graph.dfsRecursive(1, new boolean[10]);
        graph.bfs(1);
    }

    static class Graph {
        int n;
        List<List<Integer>> adjList;

        Graph(int n) {
            this.n = n;
            adjList = new ArrayList<>(n);
            for (int i = 0; i < n; ++i) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        void dfsStack(int start) {
            boolean[] visited = new boolean[n];
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(start);
            while (!stack.isEmpty()) {
                Integer u = stack.pop();
                if (!visited[u]) {
                    System.out.println(u);
                    visited[u] = true;
                }
                for (int v : adjList.get(u)) {
                    if (!visited[v]) {
                        stack.push(v);
                    }
                }
            }
        }

        void dfsRecursive(int u, boolean[] visited) {
            System.out.println(u);
            visited[u] = true;
            for (int v : adjList.get(u)) {
                if (!visited[v]) {
                    dfsRecursive(v, visited);
                }
            }
        }

        void bfs(int start) {
            boolean[] visited = new boolean[n];
            Deque<Integer> queue = new ArrayDeque<>();
            visited[start] = true;
            queue.offer(start);
            while (!queue.isEmpty()) {
                Integer u = queue.poll();
                System.out.println(u);
                for (int v : adjList.get(u)) {
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.offer(v);
                    }
                }
            }
        }


    }
}
