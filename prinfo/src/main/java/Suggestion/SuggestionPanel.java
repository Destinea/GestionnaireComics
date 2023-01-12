/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package Suggestion;

import API.Comic;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import prinfo.FenetrePrincipale;

/**
 *
 * @author Sarah-Marie JULES
 */
public class SuggestionPanel extends javax.swing.JPanel {

    /** Creates new form SuggestionPanel
     * @param frame
     * @param comic
     * @throws java.io.IOException */
    public SuggestionPanel(FenetrePrincipale frame, Comic comic) throws IOException {
        initComponents();
        jCheckBox1.setVisible(frame.getestCo());
        RemplirChamps(comic);
    }
    
    private void RemplirChamps(Comic comic) throws IOException{
        titre.setText(comic.getName());
        ImageIcon img1 = null;
        try {
                BufferedImage img = ImageIO.read(new URL(comic.getIconLink()));
                var newWidth = img.getWidth();
                float aspectRatio = (float)img.getHeight(null)/img.getWidth(null);
                int newHeight = (int)(newWidth * aspectRatio);
                Image dimg = img.getScaledInstance(newWidth, newHeight ,Image.SCALE_SMOOTH);
                img1 = new ImageIcon(dimg);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        photoComic.setIcon(img1);
        
    }
    
    public javax.swing.JPanel getPanel(){
        return this;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        photoComic = new javax.swing.JLabel();
        titre = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(51, 51, 51));

        photoComic.setText("photo");

        titre.setBackground(new java.awt.Color(51, 51, 51));
        titre.setForeground(new java.awt.Color(255, 255, 255));
        titre.setText("title11");

        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Ajouter");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photoComic, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titre, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titre, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jCheckBox1)
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addComponent(photoComic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel photoComic;
    private javax.swing.JLabel titre;
    // End of variables declaration//GEN-END:variables

}
