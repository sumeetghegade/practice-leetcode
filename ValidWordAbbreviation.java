package practice;

public class ValidWordAbbreviation {

    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;

        if(abbr.length() > word.length())
            return false;

        // 1. If both are chars, keep incrementing i and j, make sure chars are equal
        // 2. if charAt(j) is digit:
        //      a. make sure no trailing zero
        //      b. calculate number
        //      c. increment i by number
        // continue loop till end
        // 3. make sure both have been completely traversed

        while(i < word.length() && j < abbr.length()) {

            if(Character.isDigit(abbr.charAt(j))) {
                if(abbr.charAt(j) == '0')
                    return false;
                int len = 0;
                while(j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                    len = len * 10 + Character.getNumericValue(abbr.charAt(j));
                    j++;
                }
                i = i + len;
            }
            else {
                if(word.charAt(i) != abbr.charAt(j))
                    return false;
                i++; j++;
            }
        }

        if(i == word.length() && j == abbr.length())
            return true;
        return false;
    }
}
