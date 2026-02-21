package duke;

import duke.exceptions.HermesMissingDescription;
import duke.exceptions.HermesMissingTime;
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

    public TaskList load()
            throws FileNotFoundException, HermesMissingDescription, HermesMissingTime {
        TaskList tasks = new TaskList();
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            tasks.add(stringToTask(s.nextLine()));
        }
        return tasks;
    }

    public void store(TaskList tasks) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i) != null) {
                    writer.write(tasks.get(i) + System.lineSeparator());
                }
            }
        }
    }

    private Task stringToTask(String string) throws HermesMissingTime, HermesMissingDescription {
        boolean marked = string.charAt(4) == 'X';
        String description = string.substring(7);
        return switch (string.charAt(1)) {
        case 'T' -> new Todo(description, marked);
        case 'D' -> new Deadline(description, marked);
        case 'E' -> new Event(description, marked);
        default -> null;
        };
    }
}
