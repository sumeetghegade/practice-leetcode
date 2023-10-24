package practice;
import java.util.PriorityQueue;


/*
INTUITION:
Create a max heap using PQ
Keep size of PQ to k. if greater than k then we poll
at last we can return all elements in PQ.

n log(k)
 */
public class KClosestPointstoOrigin {

    public int[][] kClosest(int[][] points, int k) {

        // comparator will be decided on
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) ->  (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));

        for(int[] point: points) {
            heap.add(point);
            if(heap.size() > k) {
                heap.poll();
            }
        }

        int[][] result = new int[k][2];

        while(!heap.isEmpty()) {
            result[--k] = heap.poll();
        }

        return result;
    }

}
