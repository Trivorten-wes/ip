import java.util.Scanner;

public class Hermes {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = "        ___________________________________\n";
        String greeting = "Hello! I'm Hermes!\n"
                + "        What can I do for you?";
        String bye = line + "        Bye! Hope to see you again soon!\n" + line;

        String message = greeting;
        do {
            System.out.print(line);
            System.out.println("        " + message);
            System.out.print(line);
            message = in.nextLine();
        } while (!message.equals("bye"));

        System.out.print(bye);
    }
}
