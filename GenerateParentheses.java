package practice;

import java.util.ArrayList;
import java.util.List;


/*
Intuition:
Think of it like we have two choices at each position: '(' or ')'
So we need to make a choice in such a way that:
1. we use only the available number of parentheses
2. The resulting string is not invalid
This forms a decision tree --> recursion

So this is kind of like backtracking but with some conditions.
We can recursively keep adding brackets to the solution till we reach size 2n but we will stop when we know
that adding that particular bracket at that point will generate an invalid parentheses.
We can say this is like backtracking with pruning.

Now to think about the conditions:
1. We know that we can only put 'n' open brackets. --> add till open < n
2. We can only put a closed bracket if we have an open bracket available to close in later. --> add till close < open

If we simply performed backtracking without pruning the time complexity would be 2^2n.
But pruning results in us generating only the valid combinations and avoid calls for invalid combination
Hence this ensures that we generate combination proportional to Catalan(n).
The Catalan numbers, often denoted as C(n), represent the number of valid combinations of parentheses for n pairs.

TC: nth Catalan number:
--> O(4^n/sqrt(n))
 */
public class GenerateParentheses {

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateStrings(result, "", 0, 0, n);
        return result;
    }

    private static void generateStrings(List<String> result, String str, int open, int close, int n){
        // base condition
        if(str.length() == 2*n) {
            result.add(str);
            return;
        }

        // backtracking with pruning
        if(open < n)
            generateStrings(result, str + "(", open+1, close, n);
        if(close < open)
            generateStrings(result, str + ")", open, close+1, n);

    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
