package greedy;

import java.io.*;
import java.util.*;

public class BOJ_13904_과제 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static Hw[] homeWorks;
    static boolean[] date = new boolean[1001];
    static int ans;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        homeWorks = new Hw[n];
        for (int i = 0; i < n; i++) {
            int d = nextInt();
            int w = nextInt();
            homeWorks[i] = new Hw(d, w);
        }
        Arrays.sort(homeWorks);
        for (int i = 0; i < n; i++) {
            int targetDay = homeWorks[i].d;
            while (targetDay > 0) {
                if (!date[targetDay]) {
                    date[targetDay] = true;
                    ans += homeWorks[i].w;
                    break;
                } else {
                    --targetDay;
                }
            }
        }
        System.out.println(ans);
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

    static class Hw implements Comparable<Hw> {
        int d;
        int w;

        Hw(int d, int w) {
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Hw o) {
            int compare = Integer.compare(o.w, w);
            if (compare == 0) {
                return Integer.compare(d, o.d);
            }
            return compare;
        }
    }
}
