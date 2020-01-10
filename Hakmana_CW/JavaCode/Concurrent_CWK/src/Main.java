import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String []args)
    {
        // declaring and initializing the two thread groups technician and student
        ThreadGroup living = new ThreadGroup("Living");
        ThreadGroup nonLiving = new ThreadGroup("Non living");

        Statement statement = new Statement("Dulana",1004354);
        CurrentBankAccount currentBankAccount = new CurrentBankAccount("",0,statement);
        

        Student student = new Student(living,currentBankAccount);
        Grandmother granny = new Grandmother(living,currentBankAccount);
        University university = new University(nonLiving,currentBankAccount);
        StudentLoanCompany loanCompany = new StudentLoanCompany(nonLiving,currentBankAccount);

        student.start();
        granny.start();
        university.start();
        loanCompany.start();


        try {
            student.join();
            granny.join();
            university.join();
            loanCompany.join();
        } catch (InterruptedException exception) {
            System.out.println(exception);
        }
        statement.print();
    }
}
