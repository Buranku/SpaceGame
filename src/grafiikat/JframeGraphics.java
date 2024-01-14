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
    private static final String BACKGROUND_IMAGE_PATH = "Kuvat/avaruus2.jpg";
    private static final String PLAYER_IMAGE_PATH = "Kuvat/Alus.png";
    private static final String ENEMY_IMAGE_PATH = "Kuvat/Vihollinen.png";
    private static final String BULLET_IMAGE_PATH = "Kuvat/Ammus.png";
    private static final String BULLET_V_IMAGE_PATH = "Kuvat/Ammus2.png";
	/**
	 * maalaa laudan ja peliobjektit
	 */
	public void paint(Graphics g) {

		ImageIcon taustaKuvaIcon = new ImageIcon(BACKGROUND_IMAGE_PATH);
		ImageIcon pelaajaKuvaIcon = new ImageIcon(PLAYER_IMAGE_PATH);
		ImageIcon vihollinenKuvaIcon = new ImageIcon(ENEMY_IMAGE_PATH);
		ImageIcon ammusKuvaIcon = new ImageIcon(BULLET_IMAGE_PATH);
		ImageIcon ammusVKuvaIcon = new ImageIcon(BULLET_V_IMAGE_PATH);

		Graphics2D g2d = (Graphics2D)g;
		Image taustaKuva = taustaKuvaIcon.getImage();
		Image pelaajaKuva = pelaajaKuvaIcon.getImage();
		Image vihollinenKuva = vihollinenKuvaIcon.getImage();
		Image ammusKuva = ammusKuvaIcon.getImage();
		Image ammusVKuva = ammusVKuvaIcon.getImage();

		g.drawImage(taustaKuva,0,0, null);
		g.setColor(Color.white);
		g.setFont(new Font("Ariel", Font.BOLD,30));
		g.drawString("Score: " + Peli.pisteet, 600, 50);

		g.setColor(Color.green);
		g.drawString("HP: " + Peli.hp, 50, 50);
		g.drawString("Shot delay: "+ Peli.speed, 50, 100);
		if(Peli.pelaaja.getElossa()==false && !Peli.vaikeusValikko) {
			Peli.ajastin.stop();
			Peli.objektiLista.removeIf(objekti -> !(objekti instanceof Pelaaja));
			Peli.scores.add(Peli.pisteet);
			g.setColor(Color.red);
			g.setFont(new Font("Ariel", Font.BOLD,40));
			g.drawString("Game Over, press Enter to restart", 100, 100);
			g.setColor(Color.gray);
			g.setFont(new Font("Ariel", Font.BOLD, 30));
			g.drawString("Leaderboard", 300, 300);
			g.setColor(Color.white);


			Collections.sort(Peli.scores, Collections.reverseOrder());
			int leaderboardYPosition = 350;
    		int maxLeaderboardEntries = Math.min(Peli.scores.size(), 10);
    		for (int i = 0; i < maxLeaderboardEntries; i++) {
        		g.drawString("" + Peli.scores.get(i), 300, leaderboardYPosition);
        		leaderboardYPosition += 35;
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
		for (PeliObjekti objekti : Peli.objektiLista) {
			if (objekti.getElossa() == true) {
				if (objekti instanceof Vihollinen) {
					g2d.drawImage(vihollinenKuva, objekti.getX(), objekti.getY(), this);
				}
				else if (objekti instanceof Pelaaja) {
					g2d.drawImage(pelaajaKuva, objekti.getX(), objekti.getY(), this);
				}
				else if (objekti instanceof Ammus) {
					g.setColor(Color.RED);
					if (objekti.getElossa() == true) {
						g2d.drawImage(ammusKuva, objekti.getX(), objekti.getY(), this);
					}
				}
				else if (objekti instanceof AmmusV) {
					if(objekti.getElossa() == true) {
						g2d.drawImage(ammusVKuva, objekti.getX(), objekti.getY(), this);
					}
				}
			}
		}
	}

}
