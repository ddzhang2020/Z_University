package Chat.Server;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class StartServer {

    public ServerSocket ss = null;
    private Socket s = null;

    public ArrayList<ClientConn> cclist = new ArrayList<>();

    private DataInputStream dis;

    public boolean isStart = false;

    public JTextArea jta;

    public StartServer(JTextArea jta){
        this.jta = jta;
    }


    public void startServer() {
        try {

            ss = new ServerSocket(9999);
            isStart = true;
            jta.append("服务器已启动" + "\n");

            while (isStart) {
                s = ss.accept();
                cclist.add(new ClientConn(s, jta, isStart, cclist));

                jta.append("一个客户端连接服务器：" + s.getInetAddress() + "/" + s.getPort() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
