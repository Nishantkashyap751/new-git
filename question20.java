public class question20 {
 
    private volatile boolean isRunning = true;

    public void stopRunning() {
        isRunning = false;
        System.out.println("Main thread has set isRunning to false.");
    }

    public void startLoop() {
        while (isRunning) {
        }
        System.out.println("Worker thread detected the change and stopped.");
    }

    public static void main(String[] args) throws InterruptedException {
        question20 example = new question20();

        Thread workerThread = new Thread(() -> example.startLoop());
        workerThread.start();

        Thread.sleep(100);

        example.stopRunning();

        workerThread.join();
        System.out.println("Program finished.");
    }
}


