package stack;

import java.io.*;
import java.util.*;

public class BOJ_6198_옥상정원꾸미기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 1. 빌딩을 스택에 넣고 뺀다.
        // 1-1. 넣을 대상이 스택의 top보다 작을 때까지 스택을 pop
        // 1-2. 넣고 나서 스택 사이즈 만큼 볼 수 있는 옥상 +
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> stack = new ArrayDeque<>();
        long ans = 0;
        while (n-- > 0) {
            int building = Integer.parseInt(br.readLine());
            while (!stack.isEmpty() && stack.peek() <= building) {
                stack.pop();
            }
            ans += stack.size();
            stack.push(building);
        }
        System.out.println(ans);
    }
}
