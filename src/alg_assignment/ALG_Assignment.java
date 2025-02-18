
package alg_assignment;

import java.util.List;

/** 
 *
 * @author User
 */
public class ALG_Assignment {
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

        // Measure memory before Dynamic Programming
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();  // Run Garbage Collector
        long memoryBeforeDynamic = runtime.totalMemory() - runtime.freeMemory();
        long startTimeDynamic = System.nanoTime();

        // Dynamic Programming
        Dynamic dynamic = new Dynamic();
        List<Task> dynamicSelectedTasks = dynamic.allocateTasks(tasks, availableHours);
        
        int dynamicTotalProfit = 0;
        int dynamicTotalHours = 0;
        System.out.println("\nSelected Tasks based on Dynamic Programming:");
        for (Task task : dynamicSelectedTasks) {
            System.out.println(task);
            dynamicTotalProfit += task.getProfit();
            dynamicTotalHours += task.getTime();
        }

        long endTimeDynamic = System.nanoTime();
        long memoryAfterDynamic = runtime.totalMemory() - runtime.freeMemory();
        
        System.out.println("\nTotal Hours: " + dynamicTotalHours + " / " + availableHours);
        System.out.println("Total Profit: $" + dynamicTotalProfit);  
        System.out.println("Execution Time (Dynamic Programming): " + (endTimeDynamic - startTimeDynamic) / 1_000_000.0 + " ms");
        System.out.println("Memory Usage (Dynamic Programming): " + (memoryAfterDynamic - memoryBeforeDynamic) / 1024.0 + " KB");

        // Measure memory before Greedy Algorithm
        runtime.gc();  // Run Garbage Collector
        long memoryBeforeGreedy = runtime.totalMemory() - runtime.freeMemory();
        long startTimeGreedy = System.nanoTime();

        // Greedy Algorithm
        Greedy allocator = new Greedy();
        List<Task> selectedTasks = allocator.allocateTasks(tasks, availableHours);


        int totalProfit = 0;
        int totalHours = 0;
        System.out.println("\nSelected Tasks based on Greedy Algorithm:");
        for (Task task : selectedTasks) {
            System.out.println(task);
            totalProfit += task.getProfit();
            totalHours += task.getTime();
        }
        
        long endTimeGreedy = System.nanoTime();
        long memoryAfterGreedy = runtime.totalMemory() - runtime.freeMemory();
        
        System.out.println("Total Hours: " + totalHours + " / " + availableHours);
        System.out.println("Total Profit: $" + totalProfit);  
        System.out.println("Execution Time (Greedy Algorithm): " + (endTimeGreedy - startTimeGreedy) / 1_000_000.0 + " ms");
        System.out.println("Memory Usage (Greedy Algorithm): " + (memoryAfterGreedy - memoryBeforeGreedy) / 1024.0 + " KB");
    }
}
