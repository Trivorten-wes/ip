package duke;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import duke.exceptions.HermesMissingTime;
import duke.exceptions.HermesMissingDescription;
import duke.task.*;

import java.util.ArrayList;


import java.util.Scanner;

public class Hermes {
    static Task[] tasks = new Task[100];
    static int index = 0;
    static Printer print = new Printer();
    final static String filePath = "data/tasks.txt";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HermesFile file = new HermesFile(filePath);

        print.hello();
        String message = in.nextLine().strip();

        try {
            file.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (HermesMissingTime | HermesMissingDescription e) {
            System.out.println("File Corrupted!");
            throw new RuntimeException(e);
        }

        while (!message.equals("bye")) {
            String[] messageComponents = message.split("\\s+", 2);
            Command commandWord;
            String taskDescription = "";

            try {
                commandWord = Command.valueOf(messageComponents[0].toUpperCase());
                if (commandWord != Command.LIST) {
                    taskDescription = messageComponents[1].strip();
                }
                executeCommand(commandWord, taskDescription);
                file.write(tasks);
            } catch (IllegalArgumentException e) {
                print.add("I need proper instructions");
                print.add("Try Todo/Deadline/Event/Mark/Unmark");
            } catch (ArrayIndexOutOfBoundsException | HermesMissingDescription e) {
                print.add("You need to give me more details");
                print.add("about what task you want me to add");
            } catch (HermesMissingTime e) {
                print.add("I'm going to need a time and/or date");
            } catch (IOException e) {
                print.add("File error!");
            }
            print.display();
            message = in.nextLine();
        }

        print.bye();
    }

    /**
     * Executes the corresponding command
     * based on the command word
     * @param commandWord the command to be executed
     * @param description task description and time frame
     * @throws HermesMissingTime no time frame was given
     * @throws HermesMissingDescription the task description is empty
     */
    public static void executeCommand(Command commandWord, String description)
            throws HermesMissingTime, HermesMissingDescription {

        switch (commandWord) {
        case LIST:
            print.add("Here are your tasks:");
            for (int i = 0; i < index; i++) {
                print.add((i + 1) + "." + tasks[i]);
            }
            break;
        case MARK:
            int doneTask = Integer.parseInt(description) - 1;
            tasks[doneTask].mark();
            print.add("Good Job! This is now marked as done:");
            print.add(tasks[doneTask].toString());
            break;
        case UNMARK:
            int undoneTask = Integer.parseInt(description) - 1;
            tasks[undoneTask].unmark();
            print.add("Ok...This is now marked as undone");
            print.add(tasks[undoneTask].toString());
            break;
        case TODO:
            tasks[index] = new Todo(description);
            print.newTask(tasks[index], index);
            index++;
            break;
        case DEADLINE:
            tasks[index] = new Deadline(description);
            print.newTask(tasks[index], index);
            index++;
            break;
        case EVENT:
            tasks[index] = new Event(description);
            print.newTask(tasks[index], index);
            index++;
            break;
        default:
            // Error cases already caught above
            break;
        }
    }
}

