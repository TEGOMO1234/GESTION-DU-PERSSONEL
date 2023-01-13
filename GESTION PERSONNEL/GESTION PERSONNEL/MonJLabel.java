package gestionpersonnel;

import java.awt.*;
import javax.swing.*;

public class MonJLabel extends JLabel{
  static Font font=new Font("Arial",0,12);
  public MonJLabel(String titre) {
    super(titre);
    setFont(font);
  }
}