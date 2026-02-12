package duke;

import duke.task.Task;

public class Printer {
    private final String[] linesToPrint;
    private int numOfLines;

    String greetings1 = "Hello! I'm Hermes the God of communication!";
    String greetings2 = "What can I do for you?";
    String bye = "Bye! Hope to see you again soon!";

    Printer() {
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
        add(greetings1);
        add(greetings2);
        display();
    }

    /**
     * Prints goodbye message
     */
    public void bye() {
        add(bye);
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
    }
}
