package com.algebra.aspect.drools;

import com.algebra.generic.entity.ext.Person;
import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.drools.core.base.RuleNameMatchesAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author al
 * @date 2019/12/12 17:14
 * @description 指定规则名
 */
public class DroolsTest {

    /**
     * 根据自定义规则匹配规则
     */
    public static void secondTest(){
        //KieSession kieSession= newKisessions();
        Resource dis = ResourceFactory.newClassPathResource("rules/test.drl", Person.class);
        KieHelper helper = new KieHelper();
        helper.addResource(dis, ResourceType.DRL);
        KieSession kieSession = helper.build().newKieSession();
        Set<String> set=new HashSet<>();
        set.add("name的使用1");
        set.add("name的使用3");
        set.add("name的使用5");
        kieSession.fireAllRules(new CustomAgendaFilter(set));
    }

    /**
     * 根据命名开头匹配规则
     */
    public static void thirdTest(){
        Resource dis = ResourceFactory.newClassPathResource("rules/test.drl", Person.class);
        KieHelper helper = new KieHelper();
        helper.addResource(dis, ResourceType.DRL);
        KieSession kieSession = helper.build().newKieSession();
        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("name"));
    }

    /**
     * 根据正则表达式匹配规则
     */
    public static void forthTest(){
        Resource dis = ResourceFactory.newClassPathResource("rules/test.drl", Person.class);
        KieHelper helper = new KieHelper();
        helper.addResource(dis, ResourceType.DRL);
        KieSession kieSession = helper.build().newKieSession();
        kieSession.fireAllRules(new RuleNameMatchesAgendaFilter("\\w{0,5}[\\u4e00-\\u9fa5]{0,10}\\d"));
    }


    public static void normalTest() {
        Resource dis = ResourceFactory.newClassPathResource("rules/testdrl/testjblx.drl", Person.class);
        ObjectDataCompiler converter = new ObjectDataCompiler();
        Collection<Person> cfl = new ArrayList<Person>();
        KieHelper helper = new KieHelper();
        helper.addResource(dis, ResourceType.DRL);
        KieSession ksession = helper.build().newKieSession();
        FactHandle f = ksession.insert(cfl);
        ksession.fireAllRules(new RuleNameEndsWithAgendaFilter("bbb"));
        ksession.delete(f);
        FactHandle fs = ksession.insert(cfl);
        int i = ksession.fireAllRules(new RuleNameEndsWithAgendaFilter("aaa"));  // 指定 具体规则以bbb结尾的规则
        System.out.println( "     " + i + "次");
        ksession.dispose();
    }

    public static void main(String[] args) {
        forthTest();

    }

}
