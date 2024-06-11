package ru.gb.hw6.services;

import ru.gb.hw6.model.Note;
import ru.gb.hw6.repositories.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс NoteService взаимодействия с базой данных
 */
@Service
@AllArgsConstructor
public class NoteService {

    /**
     * Поле взаимодействия с базой данных
     */
    private NoteRepository noteRepository;

    /**
     * Метод просмотра всех заметок
     * @return список всех заметок
     */
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Метод создания заметки
     * @param note создаваемая заметка
     * @return созданная заметка
     */
    public Note addNote(Note note) {
        note.setCreationDate(LocalDate.now());
        return noteRepository.save(note);
    }
}