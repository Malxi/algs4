package algs.exercise.chapter1;

import edu.princeton.cs.algs4.StdOut;

public class BinarySearch{

    public static int rank(int[] nums, int target, int lo, int hi){
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] == target) return mid;
            if(nums[mid] > target) hi = mid - 1;
            else lo = mid + 1;
        }
        return -1;
    }

    public static int rankWithFib(int[] nums, int target, int lo, int hi){
        int fk = 0, fk_1 = 1;
        int offset = -1;
        while(fk < hi){
            fk = fk + fk_1;
            fk_1 = fk - fk_1;
        }
        while(fk_1 > 0){
            int mid = Math.min(fk - fk_1 + offset, hi);
            if(nums[mid] == target) return mid;
            if(nums[mid] > target){
                fk = mid - offset;
                fk_1 = fk_1 - fk;
            }else{
                fk = fk_1;
                fk_1 = mid - offset;
                offset = mid;
            }
        }
        return -1;
    }

    public static int strank(int[] nums, int target, int lo, int hi){
        /*
        while(hi - lo > 1){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] >= target) hi = mid;
            else lo = mid;
        }

        if(target < nums[lo]) return lo - 1;
        if(target >= nums[lo] && target < nums[hi]) return lo;
        if(target >= nums[hi]) return hi;
        return -1;
        */
        lo -= 1;
        hi += 1;
        int r = hi;
        while(hi - lo > 1){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] >= target) hi = mid;
            else lo = mid;
        }
        return hi == r ? hi - 1 : nums[hi] == target ? hi : hi - 1;
    }

    public static int count(int[] nums, int target, int lo, int hi){
        int i = 0, j = 0;
        i = strank(nums, target, lo, hi);
        //if(nums[i] < target) return 0;
        lo -= 1;
        hi += 1;
        int l = lo;
        while(hi - lo > 1){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] > target) hi = mid;
            else lo = mid;
        }
        
        j = lo == l ? lo + 1 : nums[lo] == target ? lo : lo - 1;
        
        return j - i + 1;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        int lo = 0, hi = nums.length - 1;
        if(hi == 0) return ans;
        while(hi - lo > 1){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] >= target) hi = mid;
            else lo = mid;
        }
        
        ans[0] = target == nums[lo] ? lo : target == nums[hi] ? hi : -1;
        
        hi = nums.length;
        while(hi - lo > 1){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] > target) hi = mid;
            else lo = mid;
        }
        ans[1] = target == nums[lo] ? lo : target == nums[hi] ? hi : -1;
        
        return ans;
    }

    public static int[] removeDuplicateWhitelist(int[] whitelist){
        int[] tmp = whitelist.clone();
        int pos = 0;
        for(int item : whitelist){
            if( rank(tmp, item, 0, pos - 1) < 0){
                tmp[pos++] = item;
            }
        }
        
        int[] res = new int[pos];
        for(int i = 0; i < pos; i++){
            res[i] = tmp[i];
        }

        return res;
    }

    public static void main(String []args){
        int[] whitelist = {1, 3, 5, 5, 9, 9, 11, 11, 11, 17, 19, 19};
        int[] nums = {1, 3, 4, 5, 8, 9, 10, 11, 16, 17, 19, 20};
        //whitelist = removeDuplicateWhitelist(whitelist);
        for(int item : whitelist){
            StdOut.print(item + " ");
        }
        StdOut.println();

        for(int item : nums){
            StdOut.println(item + " : " + rankWithFib(whitelist, item, 0, whitelist.length - 1));
        }

        
    }

}