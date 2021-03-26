package Java3_lesson2.work;

public class Test1 {
    private final Object monitor = new Object();

    public static void main(String[] args) {
        LetterPrinter letterPrinter = new LetterPrinter(5, 'A', 'B', 'C', 'D', 'E', 'F', '3', 'z');
        for (int i = 0; i < letterPrinter.getArgs().size(); i++) {
            int currentIndex = i;
            char currentLetter = letterPrinter.getArgs().get(i);
            new Thread(() -> letterPrinter.printLetter(currentIndex, currentLetter)).start();
        }
    }
}
