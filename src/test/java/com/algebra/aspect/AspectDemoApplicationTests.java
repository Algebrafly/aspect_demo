package com.algebra.aspect;

import com.algebra.aspect.active.simple.JmsProducer;
import com.algebra.aspect.domain.codehelper.PersonService;
import com.algebra.aspect.mapper.MyProcedureMapper;
import com.algebra.aspect.mapstruct.domain.Person;
import com.algebra.aspect.mapstruct.dto.PersonDto;
import com.algebra.aspect.mapstruct.dtomapper.PersonConverter;
import com.algebra.aspect.service.IUserService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles("")
public class AspectDemoApplicationTests {

    @Autowired
    IUserService userService;

    @Autowired
    PersonConverter personConverter;

    @Autowired
    private JmsProducer jmsProducer;

    @Autowired
    PersonService personService;

    @Autowired
    MyProcedureMapper procedureMapper;

    @Test
    public void procedureTest(){
        Map<String,Object> param = new HashMap<>();
        param.put("a",1);
        param.put("b",2);

        Map<String, Object> sum1 = procedureMapper.getSum(param);
        System.out.println(sum1);
    }

    @Test
    public void contextLoads() {
//        Map<String,Object> param = new HashMap<>();
//        param.put("uId","u_001");
//        User u = userService.getUserInfoOne("u_001",param);
//        System.out.println(u.toString());

        com.algebra.aspect.domain.codehelper.Person p = new
                com.algebra.aspect.domain.codehelper.Person("p-001","tom",18,new Date(),"asd");

        personService.insertSelective(p);
    }

    @Test
    public void mapStructTest(){
//        PersonConverter.INSTANCE.newInstance().domain2Dto();

        com.algebra.aspect.mapstruct.domain.User user = new com.algebra.aspect.mapstruct.domain.User();
        user.setAge(20);
        Person person = new Person(1L,"zhige","zhige.me@gmail.com",new Date(),user);
        PersonDto PersonDto = personConverter.domain2Dto(person);
        assertNotNull(PersonDto);
        assertEquals(PersonDto.getName(), person.getName());
        assertEquals(PersonDto.getBirth(), person.getBirthday());
        String format = DateFormatUtils.format(PersonDto.getBirth(), "yyyy-MM-dd HH:mm:ss");
        assertEquals(PersonDto.getBirthDateFormat(),format);
        assertEquals(PersonDto.getBirthExpressionFormat(),format);
        System.out.println("------------>"+PersonDto.toString());

        List<Person> people = new ArrayList<>();
        people.add(person);
        List<PersonDto> PersonDtos = personConverter.domain2Dtos(people);
        assertNotNull(PersonDtos);
        System.out.println("------------>"+PersonDtos.toString());
    }

    @Test
    public void testJms() throws Exception {
//        Destination destination = new ActiveMQQueue("batch.trade");

        for (int i=0;i<10;i++) {
//            jmsProducer.sendMessage3(JmsConfig.BATCH_TRADE_QUEUE,"hello,trade!" + i);
        }
    }


}
