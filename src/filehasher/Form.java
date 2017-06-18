/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filehasher;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author virtual
 */
public class Form extends javax.swing.JFrame {

    /**
     * Creates new form Formulary
     */
    public Form() {
        initComponents();
    }

     private static String getFileChecksum(MessageDigest digest, File file) throws IOException {
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
        return sb.toString();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        ImageIcon img = new ImageIcon((getClass().getResource("chunk.png")));
        setIconImage(img.getImage());
        
        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jList2.setEnabled(false);
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 420));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        jButton1.setText("Compare value");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, -1));
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jList1.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "You must select a file to compare.");
                } else {
                    //if (jTextField1.getText().equalsIgnoreCase(hashes.get(jList1.getSelectedIndex()))) {
                    if (jTextField1.getText().equalsIgnoreCase(jList1.getSelectedValue().toString())) {
                        jTextField1.setForeground(Color.GREEN);
                        t.start();
                    } else {
                        jTextField1.setForeground(Color.RED);
                        t.start();
                    }
                    jRadioButton1.setSelected(false);
                    jRadioButton2.setSelected(false);
                    jRadioButton3.setSelected(false);
                }
            }
        });
        
        jList1.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        jScrollPane1.setViewportView(jList1);
        jList1.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent ev)
            {
               jList2.setSelectedIndex(jList1.getSelectedIndex());
            }
        });

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 210, 240));

        jTextField1.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 220, -1));

        jButton2.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        jButton2.setText("Hash file");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!jRadioButton1.isSelected() & !jRadioButton2.isSelected() & !jRadioButton3.isSelected()) {

                    //1-sha1 2-md5 3-sha256
                    JOptionPane.showMessageDialog(null, "You must select one algorithm.");
                } else {
                    int result = fileChooser.showOpenDialog(jButton2);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        selectedFile = fileChooser.getSelectedFile();
                        System.out.println("Selected file: " + selectedFile.getAbsolutePath());

                        //jList1 = new JList(listModel
                        if (jRadioButton1.isSelected()) {
                            try {
                                //Use SHA-1 algorithm
                                MessageDigest shaDigest = MessageDigest.getInstance("SHA-1");
                                //SHA-1 checksum
                                String checksum = getFileChecksum(shaDigest, selectedFile);
                                st = checksum;
                                System.out.println(checksum);
                                System.out.println();
                            } catch (IOException ex) {
                                
                            } catch (NoSuchAlgorithmException ex) {
                                
                            }

                        }
                        if (jRadioButton2.isSelected()) {
                            //Use MD5 algorithm
                            MessageDigest md5Digest;
                            try {
                                md5Digest = MessageDigest.getInstance("MD5");
                                //Get the checksum
                                String checksum = getFileChecksum(md5Digest, selectedFile);
                                //see checksum
                                System.out.println(checksum);
                                st = checksum;
                            } catch (NoSuchAlgorithmException ex) {
                                System.out.println("err1");
                            } catch (IOException ex) {
                                System.out.println("err2");
                            }
                        }
                        if (jRadioButton3.isSelected()) {
                            byte[] buffer = new byte[8192];
                            int count;
                            try {
                                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(selectedFile));
                                while ((count = bis.read(buffer)) > 0) {
                                    digest.update(buffer, 0, count);
                                }
                                byte[] hash = digest.digest();
                                String checksum = getFileChecksum(digest, selectedFile);
                                st = checksum;
                                System.out.println(checksum);
                                //System.out.println(new BASE64Encoder().encode(hash)); //weird
                            } catch (Exception eo) {
                            }
                        }
                        //hashes.add(st);
                        String message = "<html>";//4tabulation
                        //String tab = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                        //message += selectedFile.getAbsolutePath() + "" + tab + st;
                        //message += "</html>";
                        //message="";
                        listModel.add(listModel.getSize(), selectedFile.getAbsolutePath());
                        jList2.setModel(listModel);
                        listModell.add(listModell.getSize(), st);
                        jList1.setModel(listModell);
                    }

                }

            }
        });
        
        
        jLabel2.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        jLabel2.setText("Value to compare:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        jRadioButton1.setText("SHA-1");
        jRadioButton1.setToolTipText("");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        jRadioButton2.setText("MD5");
        jRadioButton2.setToolTipText("");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, -1, -1));

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        jRadioButton3.setText("SHA-256");
        jRadioButton3.setToolTipText("");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

        jList2.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        jScrollPane2.setViewportView(jList2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 220, 240));

        jLabel1.setFont(new java.awt.Font("Consolas", 2, 10)); // NOI18N
        jLabel1.setText("FileHasher v0.1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, -1, -1));

        pack();
    }// </editor-fold>                        

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

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
            java.util.logging.Logger.getLogger(Formulary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formulary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formulary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formulary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formulary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private JFileChooser fileChooser = new JFileChooser();
    private File selectedFile;
    private String st;
    //private ArrayList<String> hashes = new ArrayList<String>();
    DefaultListModel listModel = new DefaultListModel();
    DefaultListModel listModell = new DefaultListModel();
    private Timer t = new Timer(5 * 1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            jTextField1.setForeground(Color.BLACK);
            //((Timer) e.getSource()).stop();  
        }
    });
    // End of variables declaration                   
}
