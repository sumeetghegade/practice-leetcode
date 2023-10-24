package practice;

public class ValidNumber {

    /*
   Ask clarifying questions
   1. We can only see '. if we didn't see 'e' or '.'
   2. We can only see e if we have seen a number and not seen e. We need to see a number after 'e'
   3. We can only see '+' or '-' at start/end or after e
   4. Anthing other character: return false

   */
    public boolean isNumber(String s) {
        s = s.trim();
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen)
                    return false;
                pointSeen = true;
            } else if(s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                if(eSeen || !numberSeen)
                    return false;
                numberSeen = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && !(s.charAt(i-1) == 'e' || s.charAt(i-1) == 'E'))
                    return false;
            } else
                return false;
        }
        return numberSeen;
    }
}
