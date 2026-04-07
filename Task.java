class Task {
    static int counter=1;
    int id;
    String subject;
    String topic;
    int totalMinutes;
    int remainingMinutes;
    int daysLeft;
    int priority;
    boolean completed;

    Task(String subject, String topic, int hours, int days, int priority) {
        this.id=counter++;
        this.subject = subject;
        this.topic = topic;
        this.totalMinutes = hours * 60;
        this.remainingMinutes = totalMinutes;
        this.daysLeft = days;
        this.priority = priority;
        this.completed = false;
    }
}