package com.pd.patchnumberrequestapp.controller;

import com.pd.patchnumberrequestapp.model.PatchConfig;
import com.pd.patchnumberrequestapp.repository.PatchConfigRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/config")
public class ConfigController {

    private final PatchConfigRepository patchConfigRepository;

    public ConfigController(PatchConfigRepository patchConfigRepository) {
        this.patchConfigRepository = patchConfigRepository;
    }

    @GetMapping
    public String showConfig(Model model) {
        PatchConfig config = patchConfigRepository.getConfig();
        if (config == null) {
            config = new PatchConfig();
        }
        model.addAttribute("config", config);
        return "config";
    }

    @PostMapping("/save")
    public String saveConfig(@ModelAttribute PatchConfig patchConfig) {
        PatchConfig existing = patchConfigRepository.getConfig();
        if (existing != null) {
            patchConfig.setId(existing.getId());
            patchConfigRepository.updateConfig(patchConfig);
        } else {
            // Usually not hit since db ensures 1 entry initially
        }
        return "redirect:/config?success";
    }
}
