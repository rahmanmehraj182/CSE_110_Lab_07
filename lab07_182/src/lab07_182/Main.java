/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab07_182;

/**
 *
 * @author Student
 */
import java.util.*;
import java.util.Calendar;
class Account{
    private int id = 0;
    private double balance = 0;
    private double annualInterestRate = 0;
    
    private Calendar dateCreated = Calendar.getInstance();
    
    Account(){
    }
    
    Account(int id, double balance, double annualInt){
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInt;
        dateCreated = Calendar.getInstance();
    } 
    
    public int getId(){
        return this.id;
    }
    
    public Calendar date(){
        return dateCreated;
    }
    public double getBalance(){
        return this.balance;
    }
    public double getAnnInt(){
        return this.annualInterestRate;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setBalance(double balance){
        this.balance = balance;    
    }
    public void setInt(double intr){
        this.annualInterestRate = intr;    
    }
    public double getInterestRate(){
        return this.annualInterestRate/(12*100);
    }
    public double getMonthlyInterestAmount(){
        return this.getBalance() * this.getInterestRate();
    }
    
    public void withdraw(double amnt){
        double nb = this.getBalance();
        if(amnt <= nb){
            nb = nb-amnt;
            this.setBalance(nb);
        }else{
            System.out.println("Insufficient Balance");
        }
    }
    
    public void deposit(double amnt){
        double nb = this.getBalance();
        nb = nb+amnt;
        this.setBalance(nb);
    }
    
    public void display(){
        System.out.println("Account Id: " +this.getId());
        System.out.println("Date Created: " +this.date().getTime());
        System.out.println("Current Balance: " +this.getBalance());
        System.out.println("Annual Interest Rate: " +this.getAnnInt());
        System.out.println("Monthly Interest Amount: " +this.getMonthlyInterestAmount());
       
    }
}

class CheckingAccount extends Account{
    private double overdraft = 50000;
    public void withdraw(double amnt){
        if(amnt <= overdraft && amnt <= super.getBalance()){
            double nb = this.getBalance();
            nb -= amnt;
            this.setBalance(nb);
            this.overdraft -= nb;         
        }   
    }
    
    CheckingAccount(int id, double bal, double intr, double over){
        super.setId(id);
        super.setBalance(bal);
        super.setInt(intr);
        this.overdraft = over;
    }
    
    public void display(){
        System.out.println("This is a Checking Account " );
        super.display();
        System.out.println("Overdraft Limit : " + this.overdraft );
    }  
}

class SavingsAccount extends Account{
    private long cardNumber;
    private Calendar expiryDate = Calendar.getInstance();
  
    private double creditBalance;
    
    SavingsAccount(int id, double bal, double intr, int dd, int mm, int y){
        super.setId(id);
        super.setBalance(bal);
        super.setInt(intr);
        this.setDate(mm, y, dd);
    }
    public double getCreditBalance(){
        this.creditBalance = 3*super.getBalance();
        return creditBalance;
    }
    
    public void setDate(int month, int year, int date){
        this.expiryDate.set(Calendar.YEAR, year);
        this.expiryDate.set(Calendar.MONTH, month);
        this.expiryDate.set(Calendar.DATE, date);
    }
    
    
    
    public void withdraw(double amnt){
         Calendar currDate = Calendar.getInstance();
    
        if(expiryDate.compareTo(currDate)== 1 ){
            System.out.println("Date Over!! You are dead!!a");
        }else{
            if(amnt <= super.getBalance()){
                double nb = this.getBalance();
                nb -= amnt;
                this.setBalance(nb);        
            }  
        }
           
        
    }
    
    public void display(){
         System.out.println("This is a Savings Account " );
        super.display();
        System.out.println("Credit Card Number : " + this.cardNumber );
        System.out.println("Card Expiry Date : " + this.expiryDate.getTime() );
        System.out.println("Credit Balance : " + this.getCreditBalance());
        
        
    }
    
    
}
public class Main {
    public static void main(String[] args){
        
        
        int d;
        
        Scanner inp = new Scanner(System.in);
        Calendar c = Calendar.getInstance();
       
                
        
        ArrayList<Account> checkList = new ArrayList<Account>();
        ArrayList<Account> saveList = new ArrayList<Account>();
        while(true){
            System.out.println("Enter 1 for creating a checking account");
            System.out.println("Enter 2 for creating a Savings account");
            System.out.println("Enter 3 for printing a Checking account");
            System.out.println("Enter 4 for printing a Savings account");
             d = inp.nextInt();
            if(d == 1){
             System.out.println("For a Checking Account");
             int id;
             double balance, intRate, overdraft;
//             
             System.out.println("Enter Account Id: ");
             id = inp.nextInt();
             System.out.println("Enter Balance : ");
             balance = inp.nextDouble();
             System.out.println("Enter Annual Interest Rate: ");
             intRate = inp.nextDouble();
             System.out.println("Enter Overdraft: ");
             overdraft = inp.nextDouble();
             CheckingAccount cc = new CheckingAccount(id, balance, intRate, overdraft);
             checkList.add(cc);

            }else if( d == 2){
                System.out.println("For a Savings Account");
                 int id, dd, m, y;
                 double balance, intRate;
    //             
                 System.out.println("Enter Account Id: ");
                 id = inp.nextInt();
                 System.out.println("Enter Balance : ");
                 balance = inp.nextDouble();
                 System.out.println("Enter Annual Interest Rate: ");
                 intRate = inp.nextDouble();
                 System.out.println("Enter Expiry Date: ");
                 dd = inp.nextInt();
                 System.out.println("Enter Expiry Month: ");
                 m = inp.nextInt();
                 System.out.println("Enter Expiry Year: ");
                 y = inp.nextInt();
                 SavingsAccount cc = new SavingsAccount(id, balance, intRate, dd, m, y);
                 saveList.add(cc);
            }else if(d == 3){
                for(int i = 0; i < checkList.size(); ++i){
                    checkList.get(i).display();
                }
            }else if(d == 4){
                for(int i = 0; i < saveList.size(); ++i){
                    saveList.get(i).display();
                }
            }else{
                break;
            }
        }

        
        
    }
}
