package eci.ieti.mongolab.data.model;

public class Todo {
    private String description;
    private int priority;
    private String dueDate;
    private String responsible;
    private String status; 

    public Todo() {
    }

    public Todo(String description, int priority, String dueDate, String responsible, String status) {
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.responsible = responsible;
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getResponsible() {
        return this.responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(
            "Todo [description=%s, priority='%s', dueDate='%s, responsible='%s', status='%s']",
            description, priority, dueDate, responsible, status);
    }

}
