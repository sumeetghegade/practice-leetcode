package practice;

import java.util.HashMap;

/*
Intuition:
Brute force would be to check for all substrings of s. N^3.

To improve we can use sliding window. Now at any point in our window we want to make sure that each character in 't'
is present in the window.
The best way to do this will be to keep track of chars and their count in t. as we expand or shrink our window
we keep adjusting the count of charaters so we know what we have and what we need in the current window.
Lets say we use a MAP to store chars and count.

Now if at any point the value of all keys becomes zero means that we have a substring that might be our answer.
To avoid traversing the entire map lets use a count variable which will start at map.size() and decrement when
any value in map becomes zero. this way when count is zero we have potential answer.

Now when we have a potential answer we'll try to see if we can shrink the window. We will keep shrinking and adjust
count and map if we encounter any character in 't'.

Keep doing this while keep track of min substring and we'll have our answer.

 */

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {

        int res = Integer.MAX_VALUE;
        if(t.length() > s.length())
            return "";

        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char c: t.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        int count = countMap.size();

        char[] arr = s.toCharArray();
        int len = s.length();

        // to store start of our resultant substring. [WRITE LATER]
        int start = 0;

        int sp = 0;
        int fp = 0;

        while (fp < len) {
            if(countMap.containsKey(arr[fp])) {
                countMap.put(arr[fp], countMap.get(arr[fp]) - 1);
                if(countMap.get(arr[fp]) == 0)
                    count--;
            }

            // found valid substring, start shrinking.
            while (count == 0) {
                if(fp - sp + 1 < res){
                    res = fp - sp + 1;
                    // store start of the substring.
                    start = sp;
                }
                if (countMap.containsKey(arr[sp])) {
                    countMap.put(arr[sp], countMap.get(arr[sp]) + 1);
                    if(countMap.get(arr[sp]) > 0)
                        count++;
                }
                sp++;
            }

            fp++;
        }

        return res == Integer.MAX_VALUE ? "" : s.substring(start, Math.min(start + res, s.length()));
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));

    }
}
