package ru.gb.hw6.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Класс ConsoleLogger логирования данных консоль, реализующий интерфейс логирования
 */
//@Service
public class ConsoleLogger implements Logger {

    /**
     * Метод логирования данных в консоль
     * @param message текст логирования
     */
    @Override
    public void log(String message) {
        System.out.println(LocalDateTime.now() + " " + message);
    }
}