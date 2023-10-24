package practice;

import java.util.*;

/*
INTUITION:
First approach is recursion:

for each word in wordlist:
    check if word matches substring of length word:
        if yes again do same but change start to start + word.len
at any point if we reach the end of string: means we found a combination and return true
if we never reach then return false.

now Memoization:
this can be memoized as if from any start it is not possible to reach a solutoion
and we encounter that start later: no need to proceed with recursive call just return false

so whenever we return false store that index somewhere.
when we get to that index again return false directly

 */

public class WordBreak {


    //Recursion
    // O(2^n)
    public static boolean  wordBreak1(String s, List<String> wordDict) {
        return solve1(s, 0, wordDict);
    }

    public static boolean solve1(String s, int start, List<String> wordDict) {
        if(start == s.length()) {
            return true;
        }
        for(String word: wordDict) {
            if(start + word.length() <= s.length()) {
                if(s.substring(start, start + word.length()).equals(word)) {
                    if(solve1(s, start + word.length(), wordDict))
                        return true;
                }
            }
        }
        return false;
    }


    // Recursion + Memoization
    // O(n*m*k)
    // --> We are going to fill the set n times (length of string)
    // --> For each n we iterate over m words
    // --> For each m we also perform substring operations, --> k (average length of words in worddict)
    static Set<String> set;
    public static boolean wordBreak2(String s, List<String> wordDict) {

        Set<Integer> cache = new HashSet<>();
        // gloabal var
        set = new HashSet<>(wordDict);
        return solve2(s, 0, wordDict, cache);
    }

    public static boolean solve2(String s, int start, List<String> wordDict, Set<Integer> cache) {

        if(cache.contains(start))
            return false;
        if(start == s.length()) {
            return true;
        }
        for(String word: wordDict) {
            if(start + word.length() <= s.length()) {
                if(set.contains(s.substring(start, start + word.length()))) {
                    if(solve2(s, start + word.length(), wordDict, cache))
                        return true;
                }
            }
        }
        cache.add(start);
        return false;
    }

    public static void main(String[] args) {
        System.out.println("recursion");
        boolean ans = wordBreak1("nnnnnn", new ArrayList<String>(Arrays.asList("n","nn","nnn")));
        System.out.println(ans);

        System.out.println("recursion with memoization");
        boolean ans2 = wordBreak2("nnnnnn", new ArrayList<String>(Arrays.asList("n","nn","nnn")));
        System.out.println(ans2);
    }
}
