import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m;
    static int[] records;
    static int ans;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        int low = 0;
        int high = 0;
        records = new int[n];
        for (int i = 0; i < n; i++) {
            records[i] = nextInt();
            high += records[i];
        }
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (check(mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(ans);
    }

    static boolean check(int time) {
        int cnt = 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (sum + records[i] > time) {
                ++cnt;
                if (cnt > m || records[i] > time) {
                    return false;
                }
                sum = 0;
            }
            sum += records[i];
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
