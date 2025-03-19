package alg_assignment;

import java.util.ArrayList;
import java.util.List;

public class ALG_Assignment {

    // A helper method to "clean" memory by invoking garbage collection repeatedly.
    private static void cleanMemory(Runtime runtime) {
        // Call GC multiple times with a short pause to let it settle.
        for (int i = 0; i < 3; i++) {
            runtime.gc();
            try {
                Thread.sleep(100); // wait 100ms between calls
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public static void main(String[] args) {
        int availableHours = 8;
        int maxProfit = 500;
        // Define the different task counts to test.
        int[] taskCounts = {10, 100, 1000, 10000, 100000};
        // Number of iterations for averaging measurements.
        int iterations = 5;
        
        // Get runtime instance.
        Runtime runtime = Runtime.getRuntime();
        
        // -----------------------------------------------------------
        // Measure fixed overhead for Dynamic Programming algorithm.
        // We run it on an empty input so that we can subtract this overhead.
        Dynamic dynamic = new Dynamic();
        List<Task> emptyTasks = new ArrayList<>();
        cleanMemory(runtime);
        long memBeforeDynamicBaseline = runtime.totalMemory() - runtime.freeMemory();
        dynamic.allocateTasks(emptyTasks, availableHours);
        long memAfterDynamicBaseline = runtime.totalMemory() - runtime.freeMemory();
        long baselineDynamic = memAfterDynamicBaseline - memBeforeDynamicBaseline;
        
        // -----------------------------------------------------------
        // Measure fixed overhead for Greedy algorithm.
        Greedy greedy = new Greedy();
        List<Task> emptyTasksForGreedy = new ArrayList<>();
        cleanMemory(runtime);
        long memBeforeGreedyBaseline = runtime.totalMemory() - runtime.freeMemory();
        greedy.allocateTasks(emptyTasksForGreedy, availableHours);
        long memAfterGreedyBaseline = runtime.totalMemory() - runtime.freeMemory();
        long baselineGreedy = memAfterGreedyBaseline - memBeforeGreedyBaseline;
        
        // Lists to store summary results for Dynamic and Greedy algorithms.
        // Each string contains: task count, average time taken (ms) and average adjusted extra space (KB)
        List<String> dynamicSummary = new ArrayList<>();
        List<String> greedySummary = new ArrayList<>();
        
        for (int taskCount : taskCounts) {
            System.out.println("-------------------------------------------------");
            System.out.println("Test with " + taskCount + " tasks:");
            
            // Generate tasks.
            List<Task> tasks = Task.generateRandomTasks(taskCount, availableHours, maxProfit);
            
            // Print all generated tasks.
            System.out.println("\nAll Generated Tasks:");
            for (Task task : tasks) {
                System.out.println(task);
            }
            
            // -----------------------------
            // Dynamic Programming Measurements
            // -----------------------------
            // Warm-up iteration for Dynamic (JVM warm-up)
            dynamic.allocateTasks(tasks, availableHours);
            cleanMemory(runtime);
            
            double totalTimeDynamic = 0;
            double totalAdjustedMemoryDynamic = 0;
            for (int i = 0; i < iterations; i++) {
                cleanMemory(runtime);
                long memBefore = runtime.totalMemory() - runtime.freeMemory();
                long startTime = System.nanoTime();
                dynamic.allocateTasks(tasks, availableHours);
                long endTime = System.nanoTime();
                long memAfter = runtime.totalMemory() - runtime.freeMemory();
                
                long extraMemory = memAfter - memBefore; 
                // Adjust by subtracting the fixed overhead measured earlier.
                long adjustedExtraMemory = extraMemory - baselineDynamic;
                if (adjustedExtraMemory < 0) {
                    adjustedExtraMemory = 0;
                }
                
                totalTimeDynamic += (endTime - startTime) / 1_000_000.0;
                totalAdjustedMemoryDynamic += adjustedExtraMemory / 1024.0; // convert bytes to KB
            }
            double avgTimeDynamic = totalTimeDynamic / iterations;
            double avgAdjustedMemoryDynamic = totalAdjustedMemoryDynamic / iterations;
            
            // Run one more iteration to display the selected tasks.
            List<Task> dynamicSelectedTasks = dynamic.allocateTasks(tasks, availableHours);
            int dynamicTotalProfit = 0;
            int dynamicTotalHours = 0;
            System.out.println("\nSelected Tasks based on Dynamic Programming:");
            for (Task task : dynamicSelectedTasks) {
                System.out.println(task);
                dynamicTotalProfit += task.getProfit();
                dynamicTotalHours += task.getTime();
            }
            System.out.println("\nDynamic Programming Results for " + taskCount + " tasks:");
            System.out.println("Total Hours: " + dynamicTotalHours + " / " + availableHours);
            System.out.println("Total Profit: $" + dynamicTotalProfit);
            System.out.println("Average Execution Time (Dynamic Programming): " + avgTimeDynamic + " ms");
            System.out.println("Average Adjusted Extra Memory Usage (Dynamic Programming): " + avgAdjustedMemoryDynamic + " KB");
            
            // Store summary for Dynamic.
            dynamicSummary.add(String.format("%-10d%-25.3f%-25.3f", taskCount, avgTimeDynamic, avgAdjustedMemoryDynamic));
            
            // ----------------------
            // Greedy Measurements
            // ----------------------
            // Warm-up iteration for Greedy.
            greedy.allocateTasks(tasks, availableHours);
            cleanMemory(runtime);
            
            double totalTimeGreedy = 0;
            double totalAdjustedMemoryGreedy = 0;
            for (int i = 0; i < iterations; i++) {
                cleanMemory(runtime);
                long memBefore = runtime.totalMemory() - runtime.freeMemory();
                long startTime = System.nanoTime();
                greedy.allocateTasks(tasks, availableHours);
                long endTime = System.nanoTime();
                long memAfter = runtime.totalMemory() - runtime.freeMemory();
                
                long extraMemory = memAfter - memBefore;
                long adjustedExtraMemory = extraMemory - baselineGreedy;
                if (adjustedExtraMemory < 0) {
                    adjustedExtraMemory = 0;
                }
                
                totalTimeGreedy += (endTime - startTime) / 1_000_000.0;
                totalAdjustedMemoryGreedy += adjustedExtraMemory / 1024.0;
            }
            double avgTimeGreedy = totalTimeGreedy / iterations;
            double avgAdjustedMemoryGreedy = totalAdjustedMemoryGreedy / iterations;
            
            // Run one more iteration to display the selected tasks.
            List<Task> greedySelectedTasks = greedy.allocateTasks(tasks, availableHours);
            int greedyTotalProfit = 0;
            int greedyTotalHours = 0;
            System.out.println("\nSelected Tasks based on Greedy Algorithm:");
            for (Task task : greedySelectedTasks) {
                System.out.println(task);
                greedyTotalProfit += task.getProfit();
                greedyTotalHours += task.getTime();
            }
            System.out.println("\nGreedy Algorithm Results for " + taskCount + " tasks:");
            System.out.println("Total Hours: " + greedyTotalHours + " / " + availableHours);
            System.out.println("Total Profit: $" + greedyTotalProfit);
            System.out.println("Average Execution Time (Greedy Algorithm): " + avgTimeGreedy + " ms");
            System.out.println("Average Adjusted Extra Memory Usage (Greedy Algorithm): " + avgAdjustedMemoryGreedy + " KB");
            
            // Store summary for Greedy.
            greedySummary.add(String.format("%-10d%-25.3f%-25.3f", taskCount, avgTimeGreedy, avgAdjustedMemoryGreedy));
        }
        
        // ---------------------------
        // Print Summary Tables
        // ---------------------------
        System.out.println("\n-------------------------------------------------");
        System.out.println("Summary Table - Dynamic Programming:");
        System.out.printf("%-10s%-25s%-25s%n", "Tasks", "Time taken (ms)", "Extra Space taken (KB)");
        for (String s : dynamicSummary) {
            System.out.println(s);
        }
        
        System.out.println("\n-------------------------------------------------");
        System.out.println("Summary Table - Greedy Algorithm:");
        System.out.printf("%-10s%-25s%-25s%n", "Tasks", "Time taken (ms)", "Extra Space taken (KB)");
        for (String s : greedySummary) {
            System.out.println(s);
        }
    }
}
