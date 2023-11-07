package practice;

import java.util.ArrayDeque;
import java.util.Deque;

public class FindtheWinnerofanArrayGame {

    public int getWinner(int[] arr, int k) {

        Deque<Integer> deque = new ArrayDeque<>();
        int count = 0;

        for(int num: arr) {
            deque.add(num);
        }

        int max = -1;
        int len = arr.length;

        int currWinner = -1, prevWinner = -1;;
        while(len >= 0) {
            int num1 = deque.poll();
            int num2 = deque.poll();
            max = Math.max(num1, max);
            len--;
            if(num1 > num2) {
                deque.addFirst(num1);
                deque.add(num2);
                currWinner = num1;
            } else {
                deque.addFirst(num2);
                deque.add(num1);
                currWinner = num2;
            }
            if(currWinner == prevWinner)
                count++;
            else {
                count = 1;
                prevWinner = currWinner;
            }
            if(count == k)
                return currWinner;
        }
        return max;
    }

    public static void main(String[] args) {
        FindtheWinnerofanArrayGame obj = new FindtheWinnerofanArrayGame();

        System.out.println(obj.getWinner(new int[]{3,2,1}, 10));

        System.out.println(obj.getWinner(new int[]{2,1,3,5,4,6,7}, 2));
    }
}
