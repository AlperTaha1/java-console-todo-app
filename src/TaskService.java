import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class TaskService {

    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void addTask(String title) {

        List<Task> tasks = repository.findAll();

        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(title)) {
                throw new DuplicateTaskException("Task with title '" + title + "' already exists.");
            }
        }
        repository.add(title);

    }

    public List<Task> listTasks() {
        List<Task> tasks = repository.findAll();
        return tasks;
    }

    public void startTask(int id) {
        Task task = repository.findById(id);

        if (task == null) {
            throw new TaskNotFoundException("Task not found with ID: " + id);
        }

        if (task.getStatus() == TaskStatus.DONE) {
            throw new InvalidTaskStateException("Cannot start a completed task.");
        }

        if (task.getStatus() == TaskStatus.TODO) {
            task.setStatus(TaskStatus.IN_PROGRESS);
        }
    }


    public void completeTask(int id) {
        Task task = repository.findById(id);

        if (task == null) {
            throw new TaskNotFoundException("Task not found with ID: " + id);
        }


        if (task.getStatus() != TaskStatus.IN_PROGRESS) {
            throw new InvalidTaskStateException("Task can only be completed if it is in IN_PROGRESS state.");
        }

        task.setStatus(TaskStatus.DONE);
    }


    public void deleteTask(int id) {
        boolean isDeleted = repository.deleteById(id);
        if (!isDeleted) {
            throw new TaskNotFoundException("Couldn't find task to delete. ID: " + id);
        }
    }


}
