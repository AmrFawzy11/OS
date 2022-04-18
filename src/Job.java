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
    private float start_time;
    private float burst_time;
    private Process p;

    public Job(int job_number, float start_time, float burst_time) {
        this.job_number = job_number;
        this.start_time = start_time;
        this.burst_time = burst_time;
    }

    public Job(int job_number, float start_time, float burst_time, Process p) {
        this(job_number, start_time, burst_time);
        this.p = p;
    }
    

    public void setJob_number(int job_number) {
        this.job_number = job_number;
    }

    public void setStart_time(float start_time) {
        this.start_time = start_time;
    }

    public void setBurst_time(float burst_time) {
        this.burst_time = burst_time;
    }

    public int getJob_number() {
        return job_number;
    }

    public float getStart_time() {
        return start_time;
    }

    public float getBurst_time() {
        return burst_time;
    }

    public Process getP() {
        return p;
    }
    
}
