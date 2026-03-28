package com.pd.patchnumberrequestapp.service;

import com.pd.patchnumberrequestapp.model.PatchConfig;
import com.pd.patchnumberrequestapp.model.Task;
import com.pd.patchnumberrequestapp.repository.PatchConfigRepository;
import com.pd.patchnumberrequestapp.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            if (config == null) config = new PatchConfig();
            
            String bookType = task.getBookType() != null ? task.getBookType() : "Open Book";
            String lineType = task.getLineType() != null ? task.getLineType() : "RPP1";
            
            Long nextVal = 0L;
            String prefix = "";
            
            if ("Open Book".equalsIgnoreCase(bookType)) {
                prefix = "OB";
                if ("RPP1".equalsIgnoreCase(lineType)) {
                    nextVal = config.getOpenBookRPP1() + 1;
                    config.setOpenBookRPP1(nextVal);
                } else if ("RPP2".equalsIgnoreCase(lineType)) {
                    nextVal = config.getOpenBookRPP2() + 1;
                    config.setOpenBookRPP2(nextVal);
                } else {
                    nextVal = config.getOpenBookRPP3() + 1;
                    config.setOpenBookRPP3(nextVal);
                }
            } else {
                prefix = "CB";
                if ("RPP1".equalsIgnoreCase(lineType)) {
                    nextVal = config.getClosedBookRPP1() + 1;
                    config.setClosedBookRPP1(nextVal);
                } else if ("RPP2".equalsIgnoreCase(lineType)) {
                    nextVal = config.getClosedBookRPP2() + 1;
                    config.setClosedBookRPP2(nextVal);
                } else {
                    nextVal = config.getClosedBookRPP3() + 1;
                    config.setClosedBookRPP3(nextVal);
                }
            }
            
            patchConfigRepository.updateConfig(config);
            
            // Format example: OB-RPP1-0101
            String patchNumber = String.format("%s-%s-%04d", prefix, lineType, nextVal);
            task.setPatchNumber(patchNumber);
            
            taskRepository.save(task); 
        } else {
            taskRepository.save(task);
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
