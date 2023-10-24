package practice;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {

        HashMap<Character, Character> open = new HashMap<>();
        open.put('(', ')');
        open.put('[', ']');
        open.put('{', '}');

        Stack<Character> stack = new Stack<>();

        for(char c: s.toCharArray()) {
            if(open.containsKey(c))
                stack.push(c);
            else{
                if(stack.isEmpty())
                    return false;
                if(c != open.get(stack.pop()))
                    return false;
            }
        }
        if(stack.isEmpty())
            return true;
        return false;
    }
}
