package ru.gb.hw6.controllers;

import ru.gb.hw6.model.Note;
import ru.gb.hw6.model.exceptions.ResourceNotFoundException;
import ru.gb.hw6.services.NoteService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Класс NoteController обрабатывает http запросы
 */
@Slf4j
@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {

    /**
     * Метрика количество запросов
     */
    private final Counter requestCounter = Metrics.counter("request_count");

    /**
     * Сервис обработки заметок
     */
    private NoteService noteService;

    /**
     * Метод получения всех заметок
     * @return ответ со списком всех заметок в бд
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        log.info("Getting all available notes");
        requestCounter.increment();
        return ResponseEntity.ok().body(noteService.getAllNotes());
    }

    /**
     * Метод добавления заметки в бд
     * @param note создаваемая заметка
     * @return ответ с сохраненной в бд заметкой
     */
    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        log.info("Adding note to the data base 'notes'");
        requestCounter.increment();
        return new ResponseEntity<>(noteService.addNote(note), HttpStatus.CREATED);
    }

    /**
     * Метод получения заметки по идентификатору
     * @param id получаемой заметки
     * @return ответ с заметкой
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable long id) {
        log.info("Getting note by id in the data base 'notes'");
        requestCounter.increment();
        return ResponseEntity.ok().body(noteService.findNoteById(id));
    }

    /**
     * Метод удаления заметки по идентификатору
     * @param id удаляемой заметки
     * @return ответ об удалении заметки
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable long id) {
        log.info("Deleting not by id from the data base 'notes'");
        requestCounter.increment();
        try {
            noteService.deleteNote(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            log.info("Note hasn't found!");
            return ResponseEntity.notFound().build();
        }
    }
}