import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m, k;
    static List<List<Integer>> graph;
    static int[] buildingCnt;
    static int[] inDegree;
    static boolean[] buildable;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        k = nextInt();
        graph = new ArrayList<>(n + 1);
        buildingCnt = new int[n + 1];
        buildable = new boolean[n + 1];
        inDegree = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = nextInt();
            int v = nextInt();
            graph.get(u).add(v);
            inDegree[v]--;
        }
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                buildable[i] = true;
            }
        }
        for (int i = 0; i < k; i++) {
            int cmd = nextInt();
            int target = nextInt();
            if (cmd == 1) {
                if (!build(target)) {
                    System.out.println("Lier!");
                    return;
                }
            } else {
                if (!destroy(target)) {
                    System.out.println("Lier!");
                    return;
                }
            }
        }
        System.out.println("King-God-Emperor");
    }

    static boolean build(int target) {
        if (!buildable[target]) {
            return false;
        }
        buildingCnt[target]++;
        if (buildingCnt[target] == 1) {
            for (int u : graph.get(target)) {
                ++inDegree[u];
                if (inDegree[u] == 0) {
                    buildable[u] = true;
                }
            }
        }
        return true;
    }

    static boolean destroy(int target) {
        if (buildingCnt[target] == 0) {
            return false;
        }
        buildingCnt[target]--;
        if (buildingCnt[target] == 0) {
            for (int u : graph.get(target)) {
                --inDegree[u];
                buildable[u] = false;
            }
        }
        return true;
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
