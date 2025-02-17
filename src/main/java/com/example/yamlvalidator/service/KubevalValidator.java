package com.example.yamlvalidator.service;


import com.example.yamlvalidator.model.ValidationResult;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;

@Service
public class KubevalValidator {

    public ValidationResult validateWithKubeval(Path yamlFilePath) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("kubeval", yamlFilePath.toString());
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return new ValidationResult(true, "Kubeval Validation Passed");
            } else {
                return new ValidationResult(false, "Kubeval Validation Failed: " + output);
            }
        } catch (Exception e) {
            return new ValidationResult(false, "Error Running Kubeval: " + e.getMessage());
        }
    }
}