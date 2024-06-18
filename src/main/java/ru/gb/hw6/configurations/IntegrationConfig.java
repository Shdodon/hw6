package ru.gb.hw6.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

/**
 * Класс IntegrationConfig конфигурации интеграции
 */
@Configuration
public class IntegrationConfig {

    /**
     * Метод реализации входного канала
     * @return входной канал
     */
    @Bean
    public MessageChannel textInputChannel() {
        return new DirectChannel();
    }

    /**
     * Метод реализации канала записи в файл
     * @return канал записи в файл
     */
    @Bean
    public MessageChannel fileWriterChannel() {
        return new DirectChannel();
    }

    /**
     * Метод преобразования данных из входного канала в данные для записи в файл
     * @return преобразование
     */
    @Bean
    @Transformer(inputChannel = "textInputChannel",
            outputChannel = "fileWriterChannel")
    public GenericTransformer<String, String> mainTransformer() {
        return text -> text;
    }

    /**
     * Метод активации данных записи в файл
     * @return подключенный FileWritingMessageHandler
     */
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler messageHandler() {

        FileWritingMessageHandler handler =
                new FileWritingMessageHandler(
                        new File("src/main/resources/static"));
        handler.setExpectReply(false);
        handler.setAutoCreateDirectory(true);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setFileNameGenerator(n -> "logTest.txt");
        handler.setAppendNewLine(true);
        return handler;
    }
}