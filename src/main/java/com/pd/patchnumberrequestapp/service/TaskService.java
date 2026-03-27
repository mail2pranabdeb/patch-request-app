package com.pd.patchnumberrequestapp.service;

import com.pd.patchnumberrequestapp.model.PatchConfig;
import com.pd.patchnumberrequestapp.model.Task;
import com.pd.patchnumberrequestapp.repository.PatchConfigRepository;
import com.pd.patchnumberrequestapp.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final PatchConfigRepository patchConfigRepository;

    public TaskService(TaskRepository taskRepository, PatchConfigRepository patchConfigRepository) {
        this.taskRepository = taskRepository;
        this.patchConfigRepository = patchConfigRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveTask(Task task) {
        if (task.getId() == null && (task.getPatchNumber() == null || task.getPatchNumber().isEmpty())) {
            PatchConfig config = patchConfigRepository.getConfig();
            String dateFormat = config != null && config.getDateFormat() != null ? config.getDateFormat() : "yyyyMMdd";
            int length = config != null ? config.getSequenceLength() : 3;
            
            String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern(dateFormat));
            
            taskRepository.save(task); 
            
            if (task.getId() != null) {
                String formattedId = String.format("%0" + length + "d", task.getId());
                task.setPatchNumber(dateStr + "-" + formattedId);
                taskRepository.save(task);
            }
        } else {
            taskRepository.save(task);
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
