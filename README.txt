Scheduling Algorithms
Operating Systems, Spring 2016

This program is an assignment from my Operating Systems class. It is designed to simulate scheduling algorithms for a CPU. It includes the algorithms First Come First Serve, Shortest Job First, Priority, and Round Robin.

To run the program, run the Driver java program with a schedule of tasks (see schedule.txt to see how the layout should be) where the task name comes first, then the priority of the task, and finally the burst (or required time to complete) and the name of the algorithm you would like to run as such:

First Come First Serve = "FCFS"
Shortest Job First = "SJF"
Priority = "PRI"
Round Robin = "RR"

When completed the program will report the average wait time (how long a task has spent in the ready queue), average turn around time (how long it took them to finish minus the time at which they entered the queue) and average response time (how long a task waits in the queue before being responded to the first time).

It will also report on which order the tasks ran in and how long they ran for.