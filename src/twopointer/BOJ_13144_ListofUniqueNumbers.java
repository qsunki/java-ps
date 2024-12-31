package twopointer;

import java.io.*;
import java.util.*;

public class BOJ_13144_ListofUniqueNumbers {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int start = 0;

        for (int end = 0; end < n; end++) {
            int currentValue = arr[end];
            map.put(currentValue, map.getOrDefault(currentValue, 0) + 1);
            while (map.get(currentValue) > 1) {
                int startValue = arr[start];
                map.put(startValue, map.get(startValue) - 1);
                start++;
            }
            result += (end - start + 1);
        }
        System.out.println(result);
    }
}
