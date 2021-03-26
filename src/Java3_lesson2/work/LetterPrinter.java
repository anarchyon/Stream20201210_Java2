package Java3_lesson2.work;

import java.util.*;

public class LetterPrinter {
    private final Object monitor = new Object();
    private final int count;
    private final List<Character> args;
    private Character currentLetter;

    public LetterPrinter(int count, Character... characters) {
        this.count = count;
        args = new ArrayList<>();
        Collections.addAll(args, characters);
        currentLetter = args.get(0);
    }

    public List<Character> getArgs() {
        return args;
    }

    public void printLetter(int indexForThread, char letterForThread) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < count; i++) {
                    while (currentLetter != letterForThread) {
                        monitor.wait();
                    }
                    System.out.print(letterForThread);
                    int currentIndex = (indexForThread + 1) % args.size();
                    currentLetter = args.get(currentIndex);
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
