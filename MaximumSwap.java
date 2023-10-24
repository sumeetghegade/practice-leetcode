package practice;




public class MaximumSwap {

    /*
    INTUITION:
    Lets assume that we swap any two digits in a number:
    Example: 7 2 6 3 6
    If we are swapping 2 and last 6 we can compute the number after swap as follows:
    -( 2 * 1000) - (6 * 1) + (6 * 1000) + (2 * 1)
    so using this formula we can calculate the difference a swap makes for all possible candidates
    how to find candidate?
    if we traverse from right to left, if the cur num is less than the max num that we have seen till then will be a potential candidate.
    we keep track of the max difference and add that to our input num giving us the answer.

    formula can reduce to :
    (cur_base - base_of_max) * (max - cur_digit)

    This solution is O(n) space.
    we can do in O(1) if instead of creating a char array we get the cur digit by dividing the num by 10 every iteration
     */

    public static int maximumSwap1(int num) {

        char[] digits = Integer.toString(num).toCharArray();
        int max = -1;
        int curbase = 1;
        int maxbase = 0;
        int delta = 0;

        for(int i = digits.length - 1; i >= 0; i--) {
            int curDigit = digits[i] - '0';
            if(curDigit > max) {
                max = curDigit;
                maxbase = curbase;
            } else {
                delta = Math.max(delta, (curbase - maxbase) * (max - curDigit) );
            }

            curbase *= 10;
        }
        return num + delta;
    }


    /*
    this solution depends on constraint where num <= 10^8
    INTUITION:
    If we do it by hand,
    we find the first digit from left which is smaller than the max digit to its right
    If there are multiple occurrences of the max digit then we need to consider the larger one.
     */
    public static int maximumSwap2(int num) {
        char[] numStr = new String("" + num).toCharArray();
        int[] greaterRight = new int[9];   // greaterRight[i] is the index of the rightmost greatest digit to the right of digit i in num.
        int max = numStr.length - 1;


        for (int i = numStr.length - 1; i >= 0; --i) {
            greaterRight[i] = max;
            if (numStr[max] - '0' < numStr[i] - '0') {
                max = i;
            }
        }
        for (int i = 0; i < numStr.length; ++i) {
            if (numStr[i] - '0' < numStr[greaterRight[i]] - '0') {
                char temp = numStr[i];
                numStr[i] = numStr[greaterRight[i]];
                numStr[greaterRight[i]] = temp;
                break;
            }
        }
        return Integer.parseInt(new String(numStr));
    }

    public static void main(String[] args) {
        System.out.println(maximumSwap1(726364));
    }

}
