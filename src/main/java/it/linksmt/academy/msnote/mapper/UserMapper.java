package it.linksmt.academy.msnote.mapper;

import it.linksmt.academy.msnote.dto.UserDto;
import it.linksmt.academy.msnote.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addressList", ignore = true)
    @Mapping(target = "noteList", ignore = true)
    User userDtoToUserEntity(UserDto userDto);

    @Mapping(target = "address", ignore = true)
    UserDto userEntityToUserDto(User user);
}
