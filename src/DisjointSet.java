import java.util.*;

public class DisjointSet {
    int n;
    int[] parent;

    DisjointSet(int n) {
        this.n = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public static void main(String[] args) {
        DisjointSet disjointSet = new DisjointSet(6);

        disjointSet.union(1, 2);
        disjointSet.union(2, 3);
        disjointSet.union(4, 5);
        System.out.println(Arrays.toString(disjointSet.parent));
    }

    void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u < v) {
            parent[v] = u;
        } else {
            parent[u] = v;
        }
    }

    int find(int u) {
        if (u == parent[u]) {
            return u;
        }
        parent[u] = find(parent[u]);
        return parent[u];
    }

}
