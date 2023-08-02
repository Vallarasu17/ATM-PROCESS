package ATMProcess; 

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class loadCash {
	private Map<Integer,Integer> MoneyDenominations;

    public loadCash(){
        this.MoneyDenominations = new HashMap<>();
    }
    public void load(int denomination,int quantity,int Value){
        if(MoneyDenominations.containsKey(denomination)){
            int existQuantity = MoneyDenominations.get(denomination);
            MoneyDenominations.put(denomination,existQuantity+quantity);
        }
        else{
            MoneyDenominations.put(denomination,quantity);
        }
    }
    public void saveData(String FileName){
        try(PrintWriter writer = new PrintWriter(FileName)){
            for(Map.Entry<Integer,Integer>entry:MoneyDenominations.entrySet()){
                writer.println(entry.getKey() + "," + entry.getValue() + "," + (entry.getKey()*entry.getValue()));
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public void displayDetails(){
        System.out.println("Currency denomination details:");
        System.out.println("Denomination\tQuantity\tValue");
        for(Map.Entry<Integer,Integer> entry:MoneyDenominations.entrySet()){
            System.out.format("%-14d\t%d\t\t%d%n",entry.getKey(),entry.getValue(),(entry.getKey()*entry.getValue()));
        }
    }
    public static void main(String args[]){
        loadCash atm=new loadCash();
        String FileName ="MoneyDenomination.txt";
        
        try{
            File file=new File(FileName);
            if(file.exists()){
                Scanner sc=new Scanner(file);
                while(sc.hasNextLine()){
                    String line=sc.nextLine();
                    String[] parts=line.split(",");
                    int d=Integer.parseInt(parts[0]);
                    int quantity=Integer.parseInt(parts[1]);
                    int value=Integer.parseInt(parts[2]);
                    atm.load(d,quantity,value);
                }
                sc.close();
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        atm.load(2000,20,2000*20);
        atm.load(500,100,500*100);
        atm.load(100,100,100*100);
        
        atm.saveData(FileName);
        
        atm.displayDetails();
    }

}
