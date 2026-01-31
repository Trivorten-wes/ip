public class Deadline extends Task {
    private String by;

    public void setBy(String date) {
        by = date;
    }
    public Deadline(String description) {
        String[] components = description.split("deadline |/by ");
        setDescription(components[1]);
        setBy(components[2]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
