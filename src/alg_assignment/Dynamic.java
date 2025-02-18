package alg_assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dynamic {

    public List<Task> allocateTasks(List<Task> tasks, int availableHours) {
        int n = tasks.size();
        
        // dp[i][j] will hold the maximum profit using the first i tasks with j available hours.
        int[][] dp = new int[n + 1][availableHours + 1];
        // Build the DP table in bottom-up fashion.
        for (int i = 1; i <= n; i++) {
            Task task = tasks.get(i - 1);
            int taskTime = task.getTime();
            int taskProfit = task.getProfit();
            for (int j = 1; j <= availableHours; j++) {
                if (taskTime <= j) {
                    // Decide to include or exclude the current task.
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - taskTime] + taskProfit);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Backtrack to find the selected tasks.
        List<Task> selectedTasks = new ArrayList<>();
        int remainingHours = availableHours;
        for (int i = n; i > 0; i--) {
            // If the profit comes from including this task, add it to the selected list.
            if (dp[i][remainingHours] != dp[i - 1][remainingHours]) {
                Task task = tasks.get(i - 1);
                selectedTasks.add(task);
                remainingHours -= task.getTime();
            }
        }
        // Reverse to maintain the original order.
        Collections.reverse(selectedTasks);
        return selectedTasks;
    }
}
