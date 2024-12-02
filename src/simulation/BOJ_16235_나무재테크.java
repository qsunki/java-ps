package simulation;

import java.io.*;
import java.util.*;

public class BOJ_16235_나무재테크 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();
        int k = nextInt();
        Ground ground = new Ground(n);
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = nextInt();
            }
        }
        for (int i = 0; i < m; i++) {
            int x = nextInt() - 1;
            int y = nextInt() - 1;
            int age = nextInt();
            ground.plant(x, y, age);
        }

        while (k-- > 0 && !ground.isAllDead()) {
            ground.spring();
            ground.summer();
            ground.autumn();
            //winter
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ground.addNutrients(i, j, a[i][j]);
                }
            }
        }
        System.out.println(ground.getTreeCount());

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

    static class Ground {
        static final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        static final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        int n;
        int[][] nutrientMatrix;
        PriorityQueue<Tree> trees = new PriorityQueue<>();
        Queue<Tree> deadTrees = new ArrayDeque<>();

        Ground(int n) {
            this.n = n;
            this.nutrientMatrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    nutrientMatrix[i][j] = 5;
                }
            }
        }

        void addNutrients(int x, int y, int amount) {
            nutrientMatrix[x][y] += amount;
        }

        void plant(int x, int y, int age) {
            trees.offer(new Tree(x, y, age));
        }

        boolean isAllDead() {
            return trees.isEmpty();
        }

        int getTreeCount() {
            return trees.size();
        }

        public void spring() {
            List<Tree> tmp = new ArrayList<>();
            while (!trees.isEmpty()) {
                Tree tree = trees.poll();
                if (nutrientMatrix[tree.x][tree.y] >= tree.age) {
                    nutrientMatrix[tree.x][tree.y] -= tree.age;
                    tree.age++;
                    tmp.add(tree);
                } else {
                    deadTrees.add(tree);
                }
            }
            trees.addAll(tmp);
        }

        public void summer() {
            while (!deadTrees.isEmpty()) {
                Tree deadTree = deadTrees.poll();
                addNutrients(deadTree.x, deadTree.y, deadTree.age / 2);
            }
        }

        public void autumn() {
            List<Tree> tmp = new ArrayList<>();
            for (Tree tree : trees) {
                if (tree.age % 5 == 0) {
                    for (int i = 0; i < 8; ++i) {
                        int nx = tree.x + dx[i];
                        int ny = tree.y + dy[i];
                        if (isNotValid(nx, ny)) {
                            continue;
                        }
                        tmp.add(new Tree(nx, ny, 1));
                    }
                }
            }
            trees.addAll(tmp);
        }

        boolean isNotValid(int x, int y) {
            return x < 0 || y < 0 || x >= n || y >= n;
        }

        static class Tree implements Comparable<Tree> {
            int age;
            int x;
            int y;

            Tree(int x, int y, int age) {
                this.x = x;
                this.y = y;
                this.age = age;
            }

            @Override
            public int compareTo(Tree o) {
                return Integer.compare(age, o.age);
            }
        }

    }

}
