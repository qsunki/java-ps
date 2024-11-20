package simulation;

import java.io.*;
import java.util.*;

public class BOJ_14891_톱니바퀴 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static List<List<Character>> gear = new ArrayList<>(4);

    public static void main(String[] args) throws IOException {
        gear.add(new ArrayList<>(8));
        gear.add(new ArrayList<>(8));
        gear.add(new ArrayList<>(8));
        gear.add(new ArrayList<>(8));
        for (int i = 0; i < 4; i++) {
            char[] info = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                gear.get(i).add(info[j]);
            }
        }
        int queryCnt = nextInt();
        for (int i = 0; i < queryCnt; i++) {
            int num = nextInt() - 1;
            int direct = nextInt();
            boolean[] rotated = new boolean[4];
            rotate(num, direct, rotated);
        }
        int ans = calc(gear);
        System.out.println(ans);
    }

    static int calc(List<List<Character>> gear) {
        int ret = 0;
        for (int i = 0; i < 4; i++) {
            if (gear.get(i).get(0) == '1') {
                ret += (int) Math.pow(2, i);
            }
        }
        return ret;
    }

    static void rotate(int num, int direction, boolean[] rotated) {
        boolean left = false;
        boolean right = false;
        if (num < 3 && !rotated[num + 1] && gear.get(num).get(2) != gear.get(num + 1).get(6)) {
            left = true;
        }
        if (num > 0 && !rotated[num - 1] && gear.get(num).get(6) != gear.get(num - 1).get(2)) {
            right = true;
        }
        Collections.rotate(gear.get(num), direction);
        rotated[num] = true;
        if (left) {
            rotate(num + 1, -direction, rotated);
        }
        if (right) {
            rotate(num - 1, -direction, rotated);
        }
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
