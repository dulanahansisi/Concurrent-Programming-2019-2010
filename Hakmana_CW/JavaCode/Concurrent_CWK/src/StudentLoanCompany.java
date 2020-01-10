import java.util.stream.IntStream;

public class StudentLoanCompany extends Thread {
    private ThreadGroup threadGroup;
    private CurrentBankAccount currentBankAccount;

    public StudentLoanCompany(ThreadGroup threadGroup, CurrentBankAccount currentBankAccount)
    {
        super(threadGroup,"loanCompany");
        this.threadGroup = threadGroup;
        this.currentBankAccount = currentBankAccount;
    }

    @Override
    public void run() {
        System.out.println("Loan company logged in");
        IntStream.rangeClosed(1, 3).forEach(attemptId -> {
            int randomSleepTime = RandomNumbersGenerator.getOneInt(1000, 2000);
            try {
                Transaction transaction = new Transaction("LoanCompany",attemptId*1000);
                // call the method that tries to refill papers
                this.currentBankAccount.deposit(transaction);

                // sleep the current thread for a random amount of time
                sleep(randomSleepTime);
            } catch (InterruptedException exception) {
                System.out.println(exception);
            }
        });

    }
}
