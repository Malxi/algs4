package algs.sort;

class ShellSort{

    public static void swap(int[] nums, int p, int q){
        int temp = nums[p];
        nums[p] = nums[q];
        nums[q] = temp;
    }

    public static void shellsort(int[] nums){
        if(nums == null) return;

        int h = 1;
        while(h < nums.length / 3) h = 3 * h + 1;

        int i, j;
        while(h >= 1){
            for(i = h; i < nums.length; i++){
                /*for(j = i; j >= h; j -= h ){
                    if(nums[j] < nums[j - h]) swap(nums, j, j-h);
                }*/
                int temp = nums[i];
                j = i - h;
                while(j >= 0 && nums[j] > temp){
                    nums[j + h] = nums[j];
                    j = j - h;
                }
                nums[j + h] = temp;
            }
            h = h / 3;
        }
    }

    public static void sort(int[] nums){
        shellsort(nums);
    }

    public static void main(String[] args) {
        int[] nums = {591,955,829,805,312,83,764,841,12,744,104,773,627,306,731,539,349,811,662,341,465,300,491,423,569,405,508,802,500,747,689,506,129,325,918,606,918,370,623,905,321,670,879,607,140,543,997,530,356,446,444,184,787,199,614,685,778,929,819,612,737,344,471,645,726};
        //int[] nums = {3, 1, 3, 2};
        sort(nums);
        for(int item: nums){
            System.out.printf("%d ", item);
        }
    }
}