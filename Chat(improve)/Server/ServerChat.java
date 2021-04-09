package Chat.Server;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ServerChat extends JFrame {

    //display chat history
    public JTextArea jta = new JTextArea();
    //The JScrollPane panel is a panel with scroll bars
    private JScrollPane jsp = new JScrollPane(jta);
    private JPanel panel = new JPanel();


    //process time format
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date d = new Date();
    String time = format.format(d);



    public static void main(String[] args) {
        new ServerChat();
    }

    public ServerChat() {

        init();
    }

    public void init() {
        //title
        setTitle("Server");
        setSize(600, 500);
        setLayout(new BorderLayout());

        //position the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = this.getSize();
        setLocation((int) (screenSize.getWidth() - size.getWidth()) / 2, (int) (screenSize.getHeight() - size.getHeight()) / 2);

        //window with scrool wheel
        add(jsp, BorderLayout.CENTER);
        //textarea can not be edited
        jta.setEditable(false);


        //window exit method
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        new StartServer(jta).startServer();

    }

}



