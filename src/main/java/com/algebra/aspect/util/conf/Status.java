package com.algebra.aspect.util.conf;

import com.algebra.aspect.mapstruct.domain.Person;
import com.algebra.aspect.util.AlgebraBaseEnum;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @author al
 * @date 2019/12/6 13:23
 * @description
 */
public class Status {

    enum ExpireEnum {

        EFFECT_TIME_1(1000L,TimeUnit.SECONDS),
        EFFECT_TIME_2(2000L,TimeUnit.SECONDS),
        ;
        ExpireEnum(long time, TimeUnit timeUnit){
            this.time = time;
            this.timeUnit = timeUnit;
        }
        public long getTime(){
            return EFFECT_TIME_1.time;
        };
        public TimeUnit getTimeUnit(){
            return EFFECT_TIME_1.timeUnit;
        };
        private long time;
        private TimeUnit timeUnit;
    }

    public static void main(String[] args) {
        System.out.println(AlgebraBaseEnum.EXAMPLE_1.getValue());

        System.out.println(AlgebraBaseEnum.EXAMPLE_4.getObject((Person) AlgebraBaseEnum.EXAMPLE_3.getValue()));

        System.out.println(TimeUnit.HOURS.toSeconds(1));
    }

}
