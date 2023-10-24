package practice;


/*
Maintain a pointer.
Start traversing the array.
    when you see a non-zero int, put it at pointer and increment pointer
    do this for entire array.

Now start new loop.
    start from pointer and set all elements to zero
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {

        int pointer = 0;
        for(int i = 0; i < nums.length; i++)
        {

            if(nums[i] != 0 )
            {
                nums[pointer] = nums[i];
                pointer++;
            }

        }
        for(int i = pointer; i < nums.length; i++)
        {
            nums[i] = 0;
        }

    }

    // one pass solution (less intuitive)
    // keep a zero window. at any non zero number, swap it with start of window.
    public void moveZeroes2 (int[] nums) {

        int len = nums.length;
        int zeroWindow = 0;
        for(int i = 0; i < len; i++) {
            if(nums[i] == 0) {
                zeroWindow++;
            }
            else if(zeroWindow != 0){
                int temp = nums[i];
                nums[i] = nums[i - zeroWindow];
                nums[i - zeroWindow] = temp;
            }
        }

    }
}
