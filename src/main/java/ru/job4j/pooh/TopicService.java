package ru.job4j.pooh;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TopicService implements Service {
    private final ConcurrentHashMap<String, CASMap> clientIdMap = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Optional<String> text = Optional.empty();
        int status = 403;
        if (POST.equals(req.method())) {
            for (CASMap casMap : clientIdMap.values()) {
                casMap.add(req);
            }
            status = 204;
        }
        if (GET.equals(req.method())) {
            Optional<CASMap> casMap = Optional.ofNullable(clientIdMap.get(req.clientId()));
            text = Optional.ofNullable(casMap.orElse(new CASMap()).extract(req.name()));
            status = text.isPresent() ? 200 : 404;
        }
        return new Resp(text.orElse(""), status);
    }

    public boolean subscribe(String clientId) {
        clientIdMap.putIfAbsent(clientId, new CASMap());
        return clientIdMap.get(clientId) != null;
    }
}