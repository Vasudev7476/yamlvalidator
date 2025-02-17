package com.example.yamlvalidator.service;

import com.example.yamlvalidator.model.ValidationResult;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

@Service
public class YamlValidationService {
    public ValidationResult validateYaml(InputStream yamlStream) {
        try {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(yamlStream);
            if (data != null) {
                return new ValidationResult(true, "YAML is valid");
            }
            return new ValidationResult(false, "YAML is empty");
        } catch (Exception e) {
            return new ValidationResult(false, "Invalid YAML: " + e.getMessage());
        }
    }
}