package guhar4k.multithreading.task2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class FizzBuzz {
    private int num;
    private int currentNum;
    private AtomicBoolean isCurrentNumberPrinted;
    private CountDownLatch fizzOrBuzzCd;
    private CountDownLatch numberCd;
    private CountDownLatch allTaskDoneCd;

    public FizzBuzz(int num) {
        this.num = num;
        currentNum = 1;
        isCurrentNumberPrinted = new AtomicBoolean(false);
        fizzOrBuzzCd = new CountDownLatch(1);
        numberCd = new CountDownLatch(2);
        allTaskDoneCd = new CountDownLatch(1);
    }

    public void fizz() {
        try {
            fizzOrBuzzCd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!isCurrentNumberPrinted.get() && currentNum % 3 == 0) {
            System.out.print("Fizz");
            isCurrentNumberPrinted.set(true);
        }
        numberCd.countDown();
    }

    public void buzz() {
        try {
            fizzOrBuzzCd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!isCurrentNumberPrinted.get() && currentNum % 5 == 0) {
            System.out.print("Buzz");
            isCurrentNumberPrinted.set(true);
        }
        numberCd.countDown();
    }

    public void fizzBuzz() {
        if ((currentNum % 3 == 0) && (currentNum % 5 == 0)) {
            System.out.print("FizzBuzz");
            isCurrentNumberPrinted.set(true);
        }
        fizzOrBuzzCd.countDown();
    }

    public void number() {
        try {
            numberCd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!isCurrentNumberPrinted.get()) System.out.print(currentNum);
        allTaskDoneCd.countDown();
    }

    public boolean nextNum() {
        try {
            allTaskDoneCd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (currentNum < num) {
            currentNum++;
            System.out.print(", ");
            isCurrentNumberPrinted.set(false);
            fizzOrBuzzCd = new CountDownLatch(1);
            numberCd = new CountDownLatch(2);
            allTaskDoneCd = new CountDownLatch(1);
            return true;
        }
        return false;
    }
}
