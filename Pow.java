package practice;

/*
INTUITION:
The power is very large. If we multiply the number 'n' times, n can be very large,
so not the best approach.

We can try divide and conquer.
2^10: we can multiply 2 5 times and then multiply result by itself which will give us the answer.
Similarly, we can reduce 2^5 also. But for odd power we need to take care of the extra multiplication.
This way we can do it in log(n) time.

O(log(n))
 */

public class Pow {

    public double myPow(double x, int n) {
        if(n >=0 )
            return solve(x, n);
        else
            return solve(1/x, -1*n);
    }

    public double solve(double x, int n) {
        if(x == 0)
            return 0;
        if(n == 0)
            return 1;

        double res = solve(x, n/2);
        res = res * res;
        if(n%2 == 0)
            return res;
        else
            return x * res;

    }
}
