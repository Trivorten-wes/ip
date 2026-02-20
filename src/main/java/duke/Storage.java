package duke;

import duke.exceptions.HermesMissingDescription;
import duke.exceptions.HermesMissingTime;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
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

    public ArrayList<Task> load()
            throws FileNotFoundException, HermesMissingDescription, HermesMissingTime {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            tasks.add(stringToTask(s.nextLine()));
        }
        return tasks;
    }

    public void store(ArrayList<Task> tasks) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                if (task != null) {
                    writer.write(task + System.lineSeparator());
                }
            }
        }
    }

    private Task stringToTask(String string) throws HermesMissingTime, HermesMissingDescription {
        boolean marked = string.charAt(4) == 'X';
        String description = string.substring(6);
        return switch (string.charAt(1)) {
        case 'T' -> new Todo(description, marked);
        case 'D' -> new Deadline(description, marked);
        case 'E' -> new Event(description, marked);
        default -> null;
        };
    }
}
