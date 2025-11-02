class TextState {
    public String content;

    public TextState(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}

class StateNode {
    public TextState data;
    public StateNode next;
    public StateNode prev;

    public StateNode(TextState data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class UndoRedo {
    public StateNode head;
    public StateNode tail;
    public StateNode currentState;
    public int historyLimit;
    public int size;

    public UndoRedo(int limit) {
        this.head = null;
        this.tail = null;
        this.currentState = null;
        this.historyLimit = limit;
        this.size = 0;
    }

    private int getEffectiveSize() {
        int count = 0;
        StateNode temp = this.head;
        while(temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    private void limitHistory() {
        while (this.size > this.historyLimit) {
            if (this.head == null || this.head == this.tail) return; 

            StateNode newHead = this.head.next;
            
            if (newHead != null) {
                newHead.prev = null;
            }
            this.head = newHead;
            this.size--;
            
            if (this.head == null) {
                this.tail = null;
                this.currentState = null;
            }
            System.out.println("LIMIT: Oldest state pruned.");
        }
    }

    public void addNewState(String newContent) {
        TextState state = new TextState(newContent);
        StateNode newNode = new StateNode(state);

        if (currentState != null && currentState.next != null) {
            currentState.next = null;
            this.tail = currentState;
            this.size = getEffectiveSize(); 
        }

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }

        this.currentState = newNode;
        this.size++;

        limitHistory();
        System.out.println("ACTION: New state recorded: " + newContent.substring(Math.max(0, newContent.length() - 15)));
    }

    public void undo() {
        if (currentState == null || currentState.prev == null) {
            System.out.println("UNDO: Cannot undo. Reached initial state.");
            return;
        }
        currentState = currentState.prev;
        System.out.println("UNDO: Reverted to previous state.");
    }

    public void redo() {
        if (currentState == null || currentState.next == null) {
            System.out.println("REDO: Cannot redo. Reached the latest state.");
            return;
        }
        currentState = currentState.next;
        System.out.println("REDO: Advanced to next state.");
    }

    public void displayCurrentState() {
        if (currentState == null) {
            System.out.println("\nCURRENT TEXT: [Empty]");
        } else {
            System.out.println("\nCURRENT TEXT: " + currentState.data.content);
        }
    }

    public static void main(String[] args) {
        UndoRedo editor = new UndoRedo(3);

        editor.addNewState("1. Start"); 
        editor.addNewState("2. Add World.");
        editor.addNewState("3. Add Today"); 
        editor.displayCurrentState();

        editor.addNewState("4. Final");
        editor.displayCurrentState();

        editor.undo();
        editor.displayCurrentState(); 
        editor.undo();
        editor.displayCurrentState(); 
        
        editor.redo();
        editor.displayCurrentState();
        
        editor.undo();
        editor.displayCurrentState(); 
        
        editor.addNewState("5. NEW MID-STATE."); 
        editor.displayCurrentState(); 
        
        editor.redo();
        editor.undo();
        editor.displayCurrentState();
    }
}