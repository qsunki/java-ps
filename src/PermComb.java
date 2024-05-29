import java.io.IOException;
import java.util.Arrays;

public class PermComb {
    static int[] nums = new int[]{1, 2, 3};
    static int n = 3;
    static int r = 2;

    static boolean[] visited = new boolean[nums.length];

    public static void main(String[] args) throws IOException {
        System.out.println("순열");
        printPermutation(0, new int[r]);
        System.out.println("조합");
        printCombination(0, 0, new int[r]);
        System.out.println("부분집합");
        printSubset(0);
        System.out.println("부분집합의 합");
        printSubsetSum(0, 0);
    }

    static void printPermutation(int cnt, int[] perm) {
        if (cnt == perm.length) {
            System.out.println(Arrays.toString(perm));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                perm[cnt] = nums[i];
                visited[i] = true;
                printPermutation(cnt + 1, perm);
                visited[i] = false;
            }
        }

    }

    static void printCombination(int cnt, int index, int[] comb) {
        if (cnt == comb.length) {
            System.out.println(Arrays.toString(comb));
            return;
        }
        for (int i = index; i < n; i++) {
            comb[cnt] = nums[i];
            printCombination(cnt + 1, i + 1, comb);
        }
    }

    static void printSubset(int cnt) {
        if (cnt == n) {
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    System.out.print(nums[i] + " ");
                }
            }
            System.out.println();
            return;
        }

        visited[cnt] = true;
        printSubset(cnt + 1);
        visited[cnt] = false;
        printSubset(cnt + 1);
    }

    static void printSubsetSum(int cnt, int sum) {
        if (cnt == n) {
            System.out.println(sum);
            return;
        }
        visited[cnt] = true;
        printSubsetSum(cnt + 1, sum + nums[cnt]);
        visited[cnt] = false;
        printSubsetSum(cnt + 1, sum);
    }
}