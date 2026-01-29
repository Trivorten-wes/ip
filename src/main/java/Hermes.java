import java.util.Scanner;

public class Hermes {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = "       ___________________________________\n";
        String indent = "        ";
        String greeting = line + indent + "Hello! I'm Hermes the God of communication!\n"
                + indent + "What can I do for you?\n" + line;
        String bye = line + indent + "Bye! Hope to see you again soon!\n" + line;

        String[] list = new String[100];
        int index = 0;

        System.out.print(greeting);
        String message = in.nextLine();

        while (!message.equals("bye")) {
            if (message.equals("list")) {
                System.out.print(line);
                for (int i = 0; i < index; i++) {
                    System.out.println(indent + (i+1) + ". " + list[i]);
                }
                System.out.print(line);
            } else {
                System.out.print(line);
                System.out.println("        added: " + message);
                System.out.print(line);
                list[index] = message;
                index++;
            }
            message = in.nextLine();
        }

        System.out.print(bye);
    }
}
