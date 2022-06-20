package com.pepsi.juc.aqs;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDeBug {

    static Logger log = LoggerFactory.getLogger(ReentrantLockDeBug.class);

    static Object lock = new Object();

    public static void main(String[] args) {
        // 对象头部信息 mark word
        String toPrintable = ClassLayout.parseInstance(lock).toPrintable(lock);

        ReentrantLock reentrantLock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            reentrantLock.lock();
            log.debug("lock.........");
            reentrantLock.unlock();
            log.debug("unlock........");
        }, "t1");
        t1.start();

    }

}
