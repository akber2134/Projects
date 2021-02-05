import java.util.Date;

public class Transaction {
    // amount of the transaction
    private double transaction_amount;
    //tme when the transaction took place
    private Date timestamp;
    // a note for why this transaction occurred
    private String note;
    // account that requested the transaction
    private Account transaction_account;

    /**
     * Creating new transaction
     * @param amount
     * @param account
     */
    public Transaction(double amount, Account account){
        this.transaction_account = account;
        this.transaction_amount = amount;
        this.timestamp = new Date();
        this.note = "";

    }

    /**
     * Creating new transaction with a note
     * @param amount
     * @param account
     * @param note
     */
    public Transaction(double amount, Account account, String note){
        this(amount,account);
        this.note = note;

    }

    public double getAmount() {
        return this.transaction_amount;
    }

    public String getSummaryLine() {
        if(this.transaction_amount>=0){
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.transaction_amount, this.note);
        }
        else{
            return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), -this.transaction_amount, this.note);
        }
    }
}
