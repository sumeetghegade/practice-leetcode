package practice;


/*
INTUITION:

Ants keep moving, collisions do not matter.
We don't care about which ant is going where, so when they collide,
essentially they just keep moving as they were except the ants change and that does not affect out answer.

 */

public class LastMomentBeforeAllAntsFallOutofaPlank {

    public int getLastMoment(int n, int[] left, int[] right) {

        int leftMax = -1;
        int rightMin = Integer.MAX_VALUE;

        for(int num: left) {
            leftMax = Math.max(leftMax, num);
        }

        for(int num: right) {
            rightMin = Math.min(rightMin, num);
        }

        return Math.max(leftMax, n - rightMin);

    }
}
