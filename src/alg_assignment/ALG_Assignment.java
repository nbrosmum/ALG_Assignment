/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package alg_assignment;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */
public class ALG_Assignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int availableHours = 8;
        List<Task> tasks = new ArrayList<>();
        
        // Create tasks with names, time, and profit.
        tasks.add(new Task("Task 1", 3, 300));
        tasks.add(new Task("Task 2", 4, 100));
        tasks.add(new Task("Task 3", 1, 200));
        tasks.add(new Task("Task 4", 1, 500));
        tasks.add(new Task("Task 5", 2, 250));
        
        Greedy allocator = new Greedy();
        List<Task> selectedTasks = allocator.allocateTasks(tasks, availableHours);
        
        int totalProfit = 0;
        int totalHours = 0;
        System.out.println("Selected Tasks:");
        for (Task task : selectedTasks) {
            System.out.println(task);
            totalProfit += task.getProfit();
            totalHours += task.getTime();
        }
        System.out.println("Total Hours: " + totalHours + " / " + availableHours);
        System.out.println("Total Profit: $" + totalProfit);  
    }
    
}
