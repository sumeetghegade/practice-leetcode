package practice;

import java.util.HashMap;
import java.util.Map;

/*
Simply store the non-zero elements of the vector in a map with index of element as key and the element as value.

Now when dotProduct is called we can identify the vec with lesser non zero values by comparing size of map.

iterate on the smaller map to get the dot product

O(min(len1, len3))
 */

public class DotProductOfTwoSparseVectors {

    class SparseVector {

        HashMap<Integer, Integer> store;

        SparseVector(int[] nums) {
            store = new HashMap<>();

            for(int i = 0; i < nums.length; i++) {
                if(nums[i] != 0) {
                    store.put(i, nums[i]);
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            int prod = 0;
            if(this.store.size() > vec.store.size())
                return vec.dotProduct(this);
            for(Map.Entry<Integer,Integer> entry: store.entrySet()) {
                prod += entry.getValue() * vec.store.getOrDefault(entry.getKey(), 0);
            }
            return prod;
        }
    }
}
