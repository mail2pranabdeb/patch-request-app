package com.pd.patchnumberrequestapp.service;

import com.pd.patchnumberrequestapp.model.PatchConfig;
import com.pd.patchnumberrequestapp.model.Task;
import com.pd.patchnumberrequestapp.repository.PatchConfigRepository;
import com.pd.patchnumberrequestapp.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
            if (config == null) config = new PatchConfig();
            
            String bookType = task.getBookType() != null ? task.getBookType() : "Open Book";
            String lineType = task.getLineType() != null ? task.getLineType() : "RPP1";
            
            Long nextVal = 0L;
            
            String format = "";
            String lastDeployed = "";
            String patchType = task.getPatchType() != null ? task.getPatchType() : "Code Fix";
            
            if ("Open Book".equalsIgnoreCase(bookType)) {
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
                format = "Datafix".equalsIgnoreCase(patchType) ? 
                         config.getOpenBookDatafixPatchFormat() : 
                         config.getOpenBookCodefixPatchFormat();
                lastDeployed = config.getOpenBookLastDeployedPatch();
            } else if ("Migration".equalsIgnoreCase(bookType) || "Max Migration".equalsIgnoreCase(bookType)) {
                if ("RPP1".equalsIgnoreCase(lineType)) {
                    nextVal = config.getMaxMigRPP1() + 1;
                    config.setMaxMigRPP1(nextVal);
                } else if ("RPP2".equalsIgnoreCase(lineType)) {
                    nextVal = config.getMaxMigRPP2() + 1;
                    config.setMaxMigRPP2(nextVal);
                } else {
                    nextVal = config.getMaxMigRPP3() + 1;
                    config.setMaxMigRPP3(nextVal);
                }
                format = "Datafix".equalsIgnoreCase(patchType) ? 
                         config.getMaxMigDatafixPatchFormat() : 
                         config.getMaxMigCodefixPatchFormat();
                lastDeployed = config.getMaxMigLastDeployedPatch();
            } else {
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
                format = "Datafix".equalsIgnoreCase(patchType) ? 
                         config.getClosedBookDatafixPatchFormat() : 
                         config.getClosedBookCodefixPatchFormat();
                lastDeployed = config.getClosedBookLastDeployedPatch();
            }
            
            patchConfigRepository.updateConfig(config);
            
            if (format == null || format.isEmpty()) {
                format = "PATCH_$3DIGITSEQUENCE$";
            }
            
            String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String seqStr = String.format("%03d", nextVal);
            
            // Intelligent truncation of last deployed patch date (if exists)
            String cleanVersion = (lastDeployed != null ? lastDeployed : "NONE");
            if (cleanVersion.matches(".*_\\d{8}$")) {
                cleanVersion = cleanVersion.substring(0, cleanVersion.length() - 9);
            }
            
            String patchNumber = format;
            patchNumber = patchNumber.replace("$3DIGITSEQUENCE$", seqStr);
            patchNumber = patchNumber.replace("$YYYMMDD$", dateStr);
            patchNumber = patchNumber.replace("$LASTDEPLOYEDPATCH$", cleanVersion);
            
            // Fallback if no tokens were replaced (old style)
            if (patchNumber.equals(format)) {
                patchNumber = format + "_" + seqStr + "_" + dateStr;
            }
            
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
