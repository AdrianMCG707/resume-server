package com.adrian.resumeserver.repository;

import com.adrian.resumeserver.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}

/*

One important thing to notice: this is an interface, not a class.
You're not writing any implementation code — Spring generates a fully working -
-database layer at runtime just from this declaration.
That's the power of Spring Data JPA.

 */