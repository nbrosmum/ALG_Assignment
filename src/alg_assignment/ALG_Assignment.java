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
        int taskCount = 10;
        int maxProfit = 500;
        List<Task> tasks = Task.generateRandomTasks(taskCount, availableHours, maxProfit);
        // Print all tasks before selection
        System.out.println("All Generated Tasks:");
        for (Task task : tasks) {
            System.out.println(task);
        }
        //GreedyAlgorithem
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
