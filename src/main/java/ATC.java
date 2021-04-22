import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ATC implements Runnable {
    final private int MAX_CALLS = 10;

    final private ConcurrentLinkedQueue<String> queue;

    public ATC(ConcurrentLinkedQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        Random random = new Random();
        for (int i = 0; i < MAX_CALLS; i++) {
            try {
                Thread.sleep(random.nextInt(3) * 500);
                queue.add("Заявка №" + i);
                System.out.println("ATC: пришла Заявка №" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
