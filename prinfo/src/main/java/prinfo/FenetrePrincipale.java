/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prinfo;

import API.Results;
import API.api_connection;
import AffichageCollection.UserSeriePanel;
import Collec.Collec;
import Collec.Comic_Collec;
import Collec.User_serie;
import User.User;

import java.awt.Color;
import java.awt.Choice;
import java.awt.GridLayout;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
     private User user;
     private boolean estCo = false;

     public boolean getestCo(){
         return estCo;
     }
     public void switchestCo() throws SQLException {
         estCo = !estCo;
         PanelCollection.setVisible(estCo);
         if (estCo) {
        	user=connectionFrame.getUser();
        	user.chargeCollection();
        	test();
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
    public void test() throws SQLException {
    	Comic_Collec t1 = new Comic_Collec("Volume 6", 964086, "https://comicvine.gamespot.com/a/uploads/square_medium/11145/111450787/8781226-1935607198-97840.jpg", "Torishimariyaku Shima Kōsaku", 143311, 6, 0);
        Comic_Collec t2 = new Comic_Collec("Action On Sniper Ridge", 401, "https://comicvine.gamespot.com/a/uploads/square_avatar/0/4/376-1493-401-1-battle-action.jpg", "Battle Action", 1493,11, 0);
        /*Comic_Collec t3 = new Comic_Collec("Spiderman3", 3, "iconLink", "Spiderman", 1, 3, 0);
        Comic_Collec t4 = new Comic_Collec("Batman2", 4, "iconLink", "Batman", 2, 2, 0);
        Comic_Collec t5 = new Comic_Collec("Batman1", 5, "iconLink", "Batman", 2, 1, 0);*/
        user.changeUserComicStatus(t1.getComicVersion(),0 );
        user.changeUserComicStatus(t2.getComicVersion(), 2);
        /*user.changeComicStatus(t3.getComicVersion(), 1);
        user.changeComicStatus(t4.getComicVersion(), 1);
        user.changeComicStatus(t5.getComicVersion(), 1);*/
	}
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
        accueilBtn = new javax.swing.JLabel();
        accueilBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		//rechercheBtnActionPerformed(null);
        	}
        });
        PanelCollection = new javax.swing.JPanel();
        collecBtn = new javax.swing.JLabel();
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
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 800));

        entete.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NetComics");

        PanelAccueil.setBackground(new java.awt.Color(0, 0, 0));
        PanelAccueil.setToolTipText("");
        PanelAccueil.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        PanelAccueil.setName(""); // NOI18N
        PanelAccueil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelAccueilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelAccueilMouseExited(evt);
            }
        });

        accueilBtn.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        accueilBtn.setForeground(new java.awt.Color(255, 255, 255));
        accueilBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        accueilBtn.setText("Accueil");
        accueilBtn.setName("Acc"); // NOI18N

        javax.swing.GroupLayout PanelAccueilLayout = new javax.swing.GroupLayout(PanelAccueil);
        PanelAccueil.setLayout(PanelAccueilLayout);
        PanelAccueilLayout.setHorizontalGroup(
            PanelAccueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAccueilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accueilBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelAccueilLayout.setVerticalGroup(
            PanelAccueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAccueilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accueilBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelCollection.setBackground(new java.awt.Color(0, 0, 0));
        PanelCollection.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelCollectionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelCollectionMouseExited(evt);
            }
        });

        collecBtn.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        collecBtn.setForeground(new java.awt.Color(255, 255, 255));
        collecBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        collecBtn.setText("Ma Collection");
        collecBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                collecBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelCollectionLayout = new javax.swing.GroupLayout(PanelCollection);
        PanelCollectionLayout.setHorizontalGroup(
        	PanelCollectionLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(PanelCollectionLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(collecBtn, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        			.addContainerGap())
        );
        PanelCollectionLayout.setVerticalGroup(
        	PanelCollectionLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(PanelCollectionLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(collecBtn, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        			.addContainerGap())
        );
        PanelCollection.setLayout(PanelCollectionLayout);

        jTextField1.setBackground(new java.awt.Color(0, 0, 0));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("Rechercher");
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
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelConnectionLayout.setVerticalGroup(
            PanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelConnectionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
        );

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));

        rechercheBtn.setBackground(new java.awt.Color(0, 0, 0));
        rechercheBtn.setForeground(new java.awt.Color(255, 255, 255));
        rechercheBtn.setText("Rechercher");
        rechercheBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        rechercheBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheBtnActionPerformed(evt);
            }
        });

        dropListFiltre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tout", "Personnages", "Séries", "Comic" }));

        javax.swing.GroupLayout enteteLayout = new javax.swing.GroupLayout(entete);
        entete.setLayout(enteteLayout);
        enteteLayout.setHorizontalGroup(
            enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enteteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(PanelAccueil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelCollection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(enteteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PanelConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, enteteLayout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dropListFiltre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rechercheBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        enteteLayout.setVerticalGroup(
            enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, enteteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(enteteLayout.createSequentialGroup()
                        .addComponent(PanelConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(enteteLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(PanelAccueil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(PanelCollection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(enteteLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rechercheBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dropListFiltre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(17, 17, 17))))))
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
            .addGap(0, 1010, Short.MAX_VALUE)
        );
        panelAffichageMultipleLayout.setVerticalGroup(
            panelAffichageMultipleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
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
                .addComponent(scrollPaneAffichageMultiple, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
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
                .addComponent(scrollPaneAffichageMultiple, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Navbar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(entete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contentPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(entete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPage, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jTextField1.setText("");
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void PanelAccueilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelAccueilMouseEntered
        // TODO add your handling code here:
        accueilBtn.setForeground(new Color(112, 128, 144));
    }//GEN-LAST:event_PanelAccueilMouseEntered

    private void PanelCollectionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelCollectionMouseEntered
        // TODO add your handling code here:
       collecBtn.setForeground(new Color(112,128,144));
    }//GEN-LAST:event_PanelCollectionMouseEntered

    private void PanelConnectionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelConnectionMouseEntered
        // TODO add your handling code here:
        jLabel4.setForeground(new Color(112,128,144));
    }//GEN-LAST:event_PanelConnectionMouseEntered

    private void PanelAccueilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelAccueilMouseExited
        // TODO add your handling code here:
        accueilBtn.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_PanelAccueilMouseExited

    private void PanelCollectionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelCollectionMouseExited
        // TODO add your handling code here:
        collecBtn.setForeground(new Color(255,255,255));
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

    private void collecBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rechercheBtnMouseClicked
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
    }//GEN-LAST:event_formWindowClosing


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
    private javax.swing.JLabel accueilBtn;
    private javax.swing.JLabel collecBtn;
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
