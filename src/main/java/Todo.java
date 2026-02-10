public class Todo extends Task {

    public Todo(String description) throws HermesMissingDescription {
        if (description.isEmpty()) {
            throw new HermesMissingDescription();
        }
        setDescription(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
