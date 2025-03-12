package Project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Travel_log implements Log {
    private ArrayList<String> LogTravel_Log = new ArrayList<>();

    public Travel_log(ArrayList<String> log_travel) {
        LogTravel_Log = log_travel;
    }

    @Override
    public void Logprint() {
        for (String s : LogTravel_Log) {
            System.out.println(s);
        }

    }

    @Override
    public void Logsave(String s,int id) {
        String saux =s+ id +".txt";
        try {   File myObj = new File(saux);
            BufferedWriter out = new BufferedWriter(new FileWriter(myObj));
            for (String k : LogTravel_Log) {
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
        LogTravel_Log.add(Ulti+action+obj);

    }
}