package com.pd.patchnumberrequestapp.controller;

import com.pd.patchnumberrequestapp.model.Task;
import com.pd.patchnumberrequestapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String index(Model model) {
        java.util.List<Task> allTasks = taskService.getAllTasks();
        model.addAttribute("totalTasks", allTasks.size());
        model.addAttribute("openBookPatch", allTasks.stream().filter(t -> "Open Book".equals(t.getBookType())).count());
        model.addAttribute("closedBookPatch",
                allTasks.stream().filter(t -> "Closed Book".equals(t.getBookType())).count());
        return "dashboard";
    }

    @GetMapping("/tasks")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks/list";
    }

    @GetMapping("/prepare")
    public String preparePatch() {
        return "tasks/prepare";
    }

    @GetMapping("/tasks/new")
    public String newTaskForm(Model model) {
        Task task = new Task();
        task.setPatchType("Datafix");
        task.setBookType("Open Book");
        task.setLineType("RPP1");
        model.addAttribute("task", task);
        return "tasks/form";
    }

    @PostMapping("/tasks/save")
    public String saveTask(@ModelAttribute Task task, RedirectAttributes redirectAttributes) {
        if (task.getBookType() == null || task.getLineType() == null || task.getTaskNumber() == null
                || task.getTaskShortDescription() == null || task.getRequestedBy() == null) {
            return "tasks/form";
        }

        boolean isNew = task.getId() == null;
        taskService.saveTask(task);

        if (isNew) {
            redirectAttributes.addFlashAttribute("successMessage",
                    "Successfully generated Patch Number: " + task.getPatchNumber());
        } else {
            redirectAttributes.addFlashAttribute("successMessage",
                    "Successfully updated Patch Request ID: " + task.getId());
        }
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            model.addAttribute("task", task);
            return "tasks/form";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        taskService.deleteTask(id);
        redirectAttributes.addFlashAttribute("successMessage", "Successfully deleted the patch request.");
        return "redirect:/tasks";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
