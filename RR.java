/**
 * Red Robin scheduling algorithm.
 * Megan Francis
 */

import java.util.*;

public class RR implements Algorithm
{
  //List variables to keep track of what is left, finished, and started
  private List<Task> queue;
  private List<Task> finishedTasks;
  private List<Task> runningTasks;
  
  //Task variables to hold the current and previous Tasks
  private Task currentTask;
  private Task prevTask;
  
  //Integer variable to keep track of turnaround time
  private int turnaround;
  
  //Total amount of tasks - used in many calculations
  private int totalTasks;

  
  public RR(List<Task> queue) {
    this.queue = queue;
    this.totalTasks = queue.size();
    this.finishedTasks = new ArrayList<Task>(totalTasks);
    this.runningTasks = new ArrayList<Task>(totalTasks);
    this.turnaround = 0;
    
  }
  /**
   * Runs the Round Robin Schedule
   */
  public void schedule() {
    System.out.println("Round Robin Scheduling \n");
    //Variable keeping track of how long the next task should run
    int runDuration;
    
    //Runs through all tasks in queue
    while (!queue.isEmpty()) {
      //Find next task
      currentTask = pickNextTask();
      
      //If a task hasn't been previously started, set it's response time
      if(!runningTasks.contains(currentTask)){
        currentTask.setResponse(CPU.getTime());
      }
      //If it has been previously started, make sure it wasn't just
      //runing, & then set how long it's been waiting
      else{
        if(!currentTask.equals(prevTask)){
          currentTask.setWaitTime(CPU.getTime()-(currentTask.getFinBurst()+currentTask.getResponse()));
        }
      }
      
      //Decide how long to run the task
      if(currentTask.getBurst() < 10)
        runDuration = currentTask.getBurst();
      else
        runDuration = 10;
      
      //Run task
      CPU.run(currentTask, runDuration);
      
      //Decrease what task has left to run, increase what it has finished running
      currentTask.setFinBurst(currentTask.getFinBurst()+runDuration);
      currentTask.setBurst(currentTask.getBurst()-runDuration);
      
      prevTask = currentTask;
      
      //If it's not finished, add it back to the end of the queue
      if (currentTask.getBurst()>0){
        queue.add(currentTask);
        runningTasks.add(currentTask);
      }
      //Otherwise, add it to finished tasks, remove it from running, and 
      //record the turnaround time
      else{
        finishedTasks.add(currentTask);
        runningTasks.remove(currentTask);
        turnaround += CPU.getTime();
      }
    }
  }
  
  /**
   * Picks the next task based on Round Robin Scehduling
   */
  public Task pickNextTask() {
    Task temp;
    Task nextTask = null;
    int highTask = 0;
    
    //Finds the task with the highest priority
    for(int i = 0; i<queue.size(); i++){
      temp = queue.get(i);
      if(temp.getPriority()>highTask){
        highTask = temp.getPriority();
        nextTask = temp;
      }
    }
    queue.remove(nextTask);
    return nextTask;
  }
  
  /**
   * Reports the average response time of Round Robin
   */
  public double getAverageResponseTime() {
    int response = 0;
    //Get response time from each task
    for(int i=0; i<totalTasks; i++){
      response += finishedTasks.get(i).getResponse();
    }
    return response/totalTasks;
  }
  
  /**
   * Reports the average turnaround time of Round Robin
   */
  public double getAverageTurnaroundTime() {
    return turnaround/totalTasks;
  }
  
  /**
   * Reports the average wait time of Round Robin
   */
  public double getAverageWaitTime() {
    int waittime=0;
    for(int i=0; i<totalTasks; i++){
      waittime += finishedTasks.get(i).getResponse();
      waittime += finishedTasks.get(i).getWaitTime();
    }
    return waittime/totalTasks;
  }
}

