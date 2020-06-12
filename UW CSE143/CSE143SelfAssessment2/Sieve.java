import java.util.*;

public class Sieve {

    // Sets up class variables
    Queue<Integer> primeQueue = new LinkedList<>();
    int max;

    // Constructor for the class
    // No inputs needed or variables to set so it is empty
    Sieve() {
    }

    // Computes primes up to the given n
    // Throws IllegalArgumentException if n < 2
    public void computeTo(int n) {
        // Sets the max equal to the given n
        max = n;

        // Handles exceptions for if the given argument is less than two
        if (n < 2) {
            throw new IllegalArgumentException();
        }

        // Initializes the queues
        primeQueue.clear();
        Queue<Integer> initialQueue = new LinkedList<>();

        // Fills the initialQueue with values
        // Skips over even numbers since all evens are multiples of 2 and therefore not prime
        // We know 2 is prime and will always be included so we can add that to the prime queue
        primeQueue.add(2);
        for (int i = 3; i < n; i += 2) {
            initialQueue.add(i);
        }

        // Initializes variable to use when looping through the queue
        int nextNum;

        // Loops the initialQueue, processing the values into the primeQueue
        do {
            nextNum = initialQueue.remove();
            // Add the next number to the from
            primeQueue.add(nextNum);

            // Remove multiples
            Iterator<Integer> queueIterator = initialQueue.iterator();
            while (queueIterator.hasNext()) {
                int itrNum = queueIterator.next();
                if (itrNum % nextNum == 0) {
                    queueIterator.remove();
                }
            }
        // Loops while the nextNum is less than the square root of the given max value
        } while (nextNum <= Math.sqrt(n));
        // Transfer remaining to primeQueue
        primeQueue.addAll(initialQueue);
    }

    // Reports the primes by printing them
    // Throws IllegalStateException if no call to computeTo has been made
    public void reportResults() {
        computeToCalled();
        // Loops and print the numbers
        // Every 12, enter a linebreak
        int x = 0;
        for (Integer primeNumber : primeQueue) {
            System.out.print(primeNumber + " ");
            x++;
            if (x == 12) {
                System.out.println();
                x = 0;
            }
        }
    }

    // Reports the last value of n that was used with computeTo
    // Throws IllegalStateException if no call to computeTo has been made
    public int getMax() {
        computeToCalled();
        return max;
    }

    // Reports the number of primes that were found on the last call with computeTo
    // Throws IllegalStateException if no call to computeTo has been made
    public int getCount() {
        computeToCalled();
        // Returns size of ArrayList created from the queue
        return (new ArrayList<>(primeQueue)).size();
    }

    // Helper function to help others determine if computeTo has been called yet
    private void computeToCalled() {
        if (primeQueue.isEmpty()) {
            throw new IllegalStateException();
        }
    }
}
