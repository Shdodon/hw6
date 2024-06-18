package ru.gb.hw6.services;

import org.springframework.integration.annotation.MessagingGateway;

/**
 * интерфейс FileLogger логирования данных в файл, наследник интерфейса логирования данных
 */
@MessagingGateway(defaultRequestChannel = "textInputChannel")
public interface FileLogger extends Logger {

    @Override
    void log(String message);
}