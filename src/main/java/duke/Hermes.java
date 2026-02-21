package duke;
import java.util.Scanner;
import java.io.IOException;

import duke.command.Command;
import duke.command.ListCommand;
import duke.exceptions.Errors;
import duke.exceptions.HermesInvalidParameter;
import duke.exceptions.HermesMissingTime;
import duke.exceptions.HermesMissingDescription;

public class Hermes {
    static TaskList tasks = new TaskList();
    static UI ui = new UI();
    final static String filePath = "data/tasks.txt";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Storage storage = new Storage(filePath);
        Parser parser = new Parser();

        ui.hello();
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
            String[] components = message.split("\\s+", 2);
            try {
                Command c = parser.parse(components[0]);
                if (c instanceof ListCommand) {
                    c.execute("", tasks, ui);
                } else {
                    c.execute(components[1], tasks, ui);
                }
                storage.store(tasks);
            } catch (IllegalArgumentException e) {
                ui.errorMessage(Errors.INVALID_COMMAND);
            } catch (HermesMissingTime e) {
                ui.errorMessage(Errors.MISSING_TIME);
            } catch (HermesMissingDescription | ArrayIndexOutOfBoundsException e) {
                ui.errorMessage(Errors.MISSING_DESCRIPTION);
            } catch (HermesInvalidParameter e) {
                ui.errorMessage(Errors.NOT_NUMBER);
            } catch (IOException e) {
                System.out.println("some file error");
            }
            message = in.nextLine().strip();
        }

        ui.bye();
    }
}

