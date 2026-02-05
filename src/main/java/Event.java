public class Event extends Task {
    private String start;
    private String end;

    public void setStart(String date) {
        start = date;
    }
    public void setEnd(String date) {
        end = date;
    }
    public Event(String description) {
        String[] components = description.split("event |/from |/to ");
        setDescription(components[1]);
        setStart(components[2]);
        setEnd(components[3]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(from: " + start
                + " to: " + end +")";
    }

}
