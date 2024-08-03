import java.util.*;

public class Combination {

    static int[] nums = {0, 1, 2, 3, 4};
    static int n = nums.length;
    static int r = 3;

    public static void main(String[] args) {
        printCombination(0, 0, new int[r]);
    }

    static void printCombination(int cnt, int index, int[] comb) {
        if (cnt == r) {
            System.out.println(Arrays.toString(comb));
            return;
        }
        for (int i = index; i < n; i++) {
            comb[cnt] = nums[i];
            printCombination(cnt + 1, i + 1, comb);
        }
    }
}
