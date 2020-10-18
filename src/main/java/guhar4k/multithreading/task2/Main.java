package guhar4k.multithreading.task2;

public class Main {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(100);

        Thread fizzBuzzThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                fizzBuzz.fizzBuzz();
            }
        }, "FizzBuzzThread");

        Thread fizzThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                fizzBuzz.fizz();
            }
        }, "FizzThread");

        Thread buzzThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                fizzBuzz.buzz();
            }
        }, "BuzzThread");

        Thread numberThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                fizzBuzz.number();
            }
        }, "NumberThread");

        fizzBuzzThread.start();
        fizzThread.start();
        buzzThread.start();
        numberThread.start();

        while (fizzBuzz.nextNum()) {
        }

        fizzBuzzThread.interrupt();
        fizzThread.interrupt();
        buzzThread.interrupt();
        numberThread.interrupt();
    }
}
