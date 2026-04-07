import java.util.*;

public class Planner {

    ArrayList<Task> tasks = new ArrayList<>();

    public void generateWeeklySchedule() {

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int dailyLimit = 360;

        System.out.println("\n========== WEEKLY STUDY PLAN ==========");

        for (int d = 0; d < 7; d++) {

            int remainingTime = dailyLimit;

            System.out.println("\n--- " + days[d] + " ---");
            System.out.println("ID  | Subject        | Topic              | Time");
            System.out.println("------------------------------------------------------");

            tasks.sort((a, b) -> calculateScore(b) - calculateScore(a));

            for (Task t : tasks) {

                if (t.completed || t.daysLeft <= 0 || remainingTime <= 0)
                    continue;

                int base = t.remainingMinutes / t.daysLeft;

                int variation = (d % 3) * 10;

                int assigned = Math.max(base - variation, 30);
                assigned = Math.min(assigned, remainingTime);

                System.out.printf(
                        "%-3d | %-14s | %-18s | %4d min\n",
                        t.id,
                        t.subject,
                        t.topic,
                        assigned
                );

                t.remainingMinutes -= assigned;
                t.daysLeft--;
                remainingTime -= assigned;

                if (t.remainingMinutes <= 0) {
                    t.completed = true;
                }
            }

            if (remainingTime > 0) {
                System.out.println("------------------------------------------------------");
                System.out.println("Revision / Buffer Time: " + remainingTime + " min");
            }

            if (d == 6) {
                System.out.println("Note: Light day for revision or mock test");
            }
        }

        showInsights();
    }

    public int calculateScore(Task t) {

        int baseNeed = t.remainingMinutes / t.daysLeft;
        int priorityWeight = (4 - t.priority) * 20;

        int urgencyBoost = 0;

        if (t.daysLeft <= 2)
            urgencyBoost = 40;
        else if (t.daysLeft <= 4)
            urgencyBoost = 20;

        return baseNeed + priorityWeight + urgencyBoost;
    }

    public void showInsights() {

        int totalRemaining = 0;
        int totalDays = 0;

        for (Task t : tasks) {
            if (!t.completed) {
                totalRemaining += t.remainingMinutes;
                totalDays += t.daysLeft;
            }
        }

        if (totalDays == 0)
            return;

        int avg = totalRemaining / totalDays;

        System.out.println("\n========== INSIGHTS ==========");

        if (avg > 360) {
            System.out.println("Heavy workload detected");
        } else if (avg > 250) {
            System.out.println("Moderate workload");
        } else {
            System.out.println("You are on track");
        }
    }
}