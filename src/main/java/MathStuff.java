import java.util.*;

/**
 * Library with static mathematical functions.
 * Martina Markova, 1942026, 21.05.2024
 */
public abstract class MathStuff {

    /**
     * Returns exponentiation, taking care of overflow.
     *
     * @param a the base
     * @param b the exponent
     * @pre {@code 0 <= a && 0 <= b}
     * @return {@code a ^ b} if {@code a ^ b <= Integer.MAX_VALUE}
     *     else {@code Long.MAX_VALUE}
     * @throws IllegalArgumentException if precondition violated
     */

    public static long power(int a, int b) throws IllegalArgumentException {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("power: negative argument");
        }
        // 0 <= a && 0 <= b
        long x = a; // see invariant
        int k = b; // see invariant
        long result = 1L; // see invariant

        // invariant: 0 <= k <= b && result * x^k == a^b
        while (k != 0) {
            if (k % 2 == 0) { // even exponent
                if (x <= Integer.MAX_VALUE) {
                    x *= x;
                } else {
                    x = Long.MAX_VALUE;
                }
                k /= 2;
            } else { // odd exponent
                if (result <= Integer.MAX_VALUE && x <= Integer.MAX_VALUE) {
                    result *= x;
                } else {
                    result = Long.MAX_VALUE;
                }
                k -= 1;
            }
            // invariant holds again, k has decreased
        }
        // k == 0, hence result == a^b

        if (result > Integer.MAX_VALUE) {
            return Long.MAX_VALUE;
        }
        return result;
    }

    /**
     * Record containing a base and an exponent.
     *
     * @inv {@code 0 <= base && 0 <= exponent}
     */
    public static class Power { // BEGIN RECORD TYPE

        /** The base. */
        public int base;

        /** The exponent. */
        public int exponent;

        /**
         * Constructs a Power with given base and exponent.
         *
         * @param base     the base
         * @param exponent the exponent
         * @pre {@code 0 <= base && 0 <= exponent}
         * @post {@code \result.base == base && \result.exponent == exponent}
         */
        public Power(int base, int exponent) {
            this.base = base;
            this.exponent = exponent;
        }

    } // END RECORD TYPE

    /**
     * Returns exponentiation.
     *
     * @param p the base and exponent
     * @pre {@code p != null}
     * @return {@code power(p.base, p.exponent)}
     * @throws IllegalArgumentException if precondition violated
     */
    public static long power(Power p) throws IllegalArgumentException {
        return power(p.base, p.exponent);
    }

    /**
     * Writes a number as a power with maximal exponent.
     *
     * @param n the number to 'powerize'
     * @pre {@code 2 <= n}
     * @post {@code n == power(\result) &&
     *     (\forall int b, int e;
     *      2 <= b && 1 <= e && n == b ^ e;
     *      e <= \result.exponent)}
     * @return power decomposition of {@code n} with maximal exponent
     * @throws IllegalArgumentException if precondition violated
     */

    public static Power powerize(int n) throws IllegalArgumentException {
        if (n < 2) {
            throw new IllegalArgumentException("The number must be at least 2.");
        }
        Power result = new Power(0, 0); //initialize variable
        for (int i = 1; i <= 32; i++) {
            int current = binarySearch(n, i); 
            if (current > 0) {
                result = new Power(current, i);
            }
        }
        return result;
    }

    /**
     * Implementation of binary search.
     * @param n - the given number
     * @param exp - desired exponent
     * @return - if such a base exists, we return it, otherwise we return -1.
     */
    public static int binarySearch(int n, int exp) {
        long answer; 
        int left = 0; 
        int right = n; 
        int mid;
        while (left <= right) {
            mid = left +  (right - left) / 2; 
            answer = power(mid, exp); 
            if (answer == n) {
                return mid;
            } else if (answer > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } 
        return -1;
    }

}
