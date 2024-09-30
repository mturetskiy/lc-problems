package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TasksQueueTest {
    @Test
    void testSimpleCase() {
        TasksQueue tasksQueue = new TasksQueue();
        assertEquals(8, tasksQueue.leastInterval(new char[]{'A','A','A','B','B','B'}, 2));
    }
}