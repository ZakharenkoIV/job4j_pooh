package ru.job4j.pooh;

public class QueueService implements Service {
    private final CASMap map = new CASMap();

    @Override
    public Resp process(Req req) {
        String text = "";
        int status = 403;
        if (req.method().equals("POST")) {
            status = map.add(req) ? 204 : 403;
        }
        if (req.method().equals("GET")) {
            text = map.extract(req.name());
            status = (!text.equals("null")) ? 200 : 404;
        }
        return new Resp(text, status);
    }
}