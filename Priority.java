/**
 * Priority scheduling algorithm.
 * Megan Francis
 */

import java.util.*;

public class Priority implements Algorithm
{
  private List<Task> queue;
  private Task currentTask;
  private List<Task> finishedTasks;
  
  public Priority(List<Task> queue) {
    this.queue = queue;
    this.finishedTasks = new ArrayList<Task>(queue.size());
  }
  
  /**
   * Runs the Priority Schedule
   */
  public void schedule() {
    System.out.println("Priority Scheduling \n");
    
    while (!queue.isEmpty()) {
      currentTask = pickNextTask();
      
      CPU.run(currentTask, currentTask.getBurst());
    }
  }
  
  /**
   * Picks the next task based on Priority Scheduling
   */
  public Task pickNextTask() {
    Task temp;
    Task nextTask = null;
    int highTask = 0;
    //Returns the task with the highest priority
    for(int i = 0; i<queue.size(); i++){
      temp = queue.get(i);
      if(temp.getPriority()>highTask){
        highTask = temp.getPriority();
        nextTask = temp;
      }
    }
    if (currentTask != null){
      finishedTasks.add(currentTask);
    }
    //Makes sure that all tasks are eventually added to the finsihed task list
    if (queue.size() == 1){
      finishedTasks.add(queue.get(0));
    }
    queue.remove(nextTask);
    return nextTask;
  }
  
  /**
   * Reports the average wait time of Priority Scheduling
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
   * Reports the average response time of Priority Scheduling
   */
  public double getAverageResponseTime() {
    return this.getAverageWaitTime();
  }
  
  /**
   * Reports the average turnaround time of Priority Scheduling
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
