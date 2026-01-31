public class Todo extends Task {

    public Todo(String description) {
        String[] components = description.split("todo ");
        setDescription(components[1]);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
