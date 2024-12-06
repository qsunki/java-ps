package greedy;

import java.io.*;
import java.util.*;

public class BOJ_1744_수묶기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 1. 1은 무조건 곱하지 않는게 좋음
        // 2. 1이 아닌 양수는 무조건 양수끼리 곱하는게 좋음
        // 2-1. 곱하는 두 양수는 큰 순서대로 짝을 짓는게 좋음
        // 3. 음수는 무조건 음수끼리 곱하면 좋음
        // 4. 0은 짝이없는 음수와 곱하거나 짝이 없을 때 좋음
        int n = Integer.parseInt(br.readLine());
        boolean existsZero = false;
        List<Integer> ones = new ArrayList<>(n);// count만 해도 됨...
        List<Integer> positives = new ArrayList<>(n);
        List<Integer> negatives = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                existsZero = true;
            } else if (num == 1) {
                ones.add(num);
            } else if (num > 1) {
                positives.add(num);
            } else {
                negatives.add(num);
            }
        }
        int ans = ones.size();
        positives.sort(Collections.reverseOrder());
        Collections.sort(negatives);
        ans += getMaxWhenPaired(positives);
        ans += getMaxWhenPaired(negatives);
        if (positives.size() % 2 == 1) {
            ans += positives.get(positives.size() - 1);
        }
        if (negatives.size() % 2 == 1 && !existsZero) {
            ans += negatives.get(negatives.size() - 1);
        }
        System.out.println(ans);
    }

    private static int getMaxWhenPaired(List<Integer> list) {
        int sum = 0;
        int idx = 0;
        while (idx + 1 < list.size()) {
            sum += list.get(idx) * list.get(idx + 1);
            idx += 2;
        }
        return sum;
    }
}
