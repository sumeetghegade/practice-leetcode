package practice.OA_Evan;

import java.util.*;

public class Q2 {
    public static long countMaximumProfitableGroups(List<Integer> stockPrice) {

        Stack<Integer> stack = new Stack<Integer>();

        int n = stockPrice.size();
        long output = 0;
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {

            while (!stack.isEmpty() && stockPrice.get(stack.peek()) < stockPrice.get(i)) {
                output += i - stack.pop();
            }

            if (!stack.isEmpty()) {
                if (stockPrice.get(stack.peek()) > stockPrice.get(i)) {
                    arr[i] = i - stack.peek();
                } else {
                    arr[i] = i - stack.peek() + arr[stack.peek()] - 1;
                }

            } else {
                arr[i] = i + 1;
            }

            output += arr[i];
            stack.push(i);
        }



        while (!stack.isEmpty()) {
            output += n - stack.pop();
        }

        return output - n;
    }



    public static void main(String[] args) {
        System.out.println(countMaximumProfitableGroups(Arrays.asList(1,5,2)));
        System.out.println(countMaximumProfitableGroups(Arrays.asList(3,1,3,5)));
    }

}