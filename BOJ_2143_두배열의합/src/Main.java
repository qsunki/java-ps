import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int t, n, m;
    static int[] nums1;
    static int[] nums2;
    static Map<Integer, Integer> psumInfo1 = new HashMap<>();
    static Map<Integer, Integer> psumInfo2 = new HashMap<>();
    static long ans;

    public static void main(String[] args) throws IOException {
        t = nextInt();
        n = nextInt();
        nums1 = new int[n];
        for (int i = 0; i < n; i++) {
            nums1[i] = nextInt();
        }
        m = nextInt();
        nums2 = new int[m];
        for (int i = 0; i < m; i++) {
            nums2[i] = nextInt();
        }
        for (int i = 0; i < n; i++) {
            int psum = 0;
            for (int j = i; j < n; j++) {
                psum += nums1[j];
                psumInfo1.compute(psum, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        for (int i = 0; i < m; i++) {
            int psum = 0;
            for (int j = i; j < m; j++) {
                psum += nums2[j];
                psumInfo2.compute(psum, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : psumInfo1.entrySet()) {
            int target = t - entry.getKey();
            int count = psumInfo2.getOrDefault(target, 0);
            ans += entry.getValue() * (long) count;
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
}
