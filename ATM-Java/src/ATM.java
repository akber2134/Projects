import java.util.Scanner;

public class ATM {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        Bank bank = new Bank("Bank of America");

        // creating new user with a savings account
        Account_Holder AccountHolder_A = bank.addAccountHolder("John","Doe","0000");

        //creating a checking account
        Account Account_A = new Account("Checking", AccountHolder_A, bank);
        AccountHolder_A.addAccount(Account_A);
        bank.addAccount(Account_A);

        Account_Holder Current_Account_Holder;
//        final int tries = 0 ;
        while(true){
            Current_Account_Holder =ATM.menu(bank, input);
            ATM.MainMenu(Current_Account_Holder, input);

        }

    }

    private static void MainMenu(Account_Holder current_account_holder, Scanner input) {
        // summary for account holder's account summary
        current_account_holder.printAccountsSummary();
        int pick;
        do{
            System.out.printf("Greetings %s\n", current_account_holder.getFirstName());
            System.out.println("   1) Show account transaction history");
            System.out.println("   2) Withdraw");
            System.out.println("   3) Deposit");
            System.out.println("   4) Transfer");
            System.out.println("   5) Exit");
            System.out.println("");
            System.out.println("Please Pick your option: ");
            pick = input.nextInt();
            if(pick < 1 || pick > 5){
                System.out.println("Please select the correct number");
            }

        }while(pick < 1 || pick > 5);

        switch (pick){
            case 1:
                ATM.showTransactionHistory(current_account_holder, input);
                break;
            case 2:
                ATM.WithdrawAmount(current_account_holder, input);
                break;
            case 3:
                ATM.DepositAmount(current_account_holder, input);
                break;
            case 4:
                ATM.TransferAmount(current_account_holder, input);
                break;
            case 5:
                // gobble up rest of previous input
                input.nextLine();
                break;
        }

        if (pick != 5){
            ATM.MainMenu(current_account_holder, input);
        }
    }

    private static void DepositAmount(Account_Holder current_account_holder, Scanner input) {
        int toAcc;
        double amount;
        double accBal;
        String note;


        //account to deposit to
        do{
            System.out.printf("\nEnter the number (1 - %d) of the account to Deposit to: ", current_account_holder.TotalAccounts());
            toAcc = input.nextInt() - 1; // subtract 1 because we have 0 based index and the user enters from 1.
            if (toAcc < 0 || toAcc>= current_account_holder.TotalAccounts()){
                System.out.println("Invalid account selection, try again.");
            }
        }while(toAcc < 0 || toAcc>= current_account_holder.TotalAccounts());
        accBal = current_account_holder.getAccountBalance(toAcc);

        //get the amount to deposit
        do{
            System.out.printf("\nEnter the amount to deposit (max $%.02f): $",accBal);
            amount= input.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than $0");
            }
//            else if (amount > accBal){
//                System.out.printf("\nAmount must be less than balance of (max $%.02f): $", accBal);
//            }
        } while(amount<0);

        // gobble up rest of previous input
        input.nextLine();

        // take a note
        System.out.println("Enter a note:");
        note = input.nextLine();

        //do the withdraw
        current_account_holder.TransferAmount(toAcc, amount, note);
    }

    private static void WithdrawAmount(Account_Holder current_account_holder, Scanner input) {
        int fromAcc;
        double amount;
        double accBal;
        String note;


        //account to withdraw from
        do{
            System.out.printf("\nEnter the number (1 - %d) of the account to withdraw from: ", current_account_holder.TotalAccounts());
            fromAcc = input.nextInt() - 1; // subtract 1 because we have 0 based index and the user enters from 1.
            if (fromAcc < 0 || fromAcc>= current_account_holder.TotalAccounts()){
                System.out.println("Invalid account selection, try again.");
            }
        }while(fromAcc < 0 || fromAcc>= current_account_holder.TotalAccounts());
        accBal = current_account_holder.getAccountBalance(fromAcc);

        //get the amount to withdraw
        do{
            System.out.printf("\nEnter the amount to withdraw (max $%.02f): $",accBal);
            amount= input.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than $0");
            }
            else if (amount > accBal){
                System.out.printf("\nAmount must be less than balance of (max $%.02f): $", accBal);
            }
        } while(amount<0 || amount> accBal);

        // remove rest of previous input
        input.nextLine();

        // take a note
        System.out.println("Enter a note:");
        note = input.nextLine();

        //do the withdraw
        current_account_holder.TransferAmount(fromAcc, -1*amount, note);

    }

    private static void TransferAmount(Account_Holder current_account_holder, Scanner input) {
        int fromAcc;
        int toAcc;
        double amount;
        double accBal;


        //account to transfer from
        do{
            System.out.printf("\nEnter the number (1 - %d) of the account to transfer from: ", current_account_holder.TotalAccounts());
            fromAcc = input.nextInt() - 1; // subtract 1 because we have 0 based index and the user enters from 1.
            if (fromAcc < 0 || fromAcc>= current_account_holder.TotalAccounts()){
                System.out.println("Invalid account selection, try again.");
            }
        }while(fromAcc < 0 || fromAcc>= current_account_holder.TotalAccounts());
        accBal = current_account_holder.getAccountBalance(fromAcc);

        // account to transfer to
        do{
            System.out.printf("\nEnter the number (1 - %d) of the account to transfer to: ", current_account_holder.TotalAccounts());
            toAcc = input.nextInt() - 1; // subtract 1 because we have 0 based index and the user enters from 1.
            if (toAcc < 0 || toAcc>= current_account_holder.TotalAccounts()){
                System.out.println("Invalid account selection, try again.");
            }
        }while(toAcc < 0 || toAcc>= current_account_holder.TotalAccounts());

        //get the amount to transfer
        do{
            System.out.printf("\nEnter the amount to transfer (max $%.02f): $",accBal);
            amount= input.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than $0");
            }
            else if (amount > accBal){
                System.out.printf("\nAmount must be less than balance of (max $%.02f): $", accBal);
            }
        } while(amount<0 || amount> accBal);

        // Transfer the amount
        current_account_holder.TransferAmount(fromAcc, -1*amount, String.format("Transfer to account %s ", current_account_holder.getAccountID(toAcc)));
        current_account_holder.TransferAmount(toAcc, amount, String.format("Transfer to account %s ", current_account_holder.getAccountID(fromAcc)));


    }

    private static void showTransactionHistory(Account_Holder current_account_holder, Scanner input) {
        int acc;
        do{
            System.out.printf("\nEnter the number (1 - %d) of the account: ", current_account_holder.TotalAccounts());
            acc = input.nextInt() - 1; // subtract 1 because we have 0 based index and the user enters from 1.
            if (acc < 0 || acc>= current_account_holder.TotalAccounts()){
                System.out.println("Invalid account selection, try again.");
            }
        }while(acc < 0 || acc>= current_account_holder.TotalAccounts());

        current_account_holder.printTransactionHistory(acc);
    }

    /**
     * Prints the menu for the ATM
     * @param bank
     * @param input
     * @return authenticated user
     */
    private static Account_Holder menu(Bank bank, Scanner input) {
        String ID;
        String pin;
        Account_Holder Auth_AccountHolder;

        do{
            System.out.printf("\n\n Welcome to the %s \n\n", bank.getName());
            System.out.print("\nEnter user ID:");
            ID = input.nextLine();
            System.out.print("\nEnter pin:");
            pin = input.nextLine();

            Auth_AccountHolder = bank.Log_In(ID,pin);
            if (Auth_AccountHolder == null){
                System.out.println("ID/Pin is incorrect, please check both");
            }


        }while(Auth_AccountHolder == null);
        return Auth_AccountHolder;
    }
}
