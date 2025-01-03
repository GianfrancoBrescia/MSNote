package it.linksmt.academy.msnote.controller;

import it.linksmt.academy.msnote.dto.NoteDto;
import it.linksmt.academy.msnote.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/notes")
    public List<NoteDto> recuperaCommenti() {
        return noteService.recuperaCommenti();
    }

    @GetMapping("/note/{id}")
    public NoteDto recuperaPerIdCommento(@PathVariable long id) throws ClassNotFoundException {
        return noteService.recuperaPerIdCommento(id);
    }

    @PostMapping("/note")
    public NoteDto creaCommento(@RequestBody NoteDto noteDto) throws ClassNotFoundException {
        noteService.creaCommento(noteDto);
        return noteDto;
    }

    @PutMapping("/note")
    public NoteDto modificaCommento(@RequestBody NoteDto noteDto) throws ClassNotFoundException {
        noteService.modificaCommento(noteDto);
        return noteDto;
    }

    @DeleteMapping("/note/{id}")
    public void eliminaCommento(@PathVariable long id) {
        noteService.eliminaCommento(id);
    }
}
