
public class Guitar37 implements Guitar {
    public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
    public static final int KEYBOARD_LENGTH = KEYBOARD.length();
    private GuitarString[] concert = new GuitarString[37];
    private int time = 0;

    // Constructor for class
    // Loops concert array and adds strings
    public Guitar37() {
        for (int i = 0; i < KEYBOARD_LENGTH; i++) {
            concert[i] = new GuitarString(440 * Math.pow(2, (i - 24.0) / 12.0));
        }
    }

    // Plays note from given int
    public void playNote(int pitch) {
        if (pitch >= -24 && pitch <= 12) {
            concert[pitch + 24].pluck();
        }
    }

    // Tests if given char is included in the keyboard
    public boolean hasString(char string) {
        return KEYBOARD.indexOf(string) != -1;
    }

    // Plays note from given key
    // Throws IllegalArgumentException if given a key that does not exist in the keyboard
    public void pluck(char string) {
        if (!hasString(string)) {
            throw new IllegalArgumentException();
        } else {
            concert[KEYBOARD.indexOf(string)].pluck();
        }
    }

    // Returns a total of samples from all of the strings
    public double sample() {
        double sampleSum = 0;
        for (int i = 0; i < KEYBOARD_LENGTH; i++) {
            sampleSum += concert[i].sample();
        }
        return sampleSum;
    }

    // tics all of the strings
    public void tic() {
        for (int i = 0; i < KEYBOARD_LENGTH; i++) {
            concert[i].tic();
        }
        time++;
    }

    // Returns the number of times tic() has been called
    public int time() {
        return time;
    }

}
