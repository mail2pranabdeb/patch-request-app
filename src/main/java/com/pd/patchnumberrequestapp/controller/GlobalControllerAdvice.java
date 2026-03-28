package com.pd.patchnumberrequestapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

/**
 * Exposes common application metadata like version number to all Thymeleaf
 * templates.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    private final String appVersion;

    @Autowired
    public GlobalControllerAdvice(@Autowired(required = false) BuildProperties buildProperties) {
        // BuildProperties is created by the spring-boot-maven-plugin build-info goal.
        // During local development, it might be missing, so we use a fallback.
        this.appVersion = Optional.ofNullable(buildProperties)
                .map(BuildProperties::getVersion)
                .orElse("1.0.0");
    }

    @ModelAttribute
    public void addAppVersion(Model model) {
        model.addAttribute("appVersion", appVersion);
    }
}
