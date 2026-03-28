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
            String patchType = task.getPatchType() != null ? task.getPatchType() : "CodeFix";

            // Hierarchy parts
            String prefix = "";
            String fixStr = "";
            String major = "";
            String minor = "";
            String seriesStr = "";
            String lastDeployed = "";

            if ("Open Book".equalsIgnoreCase(bookType)) {
                prefix = config.getOpenBookPrefix();
                fixStr = config.getOpenBookFixString();
                major = config.getOpenBookMajorVersion();
                minor = config.getOpenBookMinorVersion();
                lastDeployed = config.getOpenBookLastDeployedPatch();
                if ("RPP1".equalsIgnoreCase(lineType)) seriesStr = String.valueOf(config.getOpenBookRPP1());
                else if ("RPP2".equalsIgnoreCase(lineType)) seriesStr = String.valueOf(config.getOpenBookRPP2());
                else seriesStr = String.valueOf(config.getOpenBookRPP3());
            } else if ("Migration".equalsIgnoreCase(bookType) || "Max Migration".equalsIgnoreCase(bookType)) {
                prefix = config.getMaxMigPrefix();
                fixStr = config.getMaxMigFixString();
                major = config.getMaxMigMajorVersion();
                minor = config.getMaxMigMinorVersion();
                lastDeployed = config.getMaxMigLastDeployedPatch();
                if ("RPP1".equalsIgnoreCase(lineType)) seriesStr = String.valueOf(config.getMaxMigRPP1());
                else if ("RPP2".equalsIgnoreCase(lineType)) seriesStr = String.valueOf(config.getMaxMigRPP2());
                else seriesStr = String.valueOf(config.getMaxMigRPP3());
            } else {
                prefix = config.getClosedBookPrefix();
                fixStr = config.getClosedBookFixString();
                major = config.getClosedBookMajorVersion();
                minor = config.getClosedBookMinorVersion();
                lastDeployed = config.getClosedBookLastDeployedPatch();
                if ("RPP1".equalsIgnoreCase(lineType)) seriesStr = String.valueOf(config.getClosedBookRPP1());
                else if ("RPP2".equalsIgnoreCase(lineType)) seriesStr = String.valueOf(config.getClosedBookRPP2());
                else seriesStr = String.valueOf(config.getClosedBookRPP3());
            }

            String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String patchNumber = "";

            if ("CodeFix".equalsIgnoreCase(patchType)) {
                // Template: prefix_fixstring.majorversion.minorversion.series.currentnumber_yymmdd
                String basePrefix = prefix + "_" + fixStr + "." + major + "." + minor + "." + seriesStr + ".";
                String baseSuffix = "_" + dateStr;
                
                // Find next sequence for this EXACT combination today
                int nextNum = findNextSequence(basePrefix, baseSuffix);
                patchNumber = basePrefix + String.format("%03d", nextNum) + baseSuffix;
            } else {
                // Datafix: Version Chained
                // Template: $LASTDEPLOYEDPATCH$_$CURRENTSEQUENCE$_$YYYMMDD$
                String cleanVersion = (lastDeployed != null && !lastDeployed.isEmpty()) ? lastDeployed : "NONE";
                if (cleanVersion.matches(".*_\\d{8}$")) {
                    cleanVersion = cleanVersion.substring(0, cleanVersion.length() - 9);
                }
                
                String basePrefix = cleanVersion + "_DF";
                String baseSuffix = "_" + dateStr;
                int nextNum = findNextSequence(basePrefix, baseSuffix);
                patchNumber = basePrefix + String.format("%03d", nextNum) + baseSuffix;
            }

            task.setPatchNumber(patchNumber);
            taskRepository.save(task);
        } else {
            taskRepository.save(task);
        }
    }

    private int findNextSequence(String prefix, String suffix) {
        List<Task> existing = taskRepository.findAll();
        int max = 0;
        for (Task t : existing) {
            String pn = t.getPatchNumber();
            if (pn != null && pn.startsWith(prefix) && pn.endsWith(suffix)) {
                try {
                    // Extract number between prefix and suffix
                    String mid = pn.substring(prefix.length(), pn.length() - suffix.length());
                    // Mid might contain "DF" for datafixes in some legacy formats, handled by startsWith match
                    int current = Integer.parseInt(mid.replaceAll("[^0-9]", ""));
                    if (current > max) max = current;
                } catch (Exception e) { /* Ignore parsing errors */ }
            }
        }
        return max + 1;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
