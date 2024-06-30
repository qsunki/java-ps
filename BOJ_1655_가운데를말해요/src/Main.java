import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n;
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int x = Integer.parseInt(br.readLine());
            if (minHeap.size() == maxHeap.size()) {
                if (minHeap.isEmpty() || minHeap.peek() > x) {
                    maxHeap.offer(x);
                } else {
                    minHeap.offer(x);
                    maxHeap.offer(minHeap.poll());
                }
            } else {
                if (maxHeap.peek() < x) {
                    minHeap.offer(x);
                } else {
                    maxHeap.offer(x);
                    minHeap.offer(maxHeap.poll());
                }
            }
            sb.append(maxHeap.peek()).append("\n");
        }
        System.out.print(sb);
    }
}
