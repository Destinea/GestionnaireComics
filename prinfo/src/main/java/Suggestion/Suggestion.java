/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Suggestion;

import API.Comic;
import API.api_connection;
import Collec.Comic_Collec;
import java.awt.GridLayout;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import AffichagePrincipal.FenetrePrincipale;

/**
 *
 * @author Sarah-Marie JULES
 */
public class Suggestion extends javax.swing.JPanel {
    private api_connection test;
    /**
     * Creates new form Suggestion
     * @param frame
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public Suggestion(FenetrePrincipale frame) throws IOException, SQLException {
        test = new api_connection();
        initComponents();
        this.generateSuggestion(frame);
        
    }
    
    private void generateSuggestion(FenetrePrincipale frame) throws IOException, SQLException{
        contentSuggestion.setLayout(new GridLayout(3,1));
        List<Comic> firstCategorie = test.getLastComics();
        contentSuggestion.add(new Categorie(frame,firstCategorie.subList(0,3),"Derniers Ajouts"));
        List<Comic> secondCategorie = test.getRandomComics();
        if (frame.getestCo()){
            //A modifier par la collection
            contentSuggestion.add(new Categorie(frame,secondCategorie.subList(0,3),"Aléatoires"));
        } else {
            contentSuggestion.add(new Categorie(frame,secondCategorie.subList(0,3),"Aléatoires"));
        }
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo");
        Statement stmt = con.createStatement();
        List<Comic> thirdCategotie = Comic_Collec.getPlusLu(stmt);
        contentSuggestion.add(new Categorie(frame, thirdCategotie, "Les plus populaires"));
        
        contentSuggestion.setVisible(true);
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleSuggestion = new javax.swing.JLabel();
        contentSuggestion = new javax.swing.JPanel();

        setBackground(new java.awt.Color(51, 51, 51));

        TitleSuggestion.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TitleSuggestion.setForeground(new java.awt.Color(255, 255, 255));
        TitleSuggestion.setText("Suggestions");

        contentSuggestion.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout contentSuggestionLayout = new javax.swing.GroupLayout(contentSuggestion);
        contentSuggestion.setLayout(contentSuggestionLayout);
        contentSuggestionLayout.setHorizontalGroup(
            contentSuggestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        contentSuggestionLayout.setVerticalGroup(
            contentSuggestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(TitleSuggestion, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(473, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentSuggestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitleSuggestion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentSuggestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TitleSuggestion;
    private javax.swing.JPanel contentSuggestion;
    // End of variables declaration//GEN-END:variables
}
