import java.util.Scanner;

public class Hermes {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Printer print = new Printer();

        Task[] tasks = new Task[100];
        int index = 0;

        print.hello();
        String message = in.nextLine().strip();

        while (!message.equals("bye")) {

            String[] messageComponents = message.split("\\s+", 2);
            Command commandWord = Command.NULL;
            String taskDescription = "";

            try {
                commandWord = Command.valueOf(messageComponents[0].toUpperCase());
                taskDescription = messageComponents[1].strip();
            } catch (IllegalArgumentException e) {
                print.add("That is not a valid command");
            } catch (ArrayIndexOutOfBoundsException e) {
                print.add("I'm going to need more details");
            }

            try {
                switch (commandWord) {
                case LIST:
                    print.add("Here are your tasks:");
                    for (int i = 0; i < index; i++) {
                        print.add((i + 1) + "." + tasks[i]);
                    }
                    break;
                case MARK:
                    int doneTask = Integer.parseInt(message.substring(5)) - 1;
                    tasks[doneTask].mark();
                    print.add("Good Job! This is now marked as done:");
                    print.add(tasks[doneTask].toString());
                    break;
                case UNMARK:
                    int undoneTask = Integer.parseInt(message.substring(7)) - 1;
                    tasks[undoneTask].unmark();
                    print.add("Ok...This is now marked as undone");
                    print.add(tasks[undoneTask].toString());
                    break;
                case TODO:
                    tasks[index] = new Todo(taskDescription);
                    print.newTask(tasks[index], index);
                    index++;
                    break;
                case DEADLINE:
                    tasks[index] = new Deadline(taskDescription);
                    print.newTask(tasks[index], index);
                    index++;
                    break;
                case EVENT:
                    tasks[index] = new Event(taskDescription);
                    print.newTask(tasks[index], index);
                    index++;
                    break;
                default:
                    // Error cases already caught above
                    break;
                }
            } catch (HermesMissingDetails e) {
                print.add("I'm going to need more details");
            }
            print.display();
            message = in.nextLine();
        }

        print.bye();
    }
}

