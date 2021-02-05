import java.util.ArrayList;
import java.util.Random;

public class Bank {
    // Bank name
    private String name;
    // List of account holders
    private ArrayList<Account_Holder> Account_Holders;
    // list of accounts
    private ArrayList<Account> accounts;

    /**
     * Creating a bank object with the name  passed and the empty list of accounts and holders
     * @param name
     */
    public Bank(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
        this.Account_Holders = new ArrayList<Account_Holder>();
    }

    /**
     * Creating New Unique ID for Account holder
     * @return the account holder ID
     */
    public String getNewAccountHolderID() {
        String ID;
        Random rnd = new Random();
        int ID_len= 10;
        boolean unique_ID;
        // loop until the unique Id is true
        do{

            // random id generator
            ID ="";
            for (int i=0; i<ID_len;i++){
                ID += ((Integer)rnd.nextInt(10)).toString(); //range of ID numbers is from 0 to 9
            }

            //unique ID checker
            unique_ID = false;
            for( Account_Holder AH: this.Account_Holders){
                if(ID.compareTo(AH.getID()) == 0){
                    unique_ID = true;
                    break;
                }
            }

        }while(unique_ID);

        return ID;

    }

    /**
     * Creating new unique account ID
     * @return new account ID
     */
    public String getNewAccountID() {
        String ID;
        Random rnd = new Random();
        int ID_len= 15;
        boolean unique_ID;
        // loop until the unique Id is true
        do{

            // random id generator
            ID ="";
            for (int i=0; i<ID_len;i++){
                ID += ((Integer)rnd.nextInt(10)).toString(); //range of ID numbers is from 0 to 9
            }

            //unique ID checker
            unique_ID = false;
            for( Account account: this.accounts){
                if(ID.compareTo(account.getID()) == 0){
                    unique_ID = true;
                    break;
                }
            }

        }while(unique_ID);

        return ID;
    }

    /**
     * Adding account to the list of accounts for the bank
     * @param account
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public Account_Holder addAccountHolder(String FN, String LN, String pin){
        Account_Holder newAccountHolder = new Account_Holder(FN, LN, pin, this);
        this.Account_Holders.add(newAccountHolder);

        // creating a savings account
        Account newAccount = new Account("Savings", newAccountHolder, this);
        newAccountHolder.addAccount(newAccount);
        this.addAccount(newAccount);

        return newAccountHolder;
    }

    public Account_Holder Log_In(String ID, String pin){
        for( Account_Holder AH: this.Account_Holders){
            if(AH.getID().compareTo(ID) == 0 && AH.pinValidation(pin)){
                return AH;
            }
        }
        // if the user not found or pin doesnt match the login return is null;
        return null;
    }

    public String getName() {
        return this.name;
    }
}
