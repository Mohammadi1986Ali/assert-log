package com.nexora.software.assertlog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogWorker {

    private static final Logger log = LoggerFactory.getLogger(LogWorker.class);

    public void generateLog(String msg) {
        log.trace(msg);
        log.debug(msg);
        log.info(msg);
        log.warn(msg);
        log.error(msg);
    }
}
