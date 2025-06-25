import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//ASSUMPTIONS:
//Initial Balance = 1000/-
//Deposit amt cannot be >= 100
//Withdraw amount should be multiple of 100
//also tracks transactions history with time
class BankAccount{
    private double balance=1000;
    private ArrayList<String> transactions;
    BankAccount(){
        this.transactions = new ArrayList<>();
    }
    void deposit(double amt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = LocalDateTime.now().format(formatter);
        transactions.add(time + " - Deposited: ₹" + amt);
        balance+=amt;
    }
    boolean withdraw(double amt){
        if(balance>=amt) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String time = LocalDateTime.now().format(formatter);
            transactions.add(time + " - Withdrew: ₹"+amt);
            balance-=amt;
            return true;
        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String time = LocalDateTime.now().format(formatter);
            transactions.add(time + " - Failed Withdraw Attempt: ₹"+amt);
            return false;
        }
    }
    double getBalance(){
        return balance;
    }
    ArrayList<String> getTransactionHistory(){
        return transactions;
    }
}
class ATM{
    private final BankAccount bankAccount;
    ATM(BankAccount bankAccount){
        this.bankAccount=bankAccount;
    }
    void deposit(double amt){
        this.bankAccount.deposit(amt);
        System.out.println("AMOUNT DEPOSITED SUCCESSFULLY!!");
    }
    void withdraw(double amt){
        if(this.bankAccount.withdraw(amt))
                System.out.println("WITHDRAW SUCCESSFUL!!");
        else
            System.out.println("Insufficient Balance!!");
    }
    void checkBalance(){
        double balance = this.bankAccount.getBalance();
        System.out.println("Your Current Balance: "+balance);
    }
    void showTransactionHistory(){
        ArrayList<String> logs = this.bankAccount.getTransactionHistory();
        if(logs.isEmpty())
            System.out.println("No Transactions Done!!");
        else {
            for (String s:logs) {
                System.out.println(s);
            }
        }
    }
}
class AtmRunner{
    Scanner sc = new Scanner(System.in);
    BankAccount bankAcc = new BankAccount();
    ATM atm = new ATM(bankAcc);
    void start(){
        System.out.println("===== WELCOME TO THE ATM =====");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. See Transaction History");
        System.out.println("5. Exit");
        System.out.println("Please Choose an Option: ");
        int choice  = sc.nextInt();
        switch (choice){
            case 1:
                System.out.println("Please Enter the Amount to Deposit: ");
                double deposit_amt = sc.nextDouble();
                if(deposit_amt<100)
                    System.out.println("Please Deposit above 100/-");
                else
                    atm.deposit(deposit_amt);
                start();
                break;
            case 2:
                System.out.println("Please Enter the Amount to Withdraw: ");
                double withdraw_amt = sc.nextDouble();
                if(withdraw_amt % 100 != 0)
                    System.out.println("Enter the amount in multiple of 100!!");
                else
                    atm.withdraw(withdraw_amt);
                start();
                break;
            case 3:
                atm.checkBalance();
                start();
                break;
            case 4:
                atm.showTransactionHistory();
                start();
                break;
            case 5:
                System.out.println("GOODBYE! Have a Nice Day!!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Input");
                start();
        }

    }
}
public class atm_interface {
    public static void main(String[] args) {
        AtmRunner atmRunner = new AtmRunner();
        atmRunner.start();
    }
}
