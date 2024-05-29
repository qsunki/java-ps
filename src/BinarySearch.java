public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 3;

        int resultIndex = binarySearch(nums, target);
        System.out.println("target: " + target);
        System.out.println("resultIdx: " + resultIndex + " result: " + nums[resultIndex]);

    }

    private static int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
