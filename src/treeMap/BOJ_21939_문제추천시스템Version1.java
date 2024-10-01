package treeMap;

import java.io.*;
import java.util.*;

public class BOJ_21939_문제추천시스템Version1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int m;
    static HashMap<Integer, Problem> problemHashMap = new HashMap<>();
    static TreeMap<Integer, Problem> problemMap = new TreeMap<>(Comparator.comparing(o -> problemHashMap.get(o)));

    public static void main(String[] args) throws IOException {
        n = nextInt();
        for (int i = 0; i < n; ++i) {
            int p = nextInt();
            int l = nextInt();
            problemHashMap.put(p, new Problem(p, l));
            problemMap.put(p, new Problem(p, l));
        }
        m = nextInt();
        for (int i = 0; i < m; i++) {
            String command = next();
            int p;
            int l;
            switch (command) {
                case "recommend":
                    int x = nextInt();
                    Problem problem;
                    if (x == 1) {
                        problem = problemMap.lastEntry().getValue();
                    } else {
                        problem = problemMap.firstEntry().getValue();
                    }
                    sb.append(problem.number).append("\n");
                    break;
                case "add":
                    p = nextInt();
                    l = nextInt();
                    problemHashMap.put(p, new Problem(p, l));
                    problemMap.put(p, new Problem(p, l));
                    break;
                case "solved":
                    p = nextInt();
                    problemMap.remove(p);
                    problemHashMap.remove(p);
            }
        }
        System.out.println(sb);
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

    static class Problem implements Comparable<Problem> {
        int number;
        int difficulty;

        Problem(int number, int difficulty) {
            this.number = number;
            this.difficulty = difficulty;
        }

        @Override
        public int compareTo(Problem o) {
            int compare = Integer.compare(difficulty, o.difficulty);
            if (compare == 0) {
                return Integer.compare(number, o.number);
            }
            return compare;
        }
    }
}
