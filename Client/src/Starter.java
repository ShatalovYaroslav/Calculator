public class Starter {

    public static void main(String argc[]) {
        for (String str : argc) {
            if (str.compareTo("command") == 0) {
                new ConsoleClient();
                return;
            }
        }
        new DialogClient();
    }
}
