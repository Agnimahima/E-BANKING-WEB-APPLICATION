
package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener{
    
    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3;
    String pin;
    Deposit(String pin){
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1200, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 600, 400);
        add(l3);
        
          ImageIcon i27 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/bb.jpg"));
        Image i28 = i27.getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT);
        ImageIcon i19 = new ImageIcon(i28);
        JLabel l45 = new JLabel(i19);
        l45.setBounds(100,30,400,200);
        //setBounds(10, 0, 900, 900);
        l3.add(l45);
        
        l1 = new JLabel("Enter Amount You Want To Deposit");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
        
        b1 = new JButton("DEPOSIT");
        b2 = new JButton("BACK");
        
        setLayout(null);
        
        l1.setBounds(100,240,400,25);
        l3.add(l1);
        
        t1.setBounds(100,280,350,25);
        l3.add(t1);
        
        b1.setBounds(350,340,150,25);
        l3.add(b1);
        
        b2.setBounds(350,370,150,25);
        l3.add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        setSize(600,400);
        setUndecorated(true);
        setLocation(400,250);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        try{        
            String amount = t1.getText();
            Date date = new Date();
            if(ae.getSource()==b1){
                if(t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Deposit");
                }else{
                    Conn c1 = new Conn();
                    c1.s.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Deposit', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Deposited Successfully");
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }
            }else if(ae.getSource()==b2){
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
            
    }
    
   // public static void main(String[] args){
   //     new Deposit("").setVisible(true);
   // }
 }