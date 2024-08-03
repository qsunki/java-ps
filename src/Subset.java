public class Subset {
    static int[] nums = {0, 1, 2, 3, 4};
    static int n = nums.length;
    static boolean[] visited = new boolean[n];

    public static void main(String[] args) {
        printSubset(0);
        printSubsetSum(0, 0);
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
