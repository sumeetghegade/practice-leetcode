package practice;

/*
Intuition:

Brute force would be to compute each substring and then the calculate variance of each substring and return the max.
This will take n^3 time. Given the input range we definitely get TLE.

At the end, variance can be considered between any two pairs of alphabets in the string. So why don't we calculate variance
for each pair.
But again we have the same issue of how to efficiently calculate the variance even for just a pair.
If we are considering a pair we can assume all other characters to have a value of 0 and first char to be -1 and second char to be +1.
Now all we have to do is find the maximum subarray sum.
This can be done in O(n) using Kadane's algorithm.

 */

public class SubstringWithLargestVariance {
    public static int largestVariance(String s) {

        // First get the count of all characters in the string
        int[] freq = new int[26];
        boolean invalid = true;
        for (int i = 0; i < s.length(); i++) {
            // if the character frequency is never greater than 1
            // then the max variance is always 0. (ab == 0, but aab == 1)
            if (++freq[s.charAt(i) - 'a'] > 1) {
                invalid = false;
            }
        }
        if (invalid) return 0;

        // Next we need to compare the number of occurrences between
        // every character in the string so the complexity is O(26*26) worst case
        int maxVariance = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) // if freq == 0 then character does not exist
                continue;

            for (int j = 0; j < 26; j++) {
                if (i == j || freq[j] == 0) // if the same as first character or does not exist
                    continue;

                // Now we have 2 chars to compare
                // iterate over the string compare the count of the first character i
                // with the second character j and decrease their frequency
                int c1_count = 0, c2_count = 0, c1_freq = freq[i], c2_freq = freq[j];
                for (char c: s.toCharArray()) {
                    int curr = c-'a'; // current character in string

                    if (curr == i) {
                        c1_count++;
                        c1_freq--;
                    }
                    else if (curr == j) {
                        c2_count++;
                        c2_freq--;
                    }
                    else {
                        continue; // neither character increased no action required
                    }

                    // if first character count is less than the second character count
                    // and both characters have atleast one character left in the string
                    // then we reset both counts to try a better substring
                    if (c1_count < c2_count &&  c1_freq > 0 && c2_freq > 0) {
                        c1_count = 0; c2_count = 0;
                    }
                    // if second character count is atleast 1 and first character count is
                    // greater than second character count,
                    // check if variance is greater than maxVariance
                    else if (c2_count > 0 && c1_count > c2_count) {
                        maxVariance = Math.max(maxVariance, c1_count - c2_count);
                    }
                    // if both character frequency is 0 then no more variances in string
                    // for the current two characters, so we break out of the loop
                    else if (c1_freq+c2_freq == 0)
                        break;
                }
            }
        }
        return maxVariance;
    }


    public static void main(String[] args) {
        String s = "lripaa";
        System.out.println(largestVariance(s));

    }
}
