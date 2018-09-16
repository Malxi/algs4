package algs.sort;

import java.util.Random;

public class QuickSort{

    public static void shuffle(int[] nums){
        if(nums == null) return;
        Random rand = new Random();
        for(int i = 0; i < nums.length; i++){
            int sp = i + rand.nextInt(nums.length - i);
            int temp = nums[i];
            nums[i] = nums[sp];
            nums[sp] = temp;
        }
    }

    public static int[] partition3p(int[] nums, int lo, int hi){
        int v = nums[lo];
        int lt = lo, gt = hi;
        int i = lt + 1;

        while(i <= gt){
            if(nums[i] < v){
                int temp = nums[i];
                nums[i] = nums[lt];
                nums[lt] = temp;
                lt++;
            }else if(nums[i] > v){
                int temp = nums[i];
                nums[i] = nums[gt];
                nums[gt] = temp;
                gt--;
            }else{
                i++;
            }
        }

        int[] bound = new int[2];
        bound[0] = lt - 1;
        bound[1] = gt + 1;
        return bound;
    }

    public static int[] partition2p(int[] nums, int lo, int hi){    
        int v = nums[lo], blank = lo;
        int i = lo, j = hi;

        while(i < j){
            while(i < j && nums[j] >= v) j--;
            if(i < j){
                nums[blank] = nums[j];
                blank = j;
            }
            while(i < j && nums[i] <= v) i++;
            if(i < j){
                nums[blank] = nums[i];
                blank = i;
            }
        }
        nums[blank] = v;
        int[] bound = new int[2];
        bound[0] = blank - 1;
        bound[1] = blank + 1;
        return bound;
    }

    public static void sort(int[] nums, int lo, int hi){
        if(lo >= hi) return;
        int[] bound = partition2p(nums, lo, hi);
        int lp = bound[0];
        int rp = bound[1];
        sort(nums, lo, lp);
        sort(nums, rp, hi);
    }

    public static void sort(int[] nums){
        sort(nums, 0, nums.length - 1);
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        sort(nums);
        for(int item: nums){
            System.out.printf("%d ", item);
        }    
    }

}