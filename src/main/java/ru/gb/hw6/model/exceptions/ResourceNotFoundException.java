package ru.gb.hw6.model.exceptions;

import lombok.AllArgsConstructor;

/**
 * Класс ResourceNotFoundException обрабатывает исключения отсутствия данных
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}