package algs.sort;

public class HeapSort{

    public static void swap(int[] nums, int p, int q){
        int temp = nums[p];
        nums[p] = nums[q];
        nums[q] = temp;
    }

    public static void minUpAdj(int[] nums,int pos){
        int parent = (pos - 1) / 2;
        while(parent >= 0 && nums[parent] > nums[pos]){
            swap(nums, parent, pos);
            pos = parent;
            parent = (pos - 1) / 2;
        }
    }

    public static void minDownAdj(int[] nums, int pos, int len){
        int v = nums[pos];
        while(pos * 2 + 1 < len){
            int mi = pos * 2 + 1;
            if(mi + 1 < len && nums[mi + 1] < nums[mi]){
                mi = mi + 1;
            }
            if(nums[mi] < v){
                nums[pos] = nums[mi];
                pos = mi;
            }else{
                break;
            }
        }
        if(pos < len) nums[pos] = v;
    }

    public static void maxDownAdj(int[] nums, int pos, int len){
        int v = nums[pos];
        while(pos * 2 + 1 < len){
            int mi = pos * 2 + 1;
            if(mi + 1 < len && nums[mi] < nums[mi + 1]){
                mi = mi + 1;
            }
            if(v < nums[mi]){
                nums[pos] = nums[mi];
                pos = mi;
            }
            else{
                break;
            }
        }
        nums[pos] = v;
    }

    public static void minHeapSort(int[] nums){
        for(int i = 0; i < nums.length; i++){
            minUpAdj(nums, i);
        }
        for(int i = nums.length - 1; i > 0; i--){
            swap(nums, 0, i);
            minDownAdj(nums, 0, i);
        }

        for(int i = 0; i < nums.length / 2; i++){
            swap(nums, i, nums.length - i - 1);
        }
    }

    public static void maxHeapSort(int[] nums){
        for(int i = (nums.length - 1) / 2; i >= 0 ; i--){
            maxDownAdj(nums, i, nums.length);
        }
        for(int i = nums.length - 1; i > 0; i--){
            swap(nums, 0, i);
            maxDownAdj(nums, 0, i);
        }
    }

    public static void sort(int[] nums){
        minHeapSort(nums);
        //maxHeapSort(nums);
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