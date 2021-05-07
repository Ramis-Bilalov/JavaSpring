package com.bilalov.javaspringbootlessonfour.dto;

import com.bilalov.javaspringbootlessonfour.entities.User;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ProductMapper.class})
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "productDTOS", target = "products")
    User toUser(UserDTO userDTO);

    @InheritConfiguration
    UserDTO fromUser(User user);

    List<User> toUserList(List<UserDTO> userDTOS);

    List<UserDTO> fromUserList(List<User> users);
}
