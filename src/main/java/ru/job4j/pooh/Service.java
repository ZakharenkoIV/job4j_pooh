package ru.job4j.pooh;

public interface Service {
    String POST = "POST";
    String GET = "GET";

    Resp process(Req req);
}