public class Printer {
    private String[] linesToPrint;
    private int numOfLines;

    Printer() {
        linesToPrint = new String[100];
        numOfLines = 0;
    }

    /**
     * Adds a line to print queue
     * @param line line to be added
     *             to print queue
     */
    public void add(String line) {
        linesToPrint[numOfLines] = line;
        numOfLines++;
    }

    /**
     * Prints all the lines
     * in the print queue
     */
    public void display() {
        String horizontalLine = "       ___________________________________\n";
        String indent = "        ";
        System.out.print(horizontalLine);
        for (int i = 0; i < numOfLines; i++) {
            System.out.println(indent + linesToPrint[i]);
        }
        System.out.print(horizontalLine);
        numOfLines = 0;
    }

    public void newTask(Task task, int numOfItems) {
        add("Ok I have added this to your tasks:");
        add(" " + task.toString());
        add("Now you have " + numOfItems + " tasks in your list");
    }
}
