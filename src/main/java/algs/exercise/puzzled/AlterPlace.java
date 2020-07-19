package algs.exercise.puzzled;

import java.util.LinkedList;

public class AlterPlace {

    public static void replace(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        int temp;
        int n = nums.length;
        int nextInd = 0;
        int buf = nums[0];
        for (int i = 0; i < n; i++) {
            nextInd = nextInd*2+1 < n ? nextInd*2+1 : 2*(n-nextInd-1);
            temp = nums[nextInd];
            nums[nextInd] = buf;
            buf = temp;
        }

    }
    public static void main(String[] args) {
        int[] nums = new int[] {6, 5, 4, 3, 2, 1};
        replace(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

}