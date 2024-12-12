package com.oop_cw.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogManager {
    private static LogManager instance;
    // A synchronized list to hold the log messages
    private volatile List<String> logs = Collections.synchronizedList(new ArrayList<>());
    // SLF4J logger for logging messages to the console or file
    private static final Logger logger = LoggerFactory.getLogger(LogManager.class);

    private LogManager() {}

    // Singleton pattern to ensure only one instance of LogManager is created
    public static LogManager getInstance() {
        if (instance == null) {
            synchronized (LogManager.class) {
                if (instance == null) {
                    instance = new LogManager();
                }
            }
        }
        return instance;
    }

    public synchronized void addLog(String message) {
        if (logs.size() >= 100) { 
            logs.remove(0);
        }
        logs.add(message);
        logger.info(message); 
    }
    public List<String> getLogs() {
        return this.logs;
    }
}
