/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.util.ArrayList;

/**
 *
 * @author oy
 */
public class Input {
    public static ArrayList<Process> p;
    
    public static void input(javax.swing.JTextField JTextField1, javax.swing.JTextField JTextField2, javax.swing.JTextField JTextField3){
        ArrayList<Integer> Ids = split(JTextField1);
        ArrayList<Float> arrivals = split1(JTextField2);
        ArrayList<Float> bursts = split1(JTextField3);
        p = new ArrayList<Process>(Ids.size());
        for(int i=0 ; i<Ids.size() ; i++){
            Process p1 = new Process(Ids.get(i), arrivals.get(i), bursts.get(i));
            p.add(p1);
        }
    }
    
    public static ArrayList <Integer> split(javax.swing.JTextField JTextField1){
       
        ArrayList<Integer> list=new ArrayList<Integer>();
        String var = JTextField1.getText(); // var = "1 2 3"
        String[] str = var.split(" "); // str [] = {"1" , "2" , "3"}
        for(String s : str){
           int i=Integer.parseInt(s); 
           list.add(i); // list = {1 , 2 ,3}
        }
        return list;
    }
    public static ArrayList <Float> split1(javax.swing.JTextField JTextField1){
       
        ArrayList<Float> list = new ArrayList<Float>();
        String var = JTextField1.getText();
        String[] str = var.split(" ");
        for(String s : str){
           float i=Float.parseFloat(s); ; 
           list.add(i);
        }
        return list;
    }
    
    public static String print (ArrayList<Process> p){
        String s="";
        for(Process p1 :p){
            s +="number :"+ p1.getProcess_number()+"   ";
            s += "arrival :"+ p1.getArrival_time()+ "   ";
            s += "burst :"+ p1.getBurst_time()+"\n";
        }return s;
    }
    public static String print3 (ArrayList<Process> p){
        String s="";
        for(Process p1 :p){
            s +="number :"+ p1.getProcess_number()+"   ";
            s += "arrival :"+ p1.getArrival_time()+ "   ";
            s += "burst :"+ p1.getBurst_time()+"   ";
            s += "waiting time"+ p1.getWaiting_time()+"\n";
        }return s;
    }
    public static String print2 (ArrayList<Job> p){
        String s="";
        for(Job p1 :p){
            s +="number :"+ p1.getJob_number()+"   ";
            s += "arrival :"+ p1.getStart_time()+ "   ";
            s += "burst :"+ p1.getBurst_time()+"\n";
        }return s;
    }
}
