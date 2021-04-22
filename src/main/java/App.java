import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    final static private int MAX_OPERATOR = 5;
    final static private long TIMEOUT = 60000L;

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < MAX_OPERATOR; i++) {
            executorService.submit(new Operator(queue));
        }

        Thread ATC = new Thread(new ATC(queue));
        ATC.start();

        ATC.join();

        executorService.shutdown();
        try {
            System.out.println("Ожидаем обработки всех заявок...");
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
        }

        System.out.println("Работа окончена");

    }
}
