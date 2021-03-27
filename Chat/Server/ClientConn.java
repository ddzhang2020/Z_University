package Chat.Server;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

//the object is a connection object of server(thread start)
public class ClientConn implements Runnable {

    //process time format
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date d = new Date();
    String time = format.format(d);

    private JTextArea jta;

    private Socket s;
    private DataInputStream dis;

    private boolean isStart;

    private ArrayList<ClientConn> cclist;

    private File tfile = new File("D:\\total.txt");
    private FileWriter fw;

    public ClientConn(Socket s,JTextArea jta,boolean isStart,ArrayList<ClientConn> cclist) {
        this.s = s;
        this.jta = jta;
        this.isStart = isStart;
        this.cclist = cclist;
        (new Thread(this)).start();
    }

    //multiple threads accept client message at the same time
    public void run() {

        try {
            dis = new DataInputStream(s.getInputStream());
            //true loop
            while (isStart) {

                //display the received message
                String rms = dis.readUTF();
                String sms = time + "     " + s.getPort() + "：" + rms + "\n";
                jta.append(sms);

                //traverse cclist,call the send method on each client
                Iterator<ClientConn> ico = cclist.iterator();
                while (ico.hasNext()) {
                    ClientConn cco = ico.next();
                    if(cco.s.getPort() !=s.getPort()){
                        cco.send(sms);
                    }

                }

                //write chat history to a text
                try {
                    fw = new FileWriter(tfile, true);
                    fw.write(sms);
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } catch (SocketException e) {
            jta.append(s.getPort() + "客户端下线了" + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //server send the message received
    public void send(String sms){
        try {
            DataOutputStream dos = new DataOutputStream(this.s.getOutputStream());
            dos.writeUTF(sms);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

