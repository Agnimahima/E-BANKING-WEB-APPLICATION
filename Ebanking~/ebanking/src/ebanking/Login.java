package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JLabel l1,l2,l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2,b3;
  
    Login(){
        setTitle("E-BANK");
        
        
           ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/atm.jpg"));
        Image i12 = i11.getImage().getScaledInstance(900, 500, Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel l5 = new JLabel(i13);
        l5.setBounds(0,0,900,500);
        //setBounds(0, 0, 900, 900);
        add(l5);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/logo.gif"));
        Image i2 = i1.getImage().getScaledInstance(350, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        l11.setBounds(500, 25, 350, 450);
        l5.add(l11);
       Color c1 = new Color(0, 51, 255);  
        //l11.setBackground(c1);
        
        l1 = new JLabel("E-Bank Login");
         l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Osward", Font.BOLD, 30));
        l1.setBounds(180,40,450,40);
        l5.add(l1);
        
        l2 = new JLabel("Card No");
         l2.setForeground(Color.WHITE);
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setBounds(30,150,150,25);
        l5.add(l2);
        
        tf1 = new JTextField(15);
        tf1.setBounds(180,150,230,25);
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        l5.add(tf1);
        
        l3 = new JLabel("Pin ");
         l3.setForeground(Color.WHITE);
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(80,220,100,25);
        l5.add(l3);
        
        pf2 = new JPasswordField(15);
        pf2.setFont(new Font("Arial", Font.BOLD, 14));
        pf2.setBounds(180,220,230,25);
        l5.add(pf2);
                
        b1 = new JButton("SIGN IN");
        b1.setBackground(c1);
        b1.setForeground(Color.WHITE);
        
        b2 = new JButton("CLEAR");
        b2.setBackground(c1);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("SIGN UP");
        b3.setBackground(c1);
        b3.setForeground(Color.WHITE);
        
        setLayout(null);
        
        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(180,300,100,30);
        l5.add(b1);
        
        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(300,300,100,30);
        l5.add(b2);
        
        b3.setFont(new Font("Arial", Font.BOLD, 14));
        b3.setBounds(180,350,230,30);
        l5.add(b3);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        setSize(900,500);
        setLocation(300,200);
        setUndecorated(true);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        try{        
            if(ae.getSource()==b1){
                Conn c1 = new Conn();
                String cardno  = tf1.getText();
                String pin  = pf2.getText();
                String q  = "select * from login where cardnumber = '"+cardno+"' and pin = '"+pin+"'";

                ResultSet rs = c1.s.executeQuery(q);
                if(rs.next()){
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            }else if(ae.getSource()==b2){
                tf1.setText("");
                pf2.setText("");
            }else if(ae.getSource()==b3){
                setVisible(false);
                new Signup().setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new Login().setVisible(true);
    }

    
}



