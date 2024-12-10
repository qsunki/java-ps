package topologicalSort;

import java.io.*;
import java.util.*;

public class BOJ_2623_음악프로그램 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Graph graph = new Graph(n + 1);
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt - 1; ++j) {
                int to = Integer.parseInt(st.nextToken());
                graph.addEdge(from, to);
                from = to;
            }
        }
        List<Integer> sorted = graph.topologicalSort();
        for (Integer singer : sorted) {
            sb.append(singer).append("\n");
        }
        System.out.println(sb);
    }

    static class Graph {
        int size;
        int[] inDegree;
        AdjacencyList[] graph0;

        Graph(int size) {
            this.size = size;
            inDegree = new int[size];
            graph0 = new AdjacencyList[size];
            for (int i = 0; i < size; ++i) {
                graph0[i] = new AdjacencyList();
            }
        }

        void addEdge(int from, int to) {
            ++inDegree[to];
            graph0[from].add(to);
        }

        List<Integer> topologicalSort() {
            Queue<Integer> queue = new ArrayDeque<>();
            List<Integer> result = new ArrayList<>();
            for (int i = 1; i < size; ++i) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                    result.add(i);
                }
            }
            while (!queue.isEmpty()) {
                Integer node = queue.poll();
                for (Integer neighbor : graph0[node].getList()) {
                    --inDegree[neighbor];
                    if (inDegree[neighbor] == 0) {
                        queue.add(neighbor);
                        result.add(neighbor);
                    }
                }
            }
            if (result.size() != size - 1) {
                List<Integer> integers = new ArrayList<>();
                integers.add(0);
                return integers;
            }
            return result;
        }

        static class AdjacencyList {
            List<Integer> list = new ArrayList<>();

            void add(int node) {
                list.add(node);
            }

            List<Integer> getList() {
                return list;
            }
        }
    }
}
