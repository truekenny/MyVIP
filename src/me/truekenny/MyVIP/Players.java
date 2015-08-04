package me.truekenny.MyVIP;

import org.apache.commons.lang.time.DateUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class Players {
    private MyVIP plugin;

    final public String FILENAME = "plugins/MyVIP/players.txt";
    private Hashtable<String, String[]> players = new Hashtable<String, String[]>();

    public Players(MyVIP plugin) {
        this.plugin = plugin;

        load();
        save();
    }

    public boolean vip(String nick) {
        String[] data = players.get(nick.toLowerCase());

        if (data == null) {

            return false;
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = simpleDateFormat.format(date);

        if (now.compareTo(data[0]) > 0) {
            delete(nick);

            return false;
        }

        return true;
    }

    public String getDate(String nick) {
        String[] data = players.get(nick.toLowerCase());

        if (data == null) {

            return "";
        }

        return data[0];
    }

    public void add(String nick, int days) {
        Date date = DateUtils.addDays(new Date(), days);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String[] data = {simpleDateFormat.format(date)};


        players.put(nick.toLowerCase(), data);

        save();
    }

    public void delete(String nick) {
        players.remove(nick);

        save();
    }

    private boolean load() {
        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILENAME));

            while ((sCurrentLine = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(sCurrentLine);
                String nick = st.nextToken();
                String date = st.nextToken();

                String[] data = {date};
                players.put(nick, data);
            }

        } catch (IOException e) {
            plugin.log.info("File " + FILENAME + " not found");
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return true;
    }

    /**
     * Сохранение данных
     *
     * @return
     */
    private boolean save() {
        Enumeration<String> e = players.keys();
        PrintWriter out;
        try {
            out = new PrintWriter(FILENAME);
        } catch (Exception ex) {
            return false;
        }

        while (e.hasMoreElements()) {
            String nick = e.nextElement();
            String[] data = players.get(nick);

            out.printf("%s %s\n", nick, data[0]);
        }

        out.close();

        return true;
    }

    public void test() {
        add("test", 30);
        add("test", 30);

        add("test_now", 0);
        add("test_low", -1);
        add("test_more", 1);
        delete("test_404");


        plugin.log.info("Compare test_now: true=" + vip("test_now"));
        plugin.log.info("Compare test_low: false=" + vip("test_low"));
        plugin.log.info("Compare test_more: true=" + vip("test_more"));
        plugin.log.info("Compare test_404: false=" + vip("test_404"));
    }
}
