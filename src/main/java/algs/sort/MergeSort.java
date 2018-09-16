package algs.sort;

class MergeSort{

    public static void merge(int[] nums, int lof, int hif, int lor, int hir){
        int len = hir - lor + 1 + hif - lof + 1;
        int[] temp = new int[len];
        int i = lof, j = lor, k = 0;
        while(i <= hif && j <= hir){
            if(nums[i] <= nums[j]){
                temp[k++] = nums[i];
                i++;
            }else{
                temp[k++] = nums[j];
                j++;
            }
        }
        while(i <= hif){
            temp[k++] = nums[i];
            i++;
        }
        while(j <= hir){
            temp[k++] = nums[j];
            j++;
        }
        for(i = 0; i < len; i++){
            nums[lof + i] = temp[i];
        }
    }

    public static void mergesort(int[] nums, int lo, int hi){
        if(hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergesort(nums, lo, mid);
        mergesort(nums, mid + 1, hi);
        merge(nums, lo, mid, mid+1, hi);
    }

    public static void sort(int[] nums){
        mergesort(nums, 0, nums.length - 1);
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