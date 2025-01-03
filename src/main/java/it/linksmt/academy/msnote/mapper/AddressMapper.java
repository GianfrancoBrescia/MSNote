package it.linksmt.academy.msnote.mapper;

import it.linksmt.academy.msnote.dto.AddressDto;
import it.linksmt.academy.msnote.entity.Address;
import it.linksmt.academy.msnote.entity.User;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address addressDtoToAddressEntity(AddressDto addressDto);

    AddressDto addressEntityToAddressDto(Address addressEntity);

    default List<Address> addressDtoListToAddressEntityList(List<AddressDto> addressDtoList, User user) {
        return Optional.ofNullable(addressDtoList).orElse(new ArrayList<>()).stream()
                .map(addressDto -> {
                    Address address = addressDtoToAddressEntity(addressDto);
                    address.setUser(user);
                    return address;
                }).collect(Collectors.toList());
    }

    default List<AddressDto> addressEntityListToAddressDtoList(List<Address> addressEntityList) {
        return Optional.ofNullable(addressEntityList).orElse(new ArrayList<>()).stream()
                .map(this::addressEntityToAddressDto)
                .collect(Collectors.toList());
    }
}
