/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

/**
 *
 * @author oy
 */
public class Job {
    
    private int job_number;
    private int start_time;
    private int burst_time;

    public Job(int job_number, int start_time, int burst_time) {
        this.job_number = job_number;
        this.start_time = start_time;
        this.burst_time = burst_time;
    }

    public void setJob_number(int job_number) {
        this.job_number = job_number;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public void setBurst_time(int burst_time) {
        this.burst_time = burst_time;
    }

    public int getJob_number() {
        return job_number;
    }

    public int getStart_time() {
        return start_time;
    }

    public int getBurst_time() {
        return burst_time;
    }
 
}
