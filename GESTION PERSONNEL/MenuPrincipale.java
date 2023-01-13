package gestionpersonnel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Vector;



public class MenuPrincipale extends JPanel implements ActionListener{

  static File depFich ;
  static File persoFich ;

  static Vector listPersonnel=new Vector();
  static Vector listDepartement=new Vector();

 static ClassMere F=new ClassMere("Menu Principale",700,460);

  JPanel button_pn = new JPanel();
  JButton quitter = new JButton();
  JButton sauvegarder = new JButton();
  JPanel perso_pn = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  JPanel dep_pn = new JPanel();
  Border border2;
  TitledBorder titledBorder2;
  JButton modifier = new JButton();
  JButton departement = new JButton();
  JPanel Img_pn = new image("images\\accueil.jpg");
  JLabel isgi_lb = new JLabel();
  JButton nouveau = new JButton();
  JButton supprimer = new JButton();
 
  JOptionPane confirm;

  //constructeur
  public MenuPrincipale() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    F.Option.addActionListener(this);
    F.save.addActionListener(this);
    F.close.addActionListener(this);
    F.about.addActionListener(this);

    F.save.setEnabled(true);
    F.close.setEnabled(true);
    F.Option.setEnabled(true);
    F.about.setEnabled(true);

    F.copy.setEnabled(false);
    F.cut.setEnabled(false);
    F.paste.setEnabled(false);

    F.getContentPane().add(this,BorderLayout.CENTER);
    F.show();
  }



  //main
  public static void main(String[] args) {

    depFich = new File("c:\\Departement.txt");
    persoFich = new File("c:\\Personnel.txt");

    listPersonnel=Fichier.extraireFile(persoFich);
    listDepartement=Fichier.extraireFileFil(depFich);

    MenuPrincipale menuPrincipale1 = new MenuPrincipale();

  }




  private void jbInit() throws Exception {
    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(134, 134, 134));
    titledBorder1 = new TitledBorder(border1,"Espace Personnel");
    border2 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(134, 134, 134));
    titledBorder2 = new TitledBorder(border2,"Espace Département");
   
    this.setLayout(null);
    button_pn.setBackground(Color.gray);
    button_pn.setBorder(BorderFactory.createEtchedBorder());
    button_pn.setDoubleBuffered(true);
    button_pn.setBounds(new Rectangle(320, 312, 335, 54));
    button_pn.setLayout(null);
    quitter.setBounds(new Rectangle(183, 5, 142, 40));
    quitter.setText("Quitter");
    quitter.setIcon(new ImageIcon("images\\sortie.gif"));
    quitter.addActionListener(this); //ecouteur


    sauvegarder.setText("Sauvegarder");
    sauvegarder.setBounds(new Rectangle(24, 5, 142, 40));
    sauvegarder.setIcon(new ImageIcon("images\\sauvegarder.gif"));
    sauvegarder.addActionListener(this); //ecouteur
    perso_pn.setBackground(Color.lightGray);
    perso_pn.setBorder(titledBorder1);
    perso_pn.setBounds(new Rectangle(6, 5, 199, 230));
    perso_pn.setLayout(null);
    dep_pn.setBackground(Color.lightGray);
    dep_pn.setBorder(titledBorder2);
    dep_pn.setBounds(new Rectangle(276, 5, 200, 133));
    dep_pn.setLayout(null);
    modifier.setBounds(new Rectangle(34, 68, 118, 39));
    modifier.setText("Modifier");
    modifier.setIcon(new ImageIcon("images\\modifier.gif"));
    modifier.addActionListener(this);//ecouteur
    departement.setBounds(new Rectangle(17, 26, 160, 40));
    departement.setText("Département");
    departement.setIcon(new ImageIcon("images\\new.jpg"));
    departement.addActionListener(this);//ecouteur
    Img_pn.setBackground(Color.darkGray);
    Img_pn.setBounds(new Rectangle(500, 5, 185, 275));
    isgi_lb.setText("ISGI Sfax Tous Droits Réservés © 2007-2008");
    isgi_lb.setBounds(new Rectangle(2, 328, 280, 25));
    nouveau.setIcon(new ImageIcon("images\\nouveau.jpg"));
    nouveau.setText("Nouveau");
    nouveau.setBounds(new Rectangle(34, 20, 118, 39));
    nouveau.addActionListener(this);//ecouteur
    
   
    supprimer.setIcon(new ImageIcon("images\\supprimer.gif"));
    supprimer.setText("Supp.");
    supprimer.setBounds(new Rectangle(34, 119, 118, 39));
    supprimer.addActionListener(this);//ecouteur
   
    button_pn.add(sauvegarder, null);
    button_pn.add(quitter, null);
    this.add(perso_pn, null);
    perso_pn.add(modifier, null);
    perso_pn.add(nouveau, null);
    perso_pn.add(supprimer, null);
    dep_pn.add(departement, null);
    this.add(isgi_lb, null);
    this.add(button_pn, null);
    this.add(Img_pn, null);
    this.add(dep_pn, null);
  }

  void quitter_actionPerformed() {
    int result=confirm.showConfirmDialog(null,"Voulez vous sauvegarder avant de quitter??","Gestion Personnels",JOptionPane.YES_NO_CANCEL_OPTION);
    if(result==0){//reponse oui
      sauvegarder_actionParformed();
      System.exit(0);
    }
    else if(result==1){//reponse non
        System.exit(0);
    }
  }

  void sauvegarder_actionParformed(){
    Fichier.remplirFile(persoFich,listPersonnel);
    Fichier.remplirFileFil(depFich,listDepartement);
  }

  void nouveau_actionPerformed(){
    F.disable();
    new NouveauPersonnel();
  }

  void mod_supp_actionPerformed(){
    F.disable();
    new ModifierPersonnel();
  }

  void departement_actionPerformed(){
    F.disable();
    new AjouterDepartement();
  }



  void apropos_actionPerformed(){
    F.disable();
    new apropos();
  }

  void Option_actionPerformed(){
    F.disable();
    new affichage();
  }

  public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()==quitter || ae.getSource()==F.close){
      quitter_actionPerformed();
    }
    else if(ae.getSource()==sauvegarder || ae.getSource()==F.save){
      sauvegarder_actionParformed();
    }
    
    else if(ae.getSource()==nouveau){
      nouveau_actionPerformed();
    }
    else if(ae.getSource()==modifier || ae.getSource()==supprimer){
      mod_supp_actionPerformed();
    }
    
    else if(ae.getSource()==departement){
      departement_actionPerformed();
    }
    else if(ae.getSource()==F.about){
      apropos_actionPerformed();
    }
    else if(ae.getSource()==F.Option){
      Option_actionPerformed();
    }
  }
}