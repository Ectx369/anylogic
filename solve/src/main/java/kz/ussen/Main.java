package kz.ussen;

import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class Main
{
    public static void main(String[] args)
    {
        String file = "tickets.json";
        String json;
        Tickets tickets;
        try {
            json = readFileAsString(file);
            tickets = new Gson().fromJson(json, Tickets.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date reference;
        try {
            reference = dateFormat.parse("00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        AtomicReference<Integer> accum = new AtomicReference<>(0);
        AtomicInteger i = new AtomicInteger();

        tickets.getTickets().forEach(c -> {
            Date date;
            Date date2;
            try {
                date = dateFormat.parse(c.getDeparture_time());
                date2 = dateFormat.parse(c.getArrival_time());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            long seconds1 = (date.getTime() - reference.getTime()) / 1000L;
            long seconds2 = (date2.getTime() - reference.getTime()) / 1000L;

            accum.updateAndGet(v -> Math.toIntExact(v + seconds2 - seconds1));
            i.getAndIncrement();
        });

        int result = (accum.get() / i.get()) / 3600;
        System.out.println("Среднее время полёта = " + result);
    }

    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
