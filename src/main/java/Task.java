public class Task {
    private boolean done;
    private String activity;

    public Task() {
        done = false;
        activity = null;
    }

    public Task(String activity, boolean done) {
        this.activity = activity;
        this.done = done;
    }

    public boolean getDone() {
        return done;
    }

    public String getActivity() {
        return activity;
    }

    public void mark() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    public void printTask() {
        if (done) {
            System.out.print("[X] ");
        } else {
            System.out.print("[ ] ");
        }
        System.out.println(activity);
    }
}
