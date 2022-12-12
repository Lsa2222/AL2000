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
	static JFrame fenetre_catalogue_global = null;
	static JFrame fenetre_connexion = null;
	static JFrame fenetre_creer_compte_adulte = null;
	static JFrame fenetre_creer_compte_enfant = null;
	static JFrame fenetre_rendu_cd = null;
	static JFrame fenetre_succes_rendu_abo = null;
	static JFrame fenetre_succes_rendu_nonabo = null;
	static JFrame fenetre_loue_abo = null;
	static JFrame fenetre_loue_non_abo = null;
	static JFrame fenetre_recherche = null;
	static JFrame fenetre_recherche_global = null;
	static JFrame fenetre_reserve_abo = null;
	static FacadeTLI facade = new FacadeTLI();
	static HashSet<String> liste_films = new HashSet<String>();
	
	static void changesize(String image_origine, String image_redimension, int w, int h) throws IOException {
		  //fonction qui permet de changer la taille d'une image afin qu'elle rentre bien pour notre catalogue
		  //char lettre1 = image_origine
		  if (image_origine.substring(image_origine.length()-3,image_origine.length()) == "jpg") {
			// lit l'image d'entrée
			  System.out.println(image_origine);
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
				e.printStackTrace();
			}
		  }
	}
	
	static JFrame fenetresuivante(String titre, JFrame fenetreprecedente,Boolean suivant, Boolean retour, ActionListener a) {
		//fonction qui permet de créer une nouvelle fenetre, avec un titre titre,
		//un boolean suivant et retour permettant de savoir si on a besoin d'un bouton retour et/ou suivant,
		//une fenetreprecedente qui sera pour le bouton retour
		//un actionlistener qui sera pour la fonction du bouton suivant
		
		//on crée une nouvelle fenetre de la bonne taille et au centre
		JFrame fenetre = new JFrame(titre);
		fenetre.setSize(600,600);
		fenetre.setLocationRelativeTo(null);
		fenetreprecedente.setVisible(false);
		
		JButton btnretour = new JButton("retour");
		JPanel containersuite = new JPanel (new GridLayout(1,2));
		//suivant le boolean on crée notre bouton retour
		if (retour){
			btnretour.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					fenetreprecedente.setVisible(true);
					fenetre.setVisible(false);
				}
			});
			containersuite.add(btnretour);
		}
		//idem pour le bouton suivant, avec le actionlistener
		if (suivant) {
			JButton btnsuivant = new JButton("suivant");
			btnsuivant.addActionListener(a);
			containersuite.add(btnsuivant);
		}
		fenetre.add(containersuite,BorderLayout.SOUTH);
		fenetre.setVisible(true);
		return fenetre;
	}
	
	static void fenetre_informations(String titre, JFrame fenetreprecedente) {
		//on crée une fentre de base
		//la fenetre informations est recalculée à chaque fois afin que si l'utilisateur a recharger son compte
		//se soit remis à jour automatiquement
		fenetre_infos = fenetresuivante(titre, fenetreprecedente, false, true, null);
		
		// Recuperation des informations 
		ArrayList<String> information = new ArrayList<String>();
		information = facade.info();
		// Recuperation
		ArrayList<String> restrictions_list = facade.TagAbonne();
		
		//on crée nos panels afin de rajouter les informations de l'utilisateur dedans,
		//ainsi qu'un bouton permettant de recharger le compte et un bouton pour afficher l'historique
		JPanel souscontainerpanel = new JPanel (new GridLayout(3,1));
		
		JLabel textehaut = new JLabel (" Credits :");
		//récupérer les vrais crédits de l'abonné
		String argent = information.get(4) + " EUR";
		System.out.println(argent);
		JLabel textebas = new JLabel (argent);
		textehaut.setFont(new Font("Serif", Font.BOLD, 40));
		textebas.setFont(new Font("Serif", Font.BOLD, 40));
		
		JPanel paneltextehaut = new JPanel (new BorderLayout());
		paneltextehaut.add(textehaut,BorderLayout.CENTER);
		JPanel paneltextebas = new JPanel (new BorderLayout());
		paneltextebas.add(textebas,BorderLayout.CENTER);
		
		souscontainerpanel.add(paneltextehaut);
		souscontainerpanel.add(paneltextebas);
		
		//faire un actionlistener pour bien recharger le compte
		JButton btn_recharge = new JButton("recharger compte");
		btn_recharge.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fenetre_infos.setVisible(false);
				JFrame fenetre = new JFrame(titre);
				fenetre.setSize(600,600);
				fenetre.setLocationRelativeTo(null);
				fenetreprecedente.setVisible(false);
				
				JLabel montant = new JLabel(" Montant :");
				montant.setFont(new Font("Serif", Font.BOLD, 40));
				JTextField textemontant = new JTextField();
				textemontant.setFont(new Font("Serif", Font.BOLD, 40));
				
				textemontant.addKeyListener(new KeyAdapter() {
				    public void keyTyped(KeyEvent e) {
				        char c = e.getKeyChar();
				        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
				            e.consume();  // ignorer l'événement
				        }
				     }
				});
				
				JButton btnretour = new JButton("retour");
				JPanel containersuite = new JPanel (new GridLayout(1,2));
				//suivant le boolean on crée notre bouton retour
				btnretour.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						fenetre_informations("fenetreinformations",Home_abo);
						fenetre.setVisible(false);
					}
				});
				containersuite.add(btnretour);
				//idem pour le bouton suivant, avec le actionlistener
				JButton btnsuivant = new JButton("suivant");
				btnsuivant.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String argent = textemontant.getText();
						int argent_final = Integer.parseInt(argent);
						//System.out.println(argent_final);
						facade.recharger(argent_final);
						fenetre.setVisible(false);
						Home_abo.setVisible(true);
					}
				});
				containersuite.add(btnsuivant);
				
				JPanel panelcentre = new JPanel(new GridLayout(1,2));
				panelcentre.add(montant);
				panelcentre.add(textemontant);
				
				fenetre.add(containersuite,BorderLayout.SOUTH);
				fenetre.add(panelcentre,BorderLayout.CENTER);
				fenetre.setVisible(true);
			}
		});
		
		JPanel containerpanelgauche = new JPanel (new GridLayout(2,1));
		
		//faire un actionlistener pour afficher l'historique
		JButton btnhistorique = new JButton ("historique");
		JLabel restrictionshaut = new JLabel (" Restrictions :");
		//récupérer les vraies restrictions
		String restrictions = "";
		for (String restriction : restrictions_list) {
			restrictions = restrictions + restriction;
		}
		//String restrictions = " tag1, tag2, tag3";
		JLabel restrictionsbas = new JLabel (restrictions);
		restrictionshaut.setFont(new Font("Serif", Font.BOLD, 40));
		restrictionsbas.setFont(new Font("Serif", Font.BOLD, 40));
		
		JPanel souscontainerpanel2 = new JPanel(new GridLayout(3,1));
		souscontainerpanel2.add(restrictionshaut);
		souscontainerpanel2.add(restrictionsbas);
		
		containerpanelgauche.add(souscontainerpanel);
		containerpanelgauche.add(souscontainerpanel2);
		
		JPanel containerpaneldroite = new JPanel (new GridLayout(2,1));
		containerpaneldroite.add(btn_recharge);
		containerpaneldroite.add(btnhistorique);
		
		fenetre_infos.add(containerpanelgauche,BorderLayout.WEST);
		fenetre_infos.add(containerpaneldroite,BorderLayout.EAST);
	}
	
	static void fenetre_creer_compte(String titre, JFrame fenetreprecedente, boolean adulte) {
		//si la fenetre n'est pas déjà créée
		if ((adulte && fenetre_creer_compte_adulte == null)||(!adulte && fenetre_creer_compte_enfant == null)) {
			//on crée la fenetre avec des JLabel pour les infos demandées
			//des JTextField pour que l'utilisateur remplisse ses infos, ou JCheckBox pour les restrictions
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
			
			ArrayList<String> tags = facade.listeTag();
			ArrayList<JCheckBox> liste_checkbox = new ArrayList<JCheckBox>();
			
			//récupérer les tags
			for(String tag : tags) {
				System.out.println(tag);
				liste_checkbox.add(new JCheckBox(tag));
			}

	        JPanel check = new JPanel (new GridLayout(tags.size(),1));
	        
	        for(JCheckBox box : liste_checkbox) {
				check.add(box);
				box.setFont(new Font("Serif", Font.BOLD, 10));
			}
	        System.out.println(tags);
			
			JLabel carte = new JLabel(" Carte :");
			carte.setFont(new Font("Serif", Font.BOLD, 40));
			JTextField textecarte = new JTextField (30);
			textecarte.setFont(new Font("Serif", Font.BOLD, 40));
			
			//on vérifie que l'utilisateur ne tape que des chiffres ou le bouton supprimer pour le texte de la carte
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
			
			//idem pour les credits
			textecredits.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) {
			        char c = e.getKeyChar();
			        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
			            e.consume();  // ignorer l'événement
			        }
			     }
			});
			
			//pour le actionlistener du bouton suivant
			//on récupère les infos que l'utilisateur a rentré
			ActionListener effet = new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String prenom = texteprenom.getText();
					String nom = textenom.getText();
					int credits = Integer.parseInt(textecredits.getText());
					BigInteger cb;
					String mailclient = "";
					String adresseclient = "";
					int nbfilmsmax = 0;
					int res;
					
					//si c'est pour un compte adulte, on crée le compte avec les informations correspondantes
					if (adulte) {
						mailclient = textemail.getText();
						adresseclient = texteadresse.getText();
						cb = new BigInteger(textecarte.getText());
						res = facade.creerAbonne(prenom, nom, mailclient, adresseclient, credits, cb);
					}
					//idem pour un compte enfant
					else {
						ArrayList<String> tags = new ArrayList<String>();
						for(JCheckBox box : liste_checkbox) {
							if (box.isSelected()) {
								tags.add(box.getText());
							}
						}
						nbfilmsmax = Integer.parseInt(texte_nombre_films_max.getText());
						res = facade.creerEnfant(prenom, nom, credits, tags, nbfilmsmax);
					}
					//si on ne peut pas créer le compte
					if (res == 2) {
						JOptionPane.showMessageDialog(fenetre_connexion, "Pas assez de crédits.");
					}
					//si on peut le créer, on met les fenetres en pas visibles et on repart sur
					//la fenetre d'origine mais en mode abonné
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
			
			//on finit la création de la fenetre pour la création de compte en fonction
			//de si c'est un adulte ou non
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
				paneldroite.add(texte_nombre_films_max);
				JScrollPane barre = new JScrollPane(check);
				paneldroite.add(barre);
				
				fenetre_creer_compte_enfant.add(panelgauche, BorderLayout.WEST);
				fenetre_creer_compte_enfant.add(paneldroite, BorderLayout.CENTER);
			}
		}
		//si les fenetres existent déjà on les affiche simplement
		else {
			if (adulte) {
				fenetre_creer_compte_adulte.setVisible(true);
			}
			else {
				fenetre_creer_compte_enfant.setVisible(true);
			}
		}
	}
	
	static ActionListener fonction_louer(Boolean abo, ArrayList<String> s, JFrame fenetre, Boolean qr, Boolean que_en_global) {
		//fonction qui crée le actionlistener pour le bouton louer d'un film
		//passe un boolean pour abonné, une liste pour les infos du film, la fenetre du film pour la mettre en non visible
		//si le film était dispo, et un boolean pour si l'utilisateur veut louer un qr code ou blu ray
		
		//on return l'actionListener qu'on crée
		return new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//on crée la fonction du bouton suivant
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
				System.out.println(abo);
				//on vérifie si le film est dispo
				//si il est dispo
				System.out.println(s.get(0));
				if (facade.film_dispo(s.get(0))){
					System.out.println("filmdispo");
					//on passe la fenetre d'avant en pas visible
					fenetre.setVisible(false);
					
					//on crée le message à afficher
    				//Initialisation
					
    				JTextArea texte = new JTextArea("Le film a bien été loué.");
    				 
    				// Pour un retour à ligne automatique
    				texte.setLineWrap(true);
    				  
    				// Pour que les mots ne soient pas coupés
    				texte.setWrapStyleWord(true);
    				
    				texte.setFont(new Font("Serif", Font.BOLD, 40));
    				
    				if (!abo) {
    					JFrame fenetrecb = new JFrame("fenetrerentrecb");
    					fenetrecb.setSize(600,600);
    					fenetrecb.setLocationRelativeTo(null);
    					
    					JLabel cb = new JLabel(" numéro carte :");
    					cb.setFont(new Font("Serif", Font.BOLD, 40));
    					JTextField textecb = new JTextField();
    					textecb.setFont(new Font("Serif", Font.BOLD, 40));
    					
    					textecb.addKeyListener(new KeyAdapter() {
    					    public void keyTyped(KeyEvent e) {
    					        char c = e.getKeyChar();
    					        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
    					            e.consume();  // ignorer l'événement
    					        }
    					     }
    					});
    					
    					JButton btnretour = new JButton("retour");
    					JPanel containersuite = new JPanel (new GridLayout(1,2));
    					//suivant le boolean on crée notre bouton retour
    					btnretour.addActionListener(new ActionListener(){
    						public void actionPerformed(ActionEvent e){
    							fenetre.setVisible(true);;
    							fenetrecb.setVisible(false);
    						}
    					});
    					containersuite.add(btnretour);
    					//idem pour le bouton suivant, avec le actionlistener
    					JButton btnsuivant = new JButton("suivant");
    					btnsuivant.addActionListener(new ActionListener(){
    						public void actionPerformed(ActionEvent e){
    							String carte = textecb.getText();
    							BigInteger carte_final = new BigInteger(carte);
    							//System.out.println(argent_final);
    							facade.creerGuest(carte_final);
    							fenetrecb.setVisible(false);
    							fenetre_loue_non_abo = fenetresuivante("fenetreloue",fenetre_catalogue_non_abo,true, false, suivant);
    	    					fenetre_loue_non_abo.add(texte,BorderLayout.CENTER);
    		    				fenetre_loue_non_abo.setVisible(true);
    						}
    					});
    					containersuite.add(btnsuivant);
    					
    					JPanel panelcentre = new JPanel(new GridLayout(1,2));
    					panelcentre.add(cb);
    					panelcentre.add(textecb);
    					
    					fenetrecb.add(containersuite,BorderLayout.SOUTH);
    					fenetrecb.add(panelcentre,BorderLayout.CENTER);
    					fenetrecb.setVisible(true);
    					/*fenetre_loue_non_abo = fenetresuivante("fenetreloue",fenetre_catalogue_non_abo,true, false, suivant);
    					fenetre_loue_non_abo.add(texte,BorderLayout.CENTER);
	    				fenetre_loue_non_abo.setVisible(true);*/
    				}
    				
    				else {
    					//on récupère le réultat des fonctions pour louer le film en br ou qr suivant le cas
        				int res = 0;
        				/*if (que_en_global) {
        					res = 1;
        					texte.setText("Le film a bien été commandé.");
        					//res = facade.commander(s.get(0));
        				}*/
    					if (!qr) {
        					res = facade.louer_Br(s.get(0));
        				}
        				if (qr) {
        					res = facade.louer_Qr(s.get(0));
        				}
        				//si le resultat de retour est bon on crée la fenetre affichant que tout est bon
        				if (/*abo && */res ==1) {
        					if (que_en_global) {
        						fenetre_loue_abo = fenetresuivante("fenetrecommande",fenetre_catalogue_abo,true, false, suivant);
        					}
        					else {
        						fenetre_loue_abo = fenetresuivante("fenetreloue",fenetre_catalogue_abo,true, false, suivant);
        					}
        					//fenetre_loue_abo = fenetresuivante("fenetreloue",fenetre_catalogue_abo,true, false, suivant);
        					fenetre_loue_abo.add(texte,BorderLayout.CENTER);
    	    				fenetre_loue_abo.setVisible(true);
        				}
        				
        				/*else if(!abo && res == 1){
        					JFrame fenetrecb = new JFrame("fenetrerentrecb");
        					fenetrecb.setSize(600,600);
        					fenetrecb.setLocationRelativeTo(null);
        					
        					JLabel cb = new JLabel(" numéro carte :");
        					cb.setFont(new Font("Serif", Font.BOLD, 40));
        					JTextField textecb = new JTextField();
        					textecb.setFont(new Font("Serif", Font.BOLD, 40));
        					
        					textecb.addKeyListener(new KeyAdapter() {
        					    public void keyTyped(KeyEvent e) {
        					        char c = e.getKeyChar();
        					        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
        					            e.consume();  // ignorer l'événement
        					        }
        					     }
        					});
        					
        					JButton btnretour = new JButton("retour");
        					JPanel containersuite = new JPanel (new GridLayout(1,2));
        					//suivant le boolean on crée notre bouton retour
        					btnretour.addActionListener(new ActionListener(){
        						public void actionPerformed(ActionEvent e){
        							fenetre.setVisible(true);;
        							fenetrecb.setVisible(false);
        						}
        					});
        					containersuite.add(btnretour);
        					//idem pour le bouton suivant, avec le actionlistener
        					JButton btnsuivant = new JButton("suivant");
        					btnsuivant.addActionListener(new ActionListener(){
        						public void actionPerformed(ActionEvent e){
        							String carte = textecb.getText();
        							BigInteger carte_final = new BigInteger(carte);
        							//System.out.println(argent_final);
        							facade.creerGuest(carte_final);
        							fenetrecb.setVisible(false);
        							fenetre_loue_non_abo = fenetresuivante("fenetreloue",fenetre_catalogue_non_abo,true, false, suivant);
        	    					fenetre_loue_non_abo.add(texte,BorderLayout.CENTER);
        		    				fenetre_loue_non_abo.setVisible(true);
        						}
        					});
        					containersuite.add(btnsuivant);
        					
        					JPanel panelcentre = new JPanel(new GridLayout(1,2));
        					panelcentre.add(cb);
        					panelcentre.add(textecb);
        					
        					fenetrecb.add(containersuite,BorderLayout.SOUTH);
        					fenetrecb.add(panelcentre,BorderLayout.CENTER);
        					fenetrecb.setVisible(true);
        					//fenetre_loue_non_abo = fenetresuivante("fenetreloue",fenetre_catalogue_non_abo,true, false, suivant);
        					//fenetre_loue_non_abo.add(texte,BorderLayout.CENTER);
    	    				//fenetre_loue_non_abo.setVisible(true);
        				}*/
        				//si le résultat n'est pas bon, on établit le message d'erreur voulu
        				else/* if(res != 1) */{
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
        					JOptionPane.showMessageDialog(fenetre, erreur);
        				}
    				}
    				}
    			
				// si le film est pas dispo mais que l'utiliateur est un abonné
				//on lui laisse l'option de réserver le film
				else {
					System.out.println("filmpasdispo");
					JTextArea texte = new JTextArea("Le film a bien été commandé.");
   				 
    				// Pour un retour à ligne automatique
    				texte.setLineWrap(true);
    				  
    				// Pour que les mots ne soient pas coupés
    				texte.setWrapStyleWord(true);
    				
    				texte.setFont(new Font("Serif", Font.BOLD, 40));
    				if (que_en_global) {
    					fenetre_loue_abo = fenetresuivante("fenetrecommande",fenetre_catalogue_abo,true, false, suivant);
    				}
    				
					if (abo) {
						ActionListener suivant2 = new ActionListener() {
	    					public void actionPerformed(ActionEvent e){
    							fenetre_reserve_abo.setVisible(false);
    							Home_abo.setVisible(true);
	    					}
						};
						texte.setText ("Le film a bien été réservé.");
	    				 
	    				// Pour un retour à ligne automatique
	    				texte.setLineWrap(true);
	    				// Pour que les mots ne soient pas coupés
	    				texte.setWrapStyleWord(true);
	    				texte.setFont(new Font("Serif", Font.BOLD, 40));
	    				fenetre_reserve_abo = fenetresuivante("fenetre réservation",fenetre_catalogue_abo,true, false, suivant2);
    					fenetre_reserve_abo.add(texte,BorderLayout.CENTER);
	    				fenetre_reserve_abo.setVisible(true);
	    				fenetre.setVisible(false);
					}
				}
			}
		};
	}
	
	static ActionListener fonction_bouton_voir(Boolean abo,Boolean recherche,Boolean global, ArrayList<String> s, String image_origine, String image_pour_affiche) {
		//le bouton voir permet de voir toutes les informations sur un film
		//on crée l'actionlistener correspondant au bouton voir
		ActionListener voir = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println(facade.film_dispo(s.get(0)));
				JFrame fenetre;
				//on met les bonnes fenetres en pas visibles et on crée la nouvelle fenetre
				if(global){
					fenetre_catalogue_global.setVisible(false);
					fenetre = fenetresuivante(s.get(0),fenetre_catalogue_global,false,true,null);
				}
				else if (abo && recherche == false) {
					fenetre_catalogue_abo.setVisible(false);
    				fenetre = fenetresuivante(s.get(0),fenetre_catalogue_abo,false,true,null);
				}
				else if(!abo && recherche == false) {
					fenetre_catalogue_non_abo.setVisible(false);
    				fenetre = fenetresuivante(s.get(0),fenetre_catalogue_non_abo,false,true,null);
				}
				else {
					fenetre_recherche.setVisible(false);
					fenetre = fenetresuivante(s.get(0),fenetre_recherche,false,true,null);
				}
				System.out.println(image_origine);
				System.out.println(image_pour_affiche);
				// Création panels
				JPanel panel_location = new JPanel(new GridLayout(1,2));
				JPanel panel_realrest = new JPanel(new GridLayout(2,1));
				JPanel panel_afmilieu = new JPanel(new GridLayout(1,2));
				JPanel panel_centre   = new JPanel(new GridLayout(3,1));
				// get info
				JLabel film_titre = new JLabel(s.get(0));
				JLabel film_real  = new JLabel(s.get(1));
				
				//Initialisation
				JTextArea film_descr = new JTextArea(s.get(2));
				 
				// Pour un retour à ligne automatique
				film_descr.setLineWrap(true);
				  
				// Pour que les mots ne soient pas coupés
				film_descr.setWrapStyleWord(true);
				
				//on change la taille de l'image
				try {
					changesize(image_origine, image_pour_affiche, 200, 200);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				JLabel film_image = new JLabel(new ImageIcon(image_pour_affiche));
				String str_restr = new String(s.get(4));

				JLabel film_restr = new JLabel(str_restr);
				JButton btn_BR = new JButton("Location BluRay");
				JButton btn_QR = new JButton("Location QR Code");
				
				//si le film est pas dispo, soit on le met en réserver pour un abonné, soit en non dispo pour un pas abonné
				if (!facade.film_dispo(s.get(0))) {
					if (abo) {
						btn_BR.setText("réserver le film");
					}
					else {
						btn_BR.setBackground(Color.GRAY);
						btn_BR.setText("film non disponible");
					}
				}
				btn_BR.addActionListener(fonction_louer(abo,s,fenetre,false,false));
				btn_QR.addActionListener(fonction_louer(abo,s,fenetre,true,false));
				
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
					fenetre.add(film_titre, BorderLayout.NORTH);
    				fenetre.add(panel_centre, BorderLayout.CENTER);
				}
				else {
					fenetre.add(film_titre, BorderLayout.NORTH);
    				fenetre.add(panel_centre, BorderLayout.CENTER);
				}
			}
		};
	return voir;
	}
	
	static ActionListener fonction_bouton_voir_global(Boolean abo,Boolean recherche, ArrayList<String> film,ArrayList<ArrayList<String>> films_local, String image_origine, String image_pour_affiche) {
		//le bouton voir permet de voir toutes les informations sur un film
				//on crée l'actionlistener correspondant au bouton voir
				ActionListener voir = new ActionListener(){
					public void actionPerformed(ActionEvent e){
						JFrame fenetre;
						//on met les bonnes fenetres en pas visibles et on crée la nouvelle fenetre
						if (recherche) {
							fenetre_recherche.setVisible(false);
							System.out.println("ici");
							fenetre = fenetresuivante(film.get(0),fenetre_recherche,false,true,null);
						}
						else {
							System.out.println("ici2");
							fenetre_catalogue_global.setVisible(false);
							fenetre = fenetresuivante(film.get(0),fenetre_catalogue_global,false,true,null);
						}
						//fenetre = fenetresuivante(film.get(0),fenetre_recherche,false,true,null);
						// Création panels
						JPanel panel_location = new JPanel(new GridLayout(1,2));
						JPanel panel_realrest = new JPanel(new GridLayout(2,1));
						JPanel panel_afmilieu = new JPanel(new GridLayout(1,2));
						JPanel panel_centre   = new JPanel(new GridLayout(3,1));
						// get info
						JLabel film_titre = new JLabel(film.get(0));
						JLabel film_real  = new JLabel(film.get(1));
						
						//Initialisation
						JTextArea film_descr = new JTextArea(film.get(2));
						 
						// Pour un retour à ligne automatique
						film_descr.setLineWrap(true);
						  
						// Pour que les mots ne soient pas coupés
						film_descr.setWrapStyleWord(true);
						
						//on change la taille de l'image
						try {
							changesize(image_origine, image_pour_affiche, 200, 200);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						JLabel film_image = new JLabel(new ImageIcon(image_pour_affiche));
						String str_restr = new String(film.get(4));

						JLabel film_restr = new JLabel(str_restr);
						JButton btn_BR = new JButton("Location BluRay");
						JButton btn_QR = new JButton("Location QR Code");
						
						/*if (films_local.contains(film)){
							//si le film est pas dispo, soit on le met en réserver pour un abonné, soit en non dispo pour un pas abonné
							if (!facade.film_dispo(film.get(0))) {
								if (abo) {
									btn_BR.setText("réserver le film");
								}
								else {
									btn_BR.setBackground(Color.GRAY);
									btn_BR.setText("film non disponible");
								}
							}
							btn_BR.addActionListener(fonction_louer(abo,film,fenetre,false,false));
						}
						else {
							btn_BR.setText("commander le film");
							btn_BR.addActionListener(fonction_louer(abo,film,fenetre,false,true));
						}*/
						btn_BR.setText("commander le film");
						btn_BR.addActionListener(fonction_louer(abo,film,fenetre,false,true));
						btn_QR.addActionListener(fonction_louer(abo,film,fenetre,true,false));
						
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
						
						fenetre.add(film_titre, BorderLayout.NORTH);
		    			fenetre.add(panel_centre, BorderLayout.CENTER);
					}
				};
			return voir;
	}
	
	static void cree_fenetre_catalogue_global(String titre, JFrame fenetreprecedente) {
		fenetreprecedente.setVisible(false);
		if (fenetre_catalogue_global == null) {
			fenetre_catalogue_global = fenetresuivante(titre, fenetreprecedente,false, true, null);
			
			ArrayList<ArrayList<String>> liste_films = facade.getfilm();
			ArrayList<ArrayList<String>> liste_films_global = facade.catalogueGlobal();
			
			JLabel recherche = new JLabel(" Recherche :");
			recherche.setFont(new Font("Serif", Font.BOLD, 35));
			JTextField texterecherche = new JTextField (30);
			texterecherche.setFont(new Font("Serif", Font.BOLD, 40));
			String icone_origine = "D:\\Documents\\M1\\projet\\icones\\recherche.png";
			String icone_redimension = "D:\\Documents\\M1\\projet\\icones\\recherchebon.png";
			try {
				changesize(icone_origine, icone_redimension, 50, 50);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Icon icon = new ImageIcon(icone_redimension);
			JButton btnrecherche = new JButton (icon);
			
			
			JPanel panelhaut = new JPanel (new GridLayout(1,2));
			panelhaut.add(recherche);
			panelhaut.add(texterecherche);
			JPanel panel_haut_final = new JPanel(new BorderLayout());
			panel_haut_final.add(panelhaut, BorderLayout.CENTER);
			panel_haut_final.add(btnrecherche, BorderLayout.EAST);
			fenetre_catalogue_global.add(panel_haut_final,BorderLayout.NORTH);
			
			
			JPanel containerpanel = new JPanel (new GridLayout(liste_films_global.size(),1));
			
			btnrecherche.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String recherche_utilisateur = texterecherche.getText();
					for(ArrayList<String> s : liste_films_global) {
						//System.out.println(s.get(0).toLowerCase());
						//System.out.println(recherche_utilisateur);
						//System.out.println(s.get(0).toLowerCase() == recherche_utilisateur.toLowerCase());
						if (s.get(0).equalsIgnoreCase(recherche_utilisateur)) {
							//System.out.println("recherche ok");
							fenetre_catalogue_global.setVisible(false);
							String fichier = s.get(3);
				        	String fichier2 = fichier.substring(5,fichier.length()-4) + "bon.jpg";
				        	String fichier3 = fichier.substring(5,fichier.length()-4) + "affiche.jpg";
				        	fichier = fichier.substring(5,fichier.length());
				        	JButton btnvoir = new JButton("Voir");
				        	
				        	
				        	String image_origine = "D:\\Documents\\M1\\projet\\images\\"+ fichier;
				        	String image_redimension = "D:\\Documents\\M1\\projet\\images\\"+ fichier2;
				        	String image_pour_affiche = "D:\\Documents\\M1\\projet\\images\\"+ fichier3;
				        	if (liste_films.contains(s)) {
				        		btnvoir.addActionListener(fonction_bouton_voir(true,true,true,s,image_origine,image_pour_affiche));
				        	}
				        	else {
				        		btnvoir.addActionListener(fonction_bouton_voir_global(true,true,s,liste_films,image_origine,image_pour_affiche));
				        	}
							
							
							try {
								changesize(image_origine, image_redimension, 300, 300);
							} catch (IOException e2) {
								e2.printStackTrace();
							}
				        	JLabel image = new JLabel( new ImageIcon(image_redimension));
				        	
				        	JPanel souscontainer = new JPanel (new GridLayout(1,2));
				        	souscontainer.add(image);
				        	souscontainer.add(btnvoir);
				        	fenetre_recherche = fenetresuivante("fenetre recherche",fenetre_catalogue_global,false,true,null);
				        	fenetre_recherche.add(souscontainer,BorderLayout.CENTER);
						}
					}
				}
			});
			
	        for(ArrayList<String> s : liste_films_global) {
	        	String fichier = s.get(3);
	        	String fichier2 = "";
	        	String fichier3 = "";
	        	boolean image_existe = false;
	        	//System.out.println(fichier);
	        	//System.out.println(fichier.substring(fichier.length()-3,fichier.length()));
	        	
	        	if (fichier.substring(fichier.length()-3,fichier.length()).compareTo("jpg")==0) {
	        		//System.out.println("ici");
		        	fichier2 = fichier.substring(5,fichier.length()-4) + "bon.jpg";
		        	fichier3 = fichier.substring(5,fichier.length()-4) + "affiche.jpg";
		        	fichier = fichier.substring(5,fichier.length());
		        	image_existe = true;
	        	}
	        	JButton btnvoir = new JButton("Voir");
	        	
	        	
	        	String image_origine = "D:\\Documents\\M1\\projet\\images\\"+ fichier;
	        	String image_redimension = "D:\\Documents\\M1\\projet\\images\\"+ fichier2;
	        	String image_pour_affiche = "D:\\Documents\\M1\\projet\\images\\"+ fichier3;
	        	
	        	
	        	btnvoir.addActionListener(fonction_bouton_voir(true,false,true,s,image_origine,image_pour_affiche));
	        	JLabel image;
	        	if (image_existe) {
	        		try {
						changesize(image_origine, image_redimension, 300, 300);
					} catch (IOException e) {
						e.printStackTrace();
					}
		        	image = new JLabel( new ImageIcon(image_redimension));
		        	try {
						changesize(image_origine, image_redimension, 300, 300);
					} catch (IOException e) {
						e.printStackTrace();
					}
		        	image = new JLabel( new ImageIcon(image_redimension));
	        	}
	        	else {
	        		image = new JLabel(s.get(0));
	        	}
	        	JPanel souscontainer = new JPanel (new GridLayout(1,2));
	        	
	        	
	        	souscontainer.add(image);
	        	souscontainer.add(btnvoir);
	        	containerpanel.add(souscontainer);
	        }
	        //fenetre_catalogue_global.add(containerpanel,BorderLayout.CENTER);
	        JScrollPane barre = new JScrollPane(containerpanel);
		    fenetre_catalogue_global.add(barre);
		}
		else {
			fenetre_catalogue_global.setVisible(true);
		}
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
			
			JButton btn_catalogue_global = new JButton ("+ de films");
			btn_catalogue_global.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e){
    				cree_fenetre_catalogue_global("catalogue global",fenetre_catalogue_abo);
    			}
			});
			
			JLabel recherche = new JLabel(" Recherche :");
			recherche.setFont(new Font("Serif", Font.BOLD, 35));
			JTextField texterecherche = new JTextField (30);
			texterecherche.setFont(new Font("Serif", Font.BOLD, 40));
			String icone_origine = "D:\\Documents\\M1\\projet\\icones\\recherche.png";
			String icone_redimension = "D:\\Documents\\M1\\projet\\icones\\recherchebon.png";
			try {
				changesize(icone_origine, icone_redimension, 50, 50);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Icon icon = new ImageIcon(icone_redimension);
			JButton btnrecherche = new JButton (icon);
			
			
			JPanel panelhaut = new JPanel (new GridLayout(1,2));
			panelhaut.add(recherche);
			panelhaut.add(texterecherche);
			JPanel panel_haut_final = new JPanel(new BorderLayout());
			panel_haut_final.add(panelhaut, BorderLayout.CENTER);
			panel_haut_final.add(btnrecherche, BorderLayout.EAST);
			
			//panel_haut_final.add(btn_catalogue_global, BorderLayout.WEST);
			if (abo) {
				panel_haut_final.add(btn_catalogue_global, BorderLayout.WEST);
				fenetre_catalogue_abo.add(panel_haut_final,BorderLayout.NORTH);
			}
			else {
				fenetre_catalogue_non_abo.add(panel_haut_final,BorderLayout.NORTH);
			}
			
			ArrayList<ArrayList<String>> liste_films = facade.getfilm();
			
			JPanel containerpanel = new JPanel (new GridLayout(liste_films.size(),1));
			System.out.println(liste_films);
			
			btnrecherche.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e){
    				String recherche_utilisateur = texterecherche.getText();
    				for(ArrayList<String> s : liste_films) {
    					System.out.println(s.get(0).toLowerCase());
    					System.out.println(recherche_utilisateur);
    					System.out.println(s.get(0).toLowerCase() == recherche_utilisateur.toLowerCase());
    					if (s.get(0).equalsIgnoreCase(recherche_utilisateur)) {
    						System.out.println("recherche ok");
    						if (abo) {
    							fenetre_catalogue_abo.setVisible(false);
    						}
    						else {
    							fenetre_catalogue_non_abo.setVisible(false);
    						}
    						/*String fichier = s.get(3);
    			        	String fichier2 = fichier.substring(5,fichier.length()-4) + "bon.jpg";
    			        	String fichier3 = fichier.substring(5,fichier.length()-4) + "affiche.jpg";
    			        	fichier = fichier.substring(5,fichier.length());*/
    						String fichier = s.get(3);
    			        	String fichier2 = fichier + "bon.jpg";
    			        	String fichier3 = fichier + "affiche.jpg";
    			        	JButton btnvoir = new JButton("Voir");
    			        	
    			        	
    			        	/*String image_origine = "D:\\Documents\\M1\\projet\\images\\"+ fichier;
    			        	String image_redimension = "D:\\Documents\\M1\\projet\\images\\"+ fichier2;
    			        	String image_pour_affiche = "D:\\Documents\\M1\\projet\\images\\"+ fichier3;*/
    			        	String image_origine = "data/" + fichier;
    			        	String image_redimension = "data/" + fichier2;
    			        	String image_pour_affiche = "data/" + fichier3;
    						btnvoir.addActionListener(fonction_bouton_voir(abo,true,false,s,image_origine,image_pour_affiche));
    						
    						try {
    							changesize(image_origine, image_redimension, 300, 300);
    						} catch (IOException e2) {
    							e2.printStackTrace();
    						}
    			        	JLabel image = new JLabel( new ImageIcon(image_redimension));
    			        	
    			        	JPanel souscontainer = new JPanel (new GridLayout(1,2));
    			        	souscontainer.add(image);
    			        	souscontainer.add(btnvoir);
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
			
	        for(ArrayList<String> s : liste_films) {
	        	/*
	        	String fichier = s.get(3);
	        	String fichier2 = fichier.substring(5,fichier.length()-4) + "bon.jpg";
	        	String fichier3 = fichier.substring(5,fichier.length()-4) + "affiche.jpg";
	        	fichier = fichier.substring(5,fichier.length());*/
	        	String fichier = s.get(3);
	        	String chemin = (new File(fichier).getAbsolutePath());
	        	//String chemin = System.getProperty("user.dir");
	        	//System.out.println(chemin);
	        	//System.out.println(fichier);
	        	/*String fichier2 = fichier.substring(0,fichier.length()-4) + "bon.jpg";
	        	String fichier3 = fichier.substring(0,fichier.length()-4) + "affiche.jpg";
	        	System.out.println(fichier2);*/
	        	fichier = chemin;
	        	String fichier2 = chemin.substring(0,chemin.length()-4) + "bon.jpg";
	        	String fichier3 = chemin.substring(0,chemin.length()-4) + "affiche.jpg";
	        	
	        	JButton btnvoir = new JButton("Voir");
	        	
	        	
	        	/*String image_origine = "D:\\Documents\\M1\\projet\\images\\"+ fichier;
	        	String image_redimension = "D:\\Documents\\M1\\projet\\images\\"+ fichier2;
	        	String image_pour_affiche = "D:\\Documents\\M1\\projet\\images\\"+ fichier3;*/
	        	String image_origine = fichier;
	        	String image_redimension = fichier2;
	        	String image_pour_affiche = fichier3;
	        	
	        	
	        	btnvoir.addActionListener(fonction_bouton_voir(abo,false,false,s,image_origine,image_pour_affiche));
	        	
	        	try {
					changesize(image_origine, image_redimension, 300, 300);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        	JLabel image = new JLabel( new ImageIcon(image_redimension));
	        	
	        	JPanel souscontainer = new JPanel (new GridLayout(1,2));
	        	souscontainer.add(image);
	        	souscontainer.add(btnvoir);
	        	containerpanel.add(souscontainer);
	        }
	        if (abo) {
	        	fenetre_catalogue_abo.add(containerpanel,BorderLayout.CENTER);
	        	JScrollPane barre = new JScrollPane(containerpanel);
		        fenetre_catalogue_abo.add(barre);
	        }
	        else {
	        	JScrollPane barre = new JScrollPane(containerpanel);
		        fenetre_catalogue_non_abo.add(barre);
	        }
		}
	else {
		if (abo) {
			fenetre_catalogue_abo.setVisible(true);
		}
		else {
			fenetre_catalogue_non_abo.setVisible(true);
		}
	}
}
	
	
	public static void fenetre_origine (boolean abo) {
		// si la fenetre est déjà créée pour un abonné
		if (abo && Home_abo != null) {
			Home_abo.setVisible(true);
		}
		// idem pour un non abonné
		else if (!(abo) && Home_non_abo != null) {
			Home_non_abo.setVisible(true);
		}
		//sinon on crée la fenetre
		else {
			
			//bouton pour affficher le catalogue local
			JButton btncata = new JButton("catalogue");
			//bouton pour créer un nouveau compte
			JButton btncreer = new JButton("creer un compte");
			
			btncreer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//on passe la fenetre concernée en pas visible et 
					//on crée notre nouvelle fonction avec la fonction appropriée
					if (abo) {
						Home_abo.setVisible(false);
						fenetre_creer_compte("CREER COMPTE",Home_abo, false);
					}
					else {
						Home_non_abo.setVisible(false);
						fenetre_creer_compte("CREER COMPTE",Home_non_abo,true);
					}
				}
			});
			
			//bouton pour rendre un blu ray
			JButton btnrendre = new JButton ("rendre cd");
			btnrendre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// on passe la bonne fenetre en non visible
					if (abo) {
						Home_abo.setVisible(false);
					}
					else {
						Home_non_abo.setVisible(false);
					}
					//si on a pas déjà créé les fenetres
					if ((abo && (fenetre_succes_rendu_abo == null))||(!abo && (fenetre_succes_rendu_nonabo == null))) {
						//on crée le actionlistener qui sera pour le bouton suivant de la fenetre de rendu de cd
						ActionListener effet = new ActionListener(){
							public void actionPerformed(ActionEvent e){
								// si l'utilisateur peut effectivement rendre le cd
								if (facade.rendre() == 1) {
									//on met en non visible la fenetre et on crée la nouvelle
									fenetre_rendu_cd.setVisible(false);
									JFrame fenetre = new JFrame();
									fenetre.setSize(600,600);
									fenetre.setLocationRelativeTo(null);
									JButton btnsuivant = new JButton("suivant");
									//on sépare si l'utilisateur est abonné ou non pour savoir à quel menu le bouton doit renvoyer
									if (abo) {
										fenetre_succes_rendu_abo = fenetre;
										btnsuivant.addActionListener(new ActionListener(){
											public void actionPerformed(ActionEvent e){
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
								//si l'utilisateur ne peut pas rendre le cd, on affiche un message d'erreur correspondant
								else if (facade.rendre() == 2) {
									JOptionPane.showMessageDialog(fenetre_rendu_cd, "Pas assez de crédits pour rendre le film.");
								}
								else if (facade.rendre() == 3) {
									JOptionPane.showMessageDialog(fenetre_rendu_cd, "Pas assez de crédits pour rendre le film.");
								}
							}
						};
						//on crée notre fenetre de rendu de cd et on lui 
						//passse le actionlistener précédemment créé à l'aide de la fonction fenetresuivante
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
					//si on a déjà créé les fenetres
					else {
						fenetre_rendu_cd.setVisible(true);
					}
				}
			});
			
			// pour afficher le nom de la machine dans la fenetre
			JLabel titrefen = new JLabel("AL 2000");
			titrefen.setFont(new Font("Serif", Font.BOLD, 50));
			
			//rajout d'une image sur la page et du nom de la machine
			JLabel image = new JLabel( new ImageIcon("D:\\Documents\\M1\\projet\\images\\pub.png"));
			JPanel panelima = new JPanel(new BorderLayout());
			panelima.add(titrefen,BorderLayout.NORTH);
			panelima.add(image,BorderLayout.CENTER);
			
			//notre fenetre d'origine n'a pas exactement les mêmes fonctionnalitées 
			//si la personne est abonnée ou non, on crée donc la fenetre en fonction de ce dont on a besoin
			if (abo) {
				Home_abo = new JFrame("MENU ABONNE");
				Home_abo.setSize(600,600);
				JButton btninfo = new JButton ("informations");
				
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
				
				btndeco.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
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
				Home_abo.add(containerpanel,BorderLayout.WEST);
				Home_abo.add(panelima,BorderLayout.CENTER);
				Home_abo.setLocationRelativeTo(null);
				Home_abo.setVisible(true);
			}
			else {
				Home_non_abo = new JFrame("MENU");
				Home_non_abo.setSize(600,600);
				JButton btnco = new JButton("se connecter");
				JPanel panelco = new JPanel (new BorderLayout());
				panelco.add(btnco,BorderLayout.CENTER);
				
				//bouton qui va permettre à l'utilisateur de se connecter à son compte
				btnco.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						Home_non_abo.setVisible(false);

						if (fenetre_connexion == null) {
							//on crée le action listener qui correspondra au bouton suivant
							ActionListener effet = new ActionListener(){
								public void actionPerformed(ActionEvent e){
									int res = facade.connection();//on vérifie si la personne peut se connecter
									//si oui
									if (res!=0) {//1 adulte 2 enfant
										fenetre_connexion.setVisible(false);
										fenetre_origine(true);
									}
									//sinon un message d'erreur
									else {
										JOptionPane.showMessageDialog(fenetre_connexion, "Veuillez rentrer votre carte.");
									}
								}
							};
							//on crée la fenetre de connection
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
						//si la fenetre existait déjà, on la met simplement en visible
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
}
