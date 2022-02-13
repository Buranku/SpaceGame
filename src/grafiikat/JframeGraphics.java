package grafiikat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import peli.Peli;
import peliObjektit.Ammus;
import peliObjektit.AmmusV;
import peliObjektit.Pelaaja;
import peliObjektit.PeliObjekti;
import peliObjektit.Vihollinen;
/**
 * luokka grafiikoille
 */
public class JframeGraphics extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * maalaa laudan ja peliobjektit
	 */
	public void paint(Graphics g) {
		
		ImageIcon t = new ImageIcon("Kuvat/avaruus2.jpg");
		ImageIcon p = new ImageIcon("Kuvat/Alus.png");
		ImageIcon v = new ImageIcon("Kuvat/Vihollinen.png");
		ImageIcon a = new ImageIcon("Kuvat/Ammus.png");
		ImageIcon s = new ImageIcon("Kuvat/Ammus2.png");
	
		Graphics2D g2d = (Graphics2D)g;
		Image taustaKuva = t.getImage();
		Image pelaajaKuva = p.getImage();
		Image vihollinenKuva = v.getImage();
		Image ammusKuva = a.getImage();
		Image ammusVKuva = s.getImage();
		
		g.drawImage(taustaKuva,0,0, null);
		g.setColor(Color.white);
		g.setFont(new Font("Ariel", Font.BOLD,30));
		g.drawString("Score: " + Peli.pisteet, 600, 50);
		
		g.setColor(Color.green);
		g.drawString("HP: " + Peli.hp, 50, 50);
		if(Peli.pelaaja.getElossa()==false && !Peli.vaikeusValikko) {
			Peli.ajastin.stop();
			for(int i = 0; i < Peli.objektiLista.size();i++) {
				if(Peli.objektiLista.get(i) instanceof Pelaaja ) {
					
				}
				else {
					Peli.objektiLista.remove(i);
					i--;
				}
			}
			Peli.scores.add(Peli.pisteet);
			g.setColor(Color.red);
			g.setFont(new Font("Ariel", Font.BOLD,40));
			g.drawString("Game Over, press Enter to restart", 100, 100);
			g.setColor(Color.gray);
			g.setFont(new Font("Ariel", Font.BOLD, 30));
			g.drawString("Leaderboard", 300, 300);
			g.setColor(Color.white);
			
			
			Collections.sort(Peli.scores, Collections.reverseOrder());
			int c = 350;
			if(Peli.scores.size()>10) {
			
				for(int i = 0; i<10;i++) {
					g.drawString("" + Peli.scores.get(i), 300, c);
					c += 35;
				}
			}
			else {
				for(int i = 0; i<Peli.scores.size();i++) {
					g.drawString("" + Peli.scores.get(i), 300, c);
					c += 35;
				}
			}
		}
		
		else if(Peli.vaikeusValikko) {
			g.setColor(Color.white);
			g.setFont(new Font("Ariel", Font.BOLD, 30));
			g.drawString("Choose the difficulty level", 200, 200);
			g.setFont(new Font("Ariel", Font.BOLD, 20));
			g.drawString("Press 1 for EASY", 200, 230);
			g.drawString("Press 2 for NORMAL", 200, 260);
			g.drawString("Press 3 for HARD", 200, 290);
			g.drawString("Press 4 for EXPERT", 200, 320);
		}
		
		
		//Peliobjektien grafiikat
		for (PeliObjekti o : Peli.objektiLista) {
			if (o.getElossa() == true) {
				if (o instanceof Vihollinen) {
					g2d.drawImage(vihollinenKuva, o.getX(), o.getY(), this);
				}
				else if (o instanceof Pelaaja) {
					g2d.drawImage(pelaajaKuva, o.getX(), o.getY(), this);
				}
				else if (o instanceof Ammus) {
					g.setColor(Color.RED);
					if (o.getElossa() == true) {
						g2d.drawImage(ammusKuva, o.getX(), o.getY(), this);
					}
				}
				else if (o instanceof AmmusV) {
					if(o.getElossa() == true) {
						g2d.drawImage(ammusVKuva, o.getX(), o.getY(), this);
					}
				}
			}
		}
	}

}
