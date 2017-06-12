package kr.ac.jejunu.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Boobby on 17-6-9.
 */
@Entity(name = "todo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @JoinColumn(name = "userinfo_no")
    @ManyToOne
    private User user;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ToDoCategory category;

    @Column(name = "title")
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "plan_time")
    private Date planTime;

    @Column(name = "text")
    private String text;

    @Column(name = "todo_status", length = 30, columnDefinition = "varchar(30) default 'TO_DO'")
    @Enumerated(EnumType.STRING)
    private ToDoStatus toDoStatus = ToDoStatus.TO_DO;

    @Transient
    private RemainTime remainTime;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setCategory(ToDoCategory category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ToDoCategory getCategory() {
        return category;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ToDoStatus getToDoStatus() {
        return toDoStatus;
    }

    public void setToDoStatus(ToDoStatus toDoStatus) {
        this.toDoStatus = toDoStatus;
    }

    public RemainTime getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(RemainTime remainTime) {
        this.remainTime = remainTime;
    }
}
