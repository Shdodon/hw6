package ru.gb.hw6;

import ru.gb.hw6.model.Note;
import ru.gb.hw6.repositories.NoteRepository;
import ru.gb.hw6.services.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteServiceUnitTests {

    @Mock
    private NoteRepository noteRepository;


    @InjectMocks
    private NoteService noteService;

    @Test
    void addNoteTest() {
// предпосылки
        Note note = new Note();
        note.setId(1);
        note.setTitle("Заметка");
        note.setBody("test");
        note.setCreationDate(LocalDate.now());
        given(noteRepository.save(note)).willReturn((Note) note);
//        when(noteRepository.save(note)).thenReturn(note);
//  action - вызов метода
        Note result = noteService.addNote(note);
//  проверка
//        verify(noteRepository).save(new Note(1, "Заметка", "test", LocalDate.now()));
        assertEquals("Заметка", result.getTitle());
        assertEquals("test", result.getBody());
    }

    @Test
    void getAllNotesTest() {
// предпосылки
        List<Note> noteList1 = new ArrayList<>();
        noteList1.add(new Note(1, "Заметка", "test", LocalDate.now()));
        when(noteRepository.findAll()).thenReturn(noteList1);
//  action - вызов метода
        List<Note> noteList = noteService.getAllNotes();
//  проверка
        assertEquals(1, noteList.size());
        assertEquals("Заметка", noteList.get(0).getTitle());
        assertEquals("test", noteList.get(0).getBody());
    }
}
