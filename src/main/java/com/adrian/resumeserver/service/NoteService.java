package com.adrian.resumeserver.service;

import com.adrian.resumeserver.model.Note;
import com.adrian.resumeserver.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }
}


/*
@Service — registers this class with Spring so it can be injected into the Controller
Constructor injection — NoteRepository is passed in via the constructor, not created manually.
Spring provides it automatically. This is the preferred way to inject dependencies in modern Spring Boot
The methods are intentionally simple right now — getAllNotes() and createNote() just pass through to the repository.
As the project grows, this is where you'd add validation, permission checks, and more complex logic




 */