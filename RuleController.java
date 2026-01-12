package com.example.ruleengine.controller;

import com.example.ruleengine.model.Transaction;
import com.example.ruleengine.service.RuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class RuleController {

    private final RuleService ruleService;

    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    // POST /rules
    @PostMapping("/rules")
    public String addRules(@RequestBody List<String> rules) {
        ruleService.addRules(rules);
        return "Rules added successfully";
    }

    // GET /rules
    @GetMapping("/rules")
    public List<String> getRules() {
        return ruleService.getRules();
    }

    // POST /evaluate
    @PostMapping("/evaluate")
    public List<Transaction> evaluate(@RequestBody List<Transaction> transactions) {
        return ruleService.evaluate(transactions);
    }
}
