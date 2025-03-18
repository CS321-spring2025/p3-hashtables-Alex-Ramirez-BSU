//FIRST CHECKPOINT REQUIRED

public class TwinPrimeGenerator {

    //To Verify If Number Is Prime
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    //To Generate Twin Primes Within The Given Range
    public static int generateTwinPrime(int min, int max) {
        //Iterate Through Numbers In The Range [min, max]
        for (int i = min; i <= max - 2; i++) {
            // Check If i and i + 2 Are Both Primes
            if (isPrime(i) && isPrime(i + 2)) {
                //Return The Larger Prime Of The Twin Prime Pair
                return i + 2;
            }
        }
        //Return -1 if no twin primes are found in the range
        return -1;
    }
}