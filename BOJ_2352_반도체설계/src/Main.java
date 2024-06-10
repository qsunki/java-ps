import java.io.*;
import java.util.*;

import static java.util.Collections.binarySearch;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static List<Integer> sorted;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        sorted = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int target = nextInt();
            int idx = binarySearch(sorted, target);
            if (idx < 0) {
                if (-idx - 1 == sorted.size()) {
                    sorted.add(target);
                } else {
                    sorted.set(-idx - 1, target);
                }
            }
        }
        System.out.println(sorted.size());
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
