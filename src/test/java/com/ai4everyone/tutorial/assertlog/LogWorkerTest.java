package com.ai4everyone.tutorial.assertlog;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class LogWorkerTest {
    private static MemoryAppender memoryAppender;
    private static final String LOGGER_NAME = "com.ai4everyone.tutorial";
    private static final String MSG = "Message!!!";
    private static LogWorker logWorker;

    @BeforeAll
    public static void setUp() {
        Logger logger = (Logger) LoggerFactory.getLogger(LOGGER_NAME);
        memoryAppender = new MemoryAppender();
        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.DEBUG);
        logger.addAppender(memoryAppender);
        memoryAppender.start();
        logWorker = new LogWorker();
        logWorker.generateLog(MSG);
    }

    @AfterAll
    public static void cleanUp() {
        memoryAppender.reset();
        memoryAppender.stop();
    }

    @Test
    public void whenGeneratingLog_thenNumberOfLogShouldBeCorrect() {
        assertEquals(4, memoryAppender.countEventsForLogger(LOGGER_NAME));
        assertEquals(4, memoryAppender.search(MSG).size());
    }

    @Test
    public void whenGeneratingLog_thenSearchingLogShouldReturnCorrectResponse() {
        assertAll(
                () -> assertEquals(1, memoryAppender.search(MSG, Level.DEBUG).size()),
                () -> assertEquals(1, memoryAppender.search(MSG, Level.INFO).size()),
                () -> assertEquals(1, memoryAppender.search(MSG, Level.WARN).size()),
                () -> assertEquals(1, memoryAppender.search(MSG, Level.ERROR).size()),
                () -> assertTrue(memoryAppender.search(MSG, Level.TRACE).isEmpty())
        );
    }

    @Test
    public void whenGeneratingLog_thenSearchingByPatternShouldReturnCorrectResponse() {
        Pattern pattern = Pattern.compile(".*!!!");
        assertTrue(memoryAppender.containsPattern(pattern, Level.DEBUG));
        assertTrue(memoryAppender.containsPattern(pattern, Level.INFO));
        assertTrue(memoryAppender.containsPattern(pattern, Level.WARN));
        assertTrue(memoryAppender.containsPattern(pattern, Level.ERROR));
    }
}