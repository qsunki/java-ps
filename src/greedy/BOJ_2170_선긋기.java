package greedy;

import java.io.*;
import java.util.*;

public class BOJ_2170_선긋기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        Line[] lines = new Line[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lines[i] = new Line(s, e);
        }
        Arrays.sort(lines);
        int ans = lines[0].end - lines[0].start;
        int beforeEnd = lines[0].end;
        for (int i = 1; i < n; i++) {
            // 1. 안겹칠때
            // 2. 겹칠때
            if (lines[i].start >= beforeEnd) {
                ans += lines[i].end - lines[i].start;
                beforeEnd = lines[i].end;
            } else {
                if (lines[i].end > beforeEnd) {
                    ans += lines[i].end - beforeEnd;
                    beforeEnd = lines[i].end;
                }

            }
        }

        System.out.println(ans);
    }

    static class Line implements Comparable<Line> {
        int start, end;

        Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line l) {
            return Integer.compare(start, l.start);
        }
    }
}
