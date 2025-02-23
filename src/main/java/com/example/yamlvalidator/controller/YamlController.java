package com.example.yamlvalidator.controller;


import com.example.yamlvalidator.model.ValidationResult;
import com.example.yamlvalidator.service.SnsNotificationService;
import com.example.yamlvalidator.service.YamlValidationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/yaml")
public class YamlController {
    private final YamlValidationService validationService;
    private final SnsNotificationService snsService;

    public YamlController(YamlValidationService validationService, SnsNotificationService snsService) {
        this.validationService = validationService;
        this.snsService = snsService;
    }

    @PostMapping("/validate")
    public ValidationResult validateYaml(@RequestParam("file") MultipartFile file) throws IOException {
        ValidationResult result = validationService.validateYaml(file.getInputStream());
        if (!result.isValid()) {
            snsService.sendFailureNotification(result.getMessage());
        }
        return result;
    }
}