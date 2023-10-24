package practice;

import java.util.HashMap;


/*
If we were to try and solve this manually
we would process two words at a time and compare each words characters from left to right.
This problem can also be solved similarly. Just that the order will be given to us.
We can easily map the order to an increasing integer sequence. so order = "cba" --> c:1, b:2, a:2 ...
HashMap can be used or we can use a int[26]; prefer hashmap make it more readable

Second part be to validate two strings. I think its easier to check if the sequence is invalid.
It will be invalid if the first non-equal character from left two right at the same position in both string is out of order else valid
We also need to check the length of the string. so in a dictionary:
'aa' would come before 'aaaa'
so after paarsing the two strings if len(first string) > len(second string) --> invalid


 */


public class VerifyingAnAlienDictionary {
    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {

        map = new HashMap<>();

        // save order in a hashmap for easy computation
        int i = 0;
        for(char c: order.toCharArray()) {
            map.put(c, i++);
        }

        // traverse the word list. consider two words at a time.
        for(i = 1; i < words.length; i++) {
            // check if invalid
            if(isInvalid(words[i-1], words[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean isInvalid(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        // we'll traverse string we run out of chars from smaller string as that is all we need to check
        for(int i = 0; i < l1 && i < l2; i++) {
            // ignore characters till they are the same
            if(word1.charAt(i) != word2.charAt(i)){
                // if left word char is greater at any point mean string is out of order
                if(map.get(word1.charAt(i)) > map.get(word2.charAt(i)))
                    return true;
                // if first non equal characters are correct means entire string has correct order
                else
                    return false;
            }
        }
        // if both strings have same char at all position then check for length. left length > right length --> invalid
        return l1 > l2;
    }
}
