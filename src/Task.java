public class Task {

    private int id;
    private String taskName;
    private TaskStatus status;

    public Task(int id, String taskName){
        this.id = id;
        this.taskName = taskName;
        this.status = TaskStatus.TODO;
    }

    public int getId(){
        return id;
    }
    public String getTaskName(){
        return taskName;
    }
    public TaskStatus getStatus(){
        return status;
    }

    public void setStatus(TaskStatus status){
        this.status = status;
    }
}
