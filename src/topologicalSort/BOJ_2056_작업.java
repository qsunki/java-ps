package topologicalSort;

import java.io.*;
import java.util.*;

public class BOJ_2056_작업 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        List<Task> tasks = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            tasks.add(new Task());
        }
        int[] inDegree = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            tasks.get(i).cost = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int before = Integer.parseInt(st.nextToken());
                inDegree[i]++;
                tasks.get(before).nextTasks.add(i);
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        int ans = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            int sum = tasks.get(cur).beforeCost + tasks.get(cur).cost;
            ans = Math.max(ans, sum);
            for (int next : tasks.get(cur).nextTasks) {
                inDegree[next]--;
                tasks.get(next).beforeCost = Math.max(tasks.get(next).beforeCost, sum);
                if (inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        System.out.println(ans);
    }

    static class Task {
        int beforeCost;
        int cost;
        List<Integer> nextTasks = new ArrayList<>();
    }
}
