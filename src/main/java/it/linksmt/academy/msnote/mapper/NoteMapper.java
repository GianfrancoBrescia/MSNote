package it.linksmt.academy.msnote.mapper;

import it.linksmt.academy.msnote.dto.NoteDto;
import it.linksmt.academy.msnote.entity.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    @Mapping(target = "userFiscalCode", source = "user.codiceFiscale")
    NoteDto noteEntityToNoteDto(Note note);

    @Mapping(target = "user", ignore = true)
    Note noteDtoToNoteEntity(NoteDto noteDto);
}
