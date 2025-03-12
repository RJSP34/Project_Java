package Project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Cache_log implements Log {
private ArrayList<String> LogCache = new ArrayList<>();

    public Cache_log(ArrayList<String> logCache) {
        LogCache = logCache;
    }

    @Override
    public void Logprint() {
        for (String s : LogCache) {
            System.out.println(s);
        }

    }

    @Override
    public void Logsave(String s,int id) {
        String saux =s+ id +".txt";
try {   File myObj = new File(saux);
    BufferedWriter out = new BufferedWriter(new FileWriter(myObj));
    for (String k : LogCache) {
        out.write(k);
    }
    out.close();
    System.out.println("Save log of"+id);
} catch (IOException e) {
    e.printStackTrace();
}
    }

    @Override
    public void insertLog(String Ulti, String action, String obj) {
LogCache.add(Ulti+action+obj);

    }
}