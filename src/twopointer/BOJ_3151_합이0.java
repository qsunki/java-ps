package twopointer;

import java.io.*;
import java.util.*;

public class BOJ_3151_합이0 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        long ans = 0L;
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == 0) {
                    if (arr[left] == arr[right]) {
                        long count = right - left + 1;
                        ans += count * (count - 1) / 2;
                        break;
                    }
                    int leftVal = arr[left];
                    long leftCnt = 0L;
                    while (left < right && arr[left] == leftVal) {
                        leftCnt++;
                        left++;
                    }
                    int rightVal = arr[right];
                    long rightCnt = 0L;
                    while (left <= right && arr[right] == rightVal) {
                        rightCnt++;
                        --right;
                    }
                    ans += leftCnt * rightCnt;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        System.out.println(ans);
    }

}
