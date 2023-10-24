package practice;


/*
Simple approach. We can do this as we would normally add two numbers on paper.
Start iterating both strings in reverse order.
As we are looking at integer from each string we add them
    From the sum we will separate the number we want to append to sum and the carry
    caryy = sum / 10
    num to append = sum % 10
we keep doing this till we have nums left in any of both the strings and we need to make sure we also take care of the carry that we
might get at the end in some cases.

We will be appending in reverse order so we need to return the reverse of the stringbuilder.

O(n) n --> len of bigger string
 */
public class AddStrings {

    public String addStrings(String num1, String num2) {

        StringBuilder sb = new StringBuilder();
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;

        int carry = 0;

        // iterate on numbers from right to left.
        for(int i = len1, j = len2; i >= 0 || j >=0 || carry == 1; i--, j--) {

            // this condition to add 0 if we run out on numbers on left
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            // add numbers and carry if we get any from previous calculation
            int sum = n1 + n2 + carry;
            // calculate carry
            carry = sum / 10;
            // append units place of sum to output string
            sb.append(sum%10);

        }
        // we have appended from right to left so reverse
        return sb.reverse().toString();
    }
}
