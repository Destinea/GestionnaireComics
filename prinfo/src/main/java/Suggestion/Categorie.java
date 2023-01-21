/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Suggestion;
import API.Comic;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;
import AffichagePrincipal.FenetrePrincipale;

/**
 *
 * @author Sarah-Marie JULES
 */
public class Categorie extends javax.swing.JPanel {

    /**
     * Creates new form Categorie
     * @param frame
     * @param comics
     * @param name
     * @throws java.io.IOException
     */
    public Categorie(FenetrePrincipale frame, List<Comic> comics, String name) throws IOException {
        initComponents();
        initCategorie(frame,comics,name);
    }
    
    public void initCategorie(FenetrePrincipale frame, List<Comic> comics, String name) throws IOException{
        categorieName.setText(name);
        contentCategorie1 .setLayout(new GridLayout(1,3));
        for (Comic comic : comics) {//MOdifier pour avoir la serie possédée	
            contentCategorie1.add(new SuggestionPanel(frame,comic));
        }
        contentCategorie1.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        categorieName = new javax.swing.JLabel();
        contentCategorie1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(51, 51, 51));

        categorieName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        categorieName.setForeground(new java.awt.Color(255, 255, 255));
        categorieName.setText("Categorie 1");

        contentCategorie1.setBackground(new java.awt.Color(55, 55, 55));

        javax.swing.GroupLayout contentCategorie1Layout = new javax.swing.GroupLayout(contentCategorie1);
        contentCategorie1.setLayout(contentCategorie1Layout);
        contentCategorie1Layout.setHorizontalGroup(
            contentCategorie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );
        contentCategorie1Layout.setVerticalGroup(
            contentCategorie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contentCategorie1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categorieName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(categorieName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentCategorie1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel categorieName;
    private javax.swing.JPanel contentCategorie1;
    // End of variables declaration//GEN-END:variables
}
