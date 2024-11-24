package simulation;

import java.io.*;
import java.util.*;

public class BOJ_13335_트럭 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int w = nextInt();
        int l = nextInt();
        Queue<Integer> trucks = new ArrayDeque<>(n);
        for (int i = 0; i < n; i++) {
            trucks.add(nextInt());
        }
        Bridge bridge = new Bridge(w, l);
        int time = 0;
        while (!trucks.isEmpty()) {
            if (bridge.canOn(trucks.peek())) {
                assert !trucks.isEmpty();
                bridge.next(trucks.poll());
            } else {
                bridge.next(0);
            }
            ++time;
        }

        System.out.println(time + w);

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

    static class Bridge {
        int maxLoad;
        int load;
        Queue<Integer> onBridgeQueue;

        Bridge(int length, int maxLoad) {
            this.maxLoad = maxLoad;
            onBridgeQueue = new ArrayDeque<>(length);
            for (int i = 0; i < length; i++) {
                onBridgeQueue.offer(0);
            }
        }

        boolean canOn(int weight) {
            assert !onBridgeQueue.isEmpty();
            return maxLoad >= load - onBridgeQueue.peek() + weight;
        }

        void next(int weight) {
            onBridgeQueue.offer(weight);
            assert !onBridgeQueue.isEmpty();
            load += weight;
            load -= onBridgeQueue.poll();
        }
    }
}
