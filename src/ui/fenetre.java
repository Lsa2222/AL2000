package ui;
import fc.FacadeTLI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class fenetre {
	static JFrame Home_non_abo = null;
	static JFrame Home_abo = null;
	//JFrame fenetre_precedente = null;
	static JFrame fenetre_infos = null;
	static JFrame fenetre_catalogue_abo = null;
	static JFrame fenetre_catalogue_non_abo = null;
	static JFrame fenetre_connexion = null;
	static JFrame fenetre_creer_compte_adulte = null;
	static JFrame fenetre_creer_compte_enfant = null;
	static JFrame fenetre_rendu_cd = null;
	static JFrame fenetre_succes_rendu_abo = null;
	static JFrame fenetre_succes_rendu_nonabo = null;
	static JFrame fenetre_loue_abo = null;
	static JFrame fenetre_loue_non_abo = null;
	static JFrame fenetre_recherche = null;
	//static JButton btnsuivant = new JButton("suivant");
	static FacadeTLI facade = new FacadeTLI();
	static HashSet<String> liste_films = new HashSet<String>();
	
	static void changesize(String image_origine, String image_redimension, int w, int h) throws IOException {
		// lit l'image d'entrée
	      File f = new File(image_origine);
	      BufferedImage inputImage = ImageIO.read(f);
	 
	      // crée l'image de sortie
	      BufferedImage img = new BufferedImage(w, h, inputImage.getType());
	 
	      // balancer l'image d'entrée à l'image de sortie
	      Graphics2D g = img.createGraphics();
	      g.drawImage(inputImage, 0, 0, w, h, null);
	      g.dispose();
	 
	      // extrait l'extension du fichier de sortie
	      String name = image_redimension.substring(image_redimension.lastIndexOf(".") + 1);
	 
	      // écrit dans le fichier de sortie
	      try {
			ImageIO.write(img, name, new File(image_redimension));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static JFrame fenetresuivante(String titre, JFrame fenetreprecedente,Boolean suivant, Boolean retour, ActionListener a) {
		JFrame fenetre = new JFrame(titre);
		fenetre.setSize(600,600);
		fenetre.setLocationRelativeTo(null);
		fenetreprecedente.setVisible(false);
		
		JButton btnretour = new JButton("retour");
		JPanel containersuite = new JPanel (new GridLayout(1,2));
		if (retour){
			btnretour.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					fenetreprecedente.setVisible(true);
					fenetre.setVisible(false);
				}
			});
			containersuite.add(btnretour);
		}
		
		if (suivant) {
			JButton btnsuivant = new JButton("suivant");
			btnsuivant.addActionListener(a);
			//System.out.println("ici2");
			//JPanel containersuite = new JPanel (new GridLayout(1,2));
			containersuite.add(btnsuivant);
		}
		fenetre.add(containersuite,BorderLayout.SOUTH);
		/*
		else {
			fenetre.add(btnretour,BorderLayout.SOUTH);
		}
		*/
		fenetre.setVisible(true);
		return fenetre;
	}
	
	static void fenetre_informations(String titre, JFrame fenetreprecedente) {
		fenetre_infos = fenetresuivante(titre, fenetreprecedente, false, true, null);
		
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
	
	static void fenetre_creer_compte(String titre, JFrame fenetreprecedente, boolean adulte) {
		if ((adulte && fenetre_creer_compte_adulte == null)||(!adulte && fenetre_creer_compte_enfant == null)) {
			
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
			
			JLabel nombre_films_max = new JLabel("Nb films / mois :");
			nombre_films_max.setFont(new Font("Serif", Font.BOLD, 40));
			JTextField texte_nombre_films_max = new JTextField (30);
			texte_nombre_films_max.setFont(new Font("Serif", Font.BOLD, 40));
			
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
			
			
			ActionListener effet = new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//fenetre2.setVisible(false);
					String prenom = texteprenom.getText();
					String nom = textenom.getText();
					int credits = Integer.parseInt(textecredits.getText());
					BigInteger cb;
					String mailclient = "";
					String adresseclient = "";
					int nbfilmsmax = 0;
					int res;
					
					if (adulte) {
						mailclient = textemail.getText();
						adresseclient = texteadresse.getText();
						cb = new BigInteger(textecarte.getText());
						res = facade.creerAbonne(prenom, nom, mailclient, adresseclient, credits, cb);
					}
					else {
						ArrayList<String> tags = new ArrayList<String>();
						if (check1.isSelected()) {
							tags.add(check1.getText());
						}
						if (check2.isSelected()) {
							tags.add(check2.getText());
						}
						if (check3.isSelected()) {
							tags.add(check3.getText());
						}
						if (check4.isSelected()) {
							tags.add(check4.getText());
						}
						nbfilmsmax = Integer.parseInt(texte_nombre_films_max.getText());
						res = facade.creerEnfant(prenom, nom, credits, tags, nbfilmsmax);
					}
					if (res == 2) {
						JOptionPane.showMessageDialog(fenetre_connexion, "Pas assez de crédits.");
					}
					else {
						if (adulte) {
							fenetre_creer_compte_adulte.setVisible(false);
						}
						else {
							fenetre_creer_compte_enfant.setVisible(false);
						}
						fenetre_origine(true);
					}
				}
			};
			
			if (adulte) {
				JPanel panelgauche = new JPanel (new GridLayout(6,1));
				JPanel paneldroite = new JPanel (new GridLayout(6,1));
				fenetre_creer_compte_adulte = fenetresuivante("connexion",Home_non_abo,true, true, effet);
				
				panelgauche.add(prenom);
				panelgauche.add(nom);
				panelgauche.add(mail);
				panelgauche.add(adresse);
				
				panelgauche.add(credits);
				panelgauche.add(carte);
				
				paneldroite.add(texteprenom);
				paneldroite.add(textenom);
				paneldroite.add(textemail);
				paneldroite.add(texteadresse);
				
				paneldroite.add(textecredits);
				paneldroite.add(textecarte);
				
				fenetre_creer_compte_adulte.add(panelgauche, BorderLayout.WEST);
				fenetre_creer_compte_adulte.add(paneldroite, BorderLayout.CENTER);
			}
			
			else {
				JPanel panelgauche = new JPanel (new GridLayout(7,1));
				JPanel paneldroite = new JPanel (new GridLayout(7,1));
				fenetre_creer_compte_enfant = fenetresuivante("connexion",Home_abo,true,true,effet);
				
				panelgauche.add(prenom);
				panelgauche.add(nom);
				panelgauche.add(mail);
				panelgauche.add(adresse);
				
				panelgauche.add(credits);
				panelgauche.add(nombre_films_max);
				panelgauche.add(restrictions);
				
				paneldroite.add(texteprenom);
				paneldroite.add(textenom);
				paneldroite.add(textemail);
				paneldroite.add(texteadresse);
				
				paneldroite.add(textecredits);
				panelgauche.add(texte_nombre_films_max);
				paneldroite.add(check);
				
				fenetre_creer_compte_enfant.add(panelgauche, BorderLayout.WEST);
				fenetre_creer_compte_enfant.add(paneldroite, BorderLayout.CENTER);
			}
		}
		else {
			if (adulte) {
				fenetre_creer_compte_adulte.setVisible(true);
			}
			else {
				fenetre_creer_compte_enfant.setVisible(true);
			}
		}
	}
	
	static ActionListener fonction_louer(Boolean abo, ArrayList<String> s, JFrame fenetre, Boolean qr) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.out.println(facade.film_dispo(s.get(0)));
				if (facade.film_dispo(s.get(0))){
					if (abo) {
						fenetre.setVisible(false);
					}
					else {
						fenetre.setVisible(false);
					}
					//fenetre_catalogue.setVisible(false);
					ActionListener suivant = new ActionListener() {
    					public void actionPerformed(ActionEvent e){
    						if (abo) {
    							fenetre_loue_abo.setVisible(false);
    							Home_abo.setVisible(true);
    						}
    						else {
    							fenetre_loue_non_abo.setVisible(false);
    							Home_non_abo.setVisible(true);
    						}
    					}
					};
					/*if (abo) {
						JFrame fenetre2 = fenetresuivante("fenetreloue",fenetre_catalogue_abo,true, false, suivant);
					}
					else {
						JFrame fenetre2 = fenetresuivante("fenetreloue",fenetre_catalogue_non_abo,true, false, suivant);
					}*/
    				//JFrame fenetre2 = fenetresuivante("fenetreloue",fenetre,true, false, suivant);
    				//Initialisation
    				JTextArea texte = new JTextArea("Le film a bien été loué.");
    				 
    				// Pour un retour à ligne automatique
    				texte.setLineWrap(true);
    				  
    				// Pour que les mots ne soient pas coupés
    				texte.setWrapStyleWord(true);
    				
    				texte.setFont(new Font("Serif", Font.BOLD, 40));
    				
    				//fenetre2.add(texte,BorderLayout.CENTER);
    				int res = 0;
    				if (!qr) {
    					res = facade.louer_Br(s.get(0));
    				}
    				if (qr) {
    					res = facade.louer_Qr(s.get(0));
    				}
    				System.out.println(res);
    				if (abo && res ==1) {
    					fenetre_loue_abo = fenetresuivante("fenetreloue",fenetre_catalogue_abo,true, false, suivant);
    					fenetre_loue_abo.add(texte,BorderLayout.CENTER);
	    				fenetre_loue_abo.setVisible(true);
    				}
    				else if(!abo && res == 1){
    					fenetre_loue_non_abo = fenetresuivante("fenetreloue",fenetre_catalogue_non_abo,true, false, suivant);
    					fenetre_loue_non_abo.add(texte,BorderLayout.CENTER);
	    				fenetre_loue_non_abo.setVisible(true);
    				}
    				else if(res != 1) {
    					fenetre.setVisible(true);
    					String erreur = "";
    					if (res == 2) {
    						erreur = "Pas assez d'argent / credits.";
    					}
    					else if (res == 3) {
    						erreur = "Trois films ont déjà été loués.";
    					}
    					else if (res == 4) {
    						erreur = "Vos restrictions vous empêchent de réaliser cette action.";
    					}
    					
    					//JDialog message_erreur = new JDialog(fenetre,erreur);
    					JOptionPane.showMessageDialog(fenetre, erreur);
    				}
				}
			}
		};
	}
	
	static ActionListener fonction_bouton_voir(Boolean abo,Boolean recherche, ArrayList<String> s, String image_origine, String image_pour_affiche) {
		ActionListener voir = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFrame fenetre;
				/*JFrame fenetre_pour_abo = fenetresuivante(s.get(0),fenetre_catalogue_abo,false,true,null);
				JFrame fenetre_pour_pas_abo = fenetresuivante(s.get(0),fenetre_catalogue_abo,false,true,null);*/
				if (abo && recherche == false) {
					fenetre_catalogue_abo.setVisible(false);
    				fenetre = fenetresuivante(s.get(0),fenetre_catalogue_abo,false,true,null);
					//fenetre_loue_abo = fenetresuivante(s.get(0),fenetre_catalogue_abo,false,true,null);
				}
				else if(!abo && recherche == false) {
					fenetre_catalogue_non_abo.setVisible(false);
    				fenetre = fenetresuivante(s.get(0),fenetre_catalogue_non_abo,false,true,null);
					//fenetre_loue_non_abo = fenetresuivante(s.get(0),fenetre_catalogue_non_abo,false,true,null);
				}
				else {
					System.out.println("enlevefenetrerecherche");
					fenetre_recherche.setVisible(false);
					fenetre = fenetresuivante(s.get(0),fenetre_recherche,false,true,null);
				}
				/*fenetre_catalogue.setVisible(false);
				JFrame fenetre = fenetresuivante(s.get(0),fenetre_catalogue,false,true,null);*/
				// Création panels
				JPanel panel_location = new JPanel(new GridLayout(1,2));
				JPanel panel_realrest = new JPanel(new GridLayout(2,1));
				JPanel panel_afmilieu = new JPanel(new GridLayout(1,2));
				JPanel panel_centre   = new JPanel(new GridLayout(3,1));
				// get info
				JLabel film_titre = new JLabel(s.get(0));
				JLabel film_real  = new JLabel(s.get(1));
				//JLabel film_descr = new JLabel(s.get(2));
				//Initialisation
				JTextArea film_descr = new JTextArea(s.get(2));
				 
				// Pour un retour à ligne automatique
				film_descr.setLineWrap(true);
				  
				// Pour que les mots ne soient pas coupés
				film_descr.setWrapStyleWord(true);
				
				try {
					changesize(image_origine, image_pour_affiche, 200, 200);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JLabel film_image = new JLabel(new ImageIcon(image_pour_affiche));
				String str_restr = new String(s.get(4));

				JLabel film_restr = new JLabel(str_restr);
				JButton btn_BR = new JButton("Location BluRay");
				JButton btn_QR = new JButton("Location QR Code");
				
				
				if (!facade.film_dispo(s.get(0))) {
					btn_BR.setBackground(Color.GRAY);
					btn_BR.setText("film non disponible");
				}
				btn_BR.addActionListener(fonction_louer(abo,s,fenetre,false));
				btn_QR.addActionListener(fonction_louer(abo,s,fenetre,true));
				
				// add
				panel_realrest.add(film_real);
				panel_realrest.add(film_restr);

				panel_location.add(btn_BR);
				panel_location.add(btn_QR);
				                  
				panel_afmilieu.add(film_image);
				panel_afmilieu.add(panel_realrest);

				panel_centre.add(panel_afmilieu);
				panel_centre.add(film_descr);
				panel_centre.add(panel_location);
				
				if(abo) {
					//JFrame fenetre = fenetresuivante(s.get(0),fenetre_catalogue_abo,false,true,null);
					fenetre.add(film_titre, BorderLayout.NORTH);
    				fenetre.add(panel_centre, BorderLayout.CENTER);
				}
				else {
					//JFrame fenetre = fenetresuivante(s.get(0),fenetre_catalogue_non_abo,false,true,null);
					fenetre.add(film_titre, BorderLayout.NORTH);
    				fenetre.add(panel_centre, BorderLayout.CENTER);
				}
				/*fenetre.add(film_titre, BorderLayout.NORTH);
				fenetre.add(panel_centre, BorderLayout.CENTER);*/
			}
		};
	return voir;
	}
	
	static void cree_fenetre_catalogue(String titre, JFrame fenetreprecedente, Boolean abo) {
		//realisateur, titre, description, image, liste restrictions
		if((abo && fenetre_catalogue_abo == null)|| (!abo && fenetre_catalogue_non_abo == null)) {
			if (abo) {
				fenetre_catalogue_abo = fenetresuivante(titre, fenetreprecedente,false, true, null);
			}
			else {
				fenetre_catalogue_non_abo = fenetresuivante(titre, fenetreprecedente,false, true, null);
			}
			
			JLabel recherche = new JLabel(" Rechercher :");
			recherche.setFont(new Font("Serif", Font.BOLD, 40));
			JTextField texterecherche = new JTextField (30);
			texterecherche.setFont(new Font("Serif", Font.BOLD, 40));
			String icone_origine = "D:\\Documents\\M1\\projet\\icones\\recherche.png";
			String icone_redimension = "D:\\Documents\\M1\\projet\\icones\\recherchebon.png";
			try {
				changesize(icone_origine, icone_redimension, 50, 50);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Icon icon = new ImageIcon(icone_redimension);
			JButton btnrecherche = new JButton (icon);
			
			
			JPanel panelhaut = new JPanel (new GridLayout(1,2));
			panelhaut.add(recherche);
			panelhaut.add(texterecherche);
			//panelhaut.add(btnrecherche);
			JPanel panel_haut_final = new JPanel(new BorderLayout());
			panel_haut_final.add(panelhaut, BorderLayout.CENTER);
			panel_haut_final.add(btnrecherche, BorderLayout.EAST);
			if (abo) {
				fenetre_catalogue_abo.add(panel_haut_final,BorderLayout.NORTH);
			}
			else {
				fenetre_catalogue_non_abo.add(panel_haut_final,BorderLayout.NORTH);
			}
			//fenetre_catalogue.add(panel_haut_final,BorderLayout.NORTH);
			
			ArrayList<ArrayList<String>> liste_films = facade.getfilm();
			//System.out.println(liste_films.size());
			
			JPanel containerpanel = new JPanel (new GridLayout(liste_films.size(),1));
			System.out.println(liste_films);
			
			btnrecherche.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e){
    				String recherche_utilisateur = texterecherche.getText();
    				for(ArrayList<String> s : liste_films) {
    					System.out.println(s.get(0).toLowerCase());
    					System.out.println(recherche_utilisateur);
    					System.out.println(s.get(0).toLowerCase() == recherche_utilisateur.toLowerCase());
    					//fenetre_recherche = new JFrame();
    					//if (s.get(0).toLowerCase() == recherche_utilisateur) {}
    					if (s.get(0).equalsIgnoreCase(recherche_utilisateur)) {
    						System.out.println("recherche ok");
    						if (abo) {
    							fenetre_catalogue_abo.setVisible(false);
    						}
    						else {
    							fenetre_catalogue_non_abo.setVisible(false);
    						}
    						String fichier = s.get(3);
    			        	String fichier2 = fichier.substring(5,fichier.length()-4) + "bon.jpg";
    			        	String fichier3 = fichier.substring(5,fichier.length()-4) + "affiche.jpg";
    			        	fichier = fichier.substring(5,fichier.length());
    			        	//String fichier = s.get(0) + ".jpg";
    			        	//String fichier2 = s.get(0) + "bon.jpg";
    			        	JButton btnvoir = new JButton("Voir");
    			        	
    			        	
    			        	String image_origine = "D:\\Documents\\M1\\projet\\images\\"+ fichier;
    			        	String image_redimension = "D:\\Documents\\M1\\projet\\images\\"+ fichier2;
    			        	String image_pour_affiche = "D:\\Documents\\M1\\projet\\images\\"+ fichier3;
    						btnvoir.addActionListener(fonction_bouton_voir(abo,true,s,image_origine,image_pour_affiche));
    						
    						try {
    							changesize(image_origine, image_redimension, 300, 300);
    						} catch (IOException e2) {
    							// TODO Auto-generated catch block
    							//System.out.println(image_origine);
    							e2.printStackTrace();
    						}
    			        	JLabel image = new JLabel( new ImageIcon(image_redimension));
    			        	
    			        	JPanel souscontainer = new JPanel (new GridLayout(1,2));
    			        	souscontainer.add(image);
    			        	souscontainer.add(btnvoir);
    			        	/*containerpanel.add(image);
    			        	containerpanel.add(btnvoir);*/
    			        	if (abo) {
    			        		fenetre_recherche = fenetresuivante("fenetre recherche",fenetre_catalogue_abo,false,true,null);
    			        	}
    			        	else {
    			        		fenetre_recherche = fenetresuivante("fenetre recherche",fenetre_catalogue_non_abo,false,true,null);
    			        	}
    			        	fenetre_recherche.add(souscontainer,BorderLayout.CENTER);
    					}
    				}
    			}
    		});
			
			//Iterator<String> i = liste_films.iterator(); 
	        for(ArrayList<String> s : liste_films) {
	        	//System.out.println(s);
	        	//System.out.println("ici2");
	        	//System.out.println(s.get(3));
	        	String fichier = s.get(3);
	        	String fichier2 = fichier.substring(5,fichier.length()-4) + "bon.jpg";
	        	String fichier3 = fichier.substring(5,fichier.length()-4) + "affiche.jpg";
	        	fichier = fichier.substring(5,fichier.length());
	        	//String fichier = s.get(0) + ".jpg";
	        	//String fichier2 = s.get(0) + "bon.jpg";
	        	JButton btnvoir = new JButton("Voir");
	        	
	        	
	        	String image_origine = "D:\\Documents\\M1\\projet\\images\\"+ fichier;
	        	String image_redimension = "D:\\Documents\\M1\\projet\\images\\"+ fichier2;
	        	String image_pour_affiche = "D:\\Documents\\M1\\projet\\images\\"+ fichier3;
	        	
	        	
	        	btnvoir.addActionListener(fonction_bouton_voir(abo,false,s,image_origine,image_pour_affiche));
	        			/*new ActionListener(){
	    			public void actionPerformed(ActionEvent e){
	    				JFrame fenetre;
	    				if (abo) {
	    					fenetre_catalogue_abo.setVisible(false);
		    				fenetre = fenetresuivante(s.get(0),fenetre_catalogue_abo,false,true,null);
	    					//fenetre_loue_abo = fenetresuivante(s.get(0),fenetre_catalogue_abo,false,true,null);
	    				}
	    				else {
	    					fenetre_catalogue_non_abo.setVisible(false);
		    				fenetre = fenetresuivante(s.get(0),fenetre_catalogue_non_abo,false,true,null);
	    					//fenetre_loue_non_abo = fenetresuivante(s.get(0),fenetre_catalogue_non_abo,false,true,null);
	    				}
	    				// Création panels
	    				JPanel panel_location = new JPanel(new GridLayout(1,2));
	    				JPanel panel_realrest = new JPanel(new GridLayout(2,1));
	    				JPanel panel_afmilieu = new JPanel(new GridLayout(1,2));
	    				JPanel panel_centre   = new JPanel(new GridLayout(3,1));
	    				// get info
	    				JLabel film_titre = new JLabel(s.get(0));
	    				JLabel film_real  = new JLabel(s.get(1));
	    				//JLabel film_descr = new JLabel(s.get(2));
	    				//Initialisation
	    				JTextArea film_descr = new JTextArea(s.get(2));
	    				 
	    				// Pour un retour à ligne automatique
	    				film_descr.setLineWrap(true);
	    				  
	    				// Pour que les mots ne soient pas coupés
	    				film_descr.setWrapStyleWord(true);
	    				
	    				try {
							changesize(image_origine, image_pour_affiche, 200, 200);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	    				
	    				JLabel film_image = new JLabel(new ImageIcon(image_pour_affiche));
	    				String str_restr = new String(s.get(4));
	
	    				JLabel film_restr = new JLabel(str_restr);
	    				JButton btn_BR = new JButton("Location BluRay");
	    				JButton btn_QR = new JButton("Location QR Code");
	    				
	    				ActionListener loue = new ActionListener() {
	    					public void actionPerformed(ActionEvent e){
	    						if (abo) {
	    							fenetre.setVisible(false);
	    						}
	    						else {
	    							fenetre.setVisible(false);
	    						}
	    						//fenetre_catalogue.setVisible(false);
	    						ActionListener suivant = new ActionListener() {
	    	    					public void actionPerformed(ActionEvent e){
	    	    						if (abo) {
	    	    							fenetre_loue_abo.setVisible(false);
	    	    							Home_abo.setVisible(true);
	    	    						}
	    	    						else {
	    	    							fenetre_loue_non_abo.setVisible(false);
	    	    							Home_non_abo.setVisible(true);
	    	    						}
	    	    					}
	    						};
	    	    				//JFrame fenetre2 = fenetresuivante("fenetreloue",fenetre,true, false, suivant);
	    	    				//Initialisation
	    	    				JTextArea texte = new JTextArea("Le film a bien été loué.");
	    	    				 
	    	    				// Pour un retour à ligne automatique
	    	    				texte.setLineWrap(true);
	    	    				  
	    	    				// Pour que les mots ne soient pas coupés
	    	    				texte.setWrapStyleWord(true);
	    	    				
	    	    				texte.setFont(new Font("Serif", Font.BOLD, 40));
	    	    				
	    	    				//fenetre2.add(texte,BorderLayout.CENTER);
	    	    				
	    	    				if (abo) {
	    	    					fenetre_loue_abo = fenetresuivante("fenetreloue",fenetre_catalogue_abo,true, false, suivant);
	    	    					fenetre_loue_abo.add(texte,BorderLayout.CENTER);
		    	    				fenetre_loue_abo.setVisible(true);
	    	    				}
	    	    				else {
	    	    					fenetre_loue_non_abo = fenetresuivante("fenetreloue",fenetre_catalogue_non_abo,true, false, suivant);
	    	    					fenetre_loue_non_abo.add(texte,BorderLayout.CENTER);
		    	    				fenetre_loue_non_abo.setVisible(true);
	    	    				}
	    	    			}
	    				};
	    				
	    				btn_BR.addActionListener(loue);
	    				btn_QR.addActionListener(loue);
	    				
	    				// add
	    				panel_realrest.add(film_real);
	    				panel_realrest.add(film_restr);
	
	    				panel_location.add(btn_BR);
	    				panel_location.add(btn_QR);
	    				                  
	    				panel_afmilieu.add(film_image);
	    				panel_afmilieu.add(panel_realrest);
	
	    				panel_centre.add(panel_afmilieu);
	    				panel_centre.add(film_descr);
	    				panel_centre.add(panel_location);
	    				
	    				if(abo) {
	    					//JFrame fenetre = fenetresuivante(s.get(0),fenetre_catalogue_abo,false,true,null);
	    					fenetre.add(film_titre, BorderLayout.NORTH);
		    				fenetre.add(panel_centre, BorderLayout.CENTER);
	    				}
	    				else {
	    					//JFrame fenetre = fenetresuivante(s.get(0),fenetre_catalogue_non_abo,false,true,null);
	    					fenetre.add(film_titre, BorderLayout.NORTH);
		    				fenetre.add(panel_centre, BorderLayout.CENTER);
	    				}
	    			}
	    		});*/
	        	
	        	
	        	try {
					changesize(image_origine, image_redimension, 300, 300);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//System.out.println(image_origine);
					e.printStackTrace();
				}
	        	JLabel image = new JLabel( new ImageIcon(image_redimension));
	        	
	        	JPanel souscontainer = new JPanel (new GridLayout(1,2));
	        	souscontainer.add(image);
	        	souscontainer.add(btnvoir);
	        	/*containerpanel.add(image);
	        	containerpanel.add(btnvoir);*/
	        	containerpanel.add(souscontainer);
	        }
	        if (abo) {
	        	fenetre_catalogue_abo.add(containerpanel,BorderLayout.CENTER);
	        	JScrollPane barre = new JScrollPane(containerpanel);
		        fenetre_catalogue_abo.add(barre);
	        }
	        else {
	        	fenetre_catalogue_non_abo.add(containerpanel,BorderLayout.CENTER);
	        	JScrollPane barre = new JScrollPane(containerpanel);
		        fenetre_catalogue_non_abo.add(barre);
	        }
	        /*
	        fenetre_catalogue.add(containerpanel,BorderLayout.CENTER);
	        JScrollPane barre = new JScrollPane(containerpanel);
	        //getContentPane().add(barre);
	        fenetre_catalogue.add(barre);
	        */
		}
	else {
		if (abo) {
			fenetre_catalogue_abo.setVisible(true);
		}
		else {
			fenetre_catalogue_non_abo.setVisible(true);
		}
		//fenetre_catalogue.setVisible(true);
	}
	}
	
	
	public static void fenetre_origine (boolean abo) {
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
						fenetre_creer_compte("CREER COMPTE",Home_abo, false);
					}
					else {
						Home_non_abo.setVisible(false);
						fenetre_creer_compte("CREER COMPTE",Home_non_abo,true);
					}
					//fenetre_infos = fenetre_creer_compte("CREER COMPTE",Home_abo);
				}
			});
			
			JButton btnrendre = new JButton ("rendre cd");
			btnrendre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (abo) {
						Home_abo.setVisible(false);
					}
					else {
						Home_non_abo.setVisible(false);
					}

					if ((abo && (fenetre_succes_rendu_abo == null))||(!abo && (fenetre_succes_rendu_nonabo == null))) {
						ActionListener effet = new ActionListener(){
							public void actionPerformed(ActionEvent e){
								if (facade.rendre() == 1) {
									fenetre_rendu_cd.setVisible(false);
									JFrame fenetre = new JFrame();
									fenetre.setSize(600,600);
									fenetre.setLocationRelativeTo(null);
									JButton btnsuivant = new JButton("suivant");
									
									if (abo) {
										fenetre_succes_rendu_abo = fenetre;
										btnsuivant.addActionListener(new ActionListener(){
											public void actionPerformed(ActionEvent e){
												System.out.println("1");
												fenetre_succes_rendu_abo.setVisible(false);
												Home_abo.setVisible(true);
											}
										});
										fenetre_succes_rendu_abo.add(btnsuivant,BorderLayout.SOUTH);
										JLabel texte = new JLabel ("CD rendu avec succes.");
										texte.setFont(new Font("Serif", Font.BOLD, 50));
										fenetre_succes_rendu_abo.add(texte,BorderLayout.CENTER);
										fenetre_succes_rendu_abo.setVisible(true);
									}
									else {
										fenetre_succes_rendu_nonabo = fenetre;
										btnsuivant.addActionListener(new ActionListener(){
											public void actionPerformed(ActionEvent e){
												System.out.println("2");
												fenetre_succes_rendu_nonabo.setVisible(false);
												Home_non_abo.setVisible(true);
											}
										});
										fenetre_succes_rendu_nonabo.add(btnsuivant,BorderLayout.SOUTH);
										JLabel texte = new JLabel ("CD rendu avec succes.");
										texte.setFont(new Font("Serif", Font.BOLD, 50));
										fenetre_succes_rendu_nonabo.add(texte,BorderLayout.CENTER);
										fenetre_succes_rendu_nonabo.setVisible(true);
									}
								}
								else if (facade.rendre() == 2) {
									JOptionPane.showMessageDialog(fenetre_rendu_cd, "Pas assez de crédits pour rendre le film.");
								}
								else if (facade.rendre() == 3) {
									JOptionPane.showMessageDialog(fenetre_rendu_cd, "Pas assez de crédits pour rendre le film.");
								}
							}
						};
						fenetre_rendu_cd = fenetresuivante("rendre cd",Home_non_abo,true,true,effet);
						JPanel containerpanel = new JPanel (new GridLayout(2,1));
						
						JLabel textehaut = new JLabel ("Inserez le CD");
						JLabel textebas = new JLabel ("que vous voulez rendre.");
						textehaut.setFont(new Font("Serif", Font.BOLD, 50));
						textebas.setFont(new Font("Serif", Font.BOLD, 50));
						
						
						containerpanel.add(textehaut);
						containerpanel.add(textebas);
						
						fenetre_rendu_cd.add(containerpanel,BorderLayout.CENTER);
					}
					else {
						fenetre_rendu_cd.setVisible(true);
					}
				}
			});
			
			
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
				
				btncata.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						Home_abo.setVisible(false);
						System.out.println(abo);
						cree_fenetre_catalogue("Catalogue",Home_abo,abo);
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
						Home_non_abo.setVisible(false);

						if (fenetre_connexion == null) {
							System.out.println("ici");
							ActionListener effet = new ActionListener(){
								public void actionPerformed(ActionEvent e){
									//fenetre2.setVisible(false);
									int res = facade.connection();
									if (res!=0) {//1 adulte 2 enfant
										fenetre_connexion.setVisible(false);
										fenetre_origine(true);
									}
									else {
										JOptionPane.showMessageDialog(fenetre_connexion, "Veuillez rentrer votre carte.");
									}
								}
							};
							fenetre_connexion = fenetresuivante("connexion",Home_non_abo,true,true,effet);
							JPanel containerpanel = new JPanel (new GridLayout(2,1));
							
							JLabel textehaut = new JLabel ("Inserez votre");
							JLabel textebas = new JLabel ("carte d'abonnement");
							textehaut.setFont(new Font("Serif", Font.BOLD, 50));
							textebas.setFont(new Font("Serif", Font.BOLD, 50));
							
							
							containerpanel.add(textehaut);
							containerpanel.add(textebas);
							
							fenetre_connexion.add(containerpanel,BorderLayout.CENTER);
						}
						else {
							fenetre_connexion.setVisible(true);
						}
					}
				});
				
				btncata.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						Home_non_abo.setVisible(false);
						cree_fenetre_catalogue("Catalogue",Home_non_abo,abo);
					}
				});
				
				JPanel containerpanel = new JPanel(new GridLayout(4,1));
				containerpanel.add(btncata);
				containerpanel.add(panelco);
				containerpanel.add(btncreer);
				containerpanel.add(btnrendre);

				Home_non_abo.add(containerpanel, BorderLayout.WEST);
				Home_non_abo.add(containerpanel,BorderLayout.WEST);
				Home_non_abo.add(panelima,BorderLayout.CENTER);
				Home_non_abo.setLocationRelativeTo(null);
				Home_non_abo.setVisible(true);
			}
		}
	}
	/*
	public static void main(String[] args) {
		liste_films.add("blade_runner");
        liste_films.add("joker"); 
        liste_films.add("rebelle");
        liste_films.add("titanic");
        liste_films.add("ça");
        liste_films.add("novembre");
		fenetre_origine(false);
	}*/
}
