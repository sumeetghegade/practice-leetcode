package practice;

import java.util.ArrayList;
import java.util.List;


/*
INTUITION:
As we need all possible combinations this becomes a choice problem.
At every digit we need to choose one character from the corresponding string that the number represents.
We keep choosing till we run out of digits. --> base case

every time we choose we need to update our resultant string and we need to move to the next digit.
So recursion will help us do this easily.

We can store the number to string mapping in a string array.

 */
public class LetterCombinationOfAPhoneNumber {

    private static final String[] nlMap = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};;
    public List<String> letterCombinations(String digits) {

        // this our result list
        List<String> result = new ArrayList<>();
        // edge case: witout this result will be [""] instead of [] for empty input
        if(digits == null || "".equals(digits))
            return result;

        getCombination(digits, 0, result, "");
        return result;

    }

    public void getCombination(String digits, int offset, List<String> result, String str) {
        if(offset >= digits.length()) {
            result.add(str);
            return;
        }

        int curDig = digits.charAt(offset) - '0';
        for(int i = 0; i < nlMap[curDig].length(); i++) {
            getCombination(digits, offset + 1, result, str + nlMap[curDig].charAt(i));
        }
    }
}
