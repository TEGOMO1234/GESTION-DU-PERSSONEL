package gestionpersonnel;


 import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*; 
  
public  class Splash
	{
    
	JProgressBar progress;
	Thread thread;
	
	public static JFrame frame;
		
 
		public Splash(String imgPath, String message,String icone)
			{
				
				
			frame = new JFrame("Chargement");
                        frame.setIconImage(
                              Toolkit.getDefaultToolkit().getImage(icone));//icone de la Jframe
                        JPanel panel = 
                             new JPanel();
                        panel.setBackground(
                                  new Color(124,125,235));//Couleur de fond du Panel
                        panel.setSize(450,250);
                        panel.setBounds(0,0,450,250);
			JLabel texte = 
                                new JLabel(message);//Texte de la String 
                        texte.setForeground(Color.ORANGE);
                        JLabel img = 
                               new JLabel();
                        img.setIcon(
                                 new ImageIcon(imgPath));
			progress =
                             new JProgressBar(0, 100); 
                                
                        panel.setBorder(
                            BorderFactory.createLineBorder(Color.BLACK));
                        progress.setStringPainted(true);                                 
                       //ajout des éléments
                       panel.add("North",img);
                       panel.add("North",texte);
                       panel.add("East",progress); 
                       frame.getContentPane().add(BorderLayout.CENTER, panel);
		       frame.setSize(450,250);
                   //Pour définir le Splash au milieu de l'écran'
                        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                        frame.setLocation((screen.width - frame.getSize().width)/2,(screen.height - frame.getSize().height)/2);
                     // pour que ca ai vraiement l air d un splash :p
                	frame.setUndecorated(true);
                        frame.setVisible(true);
			frame.setResizable(false);
                        //Retaillage de la barre pour qu elle corresponde a la taille de la frame
			progress.setBounds(new Rectangle(10,220,430,20));

                                     thread = new Thread( new Progression());thread.start();
                                                                              
			}
		public class Progression  implements Runnable
			{
			public void run()
				{
					for (int j = 1; j < 100; j++) 
						{
							progress.setValue(j);
                                                        progress.setString(j+" %");
							try
							{
							thread.sleep(40);//determination de la rapiditée de la frame
							}
						catch(Exception e)
							{
                                                                e.printStackTrace();
                                                                frame.dispose();//en cas d' erreur pour pas rester bloqué sur le splash
							}
						}
						frame.dispose(); //fermeture de la frame lorsque le chargement est teminé
                                                
				}
			}
	} 
  