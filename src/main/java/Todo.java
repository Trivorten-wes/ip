public class Todo extends Task {

    public Todo(String description) {
        String[] components = description.split("todo");
        setDescription(components[1]);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
