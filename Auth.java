/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat2;

import java.sql.*;

/**
 *
 * @author sunny
 */
public class Auth extends javax.swing.JFrame {
    /**
     * Creates new form Auth
     */
    public Auth() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        uname = new javax.swing.JTextField();
        pwd = new javax.swing.JTextField();
        register = new javax.swing.JButton();
        login = new javax.swing.JButton();
        output = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Username :");

        jLabel2.setText("Password");

        uname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unameActionPerformed(evt);
            }
        });

        register.setText("REGISTER");
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });

        login.setText("LOGIN");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(output, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(register, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pwd, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(uname, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pwd, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(register, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(output, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void unameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unameActionPerformed

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        // TODO add your handling code here:
        String username = uname.getText();
        String password = pwd.getText();
        int min = 1050;
        int max = 15000;
        int port = min + (int)(Math.random() * ((max - min) + 1));
        String query = "SELECT * FROM users WHERE uname='"+username+"';";
        try{  
        Class.forName("com.mysql.jdbc.Driver");  

        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/chat","root","");
        PreparedStatement stmt=conn.prepareStatement(query);
        ResultSet rs=stmt.executeQuery(query);
        
        if (rs.next()) {                            //if rs.next() returns false
                                                    //then there are no rows.
           output.setText("Username already exists");

       }else {
            Statement statement = conn.createStatement();
            boolean b=statement.execute("INSERT INTO users (`uname`, `pwd`, `port`) VALUES ('"+username+"','"+password+"',"+port+");");
            if (!b){
                output.setText("Your details have been added.");
            }
       }
        conn.close();
        }catch(Exception e){
            System.out.println(e); 
      
        }
    }//GEN-LAST:event_registerActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        String username = uname.getText();
        String password = pwd.getText();
        String query = "SELECT * FROM users WHERE uname='"+username+"' AND pwd='"+password+"';";
        try{  
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/chat","root","");
        Class.forName("com.mysql.jdbc.Driver"); 
        PreparedStatement stmt=conn.prepareStatement(query);
        ResultSet rs=stmt.executeQuery(query);
        if (rs.next()){
            query = "SELECT isOnline FROM users WHERE uname='"+username+"';";
            stmt=conn.prepareStatement(query);
            rs=stmt.executeQuery(query);
            if (!rs.next()) {                            //if rs.next() returns false
                                                    //then there are no rows.
                output.setText("No output coming from the SQL");

            }else {
                 if (rs.getInt(1) == 0){
                     Statement statement = conn.createStatement();
                     //generate random ports
                     int min = 1050;
                     int max = 15000;
                     int port = min + (int)(Math.random() * ((max - min) + 1));

                     boolean b=statement.execute("UPDATE users SET isOnline=1, port="+port+" WHERE uname='"+username+"';");
//                     System.out.println(b);
                     conn.close();
                     if (!b){
//                         System.out.println("1");
                        chatGUI xx = new chatGUI(username,port);
//                         System.out.println("2");
                        xx.setVisible(true);
//                         System.out.println("3");
                        this.dispose();
//                         System.out.println("4");
                     }else{
                            output.setText("Online field not updated..");
                     }
                 }
            }
        }
        }catch(Exception e){
            System.out.println(e); 
      
        }
    }//GEN-LAST:event_loginActionPerformed

    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Auth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Auth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Auth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Auth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Auth w = new Auth();
                w.setVisible(true);
            }
        });
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JButton login;
    public javax.swing.JLabel output;
    public javax.swing.JTextField pwd;
    public javax.swing.JButton register;
    public javax.swing.JTextField uname;
    // End of variables declaration//GEN-END:variables
 
}

