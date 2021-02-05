import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Account_Holder {
    // First Name of the Account Holder
    private String First_Name;
    // Last Name of the Account Holder
    private String Last_Name;
    // Unique ID of the Account Holder
    private String ID;
    // MD5 hash of PIN of the Account Holder
    private byte PIN[];
    // List of accounts for the Account Holder
    private ArrayList<Account> accounts;

    /**
     * Creating New User
     * @param FN
     * @param LN
     * @param pin
     * @param bank
     */
    public Account_Holder(String FN, String LN, String pin, Bank bank){
        this.First_Name=FN;
        this.Last_Name=LN;

        //using MD5 hashing algorithm for hashing pin
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.PIN = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error Found 'NO SUCH ALGORITHM'");
            e.printStackTrace();
            System.exit(1);
        }

        // getting the unique ID
        this.ID = bank.getNewAccountHolderID();

        // create a list of accounts
        this.accounts= new ArrayList<Account>();

        //log
        System.out.printf("New User %s, %s with ID %s created",LN, FN, this.ID);

    }

    /**
     * Adding the account for the account holder
     * @param account
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public String getID() {
        return this.ID;
    }

    /**
     * Pin Validation for the log in function
     * @param pin
     * @return true if pin matches or false if pin doesnt match
     */
    public boolean pinValidation(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()),this.PIN);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error Found 'NO SUCH ALGORITHM'");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    public String getFirstName() {
        return this.First_Name;
    }

    public void printAccountsSummary() {
        System.out.printf("\n\n %s Accounts Summary\n", this.First_Name);
        for (int acc = 0; acc< accounts.size(); acc++){
            System.out.printf("%d) %s\n", acc + 1, this.accounts.get(acc).getSummaryLine());
        }
        System.out.println();

    }

    public int TotalAccounts() {
        return this.accounts.size();
    }

    public void printTransactionHistory(int accountIndex) {
        this.accounts.get(accountIndex).printTransactionHistory();
    }

    public double getAccountBalance(int AccIndex) {
        return this.accounts.get(AccIndex).getBalance();
    }

    public String getAccountID(int acc) {
        return this.accounts.get(acc).getID();
    }

    public void TransferAmount(int index, double amount, String note) {
        this.accounts.get(index).addTransaction(amount, note);
    }
}
