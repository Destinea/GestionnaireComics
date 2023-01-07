/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prinfo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Cyril
 */
public class AffichageDetailsSerie extends javax.swing.JFrame {

    /**
     * Creates new form AffichageDetailsSerie
     */
    public AffichageDetailsSerie(API.Serie s) {
        initComponents();
        if(s.getName()!="null"){
            Titre.setText(s.getName());
        }else{
            Titre.setVisible(false);
        }
        
        Date.setText("Débutée en "+s.getStartYear());
        Nb_numeros.setText(s.getNumberOfComics()+" numéros");
        
        if(s.getHTMLDescription()!="null"){
            HTMLDescription.setText("<html>"+s.getHTMLDescription()+"</html>");
        }
        else{
            HTMLDescription.setVisible(false);
        }
        
        ImageIcon img1 = null;
        try {
                img1 = new ImageIcon(new URL(s.getIconLink()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        Icon.setIcon(img1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Entete = new javax.swing.JSplitPane();
        Icon = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        Date = new javax.swing.JLabel();
        jSplitPane2 = new javax.swing.JSplitPane();
        Titre = new javax.swing.JLabel();
        Nb_numeros = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        HTMLDescription = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setType(java.awt.Window.Type.POPUP);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(1032, 779));

        Entete.setBackground(new java.awt.Color(0, 0, 0));
        Entete.setDividerLocation(120);
        Entete.setDividerSize(0);

        Icon.setBackground(new java.awt.Color(0, 0, 0));
        Icon.setForeground(new java.awt.Color(255, 255, 255));
        Icon.setMaximumSize(new java.awt.Dimension(180, 250));
        Icon.setMinimumSize(new java.awt.Dimension(180, 250));
        Icon.setPreferredSize(new java.awt.Dimension(180, 250));
        Entete.setLeftComponent(Icon);

        jSplitPane1.setBackground(new java.awt.Color(0, 0, 0));
        jSplitPane1.setDividerLocation(110);
        jSplitPane1.setDividerSize(0);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        Date.setBackground(new java.awt.Color(0, 0, 0));
        Date.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Date.setForeground(new java.awt.Color(255, 255, 255));
        Date.setText("Débutée en AAAA");
        jSplitPane1.setRightComponent(Date);

        jSplitPane2.setBackground(new java.awt.Color(0, 0, 0));
        jSplitPane2.setDividerLocation(500);
        jSplitPane2.setDividerSize(0);

        Titre.setBackground(new java.awt.Color(0, 0, 0));
        Titre.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Titre.setForeground(new java.awt.Color(255, 255, 255));
        Titre.setText("Titre");
        jSplitPane2.setTopComponent(Titre);

        Nb_numeros.setBackground(new java.awt.Color(0, 0, 0));
        Nb_numeros.setForeground(new java.awt.Color(255, 255, 255));
        Nb_numeros.setText("X numéros");
        jSplitPane2.setRightComponent(Nb_numeros);

        jSplitPane1.setLeftComponent(jSplitPane2);

        Entete.setRightComponent(jSplitPane1);

        jScrollPane1.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(957, 566));

        HTMLDescription.setBackground(new java.awt.Color(51, 51, 51));
        HTMLDescription.setForeground(new java.awt.Color(255, 255, 255));
        HTMLDescription.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        HTMLDescription.setText("HTML description");
        HTMLDescription.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        HTMLDescription.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        HTMLDescription.setMaximumSize(new java.awt.Dimension(37, 16));
        HTMLDescription.setMinimumSize(new java.awt.Dimension(37, 16));
        HTMLDescription.setPreferredSize(new java.awt.Dimension(37, 16));
        HTMLDescription.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HTMLDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HTMLDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(544, 544, 544))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Entete)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 219, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Entete, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AffichageDetailsSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AffichageDetailsSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AffichageDetailsSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AffichageDetailsSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date;
    private javax.swing.JSplitPane Entete;
    private javax.swing.JLabel HTMLDescription;
    private javax.swing.JLabel Icon;
    private javax.swing.JLabel Nb_numeros;
    private javax.swing.JLabel Titre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    // End of variables declaration//GEN-END:variables
}
