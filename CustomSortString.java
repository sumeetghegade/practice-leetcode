package practice;

import java.util.HashMap;


/*
INTUITION:
this is easy

we can start by looking at each character in 'order' starting from left.
if that char is present in s: append it to result (if multiple count, append 'count' times) [use a precomputed count map for this]
now append whatever is left in s in any order.


to do this first we need a map containing all chars from s and their count as keys.

use this map to form the result string.

 */
public class CustomSortString {

    public static String customSortString(String order, String s) {

        // create map with all char in s and their count
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c: s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

        // travers 'order', if char in 'order' present in 's': add it count time in result
        // remove it from map.
        for(char c: order.toCharArray()) {
            if(map.containsKey(c)) {
                int num = map.get(c);
                while(num > 0){
                    sb.append(c);
                    num--;
                }
                map.remove(c);
            }
        }

        // append remaining keys in map to result
        for(Character key: map.keySet()) {
            int num = map.get(key);
            while(num > 0) {
                sb.append(key);
                num--;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(customSortString("cba", "abcd"));
    }

}
