/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author oy
 */
public class Algorithmis {
    
    public static ArrayList<Job> fcfs (ArrayList<Process> p){
        Process.setCompare_type(0);
        Collections.sort(p);
        Process.setCompare_type(1);
        int timer = p.get(0).getArrival_time();
        ArrayList<Job> jobs = new ArrayList<Job>();
        for(int i=0 ; i<p.size() ; i++){
            Job j = new Job(p.get(i).getProcess_number() , timer , p.get(i).getBurst_time());
            jobs.add(j);
            p.get(i).setFinish_time(timer + p.get(i).getBurst_time());
            timer += p.get(i).getBurst_time();
            if(i<p.size()-1 && timer < p.get(i+1).getArrival_time()){
                j = empty(timer, i+1, p);
                timer += j.getBurst_time();
                jobs.add(j);
            }
        }return jobs;
    }

    static ArrayList<Job> SJFNP(ArrayList<Process> processes_array){
        ArrayList<Job> jobs = NP(processes_array , 1);
        return jobs;
    }
    
    static ArrayList<Job> PNP(ArrayList<Process> processes_array){
        ArrayList<Job> jobs = NP(processes_array , 2);
        return jobs;
    }

    private static ArrayList<Job> NP(ArrayList<Process> processes_array , int key)
     {
         Process.setCompare_type(0);
         Collections.sort(processes_array);
         ArrayList<Job> jobs = new ArrayList<Job>();
         int timer=0;
         int processes_executed=1;
         timer=(int)processes_array.get(0).getArrival_time();
         Job j=new Job(processes_array.get(0).getProcess_number(), timer, (int)processes_array.get(0).getBurst_time());
         jobs.add(j);
         timer += processes_array.get(0).getBurst_time();
         Process.setCompare_type(key);
         PriorityQueue <Process> PQ = new PriorityQueue <Process> ();
         while((processes_executed<processes_array.size()) || !PQ.isEmpty())
         {
            while((processes_executed<processes_array.size())&&(timer>=processes_array.get(processes_executed).getArrival_time()))
            {
                PQ.add(processes_array.get(processes_executed));
                processes_executed++;
            }
            if(PQ.isEmpty()){
                j = empty(timer, processes_executed, processes_array);
                timer += j.getBurst_time();
                jobs.add(j);
            }
            else{
                j=new Job(PQ.peek().getProcess_number(), timer, (int)PQ.peek().getBurst_time());
                jobs.add(j);
                timer=timer+(int)PQ.peek().getBurst_time();
                PQ.poll();   
            }
         
         }
        return jobs; 
     }    
        
        
    private static Job empty(int timer , int i , ArrayList<Process> p){
       Job j = new Job(-1 , timer , p.get(i).getArrival_time()-timer);
       return j;
    } 
    

    public static ArrayList<Job> RR (ArrayList<Process> p , int qt){
         Queue<Process> q= new LinkedList<>();
         ArrayList<Job> jobs = new ArrayList<Job>();
         int timer = p.get(0).getArrival_time();
         Job j;
         int i=0;
         while(i<p.size() || !q.isEmpty()){
             while(i<p.size() && timer >= p.get(i).getArrival_time()){
                 q.add(p.get(i));
                 i++;
             }
             if(q.isEmpty()){
                j = empty(timer, i, p);
                timer += j.getBurst_time();
                jobs.add(j);
            }
             else{
                if(q.peek().getBurst_time()>qt)
                {
                    j = new Job(q.peek().getProcess_number() , timer ,qt);
                    jobs.add(j);
                    q.peek().setBurst_time(q.peek().getBurst_time()- qt);
                    timer += j.getBurst_time();
                    while (i<p.size() && timer >= p.get(i).getArrival_time()){
                         q.add(p.get(i));
                         i++;
                    }
                    q.add(q.peek());
                }
                else{
                    j = new Job(q.peek().getProcess_number() , timer ,q.peek().getBurst_time());
                    jobs.add(j);
                    timer += j.getBurst_time();
                }
                 q.poll(); 
             }
         }return jobs;
    }

    }
