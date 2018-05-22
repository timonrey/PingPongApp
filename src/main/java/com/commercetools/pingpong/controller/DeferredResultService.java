package com.commercetools.pingpong.controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.commercetools.pingpong.model.ClientMessage;
import com.commercetools.pingpong.model.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service("DeferredService")
public class DeferredResultService implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(DeferredResultService.class);
    private final BlockingQueue<DeferredResult<ClientMessage>> resultQueue = new LinkedBlockingQueue<>();
    private Thread thread;
    private volatile boolean start = true;
    private LinkedBlockingQueue<ClientMessage> queue;
    private Game game;

    public void subscribe() {
        logger.info("Starting server");
        startThread();
    }

    private void startThread() {
        if (start) {
            synchronized (this) {
                if (start) {
                    start = false;
                    thread = new Thread(this, "Studio Teletype");
                    thread.start();
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                DeferredResult<ClientMessage> result = resultQueue.take();
                ClientMessage message = queue.take();
                result.setResult(message);
            } catch (InterruptedException e) {
                //throw new Exception("Cannot get latest update. " + e.getMessage(), e);
            }
        }
    }

    public void getUpdate(DeferredResult<ClientMessage> result) {
        resultQueue.add(result);
    }
}
