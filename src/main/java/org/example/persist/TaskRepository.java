package org.example.persist;

import java.util.Date;
import java.util.List;
import org.example.constants.TaskStatus;
import org.example.persist.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByDueDate(Date dueDate);

    List<TaskEntity> findAllByStatus(TaskStatus status);

}
