package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CASMap {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue
            = new ConcurrentHashMap<>();

    public boolean add(Req request) {
        queue.putIfAbsent(request.name(), new ConcurrentLinkedQueue<>());
        return queue.get(request.name()).add(request.text());
    }

    public String extract(String name) {
        return queue.getOrDefault(name, new ConcurrentLinkedQueue<>()).poll();
    }
}