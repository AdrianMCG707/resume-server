package com.adrian.resumeserver.controller;

import com.adrian.resumeserver.model.Note;
import com.adrian.resumeserver.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@Valid @RequestBody Note note) {
        return noteService.createNote(note);
    }
}

/*

@RequestMapping("/notes") — every endpoint in this class starts with /notes.
So @GetMapping becomes GET /notes and @PostMapping becomes POST /notes
@ResponseStatus(HttpStatus.CREATED) — returns HTTP 201 instead of the default 200 when a note is created.
This is the correct HTTP status for a successful POST
@RequestBody — tells Spring to parse the incoming JSON body and convert it into a Note object automatically
@Valid — triggers the validation annotations on your Note fields before the method runs






 */