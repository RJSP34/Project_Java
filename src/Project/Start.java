package Project;
import Classimports.*;
import Classimports.Bag;
import Classimports.Date;
import edu.princeton.cs.algs4.*;
import fxgrafs.Btcontroler;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Start {

    /**
     * @param args args do programa nao utilizado
     * @throws IOException caso tem um erro de io 
     * main utilizado exclusivament para executar a funçao testBSTS();
     */
    public static void main(String[] args) throws IOException {
        testBSTS();
    }

    /**
     *
     * @param path path of the Utilizadores que é para ler
     * @param BSTUtil Red black que recebe os utilizadores e armazena
     * @param BSTADMIN Red black que recebe os utilizadores admin e armazena
     * @param BSTPREM Red black que recebe os utilizadores premium e armazena
     *   Funçao utiliza o scanner para ler o ficheiro, acaso nao encontra o ficheiro da um erro FilenotFound
     */
    public static void Read_Util(String path, RedBlackBST_copy<Integer, Utilizador> BSTUtil, RedBlackBST_copy<Integer, Util_Admin> BSTADMIN,  RedBlackBST_copy<Integer, Util_premium> BSTPREM) {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String id ;
                String name ;
                String type ;
                ArrayList<Items> arra = new ArrayList<>();
                ArrayList<Integer> ar = new ArrayList<>();
                ArrayList<Integer> ow = new ArrayList<>();
                String visited ;
                String lan ;
                String lon ;
                String reg ;
                //auxiliares para guradar a informaçao lida
                String[] h1 = data.split(";");//criar um array de strings com o delimitador ;
                id = h1[0];
                //1144;RTMan;Std;2;11SpatosF,12kancT,13janmdF,14hamendF,;1,2,;-15444.3133;3551.133;Han; Exemple nao utilizado em ficheiro
                name = h1[1];
                type = h1[2];
                visited = h1[3];
                String[] h2 = h1[4].split(",");
                if (h2[0].length() != 0) {
                    for (int i = 0; i < h2.length; i++) {
                        if (h2[i].charAt(h2[i].length() - 1) == 'T') {
                            h2[i] = h2[i].substring(0, h2[i].length() - 1) + h2[i].substring(h2[i].length());
                            TravelBug s = new TravelBug(null, 0, null, null, null, -1,new Travel_log(new ArrayList<>()));
                            StringBuilder aux2 = new StringBuilder();

                            while (h2[i].charAt(0) >= '0' && h2[i].charAt(0) <= '9') {
                                aux2.append(h2[i].charAt(0));
                                h2[i] = h2[i].substring(1);
                            }
                            Items h = new Items(h2[i], true, s, Integer.parseInt(aux2.toString()));
                            arra.add(h);
                        } else {
                            h2[i] = h2[i].substring(0, h2[i].length() - 1) + h2[i].substring(h2[i].length());
                            StringBuilder aux2 = new StringBuilder();

                            while (h2[i].charAt(0) >= '0' && h2[i].charAt(0) <= '9') {
                                aux2.append(h2[i].charAt(0));
                                h2[i] = h2[i].substring(1);
                            }
                            Items h = new Items(h2[i], false, null, Integer.parseInt(aux2.toString()));
                            arra.add(h);
                        }

                    }
                }
                String[] h3 = h1[5].split(",");
                if (h3[0].length() != 0) {
                    for (String s : h3) {
                        ar.add(Integer.parseInt(s));
                    }
                }
                lan = h1[6];
                lon = h1[7];
                reg = h1[8];
                if ((type.compareTo("Admin") == 0 || type.compareTo("Premium") == 0) && h1.length != 9) {
                    String[] h4 = h1[9].split(",");
                    if (h4[0].length() != 0) {
                        for (String s : h4) {
                            ow.add(Integer.parseInt(s));
                        }
                    }

                }
                switch (type) {//onde guardar o novo utilizador
                    case "Admin":
                        BSTADMIN.put(Integer.parseInt(id), new Util_Admin(name, Integer.parseInt(visited), arra, Integer.parseInt(id), new Util_log(new ArrayList<>()), ar, new GPS(Double.parseDouble(lan), Double.parseDouble(lon), reg), ow));
                        break;
                    case "Premium":
                        BSTPREM.put(Integer.parseInt(id), new Util_premium(name, Integer.parseInt(visited), arra, Integer.parseInt(id), 0.0f, new Util_log(new ArrayList<>()), ar, new GPS(Double.parseDouble(lan), Double.parseDouble(lon), reg), ow));
                        break;
                    default:
                        BSTUtil.put(Integer.parseInt(id), new Utilizador(name, Integer.parseInt(visited), arra, Integer.parseInt(id),new Util_log(new ArrayList<>()), ar, new GPS(Double.parseDouble(lan), Double.parseDouble(lon), reg)));
                        break;
                }
            }
            myReader.close();// fechar o scanner
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     *
     * @param path path of the caches que é para ler
     * @param st Red black que recebe as caches e armazena-os
     */
    public static void Read_Cache(String path, RedBlackBST_copy<Integer, Cache> st) {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String id ;
                String type ;
                String Dyficult;
                ArrayList<Items> arra = new ArrayList<>();
                String Regiao ;
                String la ;
                String lo ;
                String[] h1 = data.split(";");
                id = h1[0];
                if (st.get(Integer.parseInt(id)) == null) {
                    //1;Premium;15sapatsaT,16asanF,17kegckF,18lgnsF,;Hard;1244.1133;3321.133;Japao;
                    type = h1[1];
                    String[] h2 = h1[2].split(",");
                    if (h2[0].length() != 0) {
                        for (int i = 0; i < h2.length; i++) {
                            if (h2[i].charAt(h2[i].length() - 1) == 'T') {
                                h2[i] = h2[i].substring(0, h2[i].length() - 1) + h2[i].substring(h2[i].length());
                                TravelBug s = new TravelBug(null, 0, null, null, null, -1,new Travel_log(new ArrayList<>()));
                                StringBuilder aux2 = new StringBuilder();
                                while (h2[i].charAt(0) >= '0' && h2[i].charAt(0) <= '9') {
                                    aux2.append(h2[i].charAt(0));
                                    h2[i] = h2[i].substring(1);
                                }
                                Items h = new Items(h2[i], true, s, Integer.parseInt(aux2.toString()));
                                arra.add(h);
                            } else {
                                h2[i] = h2[i].substring(0, h2[i].length() - 1) + h2[i].substring(h2[i].length());
                                StringBuilder aux2 = new StringBuilder();

                                while (h2[i].charAt(0) >= '0' && h2[i].charAt(0) <= '9') {
                                    aux2.append(h2[i].charAt(0));
                                    h2[i] = h2[i].substring(1);
                                }
                                Items h = new Items(h2[i], false, null, Integer.parseInt(aux2.toString()));
                                arra.add(h);
                            }

                        }
                    }
                    Dyficult = h1[3];
                    la = h1[4];
                    lo = h1[5];
                    Regiao = h1[6];
                    GPS g = new GPS(Double.parseDouble(la), Double.parseDouble(lo), Regiao);
                    Cache a = new Cache(type, arra, Dyficult, g, Integer.parseInt(id), new Cache_log(new ArrayList<>()));
                    st.put(Integer.parseInt(id), a);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     *
     * @param path path of the caches que é para escrever em ficheiro
     * @param st Red black que vai ser utilizada para guardar em ficheiro as caches
     */
    public static void Save_Cache(String path,  RedBlackBST_copy<Integer, Cache> st) {
        try {
            File myObj = new File(path);
            BufferedWriter out = new BufferedWriter(new FileWriter(myObj));
            for (Integer key : st.keys()) {
                Cache aux = st.get(key);
                int j = aux.getID_CACHE();
                out.write(j + ";" + aux.getType() + ";");
                for (int i = 0; i < aux.getObjects().size(); i++) {
                    String l = aux.getObjects().get(i).getName();
                    if (aux.getObjects().get(i).getTravel_bug()) {
                        l = aux.getObjects().get(i).getId() + l;
                        l = l + 'T';
                        out.write(l);
                        out.write(',');
                    } else {
                        l = aux.getObjects().get(i).getId() + l;
                        l = l + 'F';
                        out.write(l);
                        out.write(',');
                    }
                }
                out.write(";" + aux.getDificulty() + ";" + aux.getGps().getLatitude() + ";" + aux.getGps().getLongitude() + ";" + aux.getGps().getRegiao() + ";\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param path path of the utils que é para escrever em ficheiro
     * @param st Red black que vai ser utilizada para guardar em ficheiro as Std util
     * @param sa Red black que vai ser utilizada para guardar em ficheiro as admin Util
     * @param sp Red black que vai ser utilizada para guardar em ficheiro as Premium util
     */
    public static void Save_Util(String path,  RedBlackBST_copy<Integer, Utilizador> st, RedBlackBST_copy<Integer, Util_Admin> sa, RedBlackBST_copy<Integer, Util_premium> sp) {
        try {
            File myObj = new File(path);
            BufferedWriter out = new BufferedWriter(new FileWriter(myObj));
            for (Integer key : st.keys()) {

                Utilizador aux = st.get(key);

                out.write(aux.getID_UTI() + ";" + aux.getName() + ";Std;"+aux.getNumbVisted()+";");
                for (int i = 0; i < aux.getObjects().size(); i++) {
                    String l = aux.getObjects().get(i).getName();
                    if (aux.getObjects().get(i).getTravel_bug()) {
                        l = aux.getObjects().get(i).getId() + l;
                        l = l + 'T';
                        out.write(l);
                        out.write(',');

                    } else {
                        l = aux.getObjects().get(i).getId() + l;
                        l = l + 'F';
                        out.write(l);
                        out.write(',');
                    }

                }
                out.write(';');
                if (aux.getVisited_cache() != null || aux.getNumbVisted() != 0) {
                    for (int i = 0; i < aux.getVisited_cache().size(); i++) {
                        String l = String.valueOf(aux.getVisited_cache().get(i));
                        out.write(l);
                        out.write(',');

                    }
                    out.write(';');
                }
                out.write(aux.getLocal().getLatitude() + ";" + aux.getLocal().getLongitude() + ";" + aux.getLocal().getRegiao() + ";\n");

            }
            for (Integer key : sa.keys()) {

                Util_Admin aux = sa.get(key);
                out.write(aux.getID_UTI() + ";" + aux.getName() + ";Admin;"+aux.getNumbVisted()+";");
                for (int i = 0; i < aux.getObjects().size(); i++) {
                    String l = aux.getObjects().get(i).getName();
                    if (aux.getObjects().get(i).getTravel_bug()) {
                        l = aux.getObjects().get(i).getId() + l;
                        l = l + 'T';
                        out.write(l);
                        out.write(',');


                    } else {
                        l = aux.getObjects().get(i).getId() + l;
                        l = l + 'F';
                        out.write(l);
                        out.write(',');
                    }

                }
                out.write(';');
                if (aux.getVisited_cache() != null || aux.getNumbVisted() != 0) {
                    for (int i = 0; i < aux.getVisited_cache().size(); i++) {
                        String l = String.valueOf(aux.getVisited_cache().get(i));
                        out.write(l);
                        out.write(',');

                    }

                    out.write(';');
                }
                out.write(aux.getLocal().getLatitude() + ";" + aux.getLocal().getLongitude() + ";" + aux.getLocal().getRegiao() + ";");
                for (int i = 0; i < aux.getOwned().size(); i++) {
                    out.write(aux.getOwned().get(i) + ",");
                }
                out.write(";\n");

            }
            for (Integer key : sp.keys()) {

                Util_premium aux = sp.get(key);
                out.write(aux.getID_UTI() + ";" + aux.getName() + ";Premium;"+aux.getNumbVisted()+";");
                for (int i = 0; i < aux.getObjects().size(); i++) {
                    String l = aux.getObjects().get(i).getName();
                    if (aux.getObjects().get(i).getTravel_bug()) {
                        l = aux.getObjects().get(i).getId() + l;
                        l = l + 'T';
                        out.write(l);
                        out.write(',');


                    } else {
                        l = aux.getObjects().get(i).getId() + l;
                        l = l + 'F';
                        out.write(l);
                        out.write(',');
                    }

                }
                out.write(';');
                if (aux.getVisited_cache() != null || aux.getNumbVisted() != 0) {
                    for (int i = 0; i < aux.getVisited_cache().size(); i++) {
                        String l = String.valueOf(aux.getVisited_cache().get(i));
                        out.write(l);
                        out.write(',');

                    }
                    out.write(';');
                }
                out.write(aux.getLocal().getLatitude() + ";" + aux.getLocal().getLongitude() + ";" + aux.getLocal().getRegiao() + ";");
                for (int i = 0; i < aux.getOwned().size(); i++) {
                    out.write(aux.getOwned().get(i) + ",");
                }
                out.write(";\n");


            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param path path of the travelbugs que é para ler
     * @param BSTUtil Red_black para ver se o id do item em questao esta num Std util
     * @param BSTADMIN Red_black para ver se o id do item em questao esta num admin util
     * @param BSTPREM Red_black para ver se o id do item em questao esta num Premim util
     * @param stChe Red_black para ver se o id do item em questao esta num cache
     */
    public static void Read_Travel(String path,  RedBlackBST_copy<Integer, Utilizador> BSTUtil,  RedBlackBST_copy<Integer, Util_Admin> BSTADMIN,  RedBlackBST_copy<Integer, Util_premium> BSTPREM,  RedBlackBST_copy<Integer, Cache> stChe) {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String id = "";
                ArrayList<Integer> arra = new ArrayList<>();
                ArrayList<Integer> ar = new ArrayList<>();
                String type;
                String id_u = "";
                Items c = null;
                String[] h1 = data.split(";");
                String[] h2 = h1[3].split("/");
                String[] h3 = h1[4].split("/");
                Date date = new Date(Integer.parseInt(h2[0]),Integer.parseInt(h2[1]),Integer.parseInt(h2[2]));
                Date datef = new Date(Integer.parseInt(h3[0]),Integer.parseInt(h3[1]),Integer.parseInt(h3[2]));
                String[] h4=h1[5].split(",");
                String[] h5=h1[6].split(",");
                if(!h4[0].equals(" ")) {
                    for (String s : h4) {
                        arra.add(Integer.parseInt(s));
                    }
                }
                if(!h5[0].equals(" ")) {
                    for (String s : h5) {
                        ar.add(Integer.parseInt(s));
                    }
                }
                id = h1[0];
id_u=h1[1];
               type=h1[2];
               switch (type){
                   case "Admin":
                       if(BSTADMIN.get(Integer.valueOf(id_u))!=null){
                           if ((c=BSTADMIN.get(Integer.valueOf(id_u)).Items_get_array(Integer.valueOf(id)))!=null){
                               break;
                           }
                       }
                       break;
                   case"Premium":
                       if(BSTPREM.get(Integer.valueOf(id_u))!=null){
                           if ((c=BSTPREM.get(Integer.valueOf(id_u)).Items_get_array(Integer.valueOf(id)))!=null){
                               break;
                           }
                       }
                       break;
                   case"Std":
                       if(BSTUtil.get(Integer.valueOf(id_u))!=null){
                           if ((c=BSTUtil.get(Integer.valueOf(id_u)).Items_get_array(Integer.valueOf(id)))!=null){
                               break;
                           }
                       }
                       break;
                   case "Cache":
                       if(stChe.get(Integer.valueOf(id_u))!=null){
                           if ((c=stChe.get(Integer.valueOf(id_u)).Items_get_array(Integer.valueOf(id)))!=null){
                               break;
                           }
                       }
                       break;
                   default: System.out.println("Doenst Exist");

               }
               /*
               26;2;Cache;11/02/2000/;12/06/2020;2;2;2;
               29;3;Cache;11/02/2000/;12/06/2020;6;3;6;
                */
if(c!=null){
                TravelBug d = new TravelBug(date, Integer.parseInt(id_u), datef, arra, ar, Integer.parseInt(h1[7]),new Travel_log(new ArrayList<>()));
                c.setTravelBug(d);
}
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    /**
     *
     * @param path path of the travelbugs que é para escrever
     * @param BSTUtil Red_black para ver se o id do item em questao é um travel bug
     * @param BSTADMIN Red_black para ver se o id do item em questao é um travel bug de um admin
     * @param BSTPREM Red_black para ver se o id do item em questao é um travel bug de um prem
     * @param stChe Red_black para ver se o id do item em questao é um travel bug num cache
     */
    public static void Save_TRavel(String path,  RedBlackBST_copy<Integer, Utilizador> BSTUtil,  RedBlackBST_copy<Integer, Util_Admin> BSTADMIN,  RedBlackBST_copy<Integer, Util_premium> BSTPREM,  RedBlackBST_copy<Integer, Cache> stChe) {
        try {
            File myObj = new File(path);
            BufferedWriter out = new BufferedWriter(new FileWriter(myObj));
            for (int key : stChe.keys()) {
                ArrayList<Items> k = stChe.get(key).getObjects();
                for (Items k1 : k) {
                    //4;1122;Admin;11/02/2000/;12/06/2020;1133,0;
                    if (k1.isTravel_bug()) {
                        out.write(k1.getId() + ";" + stChe.get(key).getID_CACHE() + ";Cache;" + k1.getTravelBug().getDatetravelinicial().getDay() + "/" + k1.getTravelBug().getDatetravelinicial().getMonth() + "/" + k1.getTravelBug().getDatetravelinicial().getYear() + "/;" + k1.getTravelBug().getDatetravelimit().getDay() + "/" + k1.getTravelBug().getDatetravelimit().getMonth() + "/" + k1.getTravelBug().getDatetravelimit().getYear() + "/;");
                        for (int j = 0; j < k1.getTravelBug().getHistory().size() - 1; j++) {
                            out.write(k1.getTravelBug().getHistory().get(j) + ",");
                        }
                        out.write(k1.getTravelBug().getHistory().get(k1.getTravelBug().getHistory().size() - 1) + ";");
                        for (int j = 0; j < k1.getTravelBug().getHistory_Ches().size() - 1; j++) {
                            out.write(k1.getTravelBug().getHistory_Ches().get(j) + ",");
                        }
                        out.write(k1.getTravelBug().getHistory_Ches().get(k1.getTravelBug().getHistory_Ches().size() - 1) + ";" + k1.getTravelBug().getEndUlt() + ";\n");

                    }

                }
            }
            for (int key : BSTUtil.keys()) {
                ArrayList<Items> k = BSTUtil.get(key).getObjects();
                for (Items k1 : k) {
                    //4;1122;Admin;11/02/2000/;12/06/2020;1133,0;
                    if (k1.isTravel_bug()) {
                        out.write(k1.getId() + ";" + BSTUtil.get(key).getID_UTI() + ";Std;" + k1.getTravelBug().getDatetravelinicial().getDay() + "/" + k1.getTravelBug().getDatetravelinicial().getMonth() + "/" + k1.getTravelBug().getDatetravelinicial().getYear() + "/;" + k1.getTravelBug().getDatetravelimit().getDay() + "/" + k1.getTravelBug().getDatetravelimit().getMonth() + "/" + k1.getTravelBug().getDatetravelimit().getYear() + "/;");
                        for (int j = 0; j < k1.getTravelBug().getHistory().size() - 1; j++) {
                            out.write(k1.getTravelBug().getHistory().get(j) + ",");
                        }
                        out.write(k1.getTravelBug().getHistory().get(k1.getTravelBug().getHistory().size() - 1) + ";");
                        for (int j = 0; j < k1.getTravelBug().getHistory_Ches().size() - 1; j++) {
                            out.write(k1.getTravelBug().getHistory_Ches().get(j) + ",");
                        }
                        out.write(k1.getTravelBug().getHistory_Ches().get(k1.getTravelBug().getHistory_Ches().size() - 1) + ";" + k1.getTravelBug().getEndUlt() + ";\n");

                    }

                }
            }
            for (int key : BSTADMIN.keys()) {
                ArrayList<Items> k = BSTADMIN.get(key).getObjects();
                for (Items k1 : k) {
                    //4;1122;Admin;11/02/2000/;12/06/2020;1133,0;
                    if (k1.isTravel_bug()) {
                        out.write(k1.getId() + ";" + BSTADMIN.get(key).getID_UTI() + ";Admin;" + k1.getTravelBug().getDatetravelinicial().getDay() + "/" + k1.getTravelBug().getDatetravelinicial().getMonth() + "/" + k1.getTravelBug().getDatetravelinicial().getYear() + "/;" + k1.getTravelBug().getDatetravelimit().getDay() + "/" + k1.getTravelBug().getDatetravelimit().getMonth() + "/" + k1.getTravelBug().getDatetravelimit().getYear() + "/;");
                        for (int j = 0; j < k1.getTravelBug().getHistory().size() - 1; j++) {
                            out.write(k1.getTravelBug().getHistory().get(j) + ",");
                        }
                        out.write(k1.getTravelBug().getHistory().get(k1.getTravelBug().getHistory().size() - 1) + ";");

                        for (int j = 0; j < k1.getTravelBug().getHistory_Ches().size() - 1; j++) {
                            out.write(k1.getTravelBug().getHistory_Ches().get(j) + ",");
                        }
                        out.write(k1.getTravelBug().getHistory_Ches().get(k1.getTravelBug().getHistory_Ches().size() - 1) + ";" + k1.getTravelBug().getEndUlt() + ";\n");

                    }

                }
            }
            for (int key : BSTPREM.keys()) {
                ArrayList<Items> k = BSTPREM.get(key).getObjects();
                for (Items k1 : k) {
                    //4;1122;Admin;11/02/2000/;12/06/2020;1133,0;
                    if (k1.isTravel_bug()) {
                        out.write(k1.getId() + ";" + BSTPREM.get(key).getID_UTI() + ";Premium;" + k1.getTravelBug().getDatetravelinicial().getDay() + "/" + k1.getTravelBug().getDatetravelinicial().getMonth() + "/" + k1.getTravelBug().getDatetravelinicial().getYear() + "/;" + k1.getTravelBug().getDatetravelimit().getDay() + "/" + k1.getTravelBug().getDatetravelimit().getMonth() + "/" + k1.getTravelBug().getDatetravelimit().getYear() + "/;");

                        for (int j = 0; j < k1.getTravelBug().getHistory().size() - 1; j++) {
                            out.write(k1.getTravelBug().getHistory().get(j) + ",");
                        }
                        out.write(k1.getTravelBug().getHistory().get(k1.getTravelBug().getHistory().size() - 1) + ";");

                        for (int j = 0; j < k1.getTravelBug().getHistory_Ches().size() - 1; j++) {
                            out.write(k1.getTravelBug().getHistory_Ches().get(j) + ",");
                        }
                        out.write(k1.getTravelBug().getHistory_Ches().get(k1.getTravelBug().getHistory_Ches().size() - 1) + ";" + k1.getTravelBug().getEndUlt() + ";\n");


                    }

                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testBSTS() throws IOException {
        RedBlackBST_copy<Integer, Utilizador> BSTUtil = new  RedBlackBST_copy<>();
        RedBlackBST_copy<Integer, Util_Admin> BSTADMIN = new  RedBlackBST_copy<>();
        RedBlackBST_copy<Integer, Util_premium> BSTPREM = new  RedBlackBST_copy<>();
        ArrayList<Items> exe = new ArrayList<>();
        Items f1 = new Items("ll", false, null, 1);
        Items f2 = new Items("ll", false, null, 2);
        exe.add(f1);
        exe.add(f2);
        Utilizador st = new Utilizador("Name", 0, exe, BSTUtil.size(), null, new ArrayList<>(), new GPS(12345.222, 21123.33, "Reg"));
        BSTUtil.put(BSTUtil.size(), st);
        String path_util = "src/datapacks/Util.txt";
        String path_CAche = "src/datapacks/Cache";
        String path_Travel_bug = "src/datapacks/TravelBug";
        String path_save_test_CAche = "src/datapacks/test_save_Cache.txt";
        String path_save_test_Util = "src/datapacks/test_save_Util.txt";
        String path_save_test_Travel_BUG = "src/datapacks/test_save_TravelBug";
        Read_Util(path_util, BSTUtil, BSTADMIN, BSTPREM);
        RedBlackBST_copy<Integer, Cache> stChe = new  RedBlackBST_copy<>();
        Read_Cache(path_CAche, stChe);
        SeparateChainingHashST<Integer,Utilizador> util_has=new SeparateChainingHashST<>();
        Separete_func(util_has,BSTUtil);
        Read_Travel(path_Travel_bug, BSTUtil, BSTADMIN, BSTPREM, stChe);
        // Save_Cache(path_save_test_CAche,stChe);
      //Save_Util(path_save_test_Util, BSTUtil, BSTADMIN, BSTPREM);
        // Save_TRavel(path_save_test_Travel_BUG,BSTUtil,BSTADMIN,BSTPREM,stChe);
//test_insertedirem(BSTUtil,BSTADMIN,BSTPREM,stChe);
//test_Vsit(BSTUtil,BSTADMIN,BSTPREM,stChe);
//SYN(BSTUtil,BSTADMIN,BSTPREM,stChe);
       // Save_Util(path_save_test_Util, BSTUtil, BSTADMIN, BSTPREM);
        // BSTUtil.get(0).Visite_cache(stChe.get(1));
        // BSTUtil.get(0).getLog().Logprint();
        //BSTUtil.get(0).getLog().Logsave("src/datapacks/test_save_Log",BSTUtil.get(0).getID_UTI());
//BSTUtil.get(0).getLog().Logprint();
graf(BSTUtil,BSTADMIN,BSTPREM,stChe);
    }

    /**
     *
     * @param BSTUtil guarda os util normais
     * @param BSTADMIN guarda os util Admin
     * @param BSTPREM guarda os util premim
     * @param stChe guarda as Caches
     * @throws IOException para caso encontrar um erro de io
     * testa a inserçao,edite, e remoçao de utils e caches
     */
    public static void test_insertedirem( RedBlackBST_copy<Integer, Utilizador> BSTUtil, RedBlackBST_copy<Integer, Util_Admin> BSTADMIN,  RedBlackBST_copy<Integer, Util_premium> BSTPREM,  RedBlackBST_copy<Integer, Cache> stChe) throws IOException {
        int op ;
        do {
            System.out.println("Menu:\n1-Insert_util\n2-Insert_cache\n3-Edite_util\n4-Edite_cache\n5-Remove_util\n6-remove_che\n");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            op = Integer.parseInt(reader.readLine());
            switch (op) {
                case 1:
                    insert_util(BSTUtil, BSTADMIN, BSTPREM);
                    break;
                case 2:
                    insert_che(stChe);
                    break;
                case 3:
                    edition_util(BSTUtil, BSTADMIN, BSTPREM);
                    break;
                case 4:
                    edition_che(stChe);break;
                case 5:remove_util(BSTUtil,BSTADMIN,BSTPREM,stChe);break;
                case 6:remove_che(BSTUtil,BSTADMIN,BSTPREM,stChe);break;
                default:
                    System.out.println("NOT Recongasided");
                    break;
            }
        } while (op != 0);
    }

    public static void insert_util( RedBlackBST_copy<Integer, Utilizador> BSTUtil, RedBlackBST_copy<Integer, Util_Admin> BSTADMIN, RedBlackBST_copy<Integer, Util_premium> BSTPREM) throws IOException {
        System.out.println("Insert id:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());

        if (BSTADMIN.get(id) != null || BSTPREM.get(id) != null || BSTUtil.get(id) != null) {
            System.out.println("ALREADY EXISTES");
            return;
        }
        System.out.println("\nInsert Name:");
        String name = reader.readLine();
        String type;
        do {
            System.out.println("\nInsert Type:");
            type = reader.readLine();
            if (type.compareTo("Admin") == 0 || type.compareTo("Premium") == 0 || type.compareTo("Std") == 0) {
                break;
            }
            System.out.println("ERRO");
        } while (true);
        System.out.println("\nInsert Lan:");
        double lan = Double.parseDouble(reader.readLine());
        System.out.println("\nInsert Lon:");
        double lon = Double.parseDouble(reader.readLine());
        System.out.println("\nInsert Regiao:");
        String Reg = reader.readLine();
        switch (type) {
            case "Admin":
                BSTADMIN.put(id, new Util_Admin(name, 0, new ArrayList<>(), id,new Util_log(new ArrayList<>()), new ArrayList<>(), new GPS(lan, lon, Reg), new ArrayList<>()));
                break;
            case "Premium":
                System.out.println("insert saldo ini");
                float sald = Float.parseFloat(reader.readLine());
                BSTPREM.put(id, new Util_premium(name, 0, new ArrayList<>(), id, sald, new Util_log(new ArrayList<>()), new ArrayList<>(), new GPS(lan, lon, Reg), new ArrayList<>()));
                break;
            default:
                BSTUtil.put(id, new Utilizador(name, 0, new ArrayList<>(), id, new Util_log(new ArrayList<>()), new ArrayList<>(), new GPS(lan, lon, Reg)));
        }
        System.out.println("\nReady\n");
    }

    public static void insert_che( RedBlackBST_copy<Integer, Cache> stChe) throws IOException {
        System.out.println("Insert id:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());
        if (stChe.get(id) != null) {
            System.out.println("ALREADY EXISTES");
            return;
        }
        System.out.println("\nInsert type:");
        String name = reader.readLine();
        System.out.println("\nInsert Dificulty:");
        String type = reader.readLine();
        System.out.println("\nInsert Lan:");
        double lan = Double.parseDouble(reader.readLine());
        System.out.println("\nInsert Lon:");
        double lon = Double.parseDouble(reader.readLine());
        System.out.println("\nInsert Regiao:");
        String Reg = reader.readLine();
        if (name.compareTo("Premium") == 0) {
            stChe.put(id, new Cache("Premium", new ArrayList<>(), type, new GPS(lan, lon, Reg), id, new Cache_log(new ArrayList<>())));
        } else {
            stChe.put(id, new Cache("Normal", new ArrayList<>(), type, new GPS(lan, lon, Reg), id, new Cache_log(new ArrayList<>())));
        }
        System.out.println("\nReady\n");

    }

    public static void edition_util( RedBlackBST_copy<Integer, Utilizador> BSTUtil,  RedBlackBST_copy<Integer, Util_Admin> BSTADMIN,  RedBlackBST_copy<Integer, Util_premium> BSTPREM) throws IOException {
        System.out.println("\nInsert id:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());

        if (!((BSTADMIN.get(id) != null && BSTPREM.get(id) == null && BSTUtil.get(id) == null)||(BSTADMIN.get(id) == null && BSTPREM.get(id) != null && BSTUtil.get(id) == null)||(BSTADMIN.get(id) == null && BSTPREM.get(id) == null && BSTUtil.get(id) != null))) {
            System.out.println("Doesnt EXISTES");
            return;
        }
        System.out.println("\nInsert Name:");
        String name = reader.readLine();
        System.out.println("\nInsert Lan:");
        double lan = Double.parseDouble(reader.readLine());
        System.out.println("\nInsert Lon:");
        double lon = Double.parseDouble(reader.readLine());
        System.out.println("\nInsert Regiao:");
        String Reg = reader.readLine();
        if (BSTADMIN.get(id) != null) {
            Util_Admin a = BSTADMIN.get(id);
            a.setLocal(new GPS(lan, lon, Reg));
            a.setName(name);
            BSTADMIN.put(id, a);
            BSTADMIN.get(id).isString();
            return;
        }
        if (BSTPREM.get(id) != null) {
            Util_premium a = BSTPREM.get(id);
            a.setLocal(new GPS(lan, lon, Reg));
            a.setName(name);
            BSTPREM.put(id, a);
            BSTPREM.get(id).isString();
            return;
        }
        Utilizador a = BSTUtil.get(id);
        a.setLocal(new GPS(lan, lon, Reg));
        a.setName(name);
        BSTUtil.put(id, a);
BSTUtil.get(id).isString();
    }

    public static void edition_che( RedBlackBST_copy<Integer, Cache> stChe) throws IOException {
        System.out.println("\nInsert id:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());
        if (stChe.get(id) == null) {
            System.out.println("Doesnt EXISTES");
            return;
        }
        System.out.println("\nInsert Type:");
        String Type = reader.readLine();
        System.out.println("\nInsert Dyficulty:");
        String Dyficulty = reader.readLine();
        System.out.println("\nInsert Lan:");
        double lan = Double.parseDouble(reader.readLine());
        System.out.println("\nInsert Lon:");
        double lon = Double.parseDouble(reader.readLine());
        System.out.println("\nInsert Regiao:");
        String Reg = reader.readLine();
        Cache g = stChe.get(id);
        g.setDificulty(Dyficulty);
        g.setGps(new GPS(lan, lon, Reg));
        g.setType(Type);
        stChe.put(id, g);
        stChe.get(id).isString();
    }

    public static void remove_util( RedBlackBST_copy<Integer, Utilizador> BSTUtil,  RedBlackBST_copy<Integer, Util_Admin> BSTADMIN,  RedBlackBST_copy<Integer, Util_premium> BSTPREM, RedBlackBST_copy<Integer, Cache> stChe) throws IOException {
        System.out.println("\nInsert id:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());
        if (!((BSTADMIN.get(id) != null && BSTPREM.get(id) == null && BSTUtil.get(id) == null)||(BSTADMIN.get(id) == null && BSTPREM.get(id) != null && BSTUtil.get(id) == null)||(BSTADMIN.get(id) == null && BSTPREM.get(id) == null && BSTUtil.get(id) != null))) {
            System.out.println("Doesnt EXISTES");
            return;
        }
        for (int key : BSTUtil.keys()) {
            Utilizador aux = BSTUtil.get(key);
            for (int i = 0; i < aux.getObjects().size(); i++) {
                Items s = aux.getObjects().get(i);
                if (s.isTravel_bug()) {
                    for (int j = 0; j < s.getTravelBug().getHistory().size(); j++) {
                        if (s.getTravelBug().getHistory().get(j).compareTo(id) == 0) {
                            s.getTravelBug().getHistory().remove(j);
                            j--;
                        }

                    }

                }

            }

        }
        for (int key : BSTPREM.keys()) {
            Util_premium aux = BSTPREM.get(key);
            for (int i = 0; i < aux.getObjects().size(); i++) {
                Items s = aux.getObjects().get(i);
                if (s.isTravel_bug()) {
                    for (int j = 0; j < s.getTravelBug().getHistory().size(); j++) {
                        if (s.getTravelBug().getHistory().get(j).compareTo(id) == 0) {
                            s.getTravelBug().getHistory().remove(j);
                            j--;
                        }
                    }
                }
            }
        }
        for (int key : BSTADMIN.keys()) {
            Util_Admin aux = BSTADMIN.get(key);
            for (int i = 0; i < aux.getObjects().size(); i++) {
                Items s = aux.getObjects().get(i);
                if (s.isTravel_bug()) {
                    for (int j = 0; j < s.getTravelBug().getHistory().size(); j++) {
                        if (s.getTravelBug().getHistory().get(j).compareTo(id) == 0) {
                            s.getTravelBug().getHistory().remove(j);
                            j--;
                        }

                    }

                }

            }

        }
        for (int key:stChe.keys()) {
            Cache aux=stChe.get(key);
            for (int i = 0; i < aux.getObjects().size(); i++) {
                Items s = aux.getObjects().get(i);
                if (s.isTravel_bug()) {
                    for (int j = 0; j < s.getTravelBug().getHistory().size(); j++) {
                        if (s.getTravelBug().getHistory().get(j).compareTo(id) == 0) {
                            s.getTravelBug().getHistory().remove(j);
                            j--;
                        }

                    }

                }

            }
        }
        if(BSTADMIN.get(id)!=null){
            BSTADMIN.delete(id);
        }else if(BSTPREM.get(id)!=null){
            BSTPREM.delete(id);
        }else {
            BSTUtil.delete(id);
        }

    }
    public  static  void  remove_che( RedBlackBST_copy<Integer, Utilizador> BSTUtil,  RedBlackBST_copy<Integer, Util_Admin> BSTADMIN,  RedBlackBST_copy<Integer, Util_premium> BSTPREM, RedBlackBST_copy<Integer, Cache> stChe) throws IOException{
        System.out.println("\nInsert id:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());
        if (stChe.get(id)==null) {
            System.out.println("Doesnt EXISTES");
            return;
        }
        for (int key : BSTUtil.keys()) {
            Utilizador aux = BSTUtil.get(key);
            for (int i = 0; i < aux.getObjects().size(); i++) {
                Items s = aux.getObjects().get(i);
                if (s.isTravel_bug()) {
                    for (int j = 0; j < s.getTravelBug().getHistory_Ches().size(); j++) {
                        if (s.getTravelBug().getHistory_Ches().get(j).compareTo(id) == 0) {
                            s.getTravelBug().getHistory_Ches().remove(j);
                            j--;
                        }

                    }

                }

            }
            for (int j = 0; j <aux.getVisited_cache().size() ; j++) {
                if(aux.getVisited_cache().get(j).compareTo(id)==0){
                    aux.getVisited_cache().remove(j);
                    j--;
                }
            }

        }
        for (int key : BSTPREM.keys()) {
            Util_premium aux = BSTPREM.get(key);
            for (int i = 0; i < aux.getObjects().size(); i++) {
                Items s = aux.getObjects().get(i);
                if (s.isTravel_bug()) {
                    for (int j = 0; j < s.getTravelBug().getHistory_Ches().size(); j++) {
                        if (s.getTravelBug().getHistory_Ches().get(j).compareTo(id) == 0) {
                            s.getTravelBug().getHistory_Ches().remove(j);
                            j--;
                        }
                    }
                }
            }
            for (int j = 0; j <aux.getVisited_cache().size() ; j++) {
                if(aux.getVisited_cache().get(j).compareTo(id)==0){
                    aux.getVisited_cache().remove(j);
                    j--;
                }
            }
            for (int j = 0; j <aux.getOwned().size() ; j++) {
                if(aux.getOwned().get(j)==id){
                    aux.getOwned().remove(j);
                    j--;
                }
            }

        }
        for (int key : BSTADMIN.keys()) {
            Util_Admin aux = BSTADMIN.get(key);
            for (int i = 0; i < aux.getObjects().size(); i++) {
                Items s = aux.getObjects().get(i);
                if (s.isTravel_bug()) {
                    for (int j = 0; j < s.getTravelBug().getHistory_Ches().size(); j++) {
                        if (s.getTravelBug().getHistory_Ches().get(j).compareTo(id) == 0) {
                            s.getTravelBug().getHistory_Ches().remove(j);
                            j--;
                        }

                    }
                }

            }
            for (int j = 0; j <aux.getVisited_cache().size() ; j++) {
                if(aux.getVisited_cache().get(j).compareTo(id)==0){
                    aux.getVisited_cache().remove(j);
                    j--;
                }
            }
            for (int j = 0; j <aux.getOwned().size() ; j++) {
                if(aux.getOwned().get(j)==id){
                    aux.getOwned().remove(j);
                    j--;
                }
            }
        }
        for (int key:stChe.keys()) {
            Cache aux=stChe.get(key);
            for (int i = 0; i < aux.getObjects().size(); i++) {
                Items s = aux.getObjects().get(i);
                if (s.isTravel_bug()) {
                    for (int j = 0; j < s.getTravelBug().getHistory_Ches().size(); j++) {
                        if (s.getTravelBug().getHistory_Ches().get(j).compareTo(id) == 0) {
                            s.getTravelBug().getHistory_Ches().remove(j);
                            j--;
                        }

                    }

                }
            }
        }
        stChe.delete(id);

    }
    /**
     * test_Vsit é a funçao utilizada para testar as tarefas como visitas e não visitase uma serie de outros
     *     @param BSTUtil O Red_Black que armazena o Util normais
     *     @param BSTADMIN O Red_Black que armazena o Util admin
     *     @param BSTPREM O Red_Black que armazena o Util premium
     * @param stChe O Red_Black que armazena o cache
     */
public static  void test_Vsit( RedBlackBST_copy<Integer, Utilizador> BSTUtil,  RedBlackBST_copy<Integer, Util_Admin> BSTADMIN,  RedBlackBST_copy<Integer, Util_premium> BSTPREM, RedBlackBST_copy<Integer, Cache> stChe){

    BSTUtil.get(1).Visite_cache(stChe.get(1));
    BSTUtil.get(1).Visite_cache(stChe.get(2));
    BSTUtil.get(1).transferItemCache_Utilizador(stChe.get(2).Items_get_array(26),stChe.get(2));
    BSTUtil.get(1).Visite_cache(stChe.get(6));
    BSTUtil.get(1).Visite_cache(stChe.get(8));
    BSTUtil.get(1).Visite_cache(stChe.get(13));
    BSTUtil.get(1).Visite_cache(stChe.get(16));
    BSTUtil.get(1).Visite_cache(stChe.get(17));
    BSTUtil.get(1).transferItemCache(BSTUtil.get(1).Items_get_array(26),stChe.get(17));
    BSTUtil.get(2).Visite_cache(stChe.get(18));
    BSTUtil.get(2).Visite_cache(stChe.get(13));
    BSTUtil.get(2).Visite_cache(stChe.get(8));
    BSTUtil.get(2).transferItemCache_Utilizador(stChe.get(8).Items_get_array(35),stChe.get(8));
    BSTADMIN.get(3).Visite_cache(stChe.get(12));
    BSTADMIN.get(3).Visite_cache(stChe.get(11));
    BSTADMIN.get(3).Visite_cache(stChe.get(10));
    BSTADMIN.get(3).Visite_cache(stChe.get(8));
    BSTADMIN.get(3).Visite_cache(stChe.get(9));
    BSTADMIN.get(3).Visite_cache(stChe.get(5));
    BSTADMIN.get(3).Visite_cache(stChe.get(6));
    BSTADMIN.get(3).Visite_cache(stChe.get(4));
    BSTADMIN.get(3).Visite_cache(stChe.get(3));
    BSTADMIN.get(3).Visite_cache(stChe.get(2));
    BSTADMIN.get(3).Visite_cache(stChe.get(1));
    BSTADMIN.get(3).transferItemCache_Utilizador(stChe.get(1).Items_get_array(23),stChe.get(1));
    BSTADMIN.get(3).Visite_cache(stChe.get(7));
    BSTADMIN.get(3).Visite_cache(stChe.get(15));
    BSTADMIN.get(3).transferItemCache(BSTADMIN.get(3).Items_get_array(23),stChe.get(15));
    BSTADMIN.get(3).Visite_cache(stChe.get(17));
    BSTADMIN.get(3).Visite_cache(stChe.get(18));
    BSTADMIN.get(3).Visite_cache(stChe.get(13));
    BSTUtil.get(4).Visite_cache(stChe.get(14));
    BSTUtil.get(4).Visite_cache(stChe.get(15));
    BSTUtil.get(4).Visite_cache(stChe.get(18));
    BSTUtil.get(4).Visite_cache(stChe.get(17));
    BSTUtil.get(4).Visite_cache(stChe.get(13));
     BSTPREM.get(5).Visite_cache(stChe.get(3));
BSTPREM.get(5).transferItemCache_Utilizador(stChe.get(3).Items_get_array(29),stChe.get(3));
    BSTPREM.get(5).Visite_cache(stChe.get(8));
    BSTPREM.get(5).Visite_cache(stChe.get(9));
    BSTPREM.get(5).Visite_cache(stChe.get(10));
    BSTPREM.get(5).Visite_cache(stChe.get(16));
    BSTPREM.get(5).Visite_cache(stChe.get(11));
    BSTPREM.get(5).Visite_cache(stChe.get(12));
    BSTPREM.get(5).transferItemCache(BSTPREM.get(5).Items_get_array(29),stChe.get(12));
   // stChe.get(12).getObjects().get(1).now();
   // stChe.get(12).getD().Logprint();
    BSTADMIN.get(6).Visite_cache(stChe.get(5));
    BSTADMIN.get(6).Visite_cache(stChe.get(6));
    BSTADMIN.get(6).Visite_cache(stChe.get(7));
    BSTADMIN.get(6).Visite_cache(stChe.get(3));
    BSTADMIN.get(6).Visite_cache(stChe.get(2));
    BSTADMIN.get(6).Visite_cache(stChe.get(1));
    BSTADMIN.get(6).Visite_cache(stChe.get(8));
    BSTADMIN.get(6).Visite_cache(stChe.get(13));
    Top_5_util(BSTUtil,BSTADMIN,BSTPREM);
    Top_travel(BSTUtil,BSTADMIN,BSTPREM,stChe);
    BSTADMIN.get(6).delet_Util(BSTUtil.get(2).getID_UTI(),BSTUtil,BSTADMIN,BSTPREM,stChe);
  //  BSTADMIN.get(6).getLog().Logsave("src/datapacks/Test_log_de",BSTADMIN.get(6).getID_UTI());
    Premium_caches_not_emty(stChe);
    BSTADMIN.get(6).removeCache(BSTUtil,BSTADMIN,BSTPREM,stChe,4);
    BSTADMIN.get(6).removeCache(BSTUtil,BSTADMIN,BSTPREM,stChe,8);
    BSTADMIN.get(6).removeCache(BSTUtil,BSTADMIN,BSTPREM,stChe,18);
    for (int key:BSTUtil.keys()){
            BSTUtil.get(key).not_visiteed(stChe);
    }
    
        /* Tarefa inicial:
 - o Manuel visita as geocaches 1, 2, 6, 8, 13, 16, 17 ao passar na geocache2 tirou o travelbug2. Ao passar na geocache 17 deixou o travelbug2.
 - o Pedro visita as geocaches 18, 13, 8 ao passar na geocache 8 tirou a capa
 - o Fernando visita as geocaches 12, 11, 10, 8, 9, 5, 6, 4, 3, 2, 1, 7, 15, 17, 18, 13. Ao passar na geocache1 tirou o travelbug1. Ao passar na geocache 15 deixou ficar o travelbug1.
 - a Joana visita as geocaches 14, 15, 18, 17, 13
 - a Maria visita as geocaches 3, 8, 9, 10, 16, 11, 12. Ao passar na geocache 3 tirou o travelbug3. Ao passar na geocache12 deixou ficar o travelbug3.
 - a Filomena visita as geocaches 5, 6, 7, 3, 2, 1, 8, 13
 com o top 5 de visitas
   */
/**
 * Exemplo de um save de log e print com o util id 3 admin
 */
    //BSTADMIN.get(3).getLog().Logsave("src/datapacks/test_save_Log",BSTADMIN.get(3).getID_UTI());
    String path_save_test_Util = "src/datapacks/test_save_Util.txt";

    String path_save_test_CAche = "src/datapacks/test_save_Cache.txt";
    Save_Cache(path_save_test_CAche,stChe);
    String path_save_test_Travel_BUG = "src/datapacks/test_save_TravelBug";
     Save_TRavel(path_save_test_Travel_BUG,BSTUtil,BSTADMIN,BSTPREM,stChe);
Save_Util(path_save_test_Util,BSTUtil,BSTADMIN,BSTPREM);

}
public static  void Top_5_util( RedBlackBST_copy<Integer, Utilizador> BSTUtil,  RedBlackBST_copy<Integer, Util_Admin> BSTADMIN,  RedBlackBST_copy<Integer, Util_premium> BSTPREM){
  ST<Integer,String> aux = new ST<>();
/**
 * @param aux é um auiliar de ST onde vai ter o numer de visitas do util e o seu nome, a ST automaticamente organiza do menor ao maior
 */
    for (int key:BSTPREM.keys()){
 aux.put(BSTPREM.get(key).getNumbVisted(),BSTPREM.get(key).getName());
}
    for (int key:BSTADMIN.keys()){
        aux.put(BSTADMIN.get(key).getNumbVisted(),BSTADMIN.get(key).getName());
    }
    for (int key:BSTUtil.keys()){
        aux.put(BSTUtil.get(key).getNumbVisted(),BSTUtil.get(key).getName());
    }

    int h=0;
    int s=5;
for (int key: aux.keys()){
    if(h+5>=aux.size()){
        System.out.println(s+"\t" +key+"\tFROM:\t" +aux.get(key)+"\n");
s--;    }

        h++;
}
/*
  @param s é um axiliar onde vai de 5,4,3,2,1 para indicar o quinto,quarto... que a ST indicar
 *          @param key é um axiliar onde vai do menor int para o maior
 */


}

public static  void Top_travel( RedBlackBST_copy<Integer, Utilizador> BSTUtil,  RedBlackBST_copy<Integer, Util_Admin> BSTADMIN,  RedBlackBST_copy<Integer, Util_premium> BSTPREM, RedBlackBST_copy<Integer, Cache> stChe) {
       int aux=-1;
       String aux2 = "";
/**
 * @param aux é um auiliar de ST onde vai ter o numer de visitas do util e o seu nome, a ST automaticamente organiza do menor ao maior
 */
        for (int key : BSTPREM.keys()) {
            for (int i = 0; i <BSTPREM.get(key).getObjects().size() ; i++) {
if(BSTPREM.get(key).getObjects().get(i).isTravel_bug()){
    if(BSTPREM.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().size()>aux){
        aux=BSTPREM.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().size();
       aux2=Util_premium.class+" "+BSTPREM.get(key).getID_UTI();
    }
}
            }

        }
        for (int key : BSTADMIN.keys()) {
            for (int i = 0; i <BSTADMIN.get(key).getObjects().size() ; i++) {
                if(BSTADMIN.get(key).getObjects().get(i).isTravel_bug()){
                    if(BSTADMIN.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().size()>aux){
                        aux=BSTADMIN.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().size();
                        aux2=Util_Admin.class+" "+BSTADMIN.get(key).getID_UTI();
                    }
                }
            }

        }
        for (int key : BSTUtil.keys()) {
            for (int i = 0; i <BSTUtil.get(key).getObjects().size() ; i++) {
                if(BSTUtil.get(key).getObjects().get(i).isTravel_bug()){
                    if(BSTUtil.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().size()>aux){
                        aux=BSTUtil.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().size();
                        aux2=Utilizador.class+" "+BSTUtil.get(key).getID_UTI();
                    }
                }
            }
        }
    for (int key : stChe.keys()) {
        for (int i = 0; i <stChe.get(key).getObjects().size() ; i++) {
            if(stChe.get(key).getObjects().get(i).isTravel_bug()){
                if(stChe.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().size()>aux){
                    aux=stChe.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().size();
                    aux2=Cache.class+" "+stChe.get(key).getID_CACHE();
                }
            }
        }
    }

       System.out.println(aux+" \t"+aux2);
    }

    /**
     *
     * @param stChe O Red_Black que armazena as ches
     *     Cria um ST que ira guardar oa visited e nome
     */
    public static  void Premium_caches_not_emty( RedBlackBST_copy<Integer, Cache> stChe){
        System.out.println("Cache premuim \n");
for (int key:stChe.keys()){
    if (stChe.get(key).getType().compareTo("Premium")==0 && stChe.get(key).getObjects().size()!=0){
        System.out.println("Cache:"+stChe.get(key).getID_CACHE()+"\n");
    }
}
    }
    public  static  void Separete_func(SeparateChainingHashST<Integer,Utilizador> util_has,  RedBlackBST_copy<Integer, Utilizador> BSTUtil ){
    for (int key: BSTUtil.keys()){
        util_has.put(key,BSTUtil.get(key));
    }
    }

    /**
     *
     *     @param BSTUtil O Red_Black que armazena o Util normais
     *     @param BSTADMIN O Red_Black que armazena o Util admin
     *     @param BSTPREM O Red_Black que armazena o Util premium
     * @param stChe O Red_Black que armazena o cache
     * SYN tem o objetivo exclusivo de ir campo a campo que tem referencias as ids e verifica e na verdade existem
     */
    public static  void SYN( RedBlackBST_copy<Integer, Utilizador> BSTUtil,  RedBlackBST_copy<Integer, Util_Admin> BSTADMIN,  RedBlackBST_copy<Integer, Util_premium> BSTPREM, RedBlackBST_copy<Integer, Cache> stChe) {
        for (int key : BSTADMIN.keys()) {
            for (int i = 0; i <BSTADMIN.get(key).getObjects().size() ; i++) {
                if (BSTADMIN.get(key).getObjects().get(i).isTravel_bug()){
                    for (int j = 0; j <BSTADMIN.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().size() ; j++) {
                        if(stChe.get(BSTADMIN.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().get(j))==null){
                            BSTADMIN.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().remove(j);
                            j--;
                        }
                    }
                    for (int j = 0; j <BSTADMIN.get(key).getObjects().get(i).getTravelBug().getHistory().size() ; j++) {
                        if(BSTADMIN.get(BSTADMIN.get(key).getObjects().get(i).getTravelBug().getHistory().get(j))==null){
                            if(BSTPREM.get(BSTADMIN.get(key).getObjects().get(i).getTravelBug().getHistory().get(j))==null){
                                if(BSTUtil.get(BSTADMIN.get(key).getObjects().get(i).getTravelBug().getHistory().get(j))==null){
                            BSTADMIN.get(key).getObjects().get(i).getTravelBug().getHistory().remove(j);
                            j--;
                        }}}
                    }
                }

            }
            for (int i = 0; i <BSTADMIN.get(key).getVisited_cache().size() ; i++) {
                if(stChe.get(BSTADMIN.get(key).getVisited_cache().get(i))==null){
                    BSTADMIN.get(key).getVisited_cache().remove(i);
                    i--;
                }
            }
            BSTADMIN.get(key).setNumbVisted(BSTADMIN.get(key).getVisited_cache().size());
        }



        for (int key : BSTUtil.keys()) {
            for (int i = 0; i <BSTUtil.get(key).getObjects().size() ; i++) {
                if (BSTUtil.get(key).getObjects().get(i).isTravel_bug()){
                    for (int j = 0; j <BSTUtil.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().size() ; j++) {
                        if(stChe.get(BSTUtil.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().get(j))==null){
                            BSTUtil.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().remove(j);
                            j--;
                        }
                    }
                    for (int j = 0; j <BSTUtil.get(key).getObjects().get(i).getTravelBug().getHistory().size() ; j++) {
                        if(BSTADMIN.get(BSTUtil.get(key).getObjects().get(i).getTravelBug().getHistory().get(j))==null){
                            if(BSTPREM.get(BSTUtil.get(key).getObjects().get(i).getTravelBug().getHistory().get(j))==null){
                                if(BSTUtil.get(BSTUtil.get(key).getObjects().get(i).getTravelBug().getHistory().get(j))==null){
                                    BSTUtil.get(key).getObjects().get(i).getTravelBug().getHistory().remove(j);
                                    j--;
                                }}}
                    }
                }

            }
            for (int i = 0; i <BSTUtil.get(key).getVisited_cache().size() ; i++) {
                if(stChe.get(BSTUtil.get(key).getVisited_cache().get(i))==null){
                    BSTUtil.get(key).getVisited_cache().remove(i);
                    i--;
                }
            }
            BSTUtil.get(key).setNumbVisted(BSTUtil.get(key).getVisited_cache().size());
        }
        for (int key : BSTPREM.keys()) {
            for (int i = 0; i <BSTPREM.get(key).getObjects().size() ; i++) {
                if (BSTPREM.get(key).getObjects().get(i).isTravel_bug()){
                    for (int j = 0; j <BSTPREM.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().size() ; j++) {
                        if(stChe.get(BSTPREM.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().get(j))==null){
                            BSTPREM.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().remove(j);
                            j--;
                        }
                    }
                    for (int j = 0; j <BSTPREM.get(key).getObjects().get(i).getTravelBug().getHistory().size() ; j++) {
                        if(BSTADMIN.get(BSTPREM.get(key).getObjects().get(i).getTravelBug().getHistory().get(j))==null){
                            if(BSTPREM.get(BSTPREM.get(key).getObjects().get(i).getTravelBug().getHistory().get(j))==null){
                                if(BSTUtil.get(BSTPREM.get(key).getObjects().get(i).getTravelBug().getHistory().get(j))==null){
                                    BSTPREM.get(key).getObjects().get(i).getTravelBug().getHistory().remove(j);
                                    j--;
                                }}}
                    }
                }

            }
            for (int i = 0; i <BSTPREM.get(key).getVisited_cache().size() ; i++) {
                if(stChe.get(BSTPREM.get(key).getVisited_cache().get(i))==null){
                    BSTPREM.get(key).getVisited_cache().remove(i);
                    i--;
                }
            }
            BSTPREM.get(key).setNumbVisted(BSTPREM.get(key).getVisited_cache().size());
        }

        for (int key : stChe.keys()) {
            for (int i = 0; i <stChe.get(key).getObjects().size() ; i++) {
                if (stChe.get(key).getObjects().get(i).isTravel_bug()){
                    for (int j = 0; j <stChe.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().size() ; j++) {
                        if(stChe.get(stChe.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().get(j))==null){
                            stChe.get(key).getObjects().get(i).getTravelBug().getHistory_Ches().remove(j);
                            j--;
                        }
                    }
                    for (int j = 0; j <stChe.get(key).getObjects().get(i).getTravelBug().getHistory().size() ; j++) {
                        if(BSTADMIN.get(stChe.get(key).getObjects().get(i).getTravelBug().getHistory().get(j))==null){
                            if(BSTPREM.get(stChe.get(key).getObjects().get(i).getTravelBug().getHistory().get(j))==null){
                                if(BSTUtil.get(stChe.get(key).getObjects().get(i).getTravelBug().getHistory().get(j))==null){
                                    stChe.get(key).getObjects().get(i).getTravelBug().getHistory().remove(j);
                                    j--;
                                }}}
                    }
                }

            }
        }


    }

    /**
     *
     * @param BSTUtil O Red_Black que armazena o Util normais
     * @param BSTADMIN  Red_Black que armazena o Util admin
     * @param BSTPREM O Red_Black que armazena o Util premium
     * @param stChe O Red_Black que armazena o cache
     *              Inicio de zona de test da segunda fase
     */
    public static void graf( RedBlackBST_copy<Integer, Utilizador> BSTUtil,  RedBlackBST_copy<Integer, Util_Admin> BSTADMIN,  RedBlackBST_copy<Integer, Util_premium> BSTPREM, RedBlackBST_copy<Integer, Cache> stChe){
        EdgeWeightedDigraph_copy speed=new EdgeWeightedDigraph_copy(stChe.size());
String path="src/datapacks/Position.txt";
    RedBlackBST_copy<Integer, Geo_Cache> Geocahe=new  RedBlackBST_copy<>();
    Convert_cache_geo_with_ST(stChe,Geocahe);
    ST <Integer, Integer> regist= Read_conce_tx(path,stChe,speed);
    ST<Integer,Integer> regist_prem=new ST<>();
    String path_OBJ_BIN="src/datapacks/PositionBin_OBJ";
  //SAVE_conce_Bin(path_OBJ_BIN,Geocahe,speed);
 //   RedBlackBST_copy<Integer, Geo_Cache> Geocahe_test=new  RedBlackBST_copy<>();
  //  EdgeWeightedDigraph_copy speed3=new EdgeWeightedDigraph_copy(stChe.size());
  // ST<Integer,Integer>G_test=Read_conce_Bin(path_OBJ_BIN,Geocahe_test,speed3);
    EdgeWeightedDigraph_copy prem =  sub_grafe_prem(speed,regist,Geocahe,regist_prem);
    //double[] aux2={5.2,60};
    //double[] aux3={1,30};
    //DirectedEdge_copy aux=new DirectedEdge_copy(0,1,aux2);
  //  DirectedEdge_copy aux4=new DirectedEdge_copy(0,7,aux3);
//Edite_vertiz(speed, aux,aux4);
//int [] array_tesr={3,6,8,4};
//speed=speed.Set_set_by_removal(regist,array_tesr);
   // speed.remove_V(5,regist);
   // speed.find_shorstes_path_time(5,9);
   /* DepthFirstSearch_copy aux=new DepthFirstSearch_copy(speed,6);
    for (int v = 0; v < speed.V(); v++) {
        if (aux.marked(v))
            StdOut.print(v + " ");
    }

    StdOut.println();
    if (aux.count() != speed.V()) StdOut.println("NOT connected");
    else                         StdOut.println("connected");
*/
   // Btcontroler.main(new String[2]);
}

    /**
     *
     * @param path path que esta guardado a informaçao
     * @param stChe O Red_Black que armazena o cache
     * @param speed Grafo vazio  para guardar os edges do ficheiro
     * @return return uma symbel table com o key os ids da geocaces values sao um int os vertices correspondeste
     */

public  static ST<Integer, Integer> Read_conce_tx(String path,  RedBlackBST_copy<Integer, Cache> stChe, EdgeWeightedDigraph_copy speed) {
    ST<Integer, Integer> b = new ST<>();
int l=0;
    for (int key : stChe.keys()) {
        b.put(stChe.get(key).getID_CACHE(),l);
l++;
    }
    File myObj = new File(path);
    try {
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String[] h = myReader.nextLine().split(",");
            double[] n={Double.parseDouble(h[2]),Double.parseDouble(h[3])};
            int a=Integer.parseInt(h[0]);
            int c=Integer.parseInt(h[1]);

            if (b.get(a)==null&&b.get(c)==null){
                System.out.println("ERRROO");
                break;
            }else if ((a>0&&a<b.size()) &&(c>0&&a<b.size())) {
int aux=b.get(a);
                int aux2=b.get(c);
                speed.addEdge(new DirectedEdge_copy(aux,aux2,n));
            }}
        myReader.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return b;
}

    /**
     *
     * @param stChe O Red_Black que armazena o cache
     * @param Geocahe a O Red_Black que armazena o que guarda as Geo_chases
     *                Coverta as cahes em geoches
     */
    public  static void Convert_cache_geo_with_ST( RedBlackBST_copy<Integer, Cache> stChe,  RedBlackBST_copy<Integer, Geo_Cache> Geocahe){
        if(Geocahe==null){
            Geocahe=new  RedBlackBST_copy<>();
        }
        for (int key:stChe.keys()){
            Geocahe.put(key,new Geo_Cache(stChe.get(key).getType(),stChe.get(key).getGps(),key));
        }
    }

    /**
     *
     * @param sp grafo em referncia
     * @param d Symbel table correspondente
     * @param Geo_cache Geochaes para comparar
     * @param prem_st symbeltabel vazia para criar
     * @return um grafo ue so tem edges e vertices entre cache premium
     */

    public static  EdgeWeightedDigraph_copy sub_grafe_prem( EdgeWeightedDigraph_copy sp,ST <Integer, Integer> d, RedBlackBST_copy<Integer, Geo_Cache> Geo_cache,ST <Integer,Integer> prem_st){
        EdgeWeightedDigraph_copy prem=new EdgeWeightedDigraph_copy(sp.V());
int numb=0;
if (!prem_st.isEmpty()){
    for (int key:prem_st.keys()) {

        prem_st.remove(key);
    }

}
int count=0;
int a=0;
ArrayList<Integer> aux=new ArrayList<>() ;
        for (int i=0;i<sp.V();i++) {
            for (int key:Geo_cache.keys()) {
                if (Geo_cache.get(key).getType().compareTo("Premium")==0&&d.get(key)==i){
                    aux.add(key);
                    prem_st.put(key,count);
                    count++;
                    a=-1;
                }
            }
        }
        prem=new EdgeWeightedDigraph_copy(count);
        for (DirectedEdge_copy e :sp.edges()) {
            int to=-1;
            int from=-1;
            for (int key:prem_st.keys()) {
                if (e.to()==d.get(key)){
                    to=prem_st.get(key);
                }else if (e.from()==d.get(key)){
                    from=prem_st.get(key);
                }
            }
            if (to!=-1 && from!=-1){
                prem.addEdge(new DirectedEdge_copy(from,to,e.weight()));

            }

        }
        return prem;
    }
    public  static void SAVE_conce_Bin(String path,  RedBlackBST_copy<Integer, Geo_Cache> Geocahe, EdgeWeightedDigraph_copy speed){
        File f=new File(path);
        try{
            FileOutputStream fis=new FileOutputStream(f);
            ObjectOutputStream cis=new ObjectOutputStream(fis);
            cis.writeObject(speed);
cis.writeObject(Geocahe);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static ST<Integer, Integer> Read_conce_Bin(String path,  RedBlackBST_copy<Integer, Geo_Cache> Geocahe,EdgeWeightedDigraph_copy speed){
        File f=new File(path);
        ST<Integer, Integer> test=new ST<>();
        try{
            FileInputStream fis=new FileInputStream(f);
            ObjectInputStream cis=new ObjectInputStream(fis);
            speed=(EdgeWeightedDigraph_copy) cis.readObject();
            Geocahe=(RedBlackBST_copy<Integer, Geo_Cache>) cis.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (!speed.isEmpty()) {
            int h=0;
            for (int key:Geocahe.keys()) {
                test.put(key,h);
                h++;
            }



        }
        return test;
    }

    public static ST<Integer, Integer> Read_conce_1_Bin(String path,  RedBlackBST_copy<Integer, Geo_Cache> Geocahe,EdgeWeightedDigraph_copy speed){
        File f=new File(path);
        ST<Integer, Integer> test=new ST<>();
        try{
            FileInputStream fis=new FileInputStream(f);
            ObjectInputStream cis=new ObjectInputStream(fis);
            speed=(EdgeWeightedDigraph_copy) cis.readObject();
            Geocahe=(RedBlackBST_copy<Integer, Geo_Cache>) cis.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (!speed.isEmpty()) {
            int h=0;
            for (int key:Geocahe.keys()) {
                test.put(key,h);
                h++;
            }



        }
        return test;
    }

    public static  void Edite_vertiz( EdgeWeightedDigraph_copy sp,DirectedEdge_copy old,DirectedEdge_copy newer) {

if (sp.If_existed(old)&&!sp.If_existed(newer)){
    return;
}
       sp.switch_bag(old,newer);
    }
    public static  void insert_vertiz( EdgeWeightedDigraph_copy sp,DirectedEdge_copy newr) {

        if (sp.If_existed(newr)){
            return;
        }
        sp.add_TO_bag(newr);
    }

    }

