package kr.ac.jejunu.service;

import kr.ac.jejunu.model.RemainTime;
import kr.ac.jejunu.model.ToDo;
import kr.ac.jejunu.model.ToDoStatus;
import kr.ac.jejunu.model.User;
import kr.ac.jejunu.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Boobby on 17-6-9.
 */
@Service
@Transactional
public class ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;


    public ToDo saveToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public List<ToDo> getUsersToDoList(User user, Sort sort) {
        return toDoRepository.findAllByUserAndToDoStatus(user, ToDoStatus.TO_DO, sort);
    }

    public List<ToDo> getUsersFailList(User user, Sort sort) {
        return toDoRepository.findAllByUserAndToDoStatus(user, ToDoStatus.FAIL, sort);
    }

    public List<ToDo> getUsersDoneList(User user, Sort sort) {
        return toDoRepository.findAllByUserAndToDoStatus(user, ToDoStatus.DONE, sort);
    }

    public void deleteToDo(Long toDoNo) {
        toDoRepository.delete(toDoNo);
    }

    public ToDo getToDo(Long toDoNo) {
        return toDoRepository.findOne(toDoNo);
    }

    /**
     * user의 TO_DO 상태인 List에서 계획일이 현재시간보다 이전일 경우 FAIL로 상태 변경
     *
     * @param user
     */
    public void checkAndUpdateUsersToDoListStatus(User user) {
        List<ToDo> toDoList = toDoRepository.findAllByUserAndToDoStatus(user, ToDoStatus.TO_DO);
        Date now = new Date();
        for (ToDo toDo : toDoList) {
            if (toDo.getPlanTime().compareTo(now) <= 0) {
                toDo.setToDoStatus(ToDoStatus.FAIL);
                toDoRepository.save(toDo);
            } else {
                toDo.setRemainTime(calculateRemainTime(toDo.getPlanTime()));
            }
        }
    }

    private RemainTime calculateRemainTime(Date planTime) {
        Calendar now = Calendar.getInstance();
        Calendar plan = Calendar.getInstance();
        plan.setTime(planTime);

        int year = plan.get(Calendar.YEAR) - now.get(Calendar.YEAR);
        int month = plan.get(Calendar.MONTH) - now.get(Calendar.MONTH);
        int date = plan.get(Calendar.DATE) - now.get(Calendar.DATE);
        int hour = plan.get(Calendar.HOUR) - now.get(Calendar.HOUR);
        int minute = plan.get(Calendar.MINUTE) - now.get(Calendar.MINUTE);

        if (minute < 0) {
            hour = hour - 1;
            minute = minute + 60;
        }
        if (hour < 0) {
            date = date - 1;
            hour = hour + 24;
        }
        if (date < 0) {
            month = month - 1;
            date = date + 30; // 31일인 경우는... 어떵해야되지
        }
        if (month < 0) {
            year = year - 1;
            month = month + 12;
        }

        RemainTime remainTime1 = new RemainTime(year, month, date, hour, minute);
        RemainTime remainTime = remainTime1;

        return remainTime;
    }

    public void updateStatusToDone(Long toDoNo) {
        ToDo toDo = toDoRepository.findOne(toDoNo);
        toDo.setToDoStatus(ToDoStatus.DONE);

        toDoRepository.save(toDo);
    }

    public void updateStatusToFail(Long toDoNo) {
        ToDo toDo = toDoRepository.findOne(toDoNo);
        toDo.setToDoStatus(ToDoStatus.FAIL);

        toDoRepository.save(toDo);
    }

    public List<ToDo> getUsersAllToDoList(User user) {
        return toDoRepository.findAllByUser(user);
    }

    public void updateToDo(ToDo oldToDo) {
        toDoRepository.save(oldToDo);
    }
}
