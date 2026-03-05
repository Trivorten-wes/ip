package duke;

import duke.command.AddCommand;
import duke.exceptions.HermesMissingDescription;
import duke.exceptions.HermesInvalidTime;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        file = new File(filePath);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to create file in specified file path");
        }
    }

    /**
     * Returns a list of tasks that is read in
     * from a file that is used to store data
     * @return Returns the list of task
     * @throws FileNotFoundException Error if the file cannot be found
     * @throws HermesMissingDescription Error if the information in the file
     *                                  has missing details
     * @throws HermesInvalidTime Error if the file has a wrong date format in it
     */
    public TaskList load()
            throws FileNotFoundException, HermesMissingDescription, HermesInvalidTime {
        TaskList tasks = new TaskList();
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            tasks.add(stringToTask(s.nextLine()));
        }
        return tasks;
    }

    /**
     * Writes the task list into the file
     * @param tasks The list of tasks to be written
     * @throws IOException Error if there is an IO issue
     */
    public void store(TaskList tasks) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i) != null) {
                    writer.write(tasks.get(i) + System.lineSeparator());
                }
            }
        }
    }

    /**
     * Returns a task that is converted from
     * a string that is from the file
     * @param string The string from the file
     * @return Returns the task converted from the string
     * @throws HermesInvalidTime Error if the string has a date of an invalid format
     * @throws HermesMissingDescription Error if the task has missing details
     */
    private Task stringToTask(String string) throws HermesInvalidTime, HermesMissingDescription {
        boolean marked = string.charAt(4) == 'X';
        String description = string.substring(7);
        AddCommand command = switch (string.charAt(1)) {
        case 'T' -> new AddCommand(TaskType.TODO);
        case 'D' -> new AddCommand(TaskType.DEADLINE);
        case 'E' -> new AddCommand(TaskType.EVENT);
        default -> throw new HermesMissingDescription();
        };
        return command.silentExecute(description, marked, true);
    }
}
