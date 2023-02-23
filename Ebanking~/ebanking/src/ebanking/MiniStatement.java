package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener{
 
    JButton b1, b2;
    JLabel l1,l2,l3,l4,l13,l22;
    MiniStatement(String pin){
       // super("Mini Statement");
     //   getContentPane().setBackground(Color.blue);
       
       // setLocation(20,20);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l5 = new JLabel(i3);
        l5.setBounds(0,0,900,500);
        //setBounds(0, 0, 900, 900);
        add(l5);
         
        
        ImageIcon i27 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/ad.png"));
        Image i28 = i27.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        ImageIcon i19 = new ImageIcon(i28);
        JLabel l45 = new JLabel(i19);
        l45.setBounds(50,120,300,200);
        //setBounds(10, 0, 900, 900);
        
          ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/logo.jpg"));
        Image i12 = i11.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel l15 = new JLabel(i13);
        l15.setBounds(560,30,80,80);
        //setBounds(0, 0, 900, 900);
        l5.add(l15);
        l1 = new JLabel();
        l5.add(l1);
        // l22 = new JLabel();
        //l5.add(l22);
        
         l2 = new JLabel("E-BANK  ( India's no. one online bank )");
        l2.setBounds(480, 120, 700, 35);
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System", Font.BOLD, 18));
        l5.add(l2);
        
         l3 = new JLabel();
        l3.setBounds(80, 30, 300, 30);
        l45.add(l3);
        
         l4 = new JLabel();
        l4.setBounds(30, 130, 300, 20);
        l45.add(l4);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from login where pin = '"+pin+"'");
            while(rs.next()){
                l3.setText("Card Number: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
            }
        }catch(Exception e){}
        
         
          try{
            int bal = 0;
            Conn c1  = new Conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank where pin = '"+pin+"'");
            while(rs.next()){
                l1.setText(l1.getText() + "<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                if(rs.getString("type").equals("Deposit")){
                    bal += Integer.parseInt(rs.getString("amount"));
                }else{
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
                    
            
            }
             
            l4.setText("Your total Balance is Rs "+bal);
        }catch(Exception e){
            e.printStackTrace();
        }
        setLayout(null);
        b1 = new JButton("Exit");
        l5.add(b1);
        
        b1.addActionListener(this);
        l5.add(l45);
        l1.setBounds(480, 180, 400, 200);
       //  l22.setBounds(480, 380, 400, 200);
        b1.setBounds(150, 400, 100, 25);
        setSize(900,500);
         setLocation(300, 200);
         setUndecorated(true);
        setVisible(true);


    }
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }
    
    public static void main(String[] args){
        new MiniStatement("").setVisible(true);
    }
    
}
