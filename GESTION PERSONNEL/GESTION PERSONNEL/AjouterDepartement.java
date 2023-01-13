package gestionpersonnel;


import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class AjouterDepartement extends ClassMere implements ActionListener{

  Vector VectMat=new Vector();

  private MonJLabel dep_lb=new MonJLabel("Département: ");
  private JTextField nomDep=new JTextField(5);

  private JButton ajouter=new JButton("Ajouter");
  private JButton retour=new JButton("Retour");

  private JOptionPane confirm;

  private JPanel centre=new JPanel(),sud=new JPanel();


  public AjouterDepartement(){
    super("Ajout Département",300,300);

    ajouter.addActionListener(this);
    retour.addActionListener(this);

    centre.add(dep_lb);
    centre.add(nomDep);

    sud.add(ajouter);
    sud.add(retour);

    getContentPane().add(centre,BorderLayout.CENTER);
    getContentPane().add(sud,BorderLayout.SOUTH);


    pack();
    show();
  }



  public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()==ajouter){
      ajouter_actionPerformed();
    }
    else if(ae.getSource()==retour){
      MenuPrincipale.F.enable();
      dispose();
    }
  }


  public void ajouter_actionPerformed(){
    String nom=nomDep.getText();
    if(nom.equals("")){
      dep_lb.setForeground(Color.red);
    }
    else{
      dep_lb.setForeground(Color.black);
      if(filExist(nom)){//le département existe deja
        confirm.showMessageDialog(null,"Le Département existe deja","Département existante",JOptionPane.ERROR_MESSAGE);
      }
      else{//le département n'existe pas
        int resultat=confirm.showConfirmDialog(null,"Voulez vous vraiment ajouter ce Département?","Ajout Département",JOptionPane.YES_NO_OPTION);
        if(resultat==0){//reponse oui
          
            MenuPrincipale.listDepartement.addElement(new Departement(nom));
           new  Splash("isgi.jpg","Ajout département Chargement...","isgi.jpg");
          
        }
      }
      nomDep.setText("");
    }
  }


  //verifi si le département existe deja
  public boolean filExist(String nom){
    Departement dep;
    for(int i=0;i<MenuPrincipale.listDepartement.size();i++){
      dep=(Departement)MenuPrincipale.listDepartement.elementAt(i);
      if(dep.getNom().equalsIgnoreCase(nom)){
        return true;
      }
    }
    return false;
  }

 /* public static void main(String[] args){
    new AjouterDepartement();
  }*/
}
