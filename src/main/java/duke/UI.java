package duke;

import duke.exceptions.Errors;
import duke.task.Task;

public class UI {
    private final String[] linesToPrint;
    private int numOfLines;

    UI() {
        linesToPrint = new String[100];
        numOfLines = 0;
    }

    /**
     * Adds a line to print queue
     * @param line line to be added
     *             to print queue
     */
    public void add(String line) {
        linesToPrint[numOfLines] = line;
        numOfLines++;
    }

    /**
     * Prints all the lines
     * in the print queue
     */
    public void display() {
        String horizontalLine = "       ___________________________________\n";
        String indent = "        ";
        System.out.print(horizontalLine);
        for (int i = 0; i < numOfLines; i++) {
            System.out.println(indent + linesToPrint[i]);
        }
        System.out.print(horizontalLine);
        numOfLines = 0;
    }

    /**
     * Prints hello message
     */
    public void hello() {
        add("Hello! I'm Hermes the God of communication!");
        add("What can I do for you?");
        display();
    }

    /**
     * Prints goodbye message
     */
    public void bye() {
        add("Bye! I'm out!");
        display();
    }

    /**
     * Prints the message associated
     * with adding a new task
     * @param task task that has been added
     * @param numOfItems no. items (starts at 0)
     */
    public void newTask(Task task, int numOfItems) {
        add("Ok I have added this to your tasks:");
        add(" " + task.toString());
        add("Now you have " + (numOfItems + 1) + " tasks in your list");
        display();
    }

    /**
     * Prints the message associated
     * with removing a task
     * @param task task that has been removed
     */
    public void removeTask(Task task) {
        add("Got lazy and removed a task?");
        add(" " + task.toString());
        display();
    }

    /**
     * Prints the message associated
     * with marking a task as done
     * @param task task that is done
     */
    public void mark(Task task) {
        add("Finally got to doing work!");
        add(task.toString());
        display();
    }

    /**
     * Prints the message associated with
     * marking a task as not done
     * @param task task that is unmarked
     */
    public void unmark(Task task) {
        add("How can you even undo this");
        add(task.toString());
        display();
    }

    /**
     * Prints all the tasks in the list
     * @param tasks list of tasks to be printed
     */
    public void list(TaskList tasks) {
        add("This is all you have right now");
        for (int i = 0; i < tasks.size(); i++) {
            add((i + 1) + "." + tasks.get(i));
        }
        display();
    }

    /**
     * Prints an error message based
     * on the error
     * @param e
     */
    public void errorMessage(Errors e) {
        switch(e) {
        case NOT_NUMBER -> add("I'm going to need a number");
        case INVALID_COMMAND -> add("That is out of my realm, I need a different command");
        case MISSING_TIME -> add("Time should be dd/MM/yyyy");
        case MISSING_DESCRIPTION -> add("I need a description of the task");
        }
        display();
    }

    /**
     * Prints all the tasks in the list
     * @param tasks The lists of tasks to be printed
     */
    public void showFound(TaskList tasks) {
        add("*Snap* Here you go");
        for (int i = 0; i < tasks.size(); i++) {
            add((i + 1) + "." + tasks.get(i));
        }
        display();
    }
}
