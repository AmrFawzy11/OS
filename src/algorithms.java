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
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author oy
 */
public class Algorithmis {
    
    public static float total_turnAround_time;
    public static float total_waiting_time;
    
    public static ArrayList<Job> fcfs(ArrayList<Process> p) {
        Process.setCompare_type(0);
        Collections.sort(p);
        Process.setCompare_type(1);
        float timer = p.get(0).getArrival_time();
        ArrayList<Job> jobs = new ArrayList<Job>();
        for (int i = 0; i < p.size(); i++) {
            Job j = new Job(p.get(i).getProcess_number(), timer, p.get(i).getBurst_time(), p.get(i));
            jobs.add(j);
            p.get(i).setFinish_time(timer + p.get(i).getBurst_time());
            timer += p.get(i).getBurst_time();
            if (i < p.size() - 1 && timer < p.get(i + 1).getArrival_time()) {
                j = empty(timer, i + 1, p);
                timer += j.getBurst_time();
                jobs.add(j);
            }
        }
        return jobs;
    }
    
    static ArrayList<Job> SJFNP(ArrayList<Process> processes_array) {
        ArrayList<Job> jobs = NP(processes_array, 1);
        return jobs;
    }
    
    static ArrayList<Job> PNP(ArrayList<Process> processes_array) {
        ArrayList<Job> jobs = NP(processes_array, 2);
        return jobs;
    }
    
    private static ArrayList<Job> NP(ArrayList<Process> processes_array, int key) {
        Process.setCompare_type(0);
        Collections.sort(processes_array);
        ArrayList<Job> jobs = new ArrayList<Job>();
        float timer = 0;
        int processes_executed = 1;
        timer = processes_array.get(0).getArrival_time();
        Job j = new Job(processes_array.get(0).getProcess_number(), timer, (int) processes_array.get(0).getBurst_time(),
        processes_array.get(0));
        jobs.add(j);
        timer += processes_array.get(0).getBurst_time();
        Process.setCompare_type(key);
        PriorityQueue<Process> PQ = new PriorityQueue<Process>();
        while ((processes_executed < processes_array.size()) || !PQ.isEmpty()) {
            while ((processes_executed < processes_array.size()) && (timer >= processes_array.get(processes_executed).getArrival_time())) {
                PQ.add(processes_array.get(processes_executed));
                processes_executed++;
            }
            if (PQ.isEmpty()) {
                j = empty(timer, processes_executed, processes_array);
                timer += j.getBurst_time();
                jobs.add(j);
            } else {
                j = new Job(PQ.peek().getProcess_number(), timer, (int) PQ.peek().getBurst_time(), PQ.peek());
                jobs.add(j);
                timer = timer + (int) PQ.peek().getBurst_time();
                PQ.poll();                
            }
            
        }
        return jobs;        
    }    
    
    private static Job empty(float timer, int i, ArrayList<Process> p) {
        Job j = new Job(-1, timer, p.get(i).getArrival_time() - timer);
        return j;
    }
    
    public static ArrayList<Job> RR(ArrayList<Process> p, int qt) {
        Queue<Process> q = new LinkedList<>();
        ArrayList<Job> jobs = new ArrayList<Job>();
        float timer = p.get(0).getArrival_time();
        Job j;
        int i = 0;
        while (i < p.size() || !q.isEmpty()) {
            while (i < p.size() && timer >= p.get(i).getArrival_time()) {
                q.add(p.get(i));
                i++;
            }
            if (q.isEmpty()) {
                j = empty(timer, i, p);
                timer += j.getBurst_time();
                jobs.add(j);
            } else {
                if (q.peek().getRemaining_burst_time()> qt) {
                    j = new Job(q.peek().getProcess_number(), timer, qt, q.peek());
                    jobs.add(j);
                    q.peek().setRemaining_burst_time(q.peek().getRemaining_burst_time()- qt);
                    timer += j.getBurst_time();
                    while (i < p.size() && timer >= p.get(i).getArrival_time()) {
                        q.add(p.get(i));
                        i++;
                    }
                    q.add(q.peek());
                } else {
                    j = new Job(q.peek().getProcess_number(), timer, q.peek().getRemaining_burst_time(), q.peek());
                    jobs.add(j);
                    timer += j.getBurst_time();
                }
                q.poll();                
            }
        }
        return jobs;
    }

    static ArrayList<Job> preemptiveSjf(ArrayList<Process> jobs) {
        ArrayList<Job> result = preemptive(jobs, 1);
        return result;
    }
    
    static ArrayList<Job> preemptiveP(ArrayList<Process> jobs) {
        ArrayList<Job> result = preemptive(jobs, 2);
        return result;
    }
    
    static ArrayList<Job> preemptive(ArrayList<Process> jobs, int key) {
        
        ArrayList<Job> result = new ArrayList<>();
        
        Process.setCompare_type(0);
        Collections.sort(jobs);
        Process.setCompare_type(key);
        float currentTime = jobs.get(0).getArrival_time();
        float nextTime = 0;//variable to indicate arrival of next job
        float nextBurst = 0;// burst of the next job
        int nextIndex = 0;//index of the next job

        Job running;// running job to be added to result arraylist
        Process next;//next process to check if to add onto the ready queue

        PriorityQueue<Process> readyQueue = new PriorityQueue<>();
        ListIterator<Process> qIterator = jobs.listIterator();
        next = qIterator.next();
        int i = 0;
        while (!jobs.isEmpty()) {
            i++;
            /*   fill up the ready Queue with Jobs that have arrived and ready To execute*/
            while (next.getArrival_time() == currentTime) {
                readyQueue.add(next);
                qIterator.remove();
                if (qIterator.hasNext()) {
                    next = qIterator.next();
                } else {
                    break;
                }
            }
            /* check if the ready Queue is empty And if the Job queue does not have next then end*/
            if (readyQueue.isEmpty()) {
                if (jobs.isEmpty()) {
                    break;
                } else {
                    currentTime = jobs.get(nextIndex).getArrival_time();
                    continue;
                }
            }


            /* keep running the same job until A lower burst time comes or the job is done*/
            nextTime = next.getArrival_time();
            //if the Job will be finished before next arrives then do it and remove it from ready queue
            if (nextTime > currentTime + readyQueue.peek().getBurst_time()) {
                
                assert readyQueue.peek() != null;
                running = new Job(readyQueue.peek().getProcess_number(),
                        currentTime,
                        readyQueue.peek().getBurst_time(), readyQueue.peek());
                currentTime += readyQueue.peek().getBurst_time();
                readyQueue.poll();
                
            } //if the job wont finish before next then add a marigin from it then re do the comparisons and continue
            else {
                running = new Job(readyQueue.peek().getProcess_number(),
                        currentTime,
                        nextTime - currentTime, readyQueue.peek());
                if (nextTime != currentTime) {
                    readyQueue.peek().setBurst_time(readyQueue.peek().getBurst_time() - (nextTime - currentTime));
                } else ;
                currentTime = nextTime;
            }
            result.add(running);
            //jump the time to the time of next arrival
        }

        //empty the ready queue which is sorted according to burst time and all jobs have arrived
        while (!readyQueue.isEmpty()) {
            running = new Job(readyQueue.peek().getProcess_number(),
                    currentTime,
                    readyQueue.peek().getBurst_time(), readyQueue.peek());
            currentTime += readyQueue.peek().getBurst_time();
            readyQueue.poll();
            result.add(running);
        }
        
        for (int j = 0; j < result.size() - 1; j++) {
            while (result.get(j).getJob_number() == result.get(j + 1).getJob_number()) {
                result.get(j).setBurst_time(result.get(j).getBurst_time() + result.get(j + 1).getBurst_time());
                result.remove(j + 1);
                if (j + 1 > result.size() - 1) {
                    break;
                }
            }
            
        }
        fix(result);
        return result;
    }
    
    private static void fix(ArrayList<Job> jobs) {
        float timer = jobs.get(0).getStart_time() + jobs.get(0).getBurst_time();
        for (int i = 1; i < jobs.size(); i++) {
            if (timer < jobs.get(i).getStart_time()) {
                Job j = new Job(-1, timer, jobs.get(i).getStart_time() - timer);
                jobs.add(i, j);
                timer += j.getBurst_time();
            } else {
                timer += jobs.get(i).getBurst_time();
            }
        }
    }
    
    public static void timing (ArrayList<Job> jobs , ArrayList<Process> ps){
        for(Job j: jobs){
            if(j.getJob_number()== -1) continue;
            float finish_time = j.getStart_time()+j.getBurst_time();
            j.getP().setFinish_time(finish_time);
        }
        total_turnAround_time = 0;
        total_waiting_time = 0;
        
        for(Process p:ps){
            float turnAround_time = p.getFinish_time() - p.getArrival_time();
            float waiting_time = turnAround_time - p.getBurst_time();
            p.setTurnAround_time(turnAround_time);
            p.setWaiting_time(waiting_time);
            total_turnAround_time += turnAround_time;
            total_waiting_time += waiting_time;
        }
    }
}
