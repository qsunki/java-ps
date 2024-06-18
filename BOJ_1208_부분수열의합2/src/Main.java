import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, s;
    static int[] arr1;
    static int[] arr2;
    static List<Integer> subSums1;
    static List<Integer> subSums2;
    static long ans;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        s = nextInt();
        arr1 = new int[n / 2];
        arr2 = new int[n - n / 2];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = nextInt();
        }
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = nextInt();
        }
        subSums1 = new ArrayList<>((int) Math.pow(2, arr1.length));
        subSums2 = new ArrayList<>((int) Math.pow(2, arr2.length));
        setSubSums(0, 0, arr1, 0, subSums1);
        setSubSums(0, 0, arr2, 0, subSums2);
        Collections.sort(subSums1);
        Collections.sort(subSums2);
        countS(subSums1);
        countS(subSums2);
        countS(subSums1, subSums2);
        System.out.println(ans);
    }

    static int upperBoundIdx(List<Integer> list, int target) {
        int low = 0;
        int high = list.size() - 1;
        int ret = -1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = list.get(mid);

            if (midVal < target) {
                low = mid + 1;
            } else if (midVal > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
                ret = mid;
            }
        }
        return ret;
    }

    static int lowerBoundIdx(List<Integer> list, int target) {
        int low = 0;
        int high = list.size() - 1;
        int ret = -1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = list.get(mid);

            if (midVal < target) {
                low = mid + 1;
            } else if (midVal > target) {
                high = mid - 1;
            } else {
                high = mid - 1;
                ret = mid;
            }
        }
        return ret;
    }

    static void countS(List<Integer> subSums) {
        int upper = upperBoundIdx(subSums, s);
        if (upper >= 0) {
            int lower = lowerBoundIdx(subSums, s);
            ans += upper - lower + 1;
        }
    }

    static void countS(List<Integer> subSums1, List<Integer> subSums2) {
        int idx1 = 0;
        int idx2 = subSums2.size() - 1;
        while (idx1 < subSums1.size() && idx2 >= 0) {
            int sum = subSums1.get(idx1) + subSums2.get(idx2);
            if (sum == s) {
                int idx1ValueCnt = upperBoundIdx(subSums1, subSums1.get(idx1)) - idx1 + 1;
                int idx2ValueCnt = idx2 - lowerBoundIdx(subSums2, subSums2.get(idx2)) + 1;
                ans += idx1ValueCnt * (long) idx2ValueCnt;
                idx1 = idx1 + idx1ValueCnt;
                idx2 = idx2 - idx2ValueCnt;
            } else if (sum < s) {
                ++idx1;
            } else {
                --idx2;
            }
        }
    }

    static void setSubSums(int idx, int sum, int[] arr, int cnt, List<Integer> subSums) {
        if (idx == arr.length) {
            if (cnt != 0) {
                subSums.add(sum);
            }
            return;
        }
        setSubSums(idx + 1, sum + arr[idx], arr, cnt + 1, subSums);
        setSubSums(idx + 1, sum, arr, cnt, subSums);
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
