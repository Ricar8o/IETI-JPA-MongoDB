package eci.ieti.mongolab.data.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Todo {
    private String description;
    private int priority;
    private Date dueDate;
    private String responsible;
    private String status; 

    public Todo() {
    }

    public Todo(String description, int priority, Date dueDate, String responsible, String status) {
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

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
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
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); ; 
        return String.format(
            "Todo [description='%s', priority='%s', dueDate='%s', responsible='%s', status='%s']",
            description, priority, dateFormat.format(dueDate), responsible, status);
    }

}
