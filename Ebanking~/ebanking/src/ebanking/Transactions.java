package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Transactions extends JFrame implements ActionListener{

    JLabel l1,l10,l13,l14,l15,l22,l44;
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;
    Transactions(String pin){
        
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(0,0,900,500);
        //setBounds(0, 0, 900, 900);
        add(l2);
        
         //l2.setBackground(Color.blue);
        l1 = new JLabel("HOME SECTION");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 18));
          // l1.setText("");
           l14 = new JLabel("E-Bank is a online bank system that can manage all your transactions and");
        l14.setForeground(Color.WHITE);
        l14.setFont(new Font("System", Font.BOLD, 16));    
         l15 = new JLabel("also let you daily acess of your mini statements at your home.");
        l15.setForeground(Color.WHITE);
        l15.setFont(new Font("System", Font.BOLD, 16));   
        ImageIcon i16 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/photo-icon-home-logo-23.png"));
        Image i17 = i16.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i18 = new ImageIcon(i17);
        JLabel l5 = new JLabel(i18);
        l5.setBounds(30,10,150,150);
        l2.add(l5);
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/credit-card-logo-atm-6.png"));
        Image i5 = i4.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l3 = new JLabel(i6);
        l3.setBounds(200,260,300,200);
        //setBounds(0, 0, 900, 900);
          l10 = new JLabel();
        l10.setForeground(Color.black);
        l10.setFont(new Font("System", Font.BOLD, 16));
         l10.setBounds(30, 90, 100, 35);
       
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
        l10.setText("Rs "+balance);
            l3.add(l10);     
          
     //    l13 = new JLabel();
       // l13.setForeground(Color.black);
      //  l13.setFont(new Font("System", Font.BOLD, 16));
       // l13.setBounds(20, 50, 300, 20);
       
        //  try{
         //   Conn c = new Conn();
         //   ResultSet rs = c.s.executeQuery("select * from login where pin = '"+pin+"'");
         //   while(rs.next()){
           //   l13.setText("Card Number: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
         //   }
       // }catch(Exception e){}
         // l3.add(l13);
        
        l2.add(l3);
      
         ImageIcon i27 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/b3.gif"));
        Image i28 = i27.getImage().getScaledInstance(340, 200, Image.SCALE_DEFAULT);
        ImageIcon i19 = new ImageIcon(i28);
        JLabel l45 = new JLabel(i19);
        l45.setBounds(530,260,340,200);
        //setBounds(10, 0, 900, 900);
        l2.add(l45);
        
      
        
      
      //    l1.setBounds(480, 180, 400, 200);
       //  l2.add(l22);
        
        
        
        b1 = new JButton("DEPOSIT");
        b2 = new JButton("CASH WITHDRAWL");
        b3 = new JButton("FAST CASH");
        b4 = new JButton("MINI STATEMENT");
        b5 = new JButton("PIN CHANGE");
        b6 = new JButton("BALANCE ENQUIRY");
        b7 = new JButton("LOG OUT");
        
        setLayout(null);
        
        l1.setBounds(360,20,700,35);
        l2.add(l1);
       l14.setBounds(210,130,600,30);
       l2.add(l14);
         l15.setBounds(210,160,600,30);
       l2.add(l15);
        b1.setBounds(30,150,150,35);
        l2.add(b1);
        
        b2.setBounds(30,200,150,35);
        l2.add(b2);
        
        b3.setBounds(30,250,150,35);
        l2.add(b3);
        
        b4.setBounds(30,300,150,35);
        l2.add(b4);
        
        b5.setBounds(30,350,150,35);
        l2.add(b5);
        
        b6.setBounds(30,400,150,35);
        l2.add(b6);
        
        b7.setBounds(30,450,150,35);
        l2.add(b7);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        
        // setLayout(null);
        setSize(900,500);
        setLocation(300,200);
        setUndecorated(true);
        setVisible(true);
        
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){ 
            setVisible(false);
            new Deposit(pin).setVisible(true);
        }else if(ae.getSource()==b2){ 
            setVisible(false);
            new Withdrawl(pin).setVisible(true);
        }else if(ae.getSource()==b3){ 
            setVisible(false);
            new FastCash(pin).setVisible(true);
        }else if(ae.getSource()==b4){ 
            new MiniStatement(pin).setVisible(true);
        }else if(ae.getSource()==b5){ 
            setVisible(false);
            new Pin(pin).setVisible(true);
        }else if(ae.getSource()==b6){ 
            this.setVisible(false);
            new BalanceEnquiry(pin).setVisible(true);
        }else if(ae.getSource()==b7){ 
            System.exit(0);
        }
    }
    
    public static void main(String[] args){
        new Transactions("").setVisible(true);
    }
}