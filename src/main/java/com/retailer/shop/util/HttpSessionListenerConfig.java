package com.retailer.shop.util;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class HttpSessionListenerConfig implements HttpSessionListener {

    private static final Logger logger= LoggerFactory.getLogger(HttpSessionListenerConfig.class);

    private final AtomicInteger activeSessions;

    public HttpSessionListenerConfig() {
        super();
        activeSessions = new AtomicInteger();
    }


    /**
     * This method will be called when session created
     * @param sessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        logger.info("-------Incrementing Session Counter--------");
        activeSessions.incrementAndGet();
        logger.info("-------Session Created--------");
        sessionEvent.getSession().setAttribute("activeSessions",activeSessions.get());
        logger.info("Total Active Session : {} ", activeSessions.get());
    }

    /**
     * This method will be automatically called when session destroyed
     * @param sessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        logger.info("-------Decrementing Session Counter--------");
        activeSessions.decrementAndGet();
        sessionEvent.getSession().setAttribute("activeSessions",activeSessions.get());
        logger.info("-------Session Destroyed--------");
    }

}
