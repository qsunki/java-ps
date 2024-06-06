import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] arr;

    public static void main(String[] args) throws IOException {
        arr = new int[3];
        arr[0] = nextInt();
        arr[1] = nextInt();
        arr[2] = nextInt();
        Arrays.sort(arr);
        System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
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
