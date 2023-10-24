package practice;


/*

Simple two pointer approach.
Ignore anything except alphanumeric and convert all chars to lower case.
O(n)


 */
public class ValidPalindrome {

    public boolean isPalindrome(String s) {

        int lp = 0, rp = s.length() - 1;

        while(lp < rp)
        {
            char c1 = s.charAt(lp);
            char c2 = s.charAt(rp);
            if(!Character.isLetterOrDigit(c1))
            {
                lp++;
                continue;
            }
            if(!Character.isLetterOrDigit(c2))
            {
                rp--;
                continue;
            }

            if(Character.toLowerCase(s.charAt(lp)) != Character.toLowerCase(s.charAt(rp)))
                return false;
            lp++;
            rp--;
        }

        return true;

    }

}
