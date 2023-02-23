
package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener{
    
    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3,l4,l11;
    String pin;
    Withdrawl(String pin){
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1200, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 600, 400);
        add(l3);
        
          ImageIcon i27 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/ab.gif"));
        Image i28 = i27.getImage().getScaledInstance(200, 400, Image.SCALE_DEFAULT);
        ImageIcon i19 = new ImageIcon(i28);
        JLabel l45 = new JLabel(i19);
        l45.setBounds(380,30,200,400);
        //setBounds(10, 0, 900, 900);
        l3.add(l45);
        
         ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/credit-card-logo-atm-6.png"));
        Image i5 = i4.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l32 = new JLabel(i6);
        l32.setBounds(50,20,300,200);
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
        
        
        l2 = new JLabel("Please enter the amount");
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System", Font.BOLD, 16));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));
        
        b1 = new JButton("WITHDRAW");
        b2 = new JButton("BACK");
        
        setLayout(null);
        
                
        l2.setBounds(50,250,320,25);
        l3.add(l2);
        
        t1.setBounds(50,280,300,25);
        l3.add(t1);
        
        b1.setBounds(200,315,150,25);
        l3.add(b1);
        
        b2.setBounds(200,350,150,25);
        l3.add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        setSize(600,400);
        setLocation(400,250);
        setUndecorated(true);
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
        try{        
            String amount = t1.getText();
            Date date = new Date();
            if(ae.getSource()==b1){
                if(t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");
                }else{
                    Conn c1 = new Conn();
                    
                    ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+pin+"'");
                    int balance = 0;
                    while(rs.next()){
                       if(rs.getString("type").equals("Deposit")){
                           balance += Integer.parseInt(rs.getString("amount"));
                       }else{
                           balance -= Integer.parseInt(rs.getString("amount"));
                       }
                    }
                    if(balance < Integer.parseInt(amount)){
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }
                    
                    c1.s.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Withdraw Successfully");
                    
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }
            }else if(ae.getSource()==b2){
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        }catch(Exception e){
                e.printStackTrace();
                System.out.println("error: "+e);
        }
            
    }

    
    
    public static void main(String[] args){
        new Withdrawl("").setVisible(true);
    }
}
