package treemap;

import java.io.*;
import java.util.*;

public class BOJ_7662_이중우선순위큐 {
    static final int DELETE_MAX = 1;
    static final int DELETE_MIN = -1;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();
            while (k-- > 0) {
                String command = next();
                if (command.equals("I")) {
                    int num = nextInt();
                    Integer cnt = map.getOrDefault(num, 0);
                    map.put(num, cnt + 1);
                } else {
                    int type = nextInt();
                    if (map.isEmpty()) {
                        continue;
                    }
                    if (type == DELETE_MIN) {
                        Map.Entry<Integer, Integer> entry = map.firstEntry();
                        if (entry.getValue() == 1) {
                            map.remove(entry.getKey());
                        } else {
                            map.put(entry.getKey(), entry.getValue() - 1);
                        }
                    } else if (type == DELETE_MAX) {
                        Map.Entry<Integer, Integer> entry = map.lastEntry();
                        if (entry.getValue() == 1) {
                            map.remove(entry.getKey());
                        } else {
                            map.put(entry.getKey(), entry.getValue() - 1);
                        }
                    }
                }
            }
            if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
            }
        }
        System.out.println(sb);
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
