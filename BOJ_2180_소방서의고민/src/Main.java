import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static Fire[] fires;
    static int ans;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        fires = new Fire[n];
        for (int i = 0; i < n; i++) {
            int a = nextInt();
            int b = nextInt();
            fires[i] = new Fire(a, b);
        }
        Arrays.sort(fires);
        for (int i = 0; i < n; i++) {
            ans = (ans + ((fires[i].a * ans) % 40000 + fires[i].b)) % 40000;
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

    static class Fire implements Comparable<Fire> {
        int a;
        int b;

        Fire(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Fire o) {
            return Integer.compare(o.a * b, o.b * a);
        }
    }
}
