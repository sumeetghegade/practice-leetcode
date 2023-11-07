package practice;

import java.util.Arrays;

/*
INTUITION:
Time = Distance / Speed

so we just calculate the speed of each monster and sort it.
Then we start eliminating monsters and increment a time counter after eliminating each monster.
Once we run out of time i.e. time counter is smaller than time[i] return time counter
else if traverse entire array return length of the array.

 */

public class EliminateMaximumNumberofMonsters {

    public int eliminateMaximum(int[] dist, int[] speed) {

        int n = dist.length;
        double[] time = new double[n];
        for(int i = 0; i < n; i++) {
            time[i] = (double)dist[i] / speed[i];
        }

        Arrays.sort(time);

        int curTime = 0;
        for(int i = 0; i < n; i++) {
            if(curTime < time[i]) {
                curTime++;
            }
            else
                return curTime;
        }

        return curTime;
    }

    public static void main(String[] args) {
        EliminateMaximumNumberofMonsters obj = new EliminateMaximumNumberofMonsters();

        int ans = obj.eliminateMaximum(new int[]{1,2,3}, new int[]{1,1,1});
        System.out.println(ans);
    }
}
