package com.example.ruleengine.service;

import com.example.ruleengine.model.Transaction;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {

    private final List<String> rules = new ArrayList<>();

    public void addRules(List<String> newRules) {
        rules.addAll(newRules);
    }

    public List<String> getRules() {
        return rules;
    }

    public List<Transaction> evaluate(List<Transaction> transactions) {

        List<Transaction> matchedTransactions = new ArrayList<>();
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

        for (Transaction tx : transactions) {
            boolean matched = false;

            for (String rule : rules) {
                try {
                    engine.put("amount", tx.getAmount());
                    engine.put("type", tx.getType());
                    engine.put("category", tx.getCategory());

                    Boolean result = (Boolean) engine.eval(rule);

                    if (result != null && result) {
                        matched = true;
                        break;
                    }
                } catch (Exception e) {
                    
                }
            }

            if (matched) {
                matchedTransactions.add(tx);
            }
        }

        return matchedTransactions;
    }
}
