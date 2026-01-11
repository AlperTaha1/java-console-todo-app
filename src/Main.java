import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Task Management System Started ===");
        int id;
        boolean result;
        Scanner scanner = new Scanner(System.in);
        boolean working = true;

        TaskRepository repository = new InMemoryTaskRepository();
        TaskService taskService = new TaskService(repository);

        while(working){

            System.out.println("1-Add Task");
            System.out.println("2-Start Task");
            System.out.println("3-List Tasks");
            System.out.println("4-Complete Task");
            System.out.println("5-Delete Task");
            System.out.println("0-Exit \n");

            int select = scanner.nextInt();

            scanner.nextLine(); // clear buffer

            switch(select){
                case 1:
                    System.out.println("Task title:");
                    String task = scanner.nextLine();

                    try {
                        taskService.addTask(task);
                        System.out.println("Task added!");
                    } catch (DuplicateTaskException e) {
                        System.out.println("Error: " + e.getMessage());
                    }


                    break;

                case 2:
                    System.out.print("Enter task ID to start: ");
                    id = scanner.nextInt();

                    try {
                        taskService.startTask(id);
                        System.out.println("Task started successfully!");
                    } catch (TaskNotFoundException | InvalidTaskStateException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    List<Task> allTasks = taskService.listTasks();
                    if (allTasks.isEmpty()) {
                        System.out.println("There are no tasks to list.");
                    } else {
                        for (Task t : allTasks) {
                            System.out.println(t.getId() + " - " + t.getTaskName() + " - " + t.getStatus());
                        }
                    }
                    break;

                case 4: System.out.print("Which task do you want to complete?: ");
                    id = scanner.nextInt();

                    try {
                        taskService.completeTask(id);
                        System.out.println("Task completed");
                    } catch (TaskNotFoundException | InvalidTaskStateException e) {
                        System.out.println("Error: " + e.getMessage());
                    }


                    break;

                case 5:
                    System.out.print("Enter the ID of the task to delete: "); // Daha kibar bir İngilizce
                    id = scanner.nextInt();

                    try {
                        taskService.deleteTask(id);
                        System.out.println("Task deleted successfully.");
                    } catch (TaskNotFoundException e) {
                        System.out.println("Error: " + e.getMessage()); // Artık hatayı ekrana basıyoruz
                    }
                    break;

                case 0:
                    working = false;
                    break;

                    default: System.out.println("Wrong choice");
            }
        }

    }
}