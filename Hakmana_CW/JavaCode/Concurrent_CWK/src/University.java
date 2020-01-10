import java.util.stream.IntStream;

public class University extends Thread {
    private ThreadGroup threadGroup;
    private CurrentBankAccount currentBankAccount;

    public University(ThreadGroup threadGroup, CurrentBankAccount currentBankAccount)
    {
        super(threadGroup,"University");
        this.threadGroup = threadGroup;
        this.currentBankAccount = currentBankAccount;
    }

    @Override
    public void run() {
        System.out.println("University logged in");
        IntStream.rangeClosed(1, 4).forEach(attemptId -> {
            int randomSleepTime = RandomNumbersGenerator.getOneInt(1000, 2000);
            try {
                Transaction transaction = new Transaction("University",attemptId*1000);
                // call the method that tries to refill papers
                this.currentBankAccount.withdrawal(transaction);

                // sleep the current thread for a random amount of time
                sleep(randomSleepTime);
            } catch (InterruptedException exception) {
                System.out.println(exception);
            }
        });
    }
}
