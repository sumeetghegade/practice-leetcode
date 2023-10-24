package practice;


/*
Approach 1:
Add all elements in nums2 at the end of nums1. then sort nums1 --> (m+n) log(m+n)

Approach 2:
Start at m-1 and n-1 of both arrays. maintain a pointer at the end of nums1 (k = m+n-1).
start comparing nums1 and nums2 elements. put the bigger element at k and decrement respective pointers.
keep doing this till we have checked all elements in nums2. nums1 remaining will already be sorted.
 */
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m-1;
        int j = n-1;
        int k = m+n - 1;

        while(j >= 0 ) {
            if(i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }
}
