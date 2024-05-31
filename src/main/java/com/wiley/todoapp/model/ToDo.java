package com.wiley.todoapp.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Entity
@Table (name="todo")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column
    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @Column
    @NotNull(message = "Date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column
    @NotEmpty(message = "Status cannot be empty")
    private String status;

    public ToDo() {
    }

    public ToDo(long id, String title, Date date, String status) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
