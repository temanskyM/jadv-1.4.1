import java.util.concurrent.ConcurrentLinkedQueue;

public class Operator implements Runnable {
    final private long DELAY = 3000L;

    final private ConcurrentLinkedQueue<String> queue;

    public Operator(ConcurrentLinkedQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (true) {
                String call = queue.poll();
                if (call != null) {
                    System.out.println("Оператор: " + Thread.currentThread().getName() + " обрабатываю: " + call);
                    Thread.sleep(DELAY);
                    System.out.println("Оператор: " + Thread.currentThread().getName() + " обработал: " + call);
                } else {
                    if (Thread.interrupted())
                        break;
                    Thread.sleep(DELAY);
                }
            }
        } catch (InterruptedException e) {
        }
    }
}
