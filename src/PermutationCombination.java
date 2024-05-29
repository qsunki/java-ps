import java.util.Arrays;

public class PermutationCombination {
    static int CNT;

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int n = 5;
        int r = 3;
        permutation(nums, n, r, 0, new int[r], new boolean[n]);
        System.out.println(CNT);
    }

    static void permutation(int[] nums, int n, int r, int cnt, int[] perm, boolean[] visited) {
        if (cnt == r) {
            System.out.println(Arrays.toString(perm));
            ++CNT;
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                perm[cnt] = nums[i];
                visited[i] = true;
                permutation(nums, n, r, cnt + 1, perm, visited);
                visited[i] = false;
            }
        }
    }

    static void combination(int[] nums, int n, int r, int cnt, int start, int[] comb) {

    }
}
