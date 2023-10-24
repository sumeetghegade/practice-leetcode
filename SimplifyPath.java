package practice;

import java.util.Deque;
import java.util.LinkedList;


/*
INTUITION:
We need to handle the "..".
This can be done using a deque to keep track of previous dirs and pop if we encounter "..".
and at the end concatenate the dirs in deque to form final path.
 */

public class SimplifyPath {

    public static String simplifyPath(String path) {

        Deque<String> stack = new LinkedList<>();

        String[] arr = path.split("/");

        for(String s: arr) {
            if("".equals(s) || ".".equals(s))
                continue;
            else if("..".equals(s) && !stack.isEmpty())
                stack.pop();
            else if(!"..".equals(s)) {
                stack.push(s);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append("/");
            sb.append(stack.removeLast());
        }
        return sb.isEmpty() ? "/" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(simplifyPath("/../"));
        System.out.println(simplifyPath("/home//foo"));
        System.out.println(simplifyPath("/home/foo/../var"));
        System.out.println(simplifyPath("/../"));
    }
}
