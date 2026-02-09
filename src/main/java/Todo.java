public class Todo extends Task {

    public Todo(String description) throws HermesMissingDetails {
        setDescription(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
