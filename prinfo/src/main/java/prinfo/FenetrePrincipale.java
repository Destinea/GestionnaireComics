/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prinfo;

import API.Comic;
import API.Results;
import API.api_connection;
import AffichageCollection.UserSeriePanel;
import Collec.Comic_Collec;
import Collec.User_serie;
import User.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author nathb
 */
public class FenetrePrincipale extends javax.swing.JFrame {
    private final api_connection test;
    private List<Results> ResultatsRecherche;
    ConnectionFrame connectionFrame;
    Suggestion sugg;


    private final List<AffichageResultsMultiple> resultatsMultipleAffichage;
    private final HashSet<UserSeriePanel> series_panels;

    /**
     * Creates new form FenetrePrincipale
     */
    public FenetrePrincipale() {
        initComponents();
        test = new api_connection();
        resultatsMultipleAffichage = new LinkedList<AffichageResultsMultiple>();
        this.series_panels = new HashSet<UserSeriePanel>();
        PanelCollection.setVisible(estCo);
        Choice droplistFiltre=new Choice();
        droplistFiltre.add("Tout");
        droplistFiltre.add("Character");
        droplistFiltre.add("Comics");
        droplistFiltre.setVisible(true);
        scrollPaneAffichageMultiple.setVisible(false);
        Navbar.setVisible(false);
        sugg = new Suggestion(this);
        panelAffichageMultiple.setLayout(new GridLayout(1, 1, 5, 5));
        scrollPaneAffichageMultiple.setVisible(true);
        panelAffichageMultiple.add(sugg);
    }
    /**
     * Boolean true si on est connecté, false sinon
     */
    private User user;
    private boolean estCo = false;

    public boolean getestCo(){
        return estCo;
    }
    public void switchestCo(){
        estCo = !estCo;
        PanelCollection.setVisible(estCo);
        if (estCo) {
            user=connectionFrame.getUser();
            jLabel4.setText(user.getNametag());
        }
        else {
            jLabel4.setText("Se Connecter");
        }
    }
    public User getUser() {
        return user;
    }
    public void deleteUser() {user=null;}

    int pageNumber = 0;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        entete = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelAccueil = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PanelCollection = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        PanelConnection = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rechercheBtn = new javax.swing.JButton();
        dropListFiltre = new javax.swing.JComboBox<>();
        contentPage = new javax.swing.JPanel();
        scrollPaneAffichageMultiple = new java.awt.ScrollPane();
        panelAffichageMultiple = new java.awt.Panel();
        Navbar = new javax.swing.JPanel();
        btnPrecedent = new javax.swing.JButton();
        numPage = new javax.swing.JLabel();
        btnSuivant = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 102), 1, true));
        jPanel1.setPreferredSize(new java.awt.Dimension(1032, 800));

        entete.setBackground(new java.awt.Color(0, 0, 0));

        PanelAccueil.setBackground(new java.awt.Color(0, 0, 0));
        PanelAccueil.setToolTipText("");
        PanelAccueil.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        PanelAccueil.setMaximumSize(null);
        PanelAccueil.setName(""); // NOI18N
        PanelAccueil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelAccueilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelAccueilMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Accueil");
        jLabel2.setName("Acc"); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(94, 30));

        javax.swing.GroupLayout PanelAccueilLayout = new javax.swing.GroupLayout(PanelAccueil);
        PanelAccueil.setLayout(PanelAccueilLayout);
        PanelAccueilLayout.setHorizontalGroup(
                PanelAccueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelAccueilLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        PanelAccueilLayout.setVerticalGroup(
                PanelAccueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NetComics");


        PanelCollection.setBackground(new java.awt.Color(0, 0, 0));
        PanelCollection.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelCollectionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelCollectionMouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Ma Collection");
        jLabel3.setMinimumSize(new java.awt.Dimension(94, 19));
        jLabel3.setPreferredSize(new java.awt.Dimension(94, 30));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelCollectionLayout = new javax.swing.GroupLayout(PanelCollection);
        PanelCollection.setLayout(PanelCollectionLayout);
        PanelCollectionLayout.setHorizontalGroup(
                PanelCollectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCollectionLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                .addContainerGap())
        );
        PanelCollectionLayout.setVerticalGroup(
                PanelCollectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelCollectionLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jTextField1.setBackground(new java.awt.Color(0, 0, 0));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("Rechercher");
        jTextField1.setPreferredSize(new java.awt.Dimension(146, 27));
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);

            }
        });


        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        PanelConnection.setBackground(new java.awt.Color(0, 0, 0));
        PanelConnection.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelConnectionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelConnectionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelConnectionMouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Se Connecter");

        javax.swing.GroupLayout PanelConnectionLayout = new javax.swing.GroupLayout(PanelConnection);
        PanelConnection.setLayout(PanelConnectionLayout);
        PanelConnectionLayout.setHorizontalGroup(
                PanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelConnectionLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(19, 19, 19))
        );
        PanelConnectionLayout.setVerticalGroup(
                PanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelConnectionLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 12, Short.MAX_VALUE))
        );

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));

        rechercheBtn.setBackground(new java.awt.Color(0, 0, 0));
        rechercheBtn.setForeground(new java.awt.Color(255, 255, 255));
        rechercheBtn.setText("Rechercher");

        rechercheBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheBtnActionPerformed(evt);
            }
        });

        dropListFiltre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tout", "Personnages", "Séries", "Comic" }));
        dropListFiltre.setMinimumSize(new java.awt.Dimension(117, 22));
        dropListFiltre.setPreferredSize(new java.awt.Dimension(117, 27));

        javax.swing.GroupLayout enteteLayout = new javax.swing.GroupLayout(entete);
        entete.setLayout(enteteLayout);
        enteteLayout.setHorizontalGroup(
                enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(enteteLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel1)
                                .addGap(21, 21, 21)
                                .addComponent(PanelAccueil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(15, 15, 15)
                                .addComponent(PanelCollection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dropListFiltre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rechercheBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(PanelConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        enteteLayout.setVerticalGroup(
                enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(enteteLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(enteteLayout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(PanelConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 8, Short.MAX_VALUE))))
                        .addGroup(enteteLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(PanelAccueil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(PanelCollection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(rechercheBtn)
                                                        .addGroup(enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(dropListFiltre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        contentPage.setBackground(new java.awt.Color(51, 51, 51));
        contentPage.setForeground(new java.awt.Color(102, 102, 102));
        contentPage.setPreferredSize(new java.awt.Dimension(1030, 700));

        scrollPaneAffichageMultiple.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        panelAffichageMultiple.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelAffichageMultipleLayout = new javax.swing.GroupLayout(panelAffichageMultiple);
        panelAffichageMultiple.setLayout(panelAffichageMultipleLayout);
        panelAffichageMultipleLayout.setHorizontalGroup(
                panelAffichageMultipleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1098, Short.MAX_VALUE)
        );
        panelAffichageMultipleLayout.setVerticalGroup(
                panelAffichageMultipleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 631, Short.MAX_VALUE)
        );


        scrollPaneAffichageMultiple.add(panelAffichageMultiple);

        Navbar.setBackground(new java.awt.Color(51, 51, 51));
        Navbar.setLayout(new java.awt.GridLayout(1, 3));

        btnPrecedent.setBackground(new java.awt.Color(51, 51, 51));
        btnPrecedent.setForeground(new java.awt.Color(255, 255, 255));
        btnPrecedent.setText("Précédent");
        btnPrecedent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrecedentActionPerformed(evt);
            }
        });
        Navbar.add(btnPrecedent);

        numPage.setBackground(new java.awt.Color(51, 51, 51));
        numPage.setForeground(new java.awt.Color(255, 255, 255));
        numPage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numPage.setText("0");
        Navbar.add(numPage);

        btnSuivant.setBackground(new java.awt.Color(51, 51, 51));
        btnSuivant.setForeground(new java.awt.Color(255, 255, 255));
        btnSuivant.setText("Suivant");
        btnSuivant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuivantActionPerformed(evt);
            }
        });
        Navbar.add(btnSuivant);

        javax.swing.GroupLayout contentPageLayout = new javax.swing.GroupLayout(contentPage);
        contentPage.setLayout(contentPageLayout);
        contentPageLayout.setHorizontalGroup(
                contentPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(contentPageLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPaneAffichageMultiple, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPageLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Navbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(333, 333, 333))
        );
        contentPageLayout.setVerticalGroup(
                contentPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(contentPageLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPaneAffichageMultiple, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Navbar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(entete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(contentPage, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(entete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contentPage, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jTextField1.setText("");
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void PanelAccueilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelAccueilMouseEntered
        // TODO add your handling code here:
        jLabel2.setForeground(new Color(112, 128, 144));
    }//GEN-LAST:event_PanelAccueilMouseEntered

    private void PanelCollectionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelCollectionMouseEntered
        // TODO add your handling code here:
        jLabel3.setForeground(new Color(112,128,144));
    }//GEN-LAST:event_PanelCollectionMouseEntered

    private void PanelConnectionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelConnectionMouseEntered
        // TODO add your handling code here:
        jLabel4.setForeground(new Color(112,128,144));
    }//GEN-LAST:event_PanelConnectionMouseEntered

    private void PanelAccueilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelAccueilMouseExited
        // TODO add your handling code here:
        jLabel2.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_PanelAccueilMouseExited

    private void PanelCollectionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelCollectionMouseExited
        // TODO add your handling code here:
        jLabel3.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_PanelCollectionMouseExited

    private void PanelConnectionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelConnectionMouseExited
        // TODO add your handling code here:
        jLabel4.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_PanelConnectionMouseExited

    private void PanelConnectionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelConnectionMouseClicked
        // TODO add your handling code here:
        if(estCo){
            DeconnectionFrame deco = new DeconnectionFrame(this);
            deco.setLocationRelativeTo(null);
            deco.setVisible(true);

        }
        else {
            connectionFrame = new ConnectionFrame(this);
            connectionFrame.setLocationRelativeTo(null);
            connectionFrame.setVisible(true);
        }
    }//GEN-LAST:event_PanelConnectionMouseClicked

    private void rechercheBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheBtnActionPerformed
        // On supprime la liste précédemmant cherchée pour la set à nouveau
        sugg.setVisible(false);
        panelAffichageMultiple.removeAll();
        if (ResultatsRecherche!=null) {
            ResultatsRecherche.clear();
            resultatsMultipleAffichage.clear();
            panelAffichageMultiple.removeAll();
        }

        try {
            ResultatsRecherche = test.GetResults(jTextField1.getText(), 1,dropListFiltre.getSelectedItem().toString());
        } catch (IOException ex) {
            Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
        }

        panelAffichageMultiple.setLayout(new GridLayout(ResultatsRecherche.size() /2, 2, 5, 5));
        scrollPaneAffichageMultiple.setVisible(true);

        for (Results results : ResultatsRecherche) {
            resultatsMultipleAffichage.add(new AffichageResultsMultiple(results,this));
        }

        for (AffichageResultsMultiple affichageResultsMultiple : resultatsMultipleAffichage) {
            panelAffichageMultiple.add(affichageResultsMultiple);
        }
        contentPage.updateUI();

        numPage.setText("1");
        Navbar.setVisible(true);
        btnPrecedent.setVisible(false);
    }//GEN-LAST:event_rechercheBtnActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        jTextField1.setText("");
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rechercheBtnMouseClicked
        // TODO add your handling code here:
        // On supprime la liste précédemmant cherchée pour la set à nouveau
        panelAffichageMultiple.removeAll();
        //On prepare le layout
        panelAffichageMultiple.setLayout(new GridLayout(user.getCollection().getSeries().size(), 1, 10, 10));
        //on active la barre de scroll
        scrollPaneAffichageMultiple.setVisible(true);
        //stockage des panels pour ne pas avoir a les recharger
        for (User_serie user_serie : user.getCollection().getSeries()) {
            series_panels.add(new UserSeriePanel(user, user_serie));
        }
        //Ajout des panels au panel parent
        for (UserSeriePanel series_panel:series_panels) {
            panelAffichageMultiple.add(series_panel);
        }
        //Update du panel parent
        contentPage.updateUI();
        //pas besoin de la navigation entre les pages de résultats
        Navbar.setVisible(false);
    }//GEN-LAST:event_rechercheBtnMouseClicked

    private void btnPrecedentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrecedentActionPerformed
        int numPagePrecedente = Integer.parseInt(numPage.getText())-1;
        if (ResultatsRecherche!=null) {
            ResultatsRecherche.clear();
            resultatsMultipleAffichage.clear();
            panelAffichageMultiple.removeAll();
        }

        try {
            ResultatsRecherche = test.GetResults(jTextField1.getText(), numPagePrecedente,dropListFiltre.getSelectedItem().toString());
        } catch (IOException ex) {
            Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
        }

        panelAffichageMultiple.setLayout(new GridLayout(ResultatsRecherche.size() /2, 2, 5, 5));
        scrollPaneAffichageMultiple.setVisible(true);

        for (Results results : ResultatsRecherche) {
            resultatsMultipleAffichage.add(new AffichageResultsMultiple(results,this));
        }

        for (AffichageResultsMultiple affichageResultsMultiple : resultatsMultipleAffichage) {
            panelAffichageMultiple.add(affichageResultsMultiple);
        }
        contentPage.updateUI();
        numPage.setText(String.valueOf(numPagePrecedente));
        if(numPagePrecedente<=1){
            btnPrecedent.setVisible(false);
        }
    }//GEN-LAST:event_btnPrecedentActionPerformed

    private void btnSuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuivantActionPerformed
        int numPageSuivante = Integer.parseInt(numPage.getText())+1;
        if (ResultatsRecherche!=null) {
            ResultatsRecherche.clear();
            resultatsMultipleAffichage.clear();
            panelAffichageMultiple.removeAll();
        }

        try {
            ResultatsRecherche = test.GetResults(jTextField1.getText(), numPageSuivante,dropListFiltre.getSelectedItem().toString());
        } catch (IOException ex) {
            Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
        }

        panelAffichageMultiple.setLayout(new GridLayout(ResultatsRecherche.size() /2, 2, 5, 5));
        scrollPaneAffichageMultiple.setVisible(true);

        for (Results results : ResultatsRecherche) {
            resultatsMultipleAffichage.add(new AffichageResultsMultiple(results,this));
        }

        for (AffichageResultsMultiple affichageResultsMultiple : resultatsMultipleAffichage) {
            panelAffichageMultiple.add(affichageResultsMultiple);
        }
        contentPage.updateUI();
        numPage.setText(String.valueOf(numPageSuivante));
        btnPrecedent.setVisible(true);
    }//GEN-LAST:event_btnSuivantActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (estCo) {
            try {
                Comic_Collec.saveBdd(user);
            } catch (SQLException ex) {
                Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FenetrePrincipale main = new FenetrePrincipale();
                main.setLocationRelativeTo(null);
                main.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Navbar;
    private javax.swing.JPanel PanelAccueil;
    private javax.swing.JPanel PanelCollection;
    private javax.swing.JPanel PanelConnection;
    private javax.swing.JButton btnPrecedent;
    private javax.swing.JButton btnSuivant;
    private javax.swing.JPanel contentPage;
    private javax.swing.JComboBox<String> dropListFiltre;
    private javax.swing.JPanel entete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel numPage;
    private java.awt.Panel panelAffichageMultiple;
    private javax.swing.JButton rechercheBtn;
    private java.awt.ScrollPane scrollPaneAffichageMultiple;
    // End of variables declaration//GEN-END:variables
}

