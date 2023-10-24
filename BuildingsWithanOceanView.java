package practice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BuildingsWithanOceanView {


    /*
    Approach 1: Go from right to left.
    Keep track of max height
    if current height is greater than max:
        add to result list and update max
     */
    public int[] findBuildings1(int[] heights) {

        List<Integer> list = new ArrayList<>();

        int max = -1;
        for(int i = heights.length - 1; i >= 0; i--) {
            if(heights[i] > max) {
                list.add(i);
                max = heights[i];
            }
        }

        int i = 0;
        int[] result = new int[list.size()];
        for(int j = list.size() - 1; j >= 0; j--) {
            result[i++] = list.get(j);
        }

        return result;
    }

    /*
    Approach2: Use monotonic stack
    keep adding heights to stack.
    if current height >= stack top --> pop

    add all from stack to result in revers order
     */

    public int[] findBuildings2(int[] heights) {

        Deque<Integer> stack = new ArrayDeque<>();


        for(int i = 0; i < heights.length; i++) {
            while(!stack.isEmpty() && heights[i] >= heights[stack.peek()])
                stack.pop();
            stack.push(i);
        }

        int[] result = new int[stack.size()];

        int index = stack.size() - 1;
        while(index >= 0) {
            result[index--] = stack.pop();
        }

        return result;
    }
}
