/**
 * FCFS scheduling algorithm.
 * Megan Francis
 */

import java.util.*;

public class FCFS implements Algorithm
{
  private List<Task> queue;
  private Task currentTask;
  private List<Task> finishedTasks;
  
  public FCFS(List<Task> queue) {
    this.queue = queue;
    this.currentTask = null;
    this.finishedTasks = new ArrayList<Task>(queue.size());
  }
  
  /**
   * Runs the FCFS Schedule
   */
  public void schedule() {
    System.out.println("FCFS Scheduling \n");
    
    while (!queue.isEmpty()) {
      currentTask = pickNextTask();
      
      CPU.run(currentTask, currentTask.getBurst());
    }
  }
  
  /**
   * Picks the next task based on FCFS Scheduling
   */
  public Task pickNextTask() {
    //Picks the first task in the queue
    if (currentTask != null){
      finishedTasks.add(currentTask);
    }
    if (queue.size() == 1){
      finishedTasks.add(queue.get(0));
    }
    return queue.remove(0);
  }
  
  /**
   * Reports the average wait time of FCFS
   */
  public double getAverageWaitTime() {
    int waitDuration = 0;
    Task temp;
    int totalTasks = finishedTasks.size();
    for(int i=totalTasks-1; i>=0; i--){
      temp = finishedTasks.remove(0);
      finishedTasks.add(temp);
      waitDuration = waitDuration + (temp.getBurst()*i);
    }
    return waitDuration/totalTasks;
  }
  
  /**
   * Reports the average response time of FCFS
   */
  public double getAverageResponseTime() {
    return this.getAverageWaitTime();
  }
  
  /**
   * Reports the average turnaround time of FCFS
   */
  public double getAverageTurnaroundTime() {
    int turnaround = 0;
    Task temp;
    int totalTasks = finishedTasks.size();
    for(int i=totalTasks; i>0; i--){
      temp = finishedTasks.remove(0);
      finishedTasks.add(temp);
      turnaround = turnaround + (temp.getBurst()*i);
    }
    return turnaround/totalTasks;
  }
}
