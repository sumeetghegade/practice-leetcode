package practice;


/*
Intuition:
Simple logic--
keep checking using two pointer
once you find the first mismatch, two choices:
1. ignore left pointer char
2. ignore right pointer char

do both and keep checking
if neither case end up resulting in a plindrome return false else true

TC O(n)
SC O(1)

 */
public class ValidPalindromeII {

    public boolean validPalindrome(String s) {
        int lp = 0; int rp = s.length() - 1;

        while(lp < rp) {
            if(s.charAt(lp) != s.charAt(rp)) {
                return isPalindrome(s, lp, rp - 1) || isPalindrome(s, lp + 1, rp);
            }

            lp++;
            rp--;
        }
        return true;
    }

    public boolean isPalindrome(String s, int lp, int rp) {
        while(lp < rp) {
            if(s.charAt(lp) != s.charAt(rp))
                return false;
            lp++;
            rp--;
        }
        return true;
    }
}
