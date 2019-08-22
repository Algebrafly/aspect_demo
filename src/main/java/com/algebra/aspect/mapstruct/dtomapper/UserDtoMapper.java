package com.algebra.aspect.mapstruct.dtomapper;

import com.algebra.aspect.mapstruct.dto.UserDto;
import com.algebra.aspect.mapstruct.domain.User;
import org.mapstruct.Mapper;

/**
 * @author al
 * @date 2019/8/22 16:08
 * @description mapStruct user转换工具类
 */
@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    /**
     * dto ---> do
     * @param userDto
     * @return
     */
    User dtoToUser(UserDto userDto);

    /**
     * do ---> dto
     * @param user
     * @return
     */
    UserDto userToDto(User user);
}
