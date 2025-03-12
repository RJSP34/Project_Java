package Project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public  class Util_log implements Log {
    private  ArrayList<String> LogUtil = new ArrayList<>();

    public Util_log(ArrayList<String> logUtil) {
        LogUtil = logUtil;
    }

    @Override
    public void Logprint() {
        for (String s : LogUtil) {
            System.out.println(s);
        }
    }

    @Override
    public void Logsave(String s, int id) {
        String saux = s + id+ ".txt";
        try {
            File myObj = new File(saux);
            BufferedWriter out = new BufferedWriter(new FileWriter(myObj));
            for (int i = 0; i < LogUtil.size(); i++) {
                String h=LogUtil.get(i);
                out.write(h+"\n");
            }
            out.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("Save log of" + id);

    }



    @Override
    public void insertLog(String Ulti, String action, String obj) {
        LogUtil.add(Ulti+action+obj);
    }
}