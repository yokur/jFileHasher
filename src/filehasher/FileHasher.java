/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filehasher;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
/**
 *
 * @author virtual
 */
public class FileHasher {
static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//4centering jframe
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Form f = new Form();
        f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);//4centering jframe
        f.setTitle("jFileHasher");
        
        f.setVisible(true);
    }
}