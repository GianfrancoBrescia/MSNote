package it.linksmt.academy.msnote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoteDto {

    private String userFiscalCode;
    private long id;
    private String title;
    private String body;
}
