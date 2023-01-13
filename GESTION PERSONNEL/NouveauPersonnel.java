package gestionpersonnel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

public class NouveauPersonnel
    extends ClassMere
    implements  ActionListener {



  MonJLabel ce_lb; //label de la carte d'Personnel
  MonJLabel nom_lb;
  MonJLabel prenom_lb;
  MonJLabel dn_lb;
  MonJLabel sexe_lb;
  MonJLabel depart_lb;
  MonJLabel situation_lb;

  JTextField ce;
  JTextField nom;
  JTextField prenom;

  JComboBox jour, mois, annee; //les chmaps de date de naissance

  ButtonGroup sexe; //groupe contenant les boton radio de sexe
  JRadioButton masculin;
  JRadioButton feminin;

  JComboBox departement;
  JComboBox situation;

  JButton ajouter, retour;

  JPanel centre_pn, sud_pn; //les differents panel du frame

  JOptionPane confirm;


  //Constructeur
  public NouveauPersonnel() {

    super("Nouveau Personnel", 350, 350);

    //definir les champs du numero carte Personnel
    ce_lb = new MonJLabel("Numero Carte Personnel:");
    ce = new JTextField();
    ce.setText("" + numAuto());
    ce.disable();

    //definir les champs du nom
    nom_lb = new MonJLabel("Nom:");
    nom = new JTextField();

    //definir les champs du prenom
    prenom_lb = new MonJLabel("Prenom:");
    prenom = new JTextField();

    //definir les champs du date naissance
    dn_lb = new MonJLabel("Date de Naissance:");
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
    sexe = new ButtonGroup();
    masculin = new JRadioButton("Masculin", true);
    feminin = new JRadioButton("Feminin", false);
    sexe.add(masculin);
    sexe.add(feminin);

    //definir les champs du departement
    depart_lb = new MonJLabel("Département:");


    departement = new JComboBox();
    //initialiser la liste des departement a partir de listVecteurs
    Departement fi;
    for(i=0;i<MenuPrincipale.listDepartement.size();i++){
      fi=(Departement)MenuPrincipale.listDepartement.elementAt(i);
      departement.addItem(fi.getNom());
    }
    
    departement.setSelectedIndex(0); 

    ajouter = new JButton("Ajouter");
    ajouter.addActionListener(this);
    retour = new JButton("Retour");
    retour.addActionListener(this);

    //** ajout des elements dans les panels

     //ajout des chapms de saisie et labels dans le panel centre
    centre_pn = new JPanel();
    centre_pn.setLayout(new GridLayout(6, 2));
    centre_pn.add(ce_lb);
    centre_pn.add(ce);
    centre_pn.add(nom_lb);
    centre_pn.add(nom);
    centre_pn.add(prenom_lb);
    centre_pn.add(prenom);
    centre_pn.add(dn_lb);

    //ajout des lists jour,mois et annee dans un panel
    JPanel dn_pn = new JPanel();
    dn_pn.add(jour);
    dn_pn.add(mois);
    dn_pn.add(annee);
    //fin ajout des champs date

    centre_pn.add(dn_pn);
    centre_pn.add(sexe_lb);

    //ajout des champs du sexe dans un panel
    JPanel sexe_pn = new JPanel();
    sexe_pn.add(masculin);
    sexe_pn.add(feminin);
    //fin ajout des champs sexe

    centre_pn.add(sexe_pn);

    //ajout du liste departement est liste niveaux dans un meme panel
    JPanel departement_pn = new JPanel();
    departement_pn.add(departement);
    

    centre_pn.add(depart_lb);
    centre_pn.add(departement_pn);


    //ajout des bottons dans le panel sud
    sud_pn = new JPanel();
    sud_pn.add(ajouter, BorderLayout.PAGE_END);
    sud_pn.add(retour, BorderLayout.PAGE_END);
    //fin ajout dans le panel sud

    //** ajout des panels dans la frame
    getContentPane().add(centre_pn, BorderLayout.CENTER);
    getContentPane().add(sud_pn, BorderLayout.SOUTH);

    setResizable(false);
    show();

  }

  public void actionPerformed(ActionEvent ae) { //gere les evenements des buttons
    if (ae.getSource() == retour) {
      MenuPrincipale.F.enable();
      dispose();
    }
    else if (ae.getSource() == ajouter) {
      ajouter_actionPerformed();
    }
  }


  //methode qui donne le attribu un numero a chaque Personnel
  public int numAuto() {
    if (MenuPrincipale.listPersonnel.size() == 0) {
      return 1;
    }
    else {
      return ( ( (Personnel) MenuPrincipale.listPersonnel.elementAt(MenuPrincipale.listPersonnel.size() - 1)).
              getIdPerso() + 1);
    }
  }

  //methode qui traite le click sur ajouter
  public void ajouter_actionPerformed() {
    Personnel Perso;
    Departement dep=new Departement();
    int id = 0, j = 0, m = 0, a = 0;
    String n = "", p = "", s = "", f = "";
    Date dn;
    boolean etat = true;

    id = Integer.parseInt(ce.getText());
    n = nom.getText();
    if (n.equals("")) {
      nom_lb.setText("nom : Entrer le nom!!");
      nom_lb.setForeground(Color.red);
      etat = false;
    }else{
      nom_lb.setText("nom :");
      nom_lb.setForeground(Color.black);
    }
    p = prenom.getText();
    if (p.equals("")) {
      prenom_lb.setText("prenom : Entrer le prenom!!");
      prenom_lb.setForeground(Color.red);
      etat = false;
    }else{
      prenom_lb.setText("prenom :");
      prenom_lb.setForeground(Color.black);
    }
    //date
    try {
      j = Integer.parseInt(jour.getSelectedItem().toString());
      m = Integer.parseInt(mois.getSelectedItem().toString());
      a = Integer.parseInt(annee.getSelectedItem().toString());
    }
    catch (NumberFormatException ex) {
      etat=false;
    }
    dn = new Date(j, m, a);
    //sexe
    if (masculin.isSelected()) {
      s = "masculin";
    }
    else {
      s = "feminin";
    }
    //departement
      f=departement.getSelectedItem().toString();
   

    if (etat) { //la saisie est juste
      int result=confirm.showConfirmDialog(null,"Voulez vous vraiment ajouter cet personnel","Confirmation ajout",JOptionPane.YES_NO_OPTION);
      if(result==0){//reponse oui

        //on recupere le departement
        dep = recupDepartement(f);
        Perso = new Personnel(id, n, p, dn, s, dep);

       
        MenuPrincipale.listPersonnel.addElement(Perso);

        ce.setText(numAuto() + "");
      }
      nom.setText("");
      prenom.setText("");
       new  Splash("isgi.jpg","Ajout personnel en cours...","isgi.jpg");
      
    }
  }


  //recupere un departement a partir de son nom pour l'affecter a le Personnel a modifier
  public Departement recupDepartement(String nomDep) {
    
    Departement dep1=new Departement();
    for (int i = 0; i < MenuPrincipale.listDepartement.size(); i++) {
      dep1 = ( (Departement) MenuPrincipale.listDepartement.elementAt(i));

      if (dep1.getNom().equals(nomDep) ) {
        String nomDepartement=dep1.getNom();
       
       

        return new Departement(nomDepartement);
      }
    }
    return dep1;
  }


}