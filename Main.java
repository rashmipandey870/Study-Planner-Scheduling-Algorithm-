import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Planner planner = new Planner();

        int choice;

        do {
            System.out.println("\n========== STUDY PLANNER ==========");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Generate Weekly Schedule");
            System.out.println("4. Mark Task Completed");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();

                    System.out.print("Subject: ");
                    String subject = sc.nextLine();

                    System.out.print("Topic: ");
                    String topic = sc.nextLine();

                    System.out.print("Hours Required: ");
                    int hours = sc.nextInt();

                    System.out.print("Days Left: ");
                    int days = sc.nextInt();

                    System.out.print("Priority (1-High, 2-Medium, 3-Low): ");
                    int priority = sc.nextInt();

                    Task t = new Task(subject, topic, hours, days, priority);
                    planner.tasks.add(t);

                    System.out.println("Task Added Successfully!");
                    break;

                case 2:
                    System.out.println("\n===== ALL TASKS =====");
                    System.out.println("ID  | Subject        | Topic              | Status");
                    System.out.println("------------------------------------------------------");

                    for (Task task : planner.tasks) {
                        String status = task.completed ? "Done" : "Pending";

                        System.out.printf(
                                "%-3d | %-14s | %-18s | %s\n",
                                task.id,
                                task.subject,
                                task.topic,
                                status
                        );
                    }
                    break;

                case 3:
                    planner.generateWeeklySchedule();
                    break;

                case 4:
                    System.out.print("Enter Task ID: ");
                    int id = sc.nextInt();

                    boolean found = false;

                    for (Task task : planner.tasks) {
                        if (task.id == id) {
                            task.completed = true;
                            found = true;
                            System.out.println("Task marked as completed");
                        }
                    }

                    if (!found) {
                        System.out.println("Task ID not found");
                    }
                    break;

            }

        } while (choice != 5);

        System.out.println("Exiting program");
    }
}