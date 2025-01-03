package it.linksmt.academy.msnote.controller;

import it.linksmt.academy.msnote.dto.UserDto;
import it.linksmt.academy.msnote.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> recuperaUtenti() {
        return userService.recuperaUtenti();
    }

    @GetMapping("/user/{codiceFiscale}")
    public UserDto recuperaPerCodiceFiscale(@PathVariable String codiceFiscale) throws ClassNotFoundException {
        return userService.recuperaPerCodiceFiscale(codiceFiscale);
    }

    @PostMapping("/user")
    public UserDto creaUtente(@RequestBody UserDto userDto) {
        userService.creaUtente(userDto);
        return userDto;
    }

    @PutMapping("/user")
    public UserDto modificaUtente(@RequestBody UserDto userDto) {
        userService.modificaUtente(userDto);
        return userDto;
    }
}
