package kz.ussen;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PercentileAndAverageFromJson
{
    BufferedReader br;
    {
        try {
            br = new BufferedReader(new FileReader("tickets.json"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    {
    }
}
