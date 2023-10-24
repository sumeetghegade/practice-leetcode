package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
INTUITION:
The easiest way to do this would be to get the count of each element, sort it by count and then return k largest values.
This would take nlogn time

Another way to solve can be to use a max heap.
We can get the count of all elements and add them to a heap based on the count value.
While adding we only add upto k elements and at the end return the elements that remain in the heap.
n*log(k)

We can possibly also do this in o(n) time too. We can use bucket sort for this.
The bucket will represent the count of elements and all the elements that occur in the array i times will be placed at the ith position.
There may be multiple elements that occur i times so we'll need to store a list of numbers.
An array of lists will be best to store this data structure.

Algo:
1. Count occurences of each element and store in map.
2. Create a bucket which will be an array of lists.
3. Go over the map and put the key (element) at the position equal to its value.
4. We can travers out bucket array in reverse order and keep adding elements to it until we have added k elements.
5. Return k elements.

 */

public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> countMap = new HashMap<>();

        for(int num: nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucket = new ArrayList[nums.length + 1];

        for(Map.Entry<Integer, Integer> entry: countMap.entrySet()) {
            int count = entry.getValue();
            if(bucket[count] == null)
                bucket[count] = new ArrayList<>();
            bucket[count].add(entry.getKey());
        }

        int[] res = new int[k];
        int index = 0;
        for(int i = bucket.length - 1; i >= 0; i--) {
            if(bucket[i] != null) {
                for(int val: bucket[i]) {
                    res[index++] = val;
                    if(index == k)
                        return res;
                }
            }
        }
        return res;
    }
}
