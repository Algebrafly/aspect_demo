package com.algebra.aspect.drools;

import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

import java.util.Set;

/**
 * @author al
 * @date 2019/12/12 17:18
 * @description
 */
public class CustomAgendaFilter implements AgendaFilter {
    /**
     * 传入的rule name
     */
    private final Set<String> ruleNamesThatAreAllowedToFire;
    public CustomAgendaFilter(Set<String> ruleNamesThatAreAllowedToFire) {
        this.ruleNamesThatAreAllowedToFire = ruleNamesThatAreAllowedToFire;
    }
    @Override
    public boolean accept(Match match) {
        return ruleNamesThatAreAllowedToFire.contains(match.getRule().getName());
    }
}
