package gestionpersonnel;

/*class contenant les différents traitements d'un fichier */

import java.util.Vector;
import java.io.*;

public class Fichier {

  //method qui remplit un fichier d'Personnels a partir d'un vecteur
  static public void remplirFile(File f, Vector v) {
    Personnel e;
   
    StringBuffer ligne;
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(f));
      for (int i = 0; i < v.size(); i++) {
        e = (Personnel) v.elementAt(i);
        ligne = new StringBuffer(e.getIdPerso() + ";" + e.getNom() + ";" +
                                 e.getPrenom() + ";" + e.getDn().getJour() +
                                 ";" + e.getDn().getMois() + ";" +
                                 e.getDn().getAnnee() + ";" + e.getSexe() + ";" +
                                 e.getDepartement().getNom() + ";" );

       

        //ecriture de ligne dans le fichier
        out.write(ligne.toString());
        out.newLine();
      }
      out.close();
    }
    catch (IOException ex) {

      erreur();
    }
    //System.out.println("operation terminé avec succés");
  }

  /*Methode qui retourne un Personnel a partir d'une ligne du fichier (chaine de caractere)
   cette methode sera appeler dans la methode extraireFile */
  static public Personnel extrairePerso(String ligne) {
    int id = 0;
    try {
      id = Integer.parseInt(ligne.substring(0, ligne.indexOf(';')));
      ligne = ligne.substring(ligne.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      erreur();
    }

    String nom = ligne.substring(0, ligne.indexOf(';'));
    ligne = ligne.substring(ligne.indexOf(';') + 1);

    String prenom = ligne.substring(0, ligne.indexOf(';'));
    ligne = ligne.substring(ligne.indexOf(';') + 1);

    //recuperation de la date
    int jour = 0, mois = 0, annee = 0;
    try {
      jour = Integer.parseInt(ligne.substring(0, ligne.indexOf(';')));
      ligne = ligne.substring(ligne.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      erreur();
    }
    try {
      mois = Integer.parseInt(ligne.substring(0, ligne.indexOf(';')));
      ligne = ligne.substring(ligne.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      erreur();
    }
    try {
      annee = Integer.parseInt(ligne.substring(0, ligne.indexOf(';')));
      ligne = ligne.substring(ligne.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      erreur();
    }
    Date dn = new Date(jour, mois, annee);
    //fin recuperation date

    String sexe = ligne.substring(0, ligne.indexOf(';'));
    ligne = ligne.substring(ligne.indexOf(';') + 1);

    //recuperation de la Departement
    Departement departement;
    String nomDep = ligne.substring(0, ligne.indexOf(';'));
    ligne = ligne.substring(ligne.indexOf(';') + 1);
     departement = new Departement(nomDep);

    return (new Personnel(id, nom, prenom, dn, sexe, departement));
  }

//methode de traitement d'un erreur lors d'extraction du fichier
  static public void erreur() {
    System.out.println("ERREUR");
  }

//Methode permettant d'extraire les Personnels a partir d'un fichier et les stocker ds un vecteur
  static public Vector extraireFile(File f) {
    Vector v = new Vector();
    if (f.exists()) {
      try {
        String ch;
        Personnel e;
        BufferedReader in = new BufferedReader(new FileReader(f));
        do {
          ch = in.readLine();
          if (ch != null) {
            e = extrairePerso(ch);
            v.addElement(e);
          }
        }
        while (ch != null);
      }
      catch (IOException ex) {
        System.out.println(ex.toString());
      }
    }
    return v;
  }

  //extraction d'une departement a partir d'une ligne (chaine de caractere) cette fonction sera appelé
  //dans la methode exraireFileFil
  static public Departement extraireFil(String ligne) {
    String nom = ligne.substring(0, ligne.indexOf(';'));
    ligne = ligne.substring(ligne.indexOf(';') + 1);
      
    return (new Departement(nom));
  } //fin extraireFil

  //methode qui extrait les departements a partir d'un fichiers s'il existe
  //sinon elle initialise la liste de departements
  static public Vector extraireFileFil(File f) {
    Vector v = new Vector();
    if (f.exists()) {
      try {
        String ch;
        Departement dep;
        BufferedReader in = new BufferedReader(new FileReader(f));
        do {
          ch = in.readLine();
          if (ch != null) {
            dep = extraireFil(ch);
            v.addElement(dep);
          }
        }
        while (ch != null);
      }
      catch (IOException ex) {
        System.out.println(ex.toString());
      }
    }
    else { //si la le fichier n'existe pas on initialise la liste des département
      v.addElement(extraireFil("Informatique;"));
      v.addElement(extraireFil("Production;"));
      v.addElement(extraireFil("Resource humain;"));
      v.addElement(extraireFil("Maintenence;"));
      v.addElement(extraireFil("Comercial;"));
      v.addElement(extraireFil("Comptabilité;"));
    }
    return v;
  } //FIN extraireFileFil

  //methode qui sauvegarde un vecteur de Departements dans un fichier de Departements
  static public void remplirFileFil(File f, Vector v) {
    Departement dep;
   
    StringBuffer ligne;
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(f));
      for (int i = 0; i < v.size(); i++) {
        dep = (Departement) v.elementAt(i);
        ligne = new StringBuffer(dep.getNom() + ";");

        //ecriture de ligne dans le fichier
        out.write(ligne.toString());
        out.newLine();
      }
      out.close();
    }
    catch (IOException ex) {

      erreur();
    }
    //System.out.println("operation terminé avec succés");
  }

}