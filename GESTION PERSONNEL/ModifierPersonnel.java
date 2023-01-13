package gestionpersonnel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.net.*;

//classe qui contient la frame de modification d'Personnel

public class ModifierPersonnel
    extends ClassMere
    implements ActionListener {

  private int index = -1; //index de le Personnel recherché dans le vecteur des Personnels.
  //il sera utilisé lors du click sur modifier ou supprimer pour effacer le Personnel du vecteur

  MonJLabel idRecherche_lb;
  JTextField idRecherche; 
  MonJLabel anciensVal, nouvelsVal; //titre de nouvels valeurs et anciens valeurs
  MonJLabel nom_lb;
  MonJLabel prenom_lb;
  MonJLabel dn_lb;
  MonJLabel sexe_lb;
  MonJLabel departement_lb;
  MonJLabel situation_lb;

  JTextField nom, nomNv;
  JTextField prenom, prenomNv;
  JTextField dn;
  JTextField ancienSexe; //l'ancien sexe de l'Personnel
  JTextField ancienDepartement; //ancien departement 
  JComboBox jour, mois, annee; //les chmaps de la nouvelle date de naissance

  ButtonGroup sexe; //groupe contenant les boton radio de sexe
  JRadioButton masculin;
  JRadioButton feminin;

  JComboBox departement;
 
  JComboBox situation;

  JButton modifier, retour, rechercher, supprimer;

  JOptionPane confirm;

  //les panneux
  JPanel nord_pn, champs_pn, centre_pn, recherche_pn, dn_pn, sexe_pn,
      departement_pn, sud_pn;

  public ModifierPersonnel() {
    super("Modifier/Supprimer Personnel", 487, 343);

    idRecherche_lb = new MonJLabel("Num. Personel.:");
    idRecherche = new JTextField(10);
    rechercher = new JButton("Rechercher");

    anciensVal = new MonJLabel("Anciens Valeurs");
    nouvelsVal = new MonJLabel("Nouvels Valeurs");

    //definir les champs du nom
    nom_lb = new MonJLabel("Nom:");
    nom = new JTextField(10);
    nomNv = new JTextField(10);
    nom.disable();

    //definir les champs du prenom
    prenom_lb = new MonJLabel("Prenom:");
    prenom = new JTextField(10);
    prenomNv = new JTextField(10);
    prenom.disable();

    //definir les champs du date naissance
    dn_lb = new MonJLabel("Date de Naissance:");
    dn = new JTextField(10);
    dn.disable();
    jour = new JComboBox();
    mois = new JComboBox();
    annee = new JComboBox();

    //initialisation de la liste des jours
    int i = 0;
    for (i = 1; i <= 31; i++) {
      jour.addItem("" + i);
    }
    jour.setSelectedIndex(0); //definir l'element selectionné par defaut (le 1er element)

//initialisation de la liste des mois
    for (i = 1; i <= 12; i++) {
      mois.addItem("" + i);
    }
    mois.setSelectedIndex(0); //definir l'element selectionné par defaut (le 1er element)

//initialisation de la liste des annees
    for (i = 1970; i <= 2007; i++) {
      annee.addItem("" + i);
    }
    annee.setSelectedIndex(16); //definir l'element selectionné par defaut de l'annee (1986)

    //definir les champs du sexe
    sexe_lb = new MonJLabel("Sexe:");
    ancienSexe = new JTextField(10);
    ancienSexe.disable();
    sexe = new ButtonGroup();
    masculin = new JRadioButton("Masculin", true);
    feminin = new JRadioButton("Feminin", false);
    sexe.add(masculin);
    sexe.add(feminin);

    //definir les champs du departement
    departement_lb = new MonJLabel("Département:");

    ancienDepartement = new JTextField(10);
    ancienDepartement.disable();

    departement = new JComboBox();
    Departement fi;
     for(i=0;i<MenuPrincipale.listDepartement.size();i++){
      fi=(Departement)MenuPrincipale.listDepartement.elementAt(i);
      departement.addItem(fi.getNom());
    }

    
    departement.setSelectedIndex(0); 

    
    //definir les buttons
    modifier = new JButton("Modifier");

    modifier.addActionListener(this);
    retour = new JButton("Retour");
    retour.addActionListener(this);
    rechercher.addActionListener(this);
    supprimer = new JButton("Supprimer");
    supprimer.addActionListener(this);
    recherche_pn = new JPanel();
    recherche_pn.add(idRecherche_lb);
    recherche_pn.add(idRecherche);
    recherche_pn.add(rechercher);
    recherche_pn.add(retour);

    //panneau dn
    dn_pn = new JPanel();
    dn_pn.add(jour);
    dn_pn.add(mois);
    dn_pn.add(annee);
    //panneau sexe
    sexe_pn = new JPanel();
    sexe_pn.add(masculin);
    sexe_pn.add(feminin);

//panneau departement
    departement_pn = new JPanel();
    departement_pn.add(departement);
    
    //panneaux des champs
    champs_pn = new JPanel();
    champs_pn.setLayout(new GridLayout(6, 3));
    champs_pn.add(new Panel()); //vide
    champs_pn.add(anciensVal);
    champs_pn.add(nouvelsVal);
    champs_pn.add(nom_lb);
    champs_pn.add(nom);
    champs_pn.add(nomNv);
    champs_pn.add(prenom_lb);
    champs_pn.add(prenom);
    champs_pn.add(prenomNv);
    champs_pn.add(dn_lb);
    champs_pn.add(dn);
    champs_pn.add(dn_pn);
    champs_pn.add(sexe_lb);
    champs_pn.add(ancienSexe);
    champs_pn.add(sexe_pn);
    champs_pn.add(departement_lb);
    champs_pn.add(ancienDepartement);
    champs_pn.add(departement_pn);
    //panneau centre
    centre_pn = new JPanel();
    centre_pn.setLayout(new BorderLayout());
    centre_pn.add("North", recherche_pn);
    centre_pn.add("Center", champs_pn);

    //panneau valid sud
    sud_pn = new JPanel();
    sud_pn.add(modifier);
    sud_pn.add(supprimer);

    //initialiser les panneaux centre et sud comme invisibles
    champs_pn.setVisible(false);
    sud_pn.setVisible(false);

    //ajout dans la frame
    getContentPane().add(centre_pn, BorderLayout.CENTER);
    getContentPane().add(sud_pn, BorderLayout.SOUTH);

    show();

  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == rechercher) {
      recherche_actionPerformed();
    }
    else if (ae.getSource() == modifier) {
      modifier_actionPerformed();
    }
    else if (ae.getSource() == supprimer) {
      supprimer_actionPerformed();
    }
    else if (ae.getSource() == retour) {
      MenuPrincipale.F.enable();
      dispose();
    }
  }

  //evenement lors du click sur le button rechercher
  public void recherche_actionPerformed() {
    int id = 0;
    reset();
    try {
      id = Integer.parseInt(idRecherche.getText());
    }
    catch (NumberFormatException ex) {
    }
    if (id <= 0) {
      champs_pn.setVisible(false);
      sud_pn.setVisible(false);

      idRecherche_lb.setText("Num. Pers.: saisir un numero correcte!!");
      idRecherche_lb.setForeground(Color.red);
    }
    else {
      idRecherche_lb.setText("Num. Perso.:");
      idRecherche_lb.setForeground(Color.black);
      index = recherchePerso(id);
      if (index == -1) {
        champs_pn.setVisible(false);
        sud_pn.setVisible(false);

        confirm.showMessageDialog(null,
                                  "Aucun Personnel trouvé! Verifier le numero",
                                  "Personnel inexistant",
                                  JOptionPane.WARNING_MESSAGE);
        idRecherche.setText("");

      }
      else {
        Personnel e = (Personnel) MenuPrincipale.listPersonnel.elementAt(index);
        nom.setText(e.getNom());
        prenom.setText(e.getPrenom());
        dn.setText(e.getDn().getJour() + "/" + e.getDn().getMois() + "/" +
                   e.getDn().getAnnee());
        ancienSexe.setText(e.getSexe());
        ancienDepartement.setText(e.getDepartement().getNom());

        champs_pn.setVisible(true);
        sud_pn.setVisible(true);
      }
    }

  }

  //evenement lors du click sur le button supprimer
  public void supprimer_actionPerformed() {
    int resultat = confirm.showConfirmDialog(null,
        "Voulez vous vraiment supprimer cet personnel", "Attention",
        JOptionPane.YES_NO_OPTION);
    System.out.println(resultat);

    if (index >= 0 && resultat == 0) { // reponse oui
      MenuPrincipale.listPersonnel.removeElementAt(index); //suppression du vecteur
      reset();
      new  Splash("isgi.jpg","Suppression personnel en cours...","isgi.jpg");
      champs_pn.setVisible(false);
      sud_pn.setVisible(false);
    }
  }

  //evenement lors du click sur le button modifier
  public void modifier_actionPerformed() {
    Personnel perso;
    Departement dep = new Departement();
    int ce=0,j = 0, m = 0, a = 0;
    String n = "", p = "", s = "", f = "";
    Date dn;
    boolean etat = true;

    n = nomNv.getText();
    if (n.equals("")) {
      nom_lb.setForeground(Color.red);
      etat = false;
    }
    else {
      nom_lb.setForeground(Color.black);
    }
    p = prenomNv.getText();
    if (p.equals("")) {
      prenom_lb.setForeground(Color.red);
      etat = false;
    }
    else {
      prenom_lb.setForeground(Color.black);
    }
    //date
    try {
      j = Integer.parseInt(jour.getSelectedItem().toString());
      m = Integer.parseInt(mois.getSelectedItem().toString());
      a = Integer.parseInt(annee.getSelectedItem().toString());
    }
    catch (NumberFormatException ex) {
      etat = false;
    }
    dn = new Date(j, m, a);
    //sexe
    if (masculin.isSelected()) {
      s = "masculin";
    }
    else {
      s = "feminin";
    }
    //Departement
    f = departement.getSelectedItem().toString();
   

    if (etat) { //la saisie est juste
      //on recupere la departement

      int resultat = confirm.showConfirmDialog(null,
          "Voulez vous vraiment modifier cet Personnel", "Attention",
          JOptionPane.YES_NO_OPTION);

      if (resultat == 0) { //reponse oui
        dep = recupDepartement(f);
        System.out.println(n + p + j + m + a + s + f + dep.getNom());
        ce=((Personnel)MenuPrincipale.listPersonnel.elementAt(index)).getIdPerso();
        perso=new Personnel(ce,n,p,dn,s,dep);
        MenuPrincipale.listPersonnel.setElementAt(perso,index);
        Personnel e1=(Personnel)MenuPrincipale.listPersonnel.elementAt(index);
        champs_pn.setVisible(false);
        sud_pn.setVisible(false);

      }
    }
  }


  //recupere un departement a partir de son nom pour l'affecter a l'Personnel a modifier
  public Departement recupDepartement(String nomdep) {
    Departement dep1=new Departement();
    for (int i = 0; i < MenuPrincipale.listDepartement.size(); i++) {
      dep1 = ( (Departement) MenuPrincipale.listDepartement.elementAt(i));

      if (dep1.getNom().equals(nomdep)) {
        String nomdepartement=dep1.getNom();
        
        

        return new Departement(nomdepartement);
      }
    }
    return dep1;
  }


//recherche d'un Personnel a partir de son num
  public int recherchePerso(int num) {
    Personnel e;
    for (int i = 0; i < MenuPrincipale.listPersonnel.size(); i++) {
      e = (Personnel) MenuPrincipale.listPersonnel.elementAt(i);
      if (e.getIdPerso() == num) {
        return i;
      }
    }
    return -1;
  }


  //retablir les champs
  public void reset(){
    nomNv.setText("");
    prenomNv.setText("");
    jour.setSelectedIndex(0);
    mois.setSelectedIndex(0);
    annee.setSelectedIndex(16);
    masculin.setSelected(true);
    feminin.setSelected(false);
    departement.setSelectedIndex(0);
  }

// public static void main(String[] args) {
  //  new NouveauPersonnel();
    //new ModifierPersonnel();
  //}
}