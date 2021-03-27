package Chat.Client;

import javax.swing.*;
import java.io.DataInputStream;
import java.net.Socket;
import java.net.SocketException;

//multiple thread class
class Receive implements Runnable{
    private Socket s;
    private JTextArea jta;
    private boolean isConn;

    public Receive(Socket s,JTextArea jta,boolean isConn){
        this.s = s;
        this.jta = jta;
        this.isConn = isConn;
    }


    public void run(){
        try {
            while(isConn){
                DataInputStream dis = new DataInputStream(s.getInputStream());
                String rms = dis.readUTF();
                jta.append(rms + "\n");
            }
        }catch(SocketException e){
            jta.append(s.getPort()+"服务器意外终止");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}