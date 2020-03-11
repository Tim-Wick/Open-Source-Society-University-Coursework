public class SpaceNeedle {
    public static final int SIZE = 2;


    public static void main(String[] args) {
        spire();
        printTop();
        printUnderside();
        spire();
        printTower();
        printTop();
    }

    public static void spire() {
        for (int i = 1; i <= SIZE; i++) {
            for (int s = 1; s <= SIZE; s ++) {
                System.out.print("   ");
            }
            System.out.println("||");
        }
    }

    public static void printTop() {
        for (int s = 1; s < SIZE; s++) {
            System.out.print("   ");
        }
        System.out.println("__/||\\__");

        for (int i = 1; i < SIZE; i++) {
            int j = i * 3;
            for (int s = 1; s < SIZE - i; s++) {
                System.out.print("   ");
            }
            System.out.print("__/");
            for (int k = 1; k <= j; k++) {
                System.out.print(":");
            }
            System.out.print("||");
            for (int k = 1; k <= j; k++) {
                System.out.print(":");
            }
            System.out.println("\\__");
        }

        System.out.print("|");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print("\"\"\"\"\"\"");
        }
        System.out.println("|");
    }

    public static void printUnderside() {
        int start = (SIZE * 2) + (SIZE - 1);
        for (int i = 1; i <= SIZE; i++) {
            for (int k = 1; k < i; k++) {
                System.out.print("  ");
            }
            System.out.print("\\_");
            for (int j = 1; j <= start; j++) {
                System.out.print("/\\");
            }
            start -= 2;
            System.out.println("_/");
        }
    }

    public static void printTower() {
        for (int x = 1; x <= (SIZE * SIZE); x++) {
            for (int y = 1; y <= (SIZE * 2) + 1; y++) {
                System.out.print(" ");
            }
            for (int i = 1; i <= 2; i++) {
                System.out.print("|");
                for (int j = 1; j <= SIZE - 2; j++) {
                    System.out.print("%");
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }
}
