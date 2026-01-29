import java.util.Scanner;

public class Hermes {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = "       ___________________________________\n";
        String indent = "        ";
        String greeting = line + indent + "Hello! I'm Hermes the God of communication!\n"
                + indent + "What can I do for you?\n" + line;
        String bye = line + indent + "Bye! Hope to see you again soon!\n" + line;

        Task[] taskList = new Task[100];
        int index = 0;

        System.out.print(greeting);
        String message = in.nextLine();
        String doneness = " ";

        while (!message.equals("bye")) {
            if (message.equals("list")) {
                System.out.print(line);
                System.out.print(indent + "Your list of items:\n");
                for (int i = 0; i < index; i++) {
                    System.out.print(indent + (i+1) + ".");
                    taskList[i].printTask();
                }
                System.out.print(line);
            } else if (message.startsWith("mark ")) {
                int doneTask = Integer.parseInt(message.substring(5)) - 1;
                taskList[doneTask].mark();
                System.out.print(line + indent
                        + "Good Job! This is now marked as done:\n"
                        + indent + " ");
                taskList[doneTask].printTask();
            } else if (message.startsWith("unmark ")) {
                int doneTask = Integer.parseInt(message.substring(7)) - 1;
                taskList[doneTask].unmark();
                System.out.print(line + indent
                        + "Ok... this is now marked as undone:\n"
                        + indent + " ");
                taskList[doneTask].printTask();
            } else {
                System.out.print(line);
                System.out.println("        added: " + message);
                System.out.print(line);
                taskList[index] = new Task(message, false);
                index++;
            }
            message = in.nextLine();
        }

        System.out.print(bye);
    }
}
