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
        String graph = "";
        
        // add top bar
        graph += " ";
        for(int i=0 ; i<p.size() ; i++){
            for(int j=0; j<p.get(i).getBurst_time() ; j++)  graph += "--";
            graph += " ";
        }
        graph += "\n|";
        
        //adding process id in the middle
        for(int i=0 ; i<p.size() ; i++){
            for(int j=0; j<p.get(i).getBurst_time()-1 ; j++)  graph += " ";
            if(p.get(i).getJob_number() != -1)  graph += "P";
            graph += p.get(i).getJob_number();
            for(int j=0; j<p.get(i).getBurst_time()-1 ; j++)  graph += " ";
            graph += "|";
        }
        graph +="\n ";
        
        // adding bottom bar
        for(int i=0 ; i<p.size() ; i++){
            for(int j=0; j<p.get(i).getBurst_time() ; j++)  graph += "--";
            graph += " ";
        }
        graph += "\n";
        
        // adding timeline
        graph += p.get(0).getStart_time();
        int time =  p.get(0).getStart_time();;
        for(int i=0 ; i<p.size() ; i++){
            for(int j=0; j<p.get(i).getBurst_time() ; j++)  graph += "  ";
            time += p.get(i).getBurst_time();
            graph += time;
        }return graph;
}
}
