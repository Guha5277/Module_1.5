package guhar4k.multithreading;

public class Main {
    public static void main(String[] args) {
        Foo foo = new Foo();

        new Thread(foo::third).start();

        new Thread(foo::second).start();

        new Thread(foo::first).start();
    }
}
