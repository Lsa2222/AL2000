package ui;
import fc.FacadeTLI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class fenetre {
	static JFrame Home_non_abo = null;
	static JFrame Home_abo = null;
	//JFrame fenetre_precedente = null;
	static JFrame fenetre_infos = null;
	static JFrame fenetre_catalogue = null;
	static JFrame fenetre_connexion = null;
	static JFrame fenetre_creer_compte_adulte = null;
	//static JButton btnsuivant = new JButton("suivant");
	static FacadeTLI facade = new FacadeTLI();
	
	static JFrame fenetresuivante(String titre, JFrame fenetreprecedente,Boolean suivant, ActionListener a) {
		JFrame fenetre = new JFrame();
		fenetre.setSize(600,600);
		fenetre.setLocationRelativeTo(null);
		
		JButton btnretour = new JButton("retour");
		btnretour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fenetreprecedente.setVisible(true);
				fenetre.setVisible(false);
			}
		});
		
		if (suivant) {
			JButton btnsuivant = new JButton("suivant");
			btnsuivant.addActionListener(a);
			//System.out.println("ici2");
			JPanel containersuite = new JPanel (new GridLayout(1,2));
			containersuite.add(btnretour);
			containersuite.add(btnsuivant);
			
			fenetre.add(containersuite,BorderLayout.SOUTH);
		}
		
		else {
			fenetre.add(btnretour,BorderLayout.SOUTH);
		}
		
		fenetre.setVisible(true);
		return fenetre;
	}
	
	static void fenetre_informations(String titre, JFrame fenetreprecedente) {
		fenetre_infos = fenetresuivante(titre, fenetreprecedente, false, null);
		
		JPanel souscontainerpanel = new JPanel (new GridLayout(3,1));
		//JPanel containerpanelgauche = new JPanel (new GridLayout(2,1));
		
		JLabel textehaut = new JLabel (" Credits :");
		//récupérer les vrais crédits de l'abonné
		String argent = " 0.00 euros";
		JLabel textebas = new JLabel (argent);
		textehaut.setFont(new Font("Serif", Font.BOLD, 40));
		textebas.setFont(new Font("Serif", Font.BOLD, 40));
		
		JPanel paneltextehaut = new JPanel (new BorderLayout());
		paneltextehaut.add(textehaut,BorderLayout.CENTER);
		JPanel paneltextebas = new JPanel (new BorderLayout());
		paneltextebas.add(textebas,BorderLayout.CENTER);
		
		souscontainerpanel.add(paneltextehaut);
		souscontainerpanel.add(paneltextebas);
		
		JButton btn_recharge = new JButton("recharger compte");
		
		//containerpanelgauche.add(souscontainerpanel);
		//containerpanelgauche.add(btn_recharge);
		
		JPanel containerpanelgauche = new JPanel (new GridLayout(2,1));
		
		JButton btnhistorique = new JButton ("historique");
		//JButton btnrestrictions = new JButton("restrictions");
		JLabel restrictionshaut = new JLabel (" Restrictions :");
		//récupérer les vraies restrictions
		String restrictions = " tag1, tag2, tag3";
		JLabel restrictionsbas = new JLabel (restrictions);
		restrictionshaut.setFont(new Font("Serif", Font.BOLD, 40));
		restrictionsbas.setFont(new Font("Serif", Font.BOLD, 40));
		
		JPanel souscontainerpanel2 = new JPanel(new GridLayout(3,1));
		souscontainerpanel2.add(restrictionshaut);
		souscontainerpanel2.add(restrictionsbas);
		
		//containerpaneldroite.add(btnhistorique);
		//containerpaneldroite.add(btnrestrictions);
		containerpanelgauche.add(souscontainerpanel);
		containerpanelgauche.add(souscontainerpanel2);
		
		JPanel containerpaneldroite = new JPanel (new GridLayout(2,1));
		containerpaneldroite.add(btn_recharge);
		containerpaneldroite.add(btnhistorique);
		
		//fenetre.add(containerpanelgauche,BorderLayout.WEST);
		/*fenetre.add(souscontainerpanel,BorderLayout.CENTER);
		fenetre.add(btn_recharge,BorderLayout.WEST);
		fenetre.add(containerpaneldroite,BorderLayout.EAST);*/
		fenetre_infos.add(containerpanelgauche,BorderLayout.WEST);
		fenetre_infos.add(containerpaneldroite,BorderLayout.EAST);
	}
	
	static void fenetre_creer_compte(String titre, JFrame fenetreprecedente) {
		if (fenetre_creer_compte_adulte == null) {
			ActionListener effet = new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//fenetre2.setVisible(false);
					fenetre_connexion.setVisible(false);
					fenetre_origine(true);
				}
			};
			fenetre_creer_compte_adulte = fenetresuivante("connexion",Home_non_abo,true,effet);
			//JFrame fenetre = fenetresuivante(titre, fenetreprecedente, true);
			
			JLabel prenom = new JLabel(" Prenom :");
			prenom.setFont(new Font("Serif", Font.BOLD, 40));
			JTextField texteprenom = new JTextField (30);
			texteprenom.setFont(new Font("Serif", Font.BOLD, 40));
			
			JLabel nom = new JLabel(" Nom :");
			nom.setFont(new Font("Serif", Font.BOLD, 40));
			JTextField textenom = new JTextField (30);
			textenom.setFont(new Font("Serif", Font.BOLD, 40));
			
			JLabel mail = new JLabel(" Mail :");
			mail.setFont(new Font("Serif", Font.BOLD, 40));
			JTextField textemail = new JTextField (30);
			textemail.setFont(new Font("Serif", Font.BOLD, 40));
			
			JLabel adresse = new JLabel(" Adresse :");
			adresse.setFont(new Font("Serif", Font.BOLD, 40));
			JTextField texteadresse = new JTextField (30);
			texteadresse.setFont(new Font("Serif", Font.BOLD, 40));
			
			JLabel restrictions = new JLabel(" Restrictions :");
			restrictions.setFont(new Font("Serif", Font.BOLD, 40));
			/*JTextField texterestrictions = new JTextField (30);
			texterestrictions.setFont(new Font("Serif", Font.BOLD, 40));*/
			JCheckBox check1 = new JCheckBox("tag1"); 
		    JCheckBox check2 = new JCheckBox("tag2");
		    JCheckBox check3 = new JCheckBox("tag3");
	        JCheckBox check4 = new JCheckBox("tag4");
	        JPanel check = new JPanel ();
	        check.add(check1);
	        check.add(check2);
	        check.add(check3);
	        check.add(check4);
	        
	        check1.setFont(new Font("Serif", Font.BOLD, 30));
	        check2.setFont(new Font("Serif", Font.BOLD, 30));
	        check3.setFont(new Font("Serif", Font.BOLD, 30));
	        check4.setFont(new Font("Serif", Font.BOLD, 30));
			
			JLabel carte = new JLabel(" Carte :");
			carte.setFont(new Font("Serif", Font.BOLD, 40));
			JTextField textecarte = new JTextField (30);
			textecarte.setFont(new Font("Serif", Font.BOLD, 40));
			
			textecarte.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) {
			        char c = e.getKeyChar();
			        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
			            e.consume();  // ignorer l'événement
			        }
			     }
			});
			
			JLabel credits = new JLabel(" Credits :");
			credits.setFont(new Font("Serif", Font.BOLD, 40));
			JTextField textecredits = new JTextField (30);
			textecredits.setFont(new Font("Serif", Font.BOLD, 40));
			
			textecredits.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) {
			        char c = e.getKeyChar();
			        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
			            e.consume();  // ignorer l'événement
			        }
			     }
			});
			
			
			JPanel panelgauche = new JPanel (new GridLayout(7,1));
			JPanel paneldroite = new JPanel (new GridLayout(7,1));
			
			panelgauche.add(prenom);
			panelgauche.add(nom);
			panelgauche.add(mail);
			panelgauche.add(adresse);
			
			panelgauche.add(credits);
			panelgauche.add(carte);
			panelgauche.add(restrictions);
			
			paneldroite.add(texteprenom);
			paneldroite.add(textenom);
			paneldroite.add(textemail);
			paneldroite.add(texteadresse);
			
			paneldroite.add(textecredits);
			paneldroite.add(textecarte);
			paneldroite.add(check);
			
			fenetre_creer_compte_adulte.add(panelgauche, BorderLayout.WEST);
			fenetre_creer_compte_adulte.add(paneldroite, BorderLayout.CENTER);
			
		}
		else {
			fenetre_creer_compte_adulte.setVisible(true);
		}
	}
	
	static void fenetre_origine (boolean abo) {
		if (abo && Home_abo != null) {
			Home_abo.setVisible(true);
		}
		else if (!(abo) && Home_non_abo != null) {
			Home_non_abo.setVisible(true);
		}
		else {
			String titre;
			JLabel titrefen = new JLabel("AL 2000");
			titrefen.setFont(new Font("Serif", Font.BOLD, 50));
			
			JButton btncata = new JButton("catalogue");
			//JPanel panelcata = new JPanel (new BorderLayout());
			//panelcata.add(btncata,BorderLayout.CENTER);
			
			
			JButton btncreer = new JButton("creer un compte");
			//JPanel panelcreer = new JPanel (new BorderLayout());
			//panelcreer.add(btncreer,BorderLayout.CENTER);
			
			btncreer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if (abo) {
						Home_abo.setVisible(false);
						fenetre_creer_compte("CREER COMPTE",Home_abo);
					}
					else {
						Home_non_abo.setVisible(false);
						fenetre_creer_compte("CREER COMPTE",Home_non_abo);
					}
					//fenetre_infos = fenetre_creer_compte("CREER COMPTE",Home_abo);
				}
			});
			
			JButton btnrendre = new JButton ("rendre cd");
			
			
			JLabel image = new JLabel( new ImageIcon("D:\\Documents\\M1\\projet\\images\\pub.png"));
			JPanel panelima = new JPanel(new BorderLayout());
			panelima.add(titrefen,BorderLayout.NORTH);
			panelima.add(image,BorderLayout.CENTER);
			
			if (abo) {
				titre = "MENU ABONNE";
				Home_abo = new JFrame(titre);
				Home_abo.setSize(600,600);
				JButton btninfo = new JButton ("informations");
				//JPanel panelinfo = new JPanel (new BorderLayout());
				//panelinfo.add(btninfo, BorderLayout.CENTER);
				
				btninfo.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//fenetre.setVisible(false);
						Home_abo.setVisible(false);
						fenetre_informations("INFORMATIONS",Home_abo);
					}
				});
				
				
				JButton btndeco = new JButton("se deconnecter");
				//JPanel paneldeco = new JPanel (new BorderLayout());
				//paneldeco.add(btndeco,BorderLayout.CENTER);
				
				btndeco.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						/*fenetre.setVisible(false);
						fenetre_origine(false);*/
						Home_abo.setVisible(false);
						fenetre_origine(false);
					}
				});
				
				JPanel containerpanel = new JPanel(new GridLayout(5,1));
				containerpanel.add(btncata);
				containerpanel.add(btninfo);
				containerpanel.add(btncreer);
				containerpanel.add(btnrendre);
				containerpanel.add(btndeco);
				//fenetre.add(containerpanel,BorderLayout.WEST);
				Home_abo.add(containerpanel,BorderLayout.WEST);
				Home_abo.add(panelima,BorderLayout.CENTER);
				Home_abo.setLocationRelativeTo(null);
				Home_abo.setVisible(true);
			}
			else {
				titre = "MENU";
				Home_non_abo = new JFrame(titre);
				Home_non_abo.setSize(600,600);
				JButton btnco = new JButton("se connecter");
				JPanel panelco = new JPanel (new BorderLayout());
				panelco.add(btnco,BorderLayout.CENTER);
				
				btnco.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//fenetre.setVisible(false);
						Home_non_abo.setVisible(false);
						//JFrame fenetre2 = fenetresuivante("connexion",fenetre);
						if (fenetre_connexion == null) {
							System.out.println("ici");
							ActionListener effet = new ActionListener(){
								public void actionPerformed(ActionEvent e){
									//fenetre2.setVisible(false);
									fenetre_connexion.setVisible(false);
									fenetre_origine(true);
								}
							};
							fenetre_connexion = fenetresuivante("connexion",Home_non_abo,true,effet);
							JPanel containerpanel = new JPanel (new GridLayout(2,1));
							
							JLabel textehaut = new JLabel ("Inserez votre");
							JLabel textebas = new JLabel ("carte d'abonnement");
							textehaut.setFont(new Font("Serif", Font.BOLD, 50));
							textebas.setFont(new Font("Serif", Font.BOLD, 50));
							
							/*JPanel paneltextehaut = new JPanel (new BorderLayout());
							paneltextehaut.add(textehaut,BorderLayout.CENTER);
							JPanel paneltextebas = new JPanel (new BorderLayout());
							paneltextebas.add(textebas,BorderLayout.CENTER);*/
							
							containerpanel.add(textehaut);
							containerpanel.add(textebas);
							//fenetre2.add(containerpanel,BorderLayout.CENTER);
							fenetre_connexion.add(containerpanel,BorderLayout.CENTER);
							
							//bouton en attendant la vraie verification
							//JButton btnsuivant = new JButton("suivant");
							/*
							btnsuivant.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e){
									//fenetre2.setVisible(false);
									fenetre_connexion.setVisible(false);
									fenetre_origine(true);
								}
							});*/
							//fenetre2.add(btnsuivant,BorderLayout.EAST);
							//fenetre_connexion.add(btnsuivant,BorderLayout.EAST);
						}
						else {
							fenetre_connexion.setVisible(true);
						}
					}
				});
				
				JPanel containerpanel = new JPanel(new GridLayout(4,1));
				containerpanel.add(btncata);
				containerpanel.add(panelco);
				containerpanel.add(btncreer);
				containerpanel.add(btnrendre);
				//fenetre.add(containerpanel,BorderLayout.WEST);
				Home_non_abo.add(containerpanel, BorderLayout.WEST);
				Home_non_abo.add(containerpanel,BorderLayout.WEST);
				Home_non_abo.add(panelima,BorderLayout.CENTER);
				Home_non_abo.setLocationRelativeTo(null);
				Home_non_abo.setVisible(true);
			}
			
			/*
			JLabel image = new JLabel( new ImageIcon("D:\\Documents\\M1\\projet\\images\\pub.png"));
			JPanel panelima = new JPanel(new BorderLayout());
			panelima.add(titrefen,BorderLayout.NORTH);
			panelima.add(image,BorderLayout.CENTER);
			fenetre.add(panelima,BorderLayout.CENTER);
			fenetre.setLocationRelativeTo(null);
			fenetre.setVisible(true);*/
		}
	}
	
	public static void main(String[] args) {
		fenetre_origine(false);
	}

}
