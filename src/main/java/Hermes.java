import java.util.Scanner;

public class Hermes {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Printer print = new Printer();
        String greetings1 = "Hello! I'm Hermes the God of communication!";
        String greetings2 = "What can I do for you?";
        String bye = "Bye! Hope to see you again soon!";

        Task[] tasks = new Task[100];
        int index = 0;

        print.add(greetings1);
        print.add(greetings2);
        print.display();
        String message = in.nextLine();

        while (!message.equals("bye")) {
            if (message.equals("list")) {
                print.add("Here are your tasks:");
                for (int i = 0; i < index; i++) {
                    print.add((i+1) + "."  + tasks[i]);
                }
            } else if (message.startsWith("mark ")) {
                int doneTask = Integer.parseInt(message.substring(5)) - 1;
                tasks[doneTask].mark();
                print.add("Good Job! This is now marked as done:");
                print.add(tasks[doneTask].toString());
            } else if (message.startsWith("unmark ")) {
                int undoneTask = Integer.parseInt(message.substring(7)) - 1;
                tasks[undoneTask].unmark();
                print.add("Ok...This is now marked as undone");
                print.add(tasks[undoneTask].toString());
            } else if (message.startsWith("todo ")){
                tasks[index] = new Todo(message);
                print.newTask(tasks[index], index + 1);
                index++;
            } else if (message.startsWith("deadline ") && message.contains("/by ")) {
                tasks[index] = new Deadline(message);
                print.newTask(tasks[index],index + 1);
                index++;
            } else if (message.startsWith("event ")
                    && message.contains("/from ")
                    && message.contains("/to")) {
                tasks[index] = new Event(message);
                print.newTask(tasks[index],index + 1);
                index++;
            } else {
                print.add("Sorry I don't understand");
            }
            print.display();
            message = in.nextLine();
        }

        System.out.print(bye);
    }
}
