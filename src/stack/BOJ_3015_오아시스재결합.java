package stack;

import java.io.*;
import java.util.*;

public class BOJ_3015_오아시스재결합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        Deque<Person> stack = new ArrayDeque<>();

        int result = 0;

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());

            int count = 1;
            while (!stack.isEmpty() && stack.peek().height <= height) {
                Person top = stack.pop();
                result += top.count;
                if (top.height == height) {
                    count += top.count;
                }
            }

            if (!stack.isEmpty()) {
                result++;
            }

            stack.push(new Person(height, count));
        }

        System.out.println(result);
    }

    static class Person {
        int height;
        int count;

        public Person(int height, int count) {
            this.height = height;
            this.count = count;
        }
    }
}
