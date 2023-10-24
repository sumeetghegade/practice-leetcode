package practice;

import java.util.ArrayList;
import java.util.List;

/*
INTUITION:
What we care about is checking if for any two intervals:
does any one of them start before the other one ends.
This we can do by checking if:
min(end1, end2) >= max(start1, start2)  [explain this using examples]

if this condition is true then we need to merge the intervals and add to result list.

the choice of moving forward will depend on which of the two ends first.
we need to leave the one that ends first behind as it cannot have common interval with any other interval in the other list.

 */

public class IntervalListIntersections {

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        List<int []> list = new ArrayList<>();

        if(firstList.length == 0 || secondList.length == 0)
            return new int[0][0];

        int fp = 0, sp = 0;
        int startMax = 0;
        int endMin = 0;

        // iterate till we reach end of any one list
        while(fp < firstList.length && sp < secondList.length) {
            startMax = Math.max(firstList[fp][0], secondList[sp][0]);
            endMin = Math.min(firstList[fp][1], secondList[sp][1]);

            if(endMin >= startMax) {
                list.add(new int[]{startMax, endMin});
            }

            // move forward with the list whose interval ends before the other
            if(firstList[fp][1] == endMin) fp++;
            if(secondList[sp][1] == endMin) sp++;
        }

        return list.toArray(new int[0][0]);
    }


}
