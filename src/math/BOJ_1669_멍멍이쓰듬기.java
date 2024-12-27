package math;

import java.io.*;
import java.util.*;

public class BOJ_1669_멍멍이쓰듬기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int diff = y - x;
        int increase = 1;
        int ans = 0;
        while (diff - 2 * increase > 0) {
            diff -= 2 * increase;
            ans += 2;
            ++increase;
        }
        if (diff > 0) {
            ++ans;
            if (diff - increase > 0) {
                ++ans;
            }
        }
        System.out.println(ans);
    }
}
