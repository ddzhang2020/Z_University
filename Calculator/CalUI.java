package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalUI {
    //Initialize controls and variables in the constructor
    CalUI() {
        CalStans.f = new JFrame("Calculator");
        CalStans.mp = new JPanel();

        //Create a single-line text control
        CalStans.show = new JTextField("0");
        CalStans.showexp = new JTextField();

        //Create buttons
        CalStans.zero = new JButton("0");
        CalStans.one = new JButton("1");
        CalStans.two = new JButton("2");
        CalStans.three = new JButton("3");
        CalStans.four = new JButton("4");
        CalStans.five = new JButton("5");
        CalStans.six = new JButton("6");
        CalStans.seven = new JButton("7");
        CalStans.eight = new JButton("8");
        CalStans.nine = new JButton("9");
        CalStans.CE = new JButton("CE");
        CalStans.C = new JButton("C");
        CalStans.del = new JButton("DEL");
        CalStans.plus = new JButton("+");
        CalStans.minus = new JButton("-");
        CalStans.mul = new JButton("*");
        CalStans.div = new JButton("/");
        CalStans.neg = new JButton("+/-");
        CalStans.point = new JButton(".");
        CalStans.eql = new JButton("=");

        //Initially set the string to store the expression and result as an empty string
        CalStans.exp = CalStans.outcome = "";
    }

    public void display() {
        //Exit the program when the is closed
        CalStans.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set the properties of the single-line text box that displays the results
        CalStans.show.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        CalStans.show.setBorder(BorderFactory.createEmptyBorder());
        CalStans.show.setHorizontalAlignment(SwingConstants.RIGHT);
        CalStans.show.setEnabled(false);

        //Set the related properties of the single-line text box that displays the expression
        CalStans.showexp.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.showexp.setBorder(BorderFactory.createEmptyBorder());
        CalStans.showexp.setHorizontalAlignment(SwingConstants.RIGHT);
        CalStans.showexp.setEnabled(false);

        //Set the related properties of the button
        CalStans.CE.setForeground(Color.BLUE);
        CalStans.CE.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.CE.setContentAreaFilled(false);
        CalStans.C.setForeground(Color.BLUE);
        CalStans.C.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.C.setContentAreaFilled(false);
        CalStans.div.setForeground(Color.BLUE);
        CalStans.div.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.div.setContentAreaFilled(false);
        CalStans.mul.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.mul.setForeground(Color.BLUE);
        CalStans.mul.setContentAreaFilled(false);
        CalStans.plus.setForeground(Color.BLUE);
        CalStans.plus.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.plus.setContentAreaFilled(false);
        CalStans.minus.setForeground(Color.BLUE);
        CalStans.minus.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.minus.setContentAreaFilled(false);
        CalStans.point.setForeground(Color.BLUE);
        CalStans.point.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.point.setContentAreaFilled(false);
        CalStans.del.setForeground(Color.BLUE);
        CalStans.del.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.del.setContentAreaFilled(false);
        CalStans.eql.setForeground(Color.BLUE);
        CalStans.eql.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.eql.setContentAreaFilled(false);
        CalStans.zero.setForeground(Color.BLUE);
        CalStans.zero.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.zero.setContentAreaFilled(false);
        CalStans.one.setForeground(Color.BLUE);
        CalStans.one.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.one.setContentAreaFilled(false);
        CalStans.two.setForeground(Color.BLUE);
        CalStans.two.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.two.setContentAreaFilled(false);
        CalStans.three.setForeground(Color.BLUE);
        CalStans.three.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.three.setContentAreaFilled(false);
        CalStans.four.setForeground(Color.BLUE);
        CalStans.four.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.four.setContentAreaFilled(false);
        CalStans.five.setForeground(Color.BLUE);
        CalStans.five.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.five.setContentAreaFilled(false);
        CalStans.six.setForeground(Color.BLUE);
        CalStans.six.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.six.setContentAreaFilled(false);
        CalStans.seven.setForeground(Color.BLUE);
        CalStans.seven.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.seven.setContentAreaFilled(false);
        CalStans.eight.setForeground(Color.BLUE);
        CalStans.eight.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.eight.setContentAreaFilled(false);
        CalStans.nine.setForeground(Color.BLUE);
        CalStans.nine.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.nine.setContentAreaFilled(false);
        CalStans.neg.setForeground(Color.BLUE);
        CalStans.neg.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CalStans.neg.setContentAreaFilled(false);

        //Set GridBagLayout
        GridBagLayout gblayout = new GridBagLayout();
        CalStans.mp.setLayout(gblayout);
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.BOTH;  //Set to fill the entire space when a cell is not filled
        g.weightx = 1.0;  //Set the zoom ratio when the window becomes larger
        g.weighty = 1.0;

        g.gridx = 0;  //Position in the first row and first column
        g.gridy = 0;
        g.gridwidth = GridBagConstraints.REMAINDER;  //Fill the entire row
        g.gridheight = 1;  //Occupy a row of grid
        g.insets = new Insets(5, 5, 0, 5);  //Set the distance between this component and other components
        gblayout.setConstraints(CalStans.showexp, g);  //Apply the above settings to the showexp text box

        g.gridx = 0;
        g.gridy = 1;
        g.gridheight = 2;
        g.insets = new Insets(0, 5, 5, 5);
        gblayout.setConstraints(CalStans.show, g);

        g.insets = new Insets(5, 5, 5, 5);
        g.gridwidth = 1;
        g.gridheight = 1;

        g.gridy = 3;
        g.gridx = 0;
        gblayout.setConstraints(CalStans.CE, g);
        g.gridx = 1;
        gblayout.setConstraints(CalStans.C, g);
        g.gridx = 2;
        gblayout.setConstraints(CalStans.del, g);
        g.gridx = 3;
        gblayout.setConstraints(CalStans.div, g);

        g.gridy = 4;
        g.gridx = 0;
        gblayout.setConstraints(CalStans.seven, g);
        g.gridx = 1;
        gblayout.setConstraints(CalStans.eight, g);
        g.gridx = 2;
        gblayout.setConstraints(CalStans.nine, g);
        g.gridx = 3;
        gblayout.setConstraints(CalStans.mul, g);

        g.gridy = 5;
        g.gridx = 0;
        gblayout.setConstraints(CalStans.four, g);
        g.gridx = 1;
        gblayout.setConstraints(CalStans.five, g);
        g.gridx = 2;
        gblayout.setConstraints(CalStans.six, g);
        g.gridx = 3;
        gblayout.setConstraints(CalStans.minus, g);

        g.gridy = 6;
        g.gridx = 0;
        gblayout.setConstraints(CalStans.one, g);
        g.gridx = 1;
        gblayout.setConstraints(CalStans.two, g);
        g.gridx = 2;
        gblayout.setConstraints(CalStans.three, g);
        g.gridx = 3;
        gblayout.setConstraints(CalStans.plus, g);

        g.gridy = 7;
        g.gridx = 0;
        gblayout.setConstraints(CalStans.neg, g);
        g.gridx = 1;
        gblayout.setConstraints(CalStans.zero, g);
        g.gridx = 2;
        gblayout.setConstraints(CalStans.point, g);
        g.gridx = 3;
        gblayout.setConstraints(CalStans.eql, g);

        CalStans.mp.add(CalStans.showexp);
        CalStans.mp.add(CalStans.show);
        CalStans.mp.add(CalStans.CE);
        CalStans.mp.add(CalStans.C);
        CalStans.mp.add(CalStans.del);
        CalStans.mp.add(CalStans.div);
        CalStans.mp.add(CalStans.seven);
        CalStans.mp.add(CalStans.eight);
        CalStans.mp.add(CalStans.nine);
        CalStans.mp.add(CalStans.mul);
        CalStans.mp.add(CalStans.four);
        CalStans.mp.add(CalStans.five);
        CalStans.mp.add(CalStans.six);
        CalStans.mp.add(CalStans.minus);
        CalStans.mp.add(CalStans.one);
        CalStans.mp.add(CalStans.two);
        CalStans.mp.add(CalStans.three);
        CalStans.mp.add(CalStans.plus);
        CalStans.mp.add(CalStans.neg);
        CalStans.mp.add(CalStans.zero);
        CalStans.mp.add(CalStans.point);
        CalStans.mp.add(CalStans.eql);

        CalStans.f.setContentPane(CalStans.mp);
        CalStans.f.setSize(440, 500);
        CalStans.f.setLocationRelativeTo(null);
        CalStans.f.setVisible(true);

        ActionListener l = new CalListener();
        //Add a listener event for the button
        CalStans.CE.addActionListener(l);
        CalStans.C.addActionListener(l);
        CalStans.del.addActionListener(l);
        CalStans.plus.addActionListener(l);
        CalStans.minus.addActionListener(l);
        CalStans.mul.addActionListener(l);
        CalStans.div.addActionListener(l);
        CalStans.point.addActionListener(l);
        CalStans.neg.addActionListener(l);
        CalStans.eql.addActionListener(l);
        CalStans.zero.addActionListener(l);
        CalStans.one.addActionListener(l);
        CalStans.two.addActionListener(l);
        CalStans.three.addActionListener(l);
        CalStans.four.addActionListener(l);
        CalStans.five.addActionListener(l);
        CalStans.six.addActionListener(l);
        CalStans.seven.addActionListener(l);
        CalStans.eight.addActionListener(l);
        CalStans.nine.addActionListener(l);

    }
}
