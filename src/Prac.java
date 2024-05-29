import java.util.Arrays;

public class Prac {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 4, 5, 6};
//        System.out.println("Permutation");
//        printPermutation(nums, 5, 3);
//        System.out.println("Combination");
//        printCombination(nums, 5, 3);
        System.out.println("Subset");
        printSubset(nums, 5);
        System.out.println("SubsetSum");
        printSubsetSum(nums, 5, 3);
    }

    private static void printSubsetSum(int[] nums, int i, int i1) {
    }

    private static void printSubset(int[] nums, int n) {
        pprintSubset(nums, n, 0, new boolean[n]);

    }

    private static void pprintSubset(int[] nums, int n, int start, boolean[] visited) {
        if (start == n) {
            for (int i = 0; i < n; ++i) {
                if (visited[i]) {
                    System.out.print(nums[i] + " ");
                }
            }
            System.out.println();
            return;
        }
        visited[start] = true;
        pprintSubset(nums, n, start + 1, visited);
        visited[start] = false;
        pprintSubset(nums, n, start + 1, visited);
    }

    private static void printCombination(int[] nums, int n, int r) {
        pprintCombination(nums, n, r, 0, 0, new int[r]);
    }

    private static void pprintCombination(int[] nums, int n, int r, int start, int cnt, int[] comb) {
        if (cnt == r) {
            System.out.println(Arrays.toString(comb));
            return;
        }
        for (int i = start; i < n; i++) {
            comb[cnt] = nums[i];
            pprintCombination(nums, n, r, i + 1, cnt + 1, comb);
        }
    }

    private static void printPermutation(int[] nums, int n, int r) {
        pprintPermutation(nums, n, r, 0, new int[r], new boolean[n]);
    }

    private static void pprintPermutation(int[] nums, int n, int r, int cnt, int[] perm, boolean[] visited) {
        if (cnt == r) {
            System.out.println(Arrays.toString(perm));
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                perm[cnt] = nums[i];
                pprintPermutation(nums, n, r, cnt + 1, perm, visited);
                visited[i] = false;
            }
        }
    }


}
