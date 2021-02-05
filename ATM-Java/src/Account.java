import java.util.ArrayList;

public class Account {
    // name of the account
    private String account_name;
    // value of the balance in the account
    private  double account_balance;
    //Unique Id for the Account
    private String ID;
    // Account holder information for the account
    private Account_Holder account_holder;
    // The transactions list on the account
    private ArrayList<Transaction> account_transactions;

    /**
     * Creating New Account
     * @param name
     * @param AH
     * @param bank
     */
    public Account(String name, Account_Holder AH, Bank bank){
        this.account_name = name;
        this.account_holder = AH;
        this.ID = bank.getNewAccountID();
        this.account_transactions = new ArrayList<Transaction>();
        this.account_balance = this.getBalance();
//
//        // the account when created adds itself to the account list in account holder and bank
//        AH.addAccount(this); // if accounts in Account holder was public we can simply use AH.accounts.add(this);
//        bank.addAccount(this);
    }


    public String getID() {
        return this.ID;
    }

    public String getSummaryLine() {
        this.account_balance = this.getBalance();
        if(this.account_balance >= 0){
            return  String.format("%s : $%.02f : %s", this.ID, this.account_balance, this.account_name);
        }
        else{
            return  String.format("%s : $%(.02f) : %s", this.ID, this.account_balance, this.account_name);
        }
    }

    public double getBalance() {
        double balance=0;
        for( Transaction t: this.account_transactions){
            balance += t.getAmount();
        }
        return balance;
    }

    public void printTransactionHistory() {
        System.out.printf("\nTransaction History for account %s\n", this.ID);
        for( int t = this.account_transactions.size()-1 ; t>=0; t--){
            System.out.println(this.account_transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    public void addTransaction(double amount, String note) {
        Transaction newTransaction = new Transaction(amount,this, note);
        this.account_transactions.add(newTransaction);
    }
}
