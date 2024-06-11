package ru.gb.hw6.repositories;


import ru.gb.hw6.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс взаимодействия с базой данных
 */
public interface NoteRepository extends JpaRepository<Note, Long> {
}