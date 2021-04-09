package Chat.Client;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

//multiple thread class
public class Receive implements Runnable{
    private Socket s;
    private DataInputStream dis;
    DataOutputStream dos = null;
    //private JTextArea jta;
    private JPanel pLable;
    private boolean isConn;

    public Receive(Socket s,DataOutputStream dos,JPanel pLable,boolean isConn){
        this.s = s;
        //this.jta = jta;
        this.dos = dos;
        this.pLable = pLable;
        this.isConn = isConn;
    }


    public void run(){
        try {
            while(isConn){
                dis = new DataInputStream(s.getInputStream());
                String rms = dis.readUTF();
                //jta.append(rms + "\n");

                SwingUtilities.invokeLater(new Runnable(){
                    public void run() {

                        pLable.add(new JLabel(rms));
                        pLable.revalidate();
                    }
                });

            }
        }catch(SocketException e){
            //jta.append(s.getPort()+"服务器意外终止");
            SwingUtilities.invokeLater(new Runnable(){
                public void run() {

                    pLable.add(new JLabel(s.getPort()+"服务器意外终止"));
                    pLable.revalidate();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                dis.close();
                dos.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}