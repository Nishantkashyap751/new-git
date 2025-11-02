class Process {
    public int processID;
    public int burstTime;
    public int priority;
    public int arrivalTime;
    public int remainingTime;
    public int waitingTime;
    public int turnAroundTime;
    public int completionTime;

    public Process(int processID, int burstTime, int priority) {
        this.processID = processID;
        this.burstTime = burstTime;
        this.priority = priority;
        this.arrivalTime = 0;
        this.remainingTime = burstTime;
        this.waitingTime = 0;
        this.turnAroundTime = 0;
        this.completionTime = 0;
    }

    @Override
    public String toString() {
        return "P" + processID + " (BT: " + burstTime + ", RT: " + remainingTime + ")";
    }
}
class ProcessNode {
    public Process data;
    public ProcessNode next;

    public ProcessNode(Process data) {
        this.data = data;
        this.next = null;
    }
}
class RoundRobinSA {
    public ProcessNode head;
    public ProcessNode tail;
    public int timeQuantum;

    public RoundRobinSA(int quantum) {
        this.head = null;
        this.tail = null;
        this.timeQuantum = quantum;
    }
    public void addProcess(Process newProcess) {
        ProcessNode newNode = new ProcessNode(newProcess);

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            newNode.next = this.head; // THIS IS THE CIRCULAR LINK (Empty List Case)
        } else {
            this.tail.next = newNode;
            newNode.next = this.head; // THIS IS THE CIRCULAR LINK (General Case)
            this.tail = newNode;
        }
        System.out.println("Added: P" + newProcess.processID);
    }
    public void removeProcess(int processID) {
        if (this.head == null) {
            return;
        }
        if (this.head == this.tail && this.head.data.processID == processID) {
            this.head = null;
            this.tail = null;
            return;
        }
        if (this.head.data.processID == processID) {
            this.head = this.head.next;
            this.tail.next = this.head;
            return;
        }
        ProcessNode current = this.head.next;
        ProcessNode prev = this.head;
        while (current != this.head) {
            if (current.data.processID == processID) {
                prev.next = current.next;
                
                if (current == this.tail) {
                    this.tail = prev;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }
    public void displayQueue() {
        if (this.head == null) {
            System.out.println("Queue is empty.");
            return;
        }
        ProcessNode current = this.head;
        System.out.print("Current Queue: ");
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
        } while (current != this.head);
        System.out.println("[HEAD]");
    }
    public void simulateRoundRobin() {
        System.out.println("--- Starting Round Robin Simulation (Quantum = " + this.timeQuantum + ") ---");
        ProcessNode current = this.head;
        int currentTime = 0;
        
        while (this.head != null) {
            Process p = current.data;
            
            int executeTime = Math.min(p.remainingTime, this.timeQuantum);
            
            currentTime += executeTime;
            
            p.remainingTime -= executeTime;
            
            System.out.println("Executing P" + p.processID + " for " + executeTime + "ms. Time: " + currentTime);

            if (p.remainingTime == 0) {
                p.completionTime = currentTime;
                p.turnAroundTime = p.completionTime - p.arrivalTime;
                p.waitingTime = p.turnAroundTime - p.burstTime;
                System.out.println("================================================");
                System.out.println("P" + p.processID + " FINISHED.");
                
                this.removeProcess(p.processID); 
                
                if (this.head == null) break;
                
                current = this.head; 

            } else {
                current = current.next;
            }
            
            this.displayQueue();
        }  
        System.out.println("--- Simulation Complete ---");
    }
    public static void main(String[] args) {
        RoundRobinSA scheduler = new RoundRobinSA(2);

        scheduler.addProcess(new Process(1, 5, 1));
        scheduler.addProcess(new Process(2, 3, 2));
        scheduler.addProcess(new Process(3, 6, 1));
        
        scheduler.displayQueue();
        
        scheduler.simulateRoundRobin();
    }
}