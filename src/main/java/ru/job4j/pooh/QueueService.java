package ru.job4j.pooh;

import java.util.Optional;

public class QueueService implements Service {
    private final CASMap map = new CASMap();

    @Override
    public Resp process(Req req) {
        Optional<String> text = Optional.empty();
        int status = 403;
        if (POST.equals(req.method())) {
            status = map.add(req) ? 204 : 403;
        }
        if (GET.equals(req.method())) {
            text = Optional.ofNullable(map.extract(req.name()));
            status = text.isPresent() ? 200 : 404;
        }
        return new Resp(text.orElse(""), status);
    }
}