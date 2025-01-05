package it.linksmt.academy.msnote.service;

import it.linksmt.academy.msnote.dto.NoteDto;
import it.linksmt.academy.msnote.entity.Note;
import it.linksmt.academy.msnote.entity.User;
import it.linksmt.academy.msnote.mapper.NoteMapper;
import it.linksmt.academy.msnote.repository.NoteRepository;
import it.linksmt.academy.msnote.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteMapper noteMapper;

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<NoteDto> recuperaCommenti() {
        return noteRepository.findAll().stream()
                .sorted(Comparator.comparing(Note::getId).reversed())
                .map(noteMapper::noteEntityToNoteDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NoteDto recuperaPerIdCommento(long id) throws ClassNotFoundException {
        Note note = noteRepository.findById(id).orElseThrow(() -> new ClassNotFoundException("Commento non presente all'interno del database!"));
        return noteMapper.noteEntityToNoteDto(note);
    }

    @Transactional
    public void creaCommento(NoteDto noteDto) throws ClassNotFoundException {
        Note note = noteMapper.noteDtoToNoteEntity(noteDto);
        User user = userRepository.findById(noteDto.getUserId()).orElseThrow(() -> new ClassNotFoundException("Commento non presente all'interno del database"));
        note.setUser(user);
        note = noteRepository.save(note);
        noteDto.setId(note.getId());
    }

    @Transactional
    public void modificaCommento(NoteDto noteDto) throws ClassNotFoundException {
        Note note = noteRepository.findById(noteDto.getId()).orElseThrow(() -> new ClassNotFoundException("Commento non presente all'interno del database"));
        User user = userRepository.findById(noteDto.getUserId()).orElseThrow(() -> new ClassNotFoundException("Utente non presente all'interno del database!"));
        note.setUser(user);
        note.setTitle(noteDto.getTitle());
        note.setBody(noteDto.getBody());
        noteRepository.save(note);
    }

    public void eliminaCommento(long id) {
        noteRepository.deleteById(id);
    }
}
