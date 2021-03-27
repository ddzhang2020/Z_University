package Chat.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientChat extends JFrame {

    //display chat history
    private JTextArea jta= new JTextArea();
    //The JScrollPane panel is a panel with scroll bars
    private JScrollPane jsp = new JScrollPane(jta);
    //public JPanel pLable = new JPanel();
    //private JScrollPane jsp = new JScrollPane(pLable);
    private JPanel panel = new JPanel();
    private JTextField jtf = new JTextField();
    private JButton sendBtn = new JButton("send");

    //process time format
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date d = new Date();
    String time = format.format(d);

    private Socket s;

    private boolean isConn = false;




    public static void main(String[] args){

        new ClientChat();
    }


    public ClientChat(){

        init();
    }

    public void init(){
        //title
        setTitle("Client");
        setSize(600,500);
        setLayout(new BorderLayout());

        //position the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = this.getSize();
        setLocation((int)(screenSize.getWidth() - size.getWidth()) / 2, (int) (screenSize.getHeight() - size.getHeight()) / 2);

        //window with scrool wheel
        add(jsp,BorderLayout.CENTER);
        //textarea can not be edited
        jta.setEditable(false);


        //sending field
        panel.setLayout(new BorderLayout());
        panel.add(jtf,BorderLayout.CENTER);
        jtf.setHorizontalAlignment(JTextField.LEFT);
        //cursor focus
        jtf.requestFocus();
        panel.add(sendBtn,BorderLayout.EAST);
        add(panel,BorderLayout.SOUTH);

        //window exit method
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.conn();

        //register listener
        //click send button to send message
        sendBtn.addActionListener(new SendActionListener());
        //press enter to send message
        jtf.addActionListener(new SendActionListener());

        //multi-threaded start when the window is opend
        new Thread(new Receive(s,jta,isConn)).start();



    }

    private void conn(){
        try {
            s = new Socket("localhost", 9999);
            //Indicates the connection to the server is successful
            isConn = true;
            jta.append("已连接" + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public class SendActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            DataOutputStream dos= null;
            String sm = jtf.getText();

            //The message is empty and will not be sent
            if(sm.trim().length() == 0){
                return;
            }
            try {
                dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(sm);
                dos.flush();

                jta.append(time + "     " + "我："+ sm + "\n"+ "\n");
                jtf.setText(null);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


}
