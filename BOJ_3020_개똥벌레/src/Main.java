import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, h;
    static int[] downPsum;
    static int[] upPsum;
    static int ansMin = Integer.MAX_VALUE;
    static int ansCnt;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        h = nextInt();
        downPsum = new int[h];
        upPsum = new int[h];
        for (int i = 0; i < n / 2; i++) {
            int down = Integer.parseInt(br.readLine());
            int up = Integer.parseInt(br.readLine());
            ++downPsum[h - down];
            ++upPsum[h - up];
        }
        for (int i = 1; i < h; ++i) {
            downPsum[i] = downPsum[i - 1] + downPsum[i];
            upPsum[i] = upPsum[i - 1] + upPsum[i];
        }
        for (int i = 0; i < h; ++i) {
            int destroy = downPsum[h - 1 - i] + upPsum[i];
            if (destroy < ansMin) {
                ansMin = destroy;
                ansCnt = 1;
            } else if (destroy == ansMin) {
                ++ansCnt;
            }
        }
        System.out.println(ansMin + " " + ansCnt);
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
