package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class HermesFile {
    private final File file;

    public HermesFile(String filePath) {
        file = new File(filePath);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to create file in specified file path");
        }
    }

    public ArrayList<Task> read() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
        return tasks;
    }

    public void write(Task[] tasks) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                if (task != null) {
                    writer.write(task.toString() + System.lineSeparator());
                }
            }
        }
    }
}
