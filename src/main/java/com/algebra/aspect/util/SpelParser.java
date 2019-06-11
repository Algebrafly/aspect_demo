package com.algebra.aspect.util;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author al
 * @date 2019/6/10 20:25
 * @description
 */
public class SpelParser {

    private static ExpressionParser parser = new SpelExpressionParser();

    /**
     * EL表达式赋值
     * @param key EL表达式字符串，占位符以#开头
     * @param paramNames 占位符名称
     * @param args 占位符的真实值
     * @return el表达式经过真实值替换后的字符串值
     */
    public static String getKey(String key, String[] paramNames, Object[] args){
        // 将key字符串解析成el表达式
        Expression exp = parser.parseExpression(key);
        // 将形参和值以配对方式配置到赋值上下文中:初始化赋值上下文
        EvaluationContext context = new StandardEvaluationContext();
        if(args.length <= 0){
            return null;
        }
        for(int i = 0; i < args.length; i++){
            context.setVariable(paramNames[i],args[i]);
        }
        return exp.getValue(context,String.class);
    }

    public static void main(String[] args) {

        String key = "#name+' '+#age";

        System.out.println("EL = "+key);

        String name = "tom";
        String age = "19";

        String[] params = {"name","age"};
        String[] arg = {name,age};

        System.out.println(getKey(key,params,arg));


    }

}
