package guhar4k.multithreading;

public class Foo {
    private boolean isFirstCompleted;
    private boolean isSecondCompleted;

    public synchronized void first() {
        System.out.print("first");
        isFirstCompleted = true;
        notifyAll();
    }

    public synchronized void second() {
        while (!isFirstCompleted){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("second");
        isSecondCompleted = true;
        notifyAll();
    }

    public synchronized void third() {
        while (!isSecondCompleted){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("third");
    }
}
