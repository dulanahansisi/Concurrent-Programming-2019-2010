public class CurrentBankAccount implements BankAccount {

    private  String accountHolder;
    private int accountNumber;
    private  String cId;
    private static int amount;
    private  Statement statement;
    private Transaction trasaction;

    private static int balance;
    private boolean isOverDrawn;

    public  CurrentBankAccount(String CID, int amount,Statement statement)
    {

        this.cId = CID;
        this.amount = amount;
        trasaction = new Transaction(CID, amount);
        this.statement = statement;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String getAccountHolder() {
        return accountHolder;
    }

    @Override
    public synchronized void deposit(Transaction t) {

        balance += t.getAmount();
        statement.addTransaction(t.getCID(),t.getAmount(),balance);
        // notify all other threads
        //System.out.println("Deposit" +t.getAmount()+" balance: "+balance);
        System.out.println("Money is depositted. "+ t.toString() );
        notifyAll();

    }

    @Override
    public synchronized void withdrawal(Transaction t) {
        //System.out.println("withdraw:" +t.getAmount());
        if(!isOverdrawn()&&(balance>=t.getAmount()))
        {
            balance -= t.getAmount();
            statement.addTransaction(t.getCID(),t.getAmount(),balance);
            System.out.println("Money is withdrawn. "+ t.toString() );
            //System.out.println("withdraw" +t.getAmount()+" balance: "+balance);
        }else
        {

        }
        // notify all other threads
        notifyAll();
    }

    @Override
    public boolean isOverdrawn() {

        if(balance==0)
        {
            isOverDrawn = true;
        }else
        {
            isOverDrawn = false;
        }
        return isOverDrawn;
    }

    @Override
    public void printStatement() {
        statement.print();
    }

    public String getCID() {
        return cId;
    }

    public void  logMessage()
    {


    }

}
