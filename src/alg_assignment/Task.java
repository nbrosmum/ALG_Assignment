/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alg_assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author User
 */
public class Task {
    private String name;
    private int time;
    private int profit;

    public Task(String name, int time, int profit) {
        this.name = name;
        this.time = time;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public int getProfit() {
        return profit;
    }
    
    public static List<Task>generateRandomTasks(int taskCount,int maxHour,int maxProfit){
        List<Task> tasks = new ArrayList<>();
        Random random = new Random();
        
        for(int i = 0; i < taskCount; i++){
            String taskName = "Task " + (i + 1);
            int time  = random.nextInt(maxHour) + 1;
            int profit = random.nextInt(maxProfit / 50) * 50 + 50;
            tasks.add(new Task(taskName,time,profit));
        }
        return tasks;
    }
    public String toString(){
        return name + " (Time: " + time + " hours, Profit: $" + profit + ")";
    } 
}
