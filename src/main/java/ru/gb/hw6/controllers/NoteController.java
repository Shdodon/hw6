package ru.gb.hw6.controllers;

import  ru.gb.hw6.model.Note;
import  ru.gb.hw6.services.NoteService;
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
     * Поле взаимодействия с базой данных
     */
    private NoteService noteService;

    /**
     * Метод просмотра всех заметок
     * @return список всех заметок
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        log.info("Getting all available notes");
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        log.info("Adding note to the data base 'notes'");
        return new ResponseEntity<>(noteService.addNote(note), HttpStatus.CREATED);
    }
}