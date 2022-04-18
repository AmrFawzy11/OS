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
public class Chart {
    
    static String gantt (ArrayList<Job> p){
        Algorithmis.timing(p, Input.p);
        String graph = Input.print3(Input.p) + "\n";
        graph += "";
        
        // add top bar
        graph += " ";
        for(int i=0 ; i<p.size() ; i++){
            for(int j=0; j<8 ; j++)  graph += "--";
            graph += " ";
        }
        graph += "\n|";
        
        //adding process id in the middle
        for(int i=0 ; i<p.size() ; i++){
            for(int j=0; j<7 ; j++)  graph += " ";
            if(p.get(i).getJob_number() != -1){ 
                graph += "P";
                graph += p.get(i).getJob_number();
            }
            else graph+="    ";
            for(int j=0; j<7 ; j++)  graph += " ";
            graph += "|";
        }
        graph +="\n ";
        
        // adding bottom bar
        for(int i=0 ; i<p.size() ; i++){
            for(int j=0; j<8 ; j++)  graph += "--";
            graph += " ";
        }
        graph += "\n";
        
        // adding timeline
        graph += p.get(0).getStart_time();
        float time =  p.get(0).getStart_time();;
        for(int i=0 ; i<p.size() ; i++){
            for(int j=0; j<13 ; j++)  graph += " ";
            time += p.get(i).getBurst_time();
            graph += time;
        }
        graph += "\n\n";
        graph += "Average Turnarround time is " + (Algorithmis.total_turnAround_time)/Input.p.size() + "\n";
        graph += "Average waiting time is " + (Algorithmis.total_waiting_time)/Input.p.size() + "\n";
        return graph;
}
}
