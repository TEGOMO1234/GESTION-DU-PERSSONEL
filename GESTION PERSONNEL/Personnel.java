package gestionpersonnel;

import java.util.Vector;

public class Personnel {

  private int idPerso; 
  private String nom, prenom, sexe;
  private Date dn; //date de naissance
  private Departement departement; 


  //constructeur de la classe personne
  public Personnel(int ce, String n, String p, Date dt, String s, Departement d) {
    idPerso = ce;
    nom = n;
    prenom = p;
    dn = dt;
    sexe = s;
    departement = d;
  }

  public Date getDn() {
    return dn;
  }

  public Departement getDepartement() {
    return departement;
  }

  public int getIdPerso() {
    return idPerso;
  }


  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public String getSexe() {
    return sexe;
  }
  public void setDn(Date dn) {
    this.dn = dn;
  }
  public void setDepartement(Departement departement) {
    this.departement = departement;
  }
  public void setNom(String nom) {
    this.nom = nom;
  }
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }
  public void setSexe(String sexe) {
    this.sexe = sexe;
  }

}