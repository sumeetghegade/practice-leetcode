package practice;


/*
Simple counting approach.
We start at output = "1"

Then we iterate from 1 --> n-1  (as we start at 0th index. mean 1 is 0th for us not 1st)
at every iteration we generate a string by counting the numbers in the string and again use the same string to
generate next output.

O(n * average length of output)

 */

public class CountAndSay {

    public String countAndSay(int n) {
        if(n <= 0)
            return "-1";

        String output = "1";

        for(int i = 1; i < n; i++) {
            output = say(output);
        }

        return output;
    }

    public String say(String s) {
        StringBuilder sb = new StringBuilder();

        // iterate characters in string
        for(int i = 0; i < s.length(); i++) {
            // each num will be there atleast once
            int count = 1;
            // check if next num is also the same, if yes increment position as well as count
            while(i < s.length() - 1 && s.charAt(i) == s.charAt(i+1)){
                count++;
                i++;
            }
            // append count first and then the num
            sb.append(count);
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        CountAndSay obj = new CountAndSay();

        System.out.println(obj.countAndSay(30).length());
    }
}
