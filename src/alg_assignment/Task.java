/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alg_assignment;

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
    
    public String toString(){
        return name + " (Time: " + time + " hours, Profit: $" + profit + ")";
    } 
}
