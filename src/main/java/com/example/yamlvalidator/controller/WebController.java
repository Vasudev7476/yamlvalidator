package com.example.yamlvalidator.controller;

import com.example.yamlvalidator.model.ValidationResult;
import com.example.yamlvalidator.service.SnsNotificationService;
import com.example.yamlvalidator.service.YamlValidationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class WebController {
    private final YamlValidationService validationService;
    private final SnsNotificationService snsService;

    public WebController(YamlValidationService validationService, SnsNotificationService snsService) {
        this.validationService = validationService;
        this.snsService = snsService;
    }

    @GetMapping("/")
    public String showUploadPage() {
        return "upload";
    }

    @PostMapping("/validate-ui")
    public String validateYaml(@RequestParam("file") MultipartFile file, Model model) {
        try {
            ValidationResult result = validationService.validateYaml(file.getInputStream());
            if (!result.isValid()) {
                snsService.sendFailureNotification(result.getMessage());
            }
            model.addAttribute("validationResult", result);
            return "result";
        } catch (IOException e) {
            model.addAttribute("validationResult", new ValidationResult(false, "Error reading file"));
            return "result";
        }
    }
}
