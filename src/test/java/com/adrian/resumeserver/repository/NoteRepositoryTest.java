package com.adrian.resumeserver.repository;

import com.adrian.resumeserver.model.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    void shouldSaveAndRetrieveNote() {
        Note note = new Note("Test Title", "Test Content");
        Note saved = noteRepository.save(note);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("Test Title");
        assertThat(saved.getContent()).isEqualTo("Test Content");
        assertThat(saved.getCreatedAt()).isNotNull();
    }

    @Test
    void shouldReturnAllNotes() {
        noteRepository.save(new Note("Note 1", "Content 1"));
        noteRepository.save(new Note("Note 2", "Content 2"));
        List<Note> notes = noteRepository.findAll();
        assertThat(notes).hasSizeGreaterThanOrEqualTo(2);
    }
}
