/**
 * Class used to track the tasks the user
 * inputs into the list
 *
 * @attribute isDone if the task is marked.
 * @attribute activity what the user needs to do.
 */

public class Task {
    private boolean isDone;
    private String activity;

    public Task() {
        isDone = false;
        activity = null;
    }

    public Task(String activity, boolean done) {
        this.activity = activity;
        this.isDone = done;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getActivity() {
        return activity;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public void printTask() {
        if (isDone) {
            System.out.print("[X] ");
        } else {
            System.out.print("[ ] ");
        }
        System.out.println(activity);
    }
}
