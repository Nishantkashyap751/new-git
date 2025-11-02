class Task {
    public int taskId;
    public String taskName;
    public int priority;
    public String dueDate;
    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
    }
    @Override
    public String toString() {
        return "ID: " + taskId + ", Name: " + taskName + ", Priority: " + priority + ", Due: " + dueDate;
    }
}
class TaskNode {
    public Task data;
    public TaskNode next;
    public TaskNode(Task data) {
        this.data = data;
        this.next = null;
    }
}
class TaskScheduler {
    public TaskNode head;
    public TaskNode tail;
    public TaskScheduler() {
        this.head = null;
        this.tail = null;
    }
    public void addAtEnd(Task newTask) {
        TaskNode newNode = new TaskNode(newTask);
      if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            newNode.next = this.head; // THIS IS THE CIRCULAR LINK (Empty List Case)
        } else {
            this.tail.next = newNode;
            newNode.next = this.head; // THIS IS THE CIRCULAR LINK (General Case)
            this.tail = newNode;
        }
        System.out.println("Added to End: " + newTask.taskName);
    }
    public void displayAllTasks() {
        if (this.head == null) {
            System.out.println("The task list is empty.");
            return;
        }
        TaskNode current = this.head;
        System.out.println("\n--- Current Task List (Circular) ---");        
        do {
            System.out.println(current.data);
            current = current.next;
        } while (current != this.head);
        System.out.println("------------------------------------\n");
    }
    public void viewAndAdvanceTask() {
        if (this.head == null) {
            System.out.println("No tasks to view.");
            return;
        }
        System.out.println("\nExecuting Task: " + this.head.data.taskName);
        this.head = this.head.next;
        System.out.println("Next up: " + this.head.data.taskName);
    }
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        Task t1 = new Task(1, "Fix Bug", 1, "2025-11-01");
        Task t2 = new Task(2, "Write Docs", 2, "2025-11-05");
        Task t3 = new Task(3, "Design Feature", 1, "2025-11-02");
        scheduler.addAtEnd(t1);
        scheduler.addAtEnd(t2);
        scheduler.addAtEnd(t3);      
        scheduler.displayAllTasks();      
        scheduler.viewAndAdvanceTask();
        scheduler.viewAndAdvanceTask();     
        scheduler.displayAllTasks();
    }
}