public class Song {
    public static void main(String[] args) {
    fly();
    spider();
    bird();
    cat();
    dog();
    log();
    ending();
    }

    public static void flyDie() {
        System.out.println("I don't know why she swallowed that fly,");
        System.out.println("Perhaps she'll die.");
        System.out.println();
    }

    public static void spiderFly() {
        System.out.println("She swallowed the spider to catch the fly,");
        flyDie();
    }

    public static void birdSpider() {
        System.out.println("She swallowed the bird to catch the spider,");
        spiderFly();
    }

    public static void catBird() {
        System.out.println("She swallowed the cat to catch the bird,");
        birdSpider();
    }

    public static void dogCat() {
        System.out.println("She swallowed the dog to catch the cat,");
        catBird();
    }

    public static void logDog() {
        System.out.println("She swallowed the log to catch the dog");
        dogCat();
    }

    public static void fly() {
        System.out.println("There was an old woman who swallowed a fly.");
        flyDie();
    }

    public static void spider() {
        System.out.println("There was an old woman who swallowed a spider,\nThat wriggled and iggled and jiggled inside her.");
        spiderFly();
    }

    public static void bird() {
        System.out.println("There was an old woman who swallowed a bird,\nHow absurd to swallow a bird.");
        birdSpider();
    }

    public static void cat() {
        System.out.println("There was an old woman who swallowed a cat,\nImagine that to swallow a cat.");
        catBird();
    }

    public static void dog() {
        System.out.println("There was an old woman who swallowed a dog,\nWhat a hog to swallow a dog.");
        dogCat();
    }

    public static void log() {
        System.out.println("There was an old woman who swallowed a log,\nHow bog to swallow a log");
        logDog();
    }

    public static void ending() {
        System.out.println("There was an old woman who swallowed a horse,\nShe died of course.\n");
    }

}
