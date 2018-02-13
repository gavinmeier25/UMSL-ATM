/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umsl;

import java.io.*;
import java.util.*;
import java.text.*;

/**
 *
 * @author rmep67
 */
public class Accounts implements Serializable{
    
    protected double balance;
    protected double interest;
    protected int firstDate;
    protected int secondDate;
    protected int datediff;
    protected Calendar cal1 = new GregorianCalendar();
    protected Calendar cal2 = new GregorianCalendar();
    protected boolean dateflag = false;
    protected double rate;
    private String acctName;
    static Scanner sc = new Scanner(System.in);
    DecimalFormat money = new DecimalFormat("$#.00");
    
    public Accounts(){
        balance= 100;
        interest = .05;
    }
    public Accounts(String name){
        balance=100;
        rate=.05;
        name = acctName;
    }
    
    public void deposit(){
        
        double deposit;
        boolean run = true;
        System.out.println("Enter in the amount of money you will like to deposit");
        deposit = sc.nextDouble();
        balance =getBalance()+deposit;
        System.out.println("The current balance for this account is :\t"
        +money.format(balance)+"\n\n");
        
    }
    
    public void withdraw(){
        double with;
        System.out.println("How much would you like to withdraw:");
        with=sc.nextDouble();
        getBalance();
        if (with>balance){
            System.out.println("\nYou cannot overdraft your account!!!!\n");
        }
        else {
        
            balance=balance-with;
            System.out.println("The current balance for this account is :\t"
                    +money.format(balance)+"\n\n");
        }
    }
    
    public double getBalance(){
        boolean run= true;
        while(run){
            
            getDate2();
            if(dateflag==true){
                CalcInt();
                System.out.println("The current balance for this account is :\t"
                +money.format(balance)+"\n\n");
                run=false;
            }
            else
            {
                System.out.println("Enter in a future date.");
            }
        }
            return balance;
    }
    
    public void CalcInt() {
        
        datediff = secondDate - firstDate;           
        rate = interest/365;           
        double ratetime = Math.pow(1+rate,datediff);           
        balance = balance * ratetime;           
        firstDate = secondDate;
    
    }
    
    public void getDate1() {
        
        System.out.print("Enter todays date(mm/dd/yyyy): ");
        String inputText = sc.next( );
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        ParsePosition pos = new ParsePosition(0);
        //Date date= new Date();
        Date date = formatter.parse(inputText, pos);
        cal1.setTime(date);
        firstDate = cal1.get(Calendar.DAY_OF_YEAR);
        dateflag = true;
    
    }
    
    public void getDate2() {
        System.out.print("Enter todays date(mm/dd/yyyy): ");
        String inputText = sc.next( );
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        ParsePosition pos = new ParsePosition(0);
        //Date date= new Date();
        Date date = formatter.parse(inputText, pos);
        cal2.setTime(date);
        secondDate = cal2.get(Calendar.DAY_OF_YEAR);
        if(firstDate>secondDate){
            System.out.println("Enter in approiate date.");
            dateflag = false;
        }
        else{
            dateflag = true;
        }
        
    }
    
    public void menu(){
        int user;
        boolean stop=false;
        Scanner sc = new Scanner(System.in);
        
        if(!dateflag){
            getDate1();
        }
        while(!stop){
        
            System.out.println("Enter in number to do select your action"
                    + "\n 1)deposit \n 2)withdraw \n 3)Check Balance\n 4)Exit");
            user=sc.nextInt();
        
            switch(user){
                case 1:
                    this.deposit();
                    break;
                case 2:
                    this.withdraw();
                    break;
                case 3:
                    this.getBalance();
                    break;
                case 4:
                    stop=true;
                    break;
                default:
                    System.out.println("Try again");
                    break;
            }
        }
    }
}
