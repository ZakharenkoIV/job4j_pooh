package ru.job4j.pooh;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReqTest {

    @Test
    public void whenValidPostRequestThen() {
        String text = "POST /queue/weather HTTP/1.1" + System.lineSeparator()
                + "Host: localhost:9000" + System.lineSeparator()
                + "User-Agent: curl/7.55.1" + System.lineSeparator()
                + "Accept: */*" + System.lineSeparator()
                + "Content-Length: 7" + System.lineSeparator()
                + "Content-Type: application/x-www-form-urlencoded" + System.lineSeparator()
                + System.lineSeparator()
                + "text=13";
        Req actual = Req.of(text);

        assertThat(actual.method(), is("POST"));
        assertThat(actual.mode(), is("queue"));
        assertThat(actual.name(), is("weather"));
        assertThat(actual.text(), is("text=13"));
        assertThat(actual.clientId(), is("null"));
    }

    @Test
    public void whenValidGetRequestThen() {
        String text = "GET /queue/weather HTTP/1.1" + System.lineSeparator()
                + "Host: localhost:9000" + System.lineSeparator()
                + "User-Agent: curl/7.55.1 " + System.lineSeparator()
                + "Accept: */* ";
        Req actual = Req.of(text);

        assertThat(actual.method(), is("GET"));
        assertThat(actual.mode(), is("queue"));
        assertThat(actual.name(), is("weather"));
        assertThat(actual.text(), is("null"));
        assertThat(actual.clientId(), is("null"));
    }

    @Test
    public void whenEmptyRequestThen() {
        String text = "";
        Req actual = Req.of(text);

        assertThat(actual.method(), is(""));
        assertThat(actual.mode(), is("null"));
        assertThat(actual.name(), is("null"));
        assertThat(actual.text(), is("null"));
        assertThat(actual.clientId(), is("null"));
    }
}