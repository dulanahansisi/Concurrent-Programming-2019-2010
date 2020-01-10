import java.util.stream.IntStream;

public class Student extends Thread {

    private ThreadGroup threadGroup;
    private CurrentBankAccount currentBankAccount;

    public Student(ThreadGroup threadGroup, CurrentBankAccount currentBankAccount)
    {
        super(threadGroup,"Student");
        this.threadGroup = threadGroup;
        this.currentBankAccount = currentBankAccount;
    }

    @Override
    public void run() {
        System.out.println("Student logged in");
        IntStream.rangeClosed(1, 5).forEach(attemptId -> {
            int randomSleepTime = RandomNumbersGenerator.getOneInt(1000, 2000);
            try {
                Transaction transaction = new Transaction("Student",attemptId*1000);
                this.currentBankAccount.withdrawal(transaction);
                // sleep the current thread for a random amount of time
                sleep(randomSleepTime);
            } catch (InterruptedException exception) {
                System.out.println(exception);
            }
        });
    }
}
