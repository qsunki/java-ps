package graphsearch;

import java.io.*;
import java.util.*;

public class BOJ_1697_숨바꼭질 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static boolean[] visited = new boolean[200_001];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Queue<State> q = new ArrayDeque<>();
        q.add(new State(n, 0));
        visited[n] = true;
        while (!q.isEmpty()) {
            State cur = q.poll();
            if (cur.x == k) {
                System.out.println(cur.time);
                return;
            }
            for (int nx : new int[]{cur.x - 1, cur.x + 1, cur.x * 2}) {
                if (nx < 0 || nx >= visited.length || visited[nx]) {
                    continue;
                }
                q.add(new State(nx, cur.time + 1));
                visited[nx] = true;
            }
        }
    }

    static class State {
        int x;
        int time;

        State(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}
