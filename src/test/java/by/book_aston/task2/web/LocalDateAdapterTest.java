package by.book_aston.task2.web;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateAdapterTest {

    @Test
    void serialize() {
        LocalDate now = LocalDate.now();
        String json = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create().toJson(now);
        String nowStr = ("\"" + now + "\"");
        assertEquals(nowStr,json);
    }

    @Test
    void deserialize() {
        LocalDate now = LocalDate.now();
        LocalDate localDate = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create().fromJson(String.valueOf(now), LocalDate.class);
        assertEquals(now,localDate);
    }
}