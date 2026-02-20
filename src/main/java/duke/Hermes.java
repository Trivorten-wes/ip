package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

import duke.command.CommandWord;
import duke.exceptions.HermesMissingTime;
import duke.exceptions.HermesMissingDescription;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Hermes {
    static ArrayList<Task> tasks = new ArrayList<>();
    static UI print = new UI();
    final static String filePath = "data/tasks.txt";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Storage storage = new Storage(filePath);

        print.hello();
        String message = in.nextLine().strip();

        try {
            tasks = storage.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (HermesMissingTime | HermesMissingDescription e) {
            System.out.println("File Corrupted!");
            throw new RuntimeException(e);
        }

        while (!message.equals("bye")) {

            String[] messageComponents = message.split("\\s+", 2);
            CommandWord commandWord;
            String taskDescription = "";

            print.display();
            message = in.nextLine();
        }

        print.bye();
    }

//    /**
//     * Executes the corresponding command
//     * based on the command word
//     * @param commandWord the command to be executed
//     * @param description task description and time frame
//     * @throws HermesMissingTime no time frame was given
//     * @throws HermesMissingDescription the task description is empty
//     */
//    public static void executeCommand(CommandWord commandWord, String description)
//            throws HermesMissingTime, HermesMissingDescription {
//
//        switch (commandWord) {
//        case LIST:
//            print.add("Here are your tasks:");
//            for (int i = 0; i < tasks.size(); i++) {
//                print.add((i + 1) + "." + tasks.get(i));
//            }
//            break;
//        case MARK:
//            int doneTask = Integer.parseInt(description) - 1;
//            tasks.get(doneTask).setDone(true);
//            tasks.get(doneTask).setDone(true);
//            print.add("Good Job! This is now marked as done:");
//            print.add(tasks.get(doneTask).toString());
//            break;
//        case UNMARK:
//            int undoneTask = Integer.parseInt(description) - 1;
//            tasks.get(undoneTask).setDone(false);
//            tasks.get(undoneTask).setDone(false);
//            print.add("Ok...This is now marked as undone");
//            print.add(tasks.get(undoneTask).toString());
//            break;
//        case DELETE:
//            int deleteTask = Integer.parseInt(description) - 1;
//            print.removeTask(tasks.get(deleteTask));
//            tasks.remove(deleteTask);
//            break;
//        default:
//            // Error cases already caught above
//            break;
//        }
//    }
//
//    public static class Storage {
//        private final File file;
//
//        public Storage(String filePath) {
//            file = new File(filePath);
//            try {
//                file.getParentFile().mkdirs();
//                file.createNewFile();
//            } catch (IOException e) {
//                System.out.println("Failed to create file in specified file path");
//            }
//        }
//
//        public ArrayList<Task> load()
//                throws FileNotFoundException, HermesMissingDescription, HermesMissingTime {
//            ArrayList<Task> tasks = new ArrayList<>();
//            Scanner s = new Scanner(file);
//            while (s.hasNext()) {
//                tasks.add(stringToTask(s.nextLine()));
//            }
//            return tasks;
//        }

//        public void store(ArrayList<Task> tasks) throws IOException {
//            try (FileWriter writer = new FileWriter(file)) {
//                for (Task task : tasks) {
//                    if (task != null) {
//                        writer.write(task + System.lineSeparator());
//                    }
//                }
//            }
//        }

//        private Task stringToTask(String string) throws HermesMissingTime, HermesMissingDescription {
//            boolean marked = string.charAt(4) == 'X';
//            String description = string.substring(6);
//            return switch (string.charAt(1)) {
//            case 'T' -> new Todo(description, marked);
//            case 'D' -> new Deadline(description, marked);
//            case 'E' -> new Event(description, marked);
//            default -> null;
//            };
//        }
}

