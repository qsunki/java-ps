import java.io.*;
import java.util.*;

import static java.util.Collections.binarySearch;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static List<Integer> LIS;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        LIS = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            int x = nextInt();
            int idx = binarySearch(LIS, x);
            if (idx < 0) {
                if (-idx - 1 == LIS.size()) {
                    LIS.add(x);
                } else {
                    LIS.set(-idx - 1, x);
                }
            }
        }
        System.out.println(LIS.size());
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
