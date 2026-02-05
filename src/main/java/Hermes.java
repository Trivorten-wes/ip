import java.util.Scanner;

public class Hermes {
    static String[] linesToPrint = new String[100];
    static int numOfLines = 0;
    /**
     * Adds a line to print queue
     * @param line line to be added
     *             to print queue
     */
    public static void addToPrint(String line) {
        linesToPrint[numOfLines] = line;
        numOfLines++;
    }

    /**
     * Prints all the lines
     * in the print queue
     */
    public static void printWithLine() {
        String horizontalLine = "       ___________________________________\n";
        String indent = "        ";
        System.out.print(horizontalLine);
        for (int i = 0; i < numOfLines; i++) {
            System.out.println(indent + linesToPrint[i]);
        }
        System.out.print(horizontalLine);
        numOfLines = 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String greetings1 = "Hello! I'm Hermes the God of communication!";
        String greetings2 = "What can I do for you?";
        String bye = "Bye! Hope to see you again soon!";

        Task[] tasks = new Task[100];
        int index = 0;

        addToPrint(greetings1);
        addToPrint(greetings2);
        printWithLine();
        String message = in.nextLine();

        while (!message.equals("bye")) {
            if (message.equals("list")) {
                addToPrint("Here are your tasks:");
                for (int i = 0; i < index; i++) {
                    addToPrint ((i+1) + "."  + tasks[i]);
                }
            } else if (message.startsWith("mark ")) {
                int doneTask = Integer.parseInt(message.substring(5)) - 1;
                tasks[doneTask].mark();
                addToPrint("Good Job! This is now marked as done:");
                addToPrint(tasks[doneTask].toString());
            } else if (message.startsWith("unmark ")) {
                int undoneTask = Integer.parseInt(message.substring(7)) - 1;
                tasks[undoneTask].unmark();
                addToPrint("Ok...This is now marked as undone");
                addToPrint(tasks[undoneTask].toString());
            } else if (message.startsWith("todo ")){
                tasks[index] = new Todo(message);
                index++;
                addToPrint("Ok I have added this to your tasks:");
                addToPrint(" " + tasks[index - 1]);
                addToPrint("Now you have " + index + " in your list");
            } else if (message.startsWith("deadline ") && message.contains("/by ")) {
                tasks[index] = new Deadline(message);
                index++;
                addToPrint("Ok I have added this to your tasks:");
                addToPrint(" " + tasks[index - 1]);
                addToPrint("Now you have " + index + " tasks in your list");
            } else if (message.startsWith("event ")
                    && message.contains("/from ")
                    && message.contains("/to")) {
                tasks[index] = new Event(message);
                index++;
                addToPrint("Ok I have added this to your tasks:");
                addToPrint(" " + tasks[index - 1]);
                addToPrint("Now you have " + index + " tasks in your list");
            } else {
                addToPrint("Sorry I don't understand");
            }
            printWithLine();
            message = in.nextLine();
        }

        System.out.print(bye);
    }
}
