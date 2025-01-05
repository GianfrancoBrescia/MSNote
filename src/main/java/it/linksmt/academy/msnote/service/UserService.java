package it.linksmt.academy.msnote.service;

import it.linksmt.academy.msnote.dto.UserDto;
import it.linksmt.academy.msnote.entity.Address;
import it.linksmt.academy.msnote.entity.User;
import it.linksmt.academy.msnote.mapper.AddressMapper;
import it.linksmt.academy.msnote.mapper.UserMapper;
import it.linksmt.academy.msnote.repository.AddressRepository;
import it.linksmt.academy.msnote.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AddressMapper addressMapper;
    private final UserMapper userMapper;

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDto> recuperaUtenti() {
        return userRepository.findAll().stream()
                .sorted(Comparator.comparing(User::getId).reversed())
                .map(user -> {
                    UserDto userDto = userMapper.userEntityToUserDto(user);
                    List<Address> addressListByIdUser = addressRepository.findByIdUser(user.getId());
                    userDto.setAddress(addressMapper.addressEntityListToAddressDtoList(addressListByIdUser));
                    return userDto;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto recuperaPerCodiceFiscale(String codiceFiscale) throws ClassNotFoundException {
        User user = userRepository.findByCodiceFiscale(codiceFiscale);

        if (user == null) {
            throw new ClassNotFoundException("L'utente " + codiceFiscale + " non e' presente all'interno del database!");
        }

        UserDto userDto = userMapper.userEntityToUserDto(user);
        userDto.setAddress(addressMapper.addressEntityListToAddressDtoList(user.getAddressList()));
        return userDto;
    }

    @Transactional
    public void creaUtente(UserDto userDto) {
        userDto.setVisibility(true);
        User user = userRepository.save(userMapper.userDtoToUserEntity(userDto));
        addressRepository.saveAll(addressMapper.addressDtoListToAddressEntityList(userDto.getAddress(), user));
    }

    @Transactional
    public void modificaUtente(UserDto userDto) {
        User user = userRepository.findByCodiceFiscale(userDto.getCodiceFiscale());
        userMapper.updateEntity(user, userDto);
        userRepository.save(user);

        addressRepository.deleteAll(addressRepository.findByIdUser(user.getId()));
        addressRepository.saveAll(addressMapper.addressDtoListToAddressEntityList(userDto.getAddress(), user));
    }
}
