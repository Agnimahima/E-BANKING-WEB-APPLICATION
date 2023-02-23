package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.util.*;

class BalanceEnquiry extends JFrame implements ActionListener {

    JTextField t1, t2;
    JButton b1, b2, b3;
    JLabel l1, l2, l3,l11;
    String pin;

    BalanceEnquiry(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1200, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 600, 400);
        add(l3);

        l1 = new JLabel();
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("BACK");

        setLayout(null);

          ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/credit-card-logo-atm-6.png"));
        Image i5 = i4.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l32 = new JLabel(i6);
        l32.setBounds(150,50,300,200);
        //setBounds(0, 0, 900, 900);
          l11 = new JLabel();
        l11.setForeground(Color.black);
        l11.setFont(new Font("System", Font.BOLD, 16));
         l11.setBounds(30, 90, 100, 35);
       
         int balance = 0;
        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+pin+"'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        }catch(Exception e){}
        l11.setText("Rs "+balance);
            l32.add(l11);     
            l3.add(l32);

        l1.setBounds(170, 250, 400, 35);
        l3.add(l1);

        b1.setBounds(230, 320, 150, 35);
        l3.add(b1);
        int bal = 0;
        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+pin+"'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }
        }catch(Exception e){}
        
        l1.setText("Your Current Account Balance is Rs "+bal);

        b1.addActionListener(this);

        setSize(600, 370);
        setUndecorated(true);
        setLocation(400, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pin).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("").setVisible(true);
    }
}
