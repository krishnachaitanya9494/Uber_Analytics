class tasks extends Thread {
    public void run() {
        for (int i = 0; i <= 5; i++) {
            System.out.println("thread 1 is running  " + i);
        }

    }
}

class tasks2 extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(("thread 2 is running " + i));
        }
    }
}

public class main {

    public static void main(String[] args) {
        tasks t1 = new tasks();
        tasks2 t2 = new tasks2();
        t1.start();
        t2.start();
    }

}