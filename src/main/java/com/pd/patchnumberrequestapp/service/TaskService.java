package com.pd.patchnumberrequestapp.service;

import com.pd.patchnumberrequestapp.model.PatchConfig;
import com.pd.patchnumberrequestapp.model.Task;
import com.pd.patchnumberrequestapp.repository.PatchConfigRepository;
import com.pd.patchnumberrequestapp.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            if (config == null)
                config = new PatchConfig();

            String bookType = task.getBookType() != null ? task.getBookType() : "Open Book";
            String lineType = task.getLineType() != null ? task.getLineType() : "RPP1";

            Long nextVal = 0L;

            String lastDeployed = "";
            String series = "";
            String fixedStrVal = "";
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
                series = config.getOpenBookSeries();
                fixedStrVal = config.getOpenBookFixedString();
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
                series = config.getMaxMigSeries();
                fixedStrVal = config.getMaxMigFixedString();
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
                series = config.getClosedBookSeries();
                fixedStrVal = config.getClosedBookFixedString();
                lastDeployed = config.getClosedBookLastDeployedPatch();
            }

            patchConfigRepository.updateConfig(config);

            // Hardcoded Templates
            String template = "";
            if ("Datafix".equalsIgnoreCase(patchType)) {
                template = "$LASTDEPLOYEDPATCH$_$CURRENTSEQUENCE$_$YYYMMDD$";
            } else {
                template = "$CAT_PREFIX$_$FIXED_STR$.$SERIES$_$CURRENTSEQUENCE$_$YYYMMDD$";
            }

            String catPrefixToken = "";
            if ("Open Book".equalsIgnoreCase(bookType)) {
                catPrefixToken = "RPP";
            } else if ("Migration".equalsIgnoreCase(bookType) || "Max Migration".equalsIgnoreCase(bookType)) {
                catPrefixToken = "Max";
            } else {
                catPrefixToken = "Mig";
            }

            String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String seqStr = String.format("%03d", nextVal);

            // Smart Sequence: Prepend DF if it's a Datafix
            String smartSeq = "Datafix".equalsIgnoreCase(patchType) ? "DF" + seqStr : seqStr;

            // Intelligent truncation of last deployed patch date (if exists)
            String cleanVersion = (lastDeployed != null && !lastDeployed.isEmpty()) ? lastDeployed : "NONE";
            if (cleanVersion.matches(".*_\\d{8}$")) {
                cleanVersion = cleanVersion.substring(0, cleanVersion.length() - 9);
            }

            // Better way: Use a Map for all tokens
            Map<String, String> tokens = new HashMap<>();
            tokens.put("$CAT_PREFIX$", catPrefixToken);
            tokens.put("$CURRENTSEQUENCE$", smartSeq);
            tokens.put("$YYYMMDD$", dateStr);
            tokens.put("$LASTDEPLOYEDPATCH$", cleanVersion);
            tokens.put("$SERIES$", (series != null ? series : "01.01.01"));
            tokens.put("$FIXED_STR$", (fixedStrVal != null ? fixedStrVal : "PATCH"));

            String patchNumber = template;
            for (Map.Entry<String, String> entry : tokens.entrySet()) {
                patchNumber = patchNumber.replace(entry.getKey(), entry.getValue());
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
