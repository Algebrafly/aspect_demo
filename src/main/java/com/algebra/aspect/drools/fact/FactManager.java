package com.algebra.aspect.drools.fact;

import lombok.extern.slf4j.Slf4j;
import org.drools.compiler.kie.builder.impl.KieModuleCache;
import org.drools.compiler.kie.builder.impl.KieModuleCacheHelper;
import org.drools.core.impl.StatelessKnowledgeSessionImpl;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.RuleUnit;
import org.kie.internal.runtime.StatelessKnowledgeSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author al
 * @date 2019/12/30 16:47
 * @description 管理Fact
 */
@Slf4j
public class FactManager {

//    public <T> Collection<T> invokeRules(RuleUnit.Identity identity, Object... facts) throws Exception {
//
//        KieSessionsPool kieSessionsPool = kieCache.get(identity);
//        KieSession kieSession = kieSessionsPool.newKieSession();
//        List<FactHandle> factHandles = new ArrayList<>(facts.length);
//        for (Object fact: facts){
//            factHandles.add(kieSession.insert(fact));
//        }
//        kieSession.fireAllRules();
//
//        Class resultClass = resolveResultClass(identity);
//        Collection<T> results = (Collection<T>)kieSession.getObjects(obj -> resultClass.isInstance(obj));
//        List<T> list = handleResult(results, identity, facts);
//
//        //ATENTION: after run ten minutes, fact object accumulated too many, so delete by manually.
//        for (FactHandle factHandle: factHandles){
//            kieSession.delete(factHandle);
//        }
//        // return session into pool
//        kieSession.dispose();
//
//        log.info("execute identity: {}, output: {}", identity, list);
//        return list;
//    }

    public static void main(String[] args) {
//        StatelessKnowledgeSession session = new StatelessKnowledgeSessionImpl()

    }

}
