package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;

public class TopicService implements Service {
    private final ConcurrentHashMap<String, CASMap> clientIdMap = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String text = "";
        int status = 403;
        if (req.method().equals("POST")) {
            for (CASMap casMap : clientIdMap.values()) {
                casMap.add(req);
            }
            status = 204;
        }
        if (req.method().equals("GET")) {
            CASMap casMap = clientIdMap.get(req.clientId());
            text = (casMap != null) ? casMap.extract(req.name()) : "null";
            status = (text != null && !text.equals("null")) ? 200 : 404;
        }
        return new Resp(text, status);
    }

    public boolean subscribe(String clientId) {
        clientIdMap.putIfAbsent(clientId, new CASMap());
        return clientIdMap.get(clientId) != null;
    }
}