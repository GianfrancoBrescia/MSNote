package it.linksmt.academy.msnote.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {

    private String codiceFiscale;
    private String firstname;
    private String lastname;
    private Integer age;
    private String email;
    private List<AddressDto> address;
    private boolean visibility;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")
    private LocalDate birthdate;
}
