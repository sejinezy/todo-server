package org.example.web;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constants.TaskStatus;
import org.example.model.Task;
import org.example.model.TaskRequest;
import org.example.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * 새로운 할 일 추가
     *
     * @param request 추가하고자 하는 할 일
     * @return 추가된 할 일
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest request) {
        Task result = taskService.add(request.getTitle(), request.getDescription(), request.getDueDate());
        return ResponseEntity.ok(result); // 상태코드 200
    }

    /**
     * 특정 마감일에 해당하는 할일 목록 반환
     * @param dueDate 할일의 마감일
     * @return 마감일에 해당하는 할일 목록
     */
    @GetMapping
    public ResponseEntity<List<Task>> getTask(Optional<String> dueDate) {
        List<Task> result;

        if (dueDate.isPresent()) {
            result = this.taskService.getByDueDate(dueDate.get());
        } else {
            result = this.taskService.getAll();
        }

        return ResponseEntity.ok(result);
    }


    /**
     * 특정 ID에 해당하는 할일을 조회
     * @param id 할일 ID
     * @return ID에 해당하는 할일 객체
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> fetchOneTask(@PathVariable long id) {
        Task result = taskService.getOne(id);
        return ResponseEntity.ok(result);
    }


    /**
     * 특정 상태에 해당하는 할일 목록을 반환
     * @param status 할일 상태
     * @return 상태에 해당하는 할일 목록
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getByStatus(@PathVariable TaskStatus status) {
        List<Task> result = taskService.getByStatus(status);
        return ResponseEntity.ok(result);

    }

}
