package practice;


/*
Intuition:
Brute force would be to find all possible substrings and in each substring check
if possible to create repeating characters, if yes keep track of max length substring.

Lets just consider the problem of checking if repeating character replacement is possible in a substring.
This can be done if we find the dominant character in that string (max freq char) and then substract that number from
length of string. If this number is less than or equal to 'k' this is a possible answer.
We can find dominant character by counting.

Now checking all possible substring is not efficient.
We can do sliding window where we keep track of freq of each char and once we see that the substring in window can no more give us a
repeating char string we reduce size of window and adjust freq.
 */
public class LongestRepeatingCharacterReplacement {
    public static int characterReplacement(String s, int k) {

        int res = 0;
        int len = s.length();
        int[] countArr = new int[26];

        int sp = 0;
        int fp = 0;
        while(sp < len && fp < len) {
            // calc freq
            countArr[s.charAt(fp) - 'A']++;

            // find dominant character. (this can be optimized)
            int domCharCount = getMax(countArr);

            // check to find if possible to make repeating char string, if yes update res with max value
            if(fp - sp + 1 - domCharCount <= k) {
                res = Math.max(res, fp - sp + 1);
            }
            // if no then reduce window size while adjusting freq.
            else {
                countArr[s.charAt(sp) - 'A']--;
                sp++;
            }
            fp++;
        }

        return res;
    }


    public static int getMax(int[] arr) {
        int max = 0;
        for(int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "AABBCDCCCC";
        System.out.println(characterReplacement(s, 1));
    }

}
