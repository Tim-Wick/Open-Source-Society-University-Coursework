import java.util.*;

public class GuitarString {

    private Queue<Double> ringBuffer = new LinkedList<>();
    private Random r = new Random();
    public static final double DECAY_FACTOR = .996;

    // Constructor when class is given a specific frequency
    // Initializes the ringBuffer to appropriate length based on given frequency
    public GuitarString(double frequency) {
        int capacity = (int)Math.round(StdAudio.SAMPLE_RATE/frequency);
        if (frequency <=0 || capacity < 2) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < capacity; i++) {
            ringBuffer.add(0.0);
        }
    }

    // Constructor when class is given an array of values
    public GuitarString(double[] init) {
        if (init.length < 2) {
            throw new IllegalArgumentException();
        }
        for (double v : init) {
            ringBuffer.add(v);
        }
    }

    // Adds value to buffer between -.5 (inclusive) and .5 (exclusive) and removes first value, effectively replacing all values
    public void pluck() {
        for (int i = 0; i < ringBuffer.size(); i++) {
            ringBuffer.add(r.nextDouble() - .5);
            ringBuffer.remove();
        }
    }

    // Replaces last value with an average of the first two values and removes the first value
    public void tic() {
        double first = ringBuffer.remove();
        double second = ringBuffer.peek();
        ringBuffer.add((first + second) / 2 * DECAY_FACTOR);
    }

    // Returns the first value of the buffer
    public double sample() {
        return ringBuffer.peek();
    }

}
