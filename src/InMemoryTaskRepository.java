import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryTaskRepository implements TaskRepository {

    private List<Task> tasks = new ArrayList<>();
    private int nextId = 0;

    @Override
    public Task add(String title) {
        Task task = new Task(nextId, title);
        tasks.add(task);
        nextId++;
        return task;
    }

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public Task findById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        Iterator<Task> iterator = tasks.iterator();

        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
