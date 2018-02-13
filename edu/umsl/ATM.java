/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umsl;

import java.io.*;
import java.util.*;


/**
 *
 * @author rmep67
 */
public class ATM {
    private Accounts[] acc= new Accounts[3];
    boolean accountsLoaded = false;
    
    public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException{
        ATM myATM = new ATM();
        System.out.println("---Welcome to a Non-existent ATM!---");
        myATM.TopMenu();          
        }     
   
    private void populate(){
        
        for(int i =0; i<acc.length; i++){
            acc[i] = new Accounts();
        }
        accountsLoaded = true;
    }
    
    public void LoadAccounts() throws FileNotFoundException, ClassNotFoundException{
        try{
            FileInputStream instream = new FileInputStream("Data.txt");
            ObjectInputStream is = new ObjectInputStream(instream);
            acc = (Accounts[]) is.readObject();
            accountsLoaded= true;
            
        } catch (IOException e) {
            System.out.println("Error when tring to read file");
        }
    }
    
    public void SaveAccounts() throws FileNotFoundException {
        try{
            FileOutputStream outStream = new FileOutputStream("Data.txt");
            ObjectOutputStream os = new ObjectOutputStream(outStream);
            
            os.writeObject(acc);
            os.flush();
            os.close();
        }
        catch (IOException e){
            System.out.println("Couldn't save object to file");
        }
    }
    public void TopMenu() throws FileNotFoundException, ClassNotFoundException{
        boolean run = true;
        int access;
        Scanner sc= new Scanner(System.in);
        while(run){
            System.out.println("Would you like to do, press anything else to exit: \n1) Populate Accounts"
                    + "\n2) Load Accounts \n3) Account Menu \n4) Save Accounts");
            access=sc.nextInt();
            
            if(access==1) {
                populate();
                System.out.println("Accounts are now defaulted.\n\n");
            }
            else if (access==2){
                LoadAccounts();
                System.out.println("\nAccounts are now Loaded.\n\n");
            }
            else if (access==3){
               
                System.out.println("\nWhich account will you like to access");
                System.out.println("Account 0, 1, 2, press anything else to go back");
                access=sc.nextInt();
                
                switch (access) {
                    case 0:
                        acc[0].menu();
                        break;
                    case 1:
                        acc[1].menu();
                        break;
                    case 2:
                        acc[2].menu();
                        break;
                    default:
                        run=false;
                        break;
                }
            }
            else if (access==4) {
                SaveAccounts();
            }
            else {
                run=false;
            }
        }
    }
}

