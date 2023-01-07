/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package prinfo;

import API.Results;
import API.api_connection;
import com.mysql.cj.xdevapi.Result;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Jujubaca
 */
public class AffichageResultsMultiple extends javax.swing.JPanel {
    
    private final Results resultat;
    
    /**
     * Creates new form AffichageResultsMultiple
     */
    public AffichageResultsMultiple(Results res) {
        resultat=res;
        initComponents();
        RemplirChamps();
    }
    
    private void RemplirChamps(){
        titre.setText(resultat.getName());
        type.setText(resultat.getType());
        ImageIcon img1 = null;
        try {
            img1 = new ImageIcon(new URL(resultat.getIconLink()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(AffichageResultsMultiple.class.getName()).log(Level.SEVERE, null, ex);
        }
        iconLink.setIcon(img1);
    }
    
    public javax.swing.JPanel getPanel(){
        return this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iconLink = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        titre = new javax.swing.JLabel();
        type = new javax.swing.JLabel();

        iconLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconLinkMouseClicked(evt);
            }
        });

        titre.setText("titre1");

        type.setText("type1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titre)
                    .addComponent(type))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titre)
                .addGap(18, 18, 18)
                .addComponent(type)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconLink, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iconLink, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void iconLinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconLinkMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
            api_connection apiConnection = new api_connection();
            try {
                switch (resultat.getType()){
                    case "character":
                        AffichageDetailPersonnage affichageDetailPersonnage=
                                new AffichageDetailPersonnage(apiConnection.getCharacter(resultat.getId()));
                        affichageDetailPersonnage.setVisible(true);
                        break;
                    case "issue":
                        AffichageDetailsComic affichageDetailsComic =
                                new AffichageDetailsComic(apiConnection.getComic(resultat.getId()));
                        affichageDetailsComic.setVisible(true);
                        break;
                    case "volume":
                        AffichageDetailsSerie affichageDetailsSerie =
                                new AffichageDetailsSerie(apiConnection.getSerie(resultat.getId()));
                        affichageDetailsSerie.setVisible(true);
                        break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
  }
    }//GEN-LAST:event_iconLinkMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconLink;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel titre;
    private javax.swing.JLabel type;
    // End of variables declaration//GEN-END:variables
}
