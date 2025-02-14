/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alg_assignment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 *
 * @author User
 */
public class Greedy {
    public double getProfitToTimeRatio(Task task) {
       return (double) task.getProfit() / task.getTime();
    }

    public List<Task> allocateTasks(List<Task> tasks, int availableHours){
       Collections.sort(tasks, new Comparator<Task>(){
           public int compare(Task t1,Task t2){
               return Double.compare(getProfitToTimeRatio(t2), getProfitToTimeRatio(t1));
           }
       });
       List<Task> selectedTasks = new ArrayList<>();
       int remainingHours = availableHours;

       for(Task task : tasks){
           if(task.getTime() <= remainingHours){
               selectedTasks.add(task);
               remainingHours -= task.getTime();
           }
           if(remainingHours == 0) break;
       }
       return selectedTasks;
    }
}
