import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int[] a;
    static int[] b;
    static int[] c;
    static int[] d;

    static int[] comb1;
    static int[] comb2;
    static long ans;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        a = new int[n];
        b = new int[n];
        c = new int[n];
        d = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
            b[i] = nextInt();
            c[i] = nextInt();
            d[i] = nextInt();
        }
        comb1 = new int[n * n];
        comb2 = new int[n * n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                comb1[i * n + j] = a[i] + b[j];
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                comb2[i * n + j] = c[i] + d[j];
            }
        }
        Arrays.sort(comb1);
        Arrays.sort(comb2);
        int left = 0;
        int right = n * n - 1;
        while (left < n * n && right >= 0) {
            int sum = comb1[left] + comb2[right];
            if (sum == 0) {
                int rightestOfLeft = upperBound(comb1, comb1[left]);
                int leftestOfRight = lowerBound(comb2, comb2[right]);
                int comb1term = rightestOfLeft - left + 1;
                int comb2term = right - leftestOfRight + 1;
                ans += comb1term * (long) comb2term;
                left += comb1term;
                right -= comb2term;
            } else if (sum > 0) {
                --right;
            } else {
                ++left;
            }
        }
        System.out.println(ans);
    }

    static int lowerBound(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int ret = 0;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            long midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else {
                ret = mid;
                high = mid - 1;
            }
        }
        return ret;
    }

    static int upperBound(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int ret = 0;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            long midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else {
                ret = mid;
                low = mid + 1;
            }
        }
        return ret;
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
