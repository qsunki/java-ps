import java.util.*;

public class Permutation {
    static int[] nums = {0, 1, 2, 3, 4};
    static int n = nums.length;
    static boolean[] visited = new boolean[n];
    static int r = 2;

    public static void main(String[] args) {
        printPermutation(0, new int[r]);
    }

    static void printPermutation(int cnt, int[] perm) {
        if (cnt == r) {
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
}
