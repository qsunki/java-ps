package dp;

import java.io.*;
import java.util.*;

public class BOJ_2213_트리의독립집합 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int[][] dp;
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        Tree tree = new Tree(n);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int weight = Integer.parseInt(st.nextToken());
            tree.setWeight(i, weight);
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.addEdge(u, v);
        }

        dp = new int[n + 1][2];
        dfs(tree, 1, 0);
        getAnswer(tree, 1, 0, false, new boolean[n + 1]);

        int maxWeight = Math.max(dp[1][0], dp[1][1]);
        sb.append(maxWeight).append("\n");
        Collections.sort(result);
        for (int node : result) {
            sb.append(node).append(" ");
        }
        System.out.println(sb);
    }

    static void dfs(Tree tree, int node, int parent) {
        dp[node][0] = 0;
        dp[node][1] = tree.getWeight(node);

        for (int child : tree.getChildren(node)) {
            if (child == parent) continue;
            dfs(tree, child, node);

            dp[node][0] += Math.max(dp[child][0], dp[child][1]);
            dp[node][1] += dp[child][0];
        }
    }

    static void getAnswer(Tree tree, int node, int parent, boolean isParentIncluded, boolean[] visited) {
        visited[node] = true;

        if (isParentIncluded || dp[node][1] <= dp[node][0]) {
            for (int child : tree.getChildren(node)) {
                if (child == parent) continue;
                getAnswer(tree, child, node, false, visited);
            }
        } else {
            result.add(node);
            for (int child : tree.getChildren(node)) {
                if (child == parent) continue;
                getAnswer(tree, child, node, true, visited);
            }
        }
    }

    static class Tree {
        List<List<Integer>> graph;
        int n;
        int[] weight;

        Tree(int n) {
            this.n = n;
            graph = new ArrayList<>(n + 1);
            weight = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
        }

        int getWeight(int node) {
            return weight[node];
        }

        void setWeight(int node, int weight) {
            this.weight[node] = weight;
        }

        void addEdge(int u, int v) {
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        List<Integer> getChildren(int node) {
            return graph.get(node);
        }
    }
}
