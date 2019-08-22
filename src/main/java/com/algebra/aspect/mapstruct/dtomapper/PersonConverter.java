package com.algebra.aspect.mapstruct.dtomapper;

import com.algebra.aspect.mapstruct.domain.Person;
import com.algebra.aspect.mapstruct.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author al
 * @date 2019/8/22 16:50
 * @description
 */
@Mapper(componentModel = "spring")
public interface PersonConverter {

    Class<? extends PersonConverter> INSTANCE = Mappers.getMapperClass(PersonConverter.class);

    @Mappings({
            @Mapping(source = "birthday",target = "birth"),
            @Mapping(source = "birthday",target = "birthDateFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "birthExpressionFormat", expression = "java(org.apache.commons.lang3.time.DateFormatUtils.format(person.getBirthday(),\"yyyy-MM-dd HH:mm:ss\"))"),
            @Mapping(source = "user.age",target = "age"),
            @Mapping(target = "email",ignore = true)
    })
    PersonDto domain2Dto(Person person);

    List<PersonDto> domain2Dtos(List<Person> personList);

}
