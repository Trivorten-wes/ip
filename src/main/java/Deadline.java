public class Deadline extends Task {
    private String by;

    public void setBy(String date) {
        by = date;
    }
    public Deadline(String description) throws HermesMissingDetails {
        String[] components = description.split("/by ");
        if (components.length <= 1) {
            throw new HermesMissingDetails();
        }
        setDescription(components[0]);
        setBy(components[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
