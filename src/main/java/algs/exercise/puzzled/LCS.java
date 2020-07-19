package algs.exercise.puzzled;

public class LCS {
    
    public static int bisearch(int[] dp, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (dp[mid] < target && (mid+1>=hi || dp[mid+1] > target)) {
                return mid + 1;
            }else if (dp[mid] < target) {
                hi = mid - 1;
            }else {
                lo = mid + 1;
            }
        }
        return 0;
    }

    public static int lcs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        /* dp[k]: the min element of length k */
        int[] dp = new int[nums.length + 1];
        int len = 1;
        
        dp[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= dp[len]) {
                dp[++len] = nums[i];
            }else {
                int j = bisearch(dp, 1, len, nums[i]);
                dp[j] = nums[i];
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 2, 3, 2};
        System.out.println(lcs(nums));
    }

}