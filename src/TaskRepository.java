import java.util.List;

public interface TaskRepository {

    Task add(String title);

    List<Task> findAll();

    Task findById(int id);

    boolean deleteById(int id);
}
