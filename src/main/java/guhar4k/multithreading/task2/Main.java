package guhar4k.multithreading.task2;

import java.util.concurrent.Phaser;

public class Main {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);
        FizzBuzz fizzBuzz = new FizzBuzz(100);

        Thread fizzBuzzThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                fizzBuzz.fizzBuzz();
                phaser.arriveAndAwaitAdvance();
            }
        }, "FizzBuzzThread");

        Thread fizzThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                fizzBuzz.fizz();
                phaser.arriveAndAwaitAdvance();
            }
        }, "FizzThread");

        Thread buzzThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                fizzBuzz.buzz();
                phaser.arriveAndAwaitAdvance();
            }
        }, "BuzzThread");

        Thread numberThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                fizzBuzz.number();
                phaser.arriveAndAwaitAdvance();
            }
        }, "NumberThread");

        fizzBuzzThread.start();
        fizzThread.start();
        buzzThread.start();
        numberThread.start();

        while (fizzBuzz.nextNum()){
            phaser.arrive();
        }

        fizzBuzzThread.interrupt();
        fizzThread.interrupt();
        buzzThread.interrupt();
        numberThread.interrupt();

        phaser.arriveAndDeregister();
        phaser.forceTermination();
    }
}
