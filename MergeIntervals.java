package practice;

import java.util.*;
import java.util.List;

public class MergeIntervals {

    /*
    INTUITION:
    The idea is to sort the intervals based on their start time.
    Now we can check if the start time of an interval is less than the end time of previous interval.
    If yes then we merge the two intervals. We do this by taking the start time of prev interval and the end time of new interval
    will be the max(end current, end prev).

    keep doing this and return result.
     */

    public int[][] merge(int[][] intervals) {

        // sort based on start time of intervals
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // create a list to store as we don't know the final size of our result.
        List<int[]> result = new ArrayList<>();


        // we can use a temp arr to perform the merge
        int[] temp = intervals[0];
        // add this temp to our list and we'll keep modifying this array till we can no more merge
        result.add(temp);
        for(int[] interval: intervals) {
            // condition to merge
            if(interval[0] <= temp[1]) {
                // set end time of merge interval
                temp[1] = Math.max(temp[1], interval[1]);
            }
            // no more merge possible
            else {
                // set the temp to out current interval
                temp = interval;
                // add to list so merge can be performed
                result.add(temp);
            }
        }
        //convert to array and return
        return result.toArray(new int[result.size()][]);
    }
}
