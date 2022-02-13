package peli;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import grafiikat.JframeGraphics;
import peliObjektit.Ammus;
import peliObjektit.AmmusV;
import peliObjektit.Pelaaja;
import peliObjektit.PeliObjekti;
import peliObjektit.Vihollinen;

/**
 * luokka pelin suorittamiselle,
 * sisaltaa lautaa ja pelia koskeavia attribuutteja
 */
public class Peli extends JPanel implements KeyListener, ActionListener{

	private static final long serialVersionUID = 1L;
	public JframeGraphics gui;
	public static Pelaaja pelaaja; 
	public static Timer ajastin;
	public int ticks = 0;
	public Dimension dim;
	public int leveys, korkeus;
	public static ArrayList<PeliObjekti> objektiLista = new ArrayList<PeliObjekti>();
	public static int pisteet;
	public static int hp;
	public int vihollisMaara;
	public static int shootingWait;
	public static ArrayList<Integer> scores;
	public Random rand;
	public int speed;
	public static boolean vaikeusValikko;
	public int vaikeusPisteet;
	public int bulletSpeed;
	
	/**
	 * alustaa uuden laudan, attribuutteja,
	 * sisaltaa keylistenerit
	 */
	public Peli() {
		
		Peli.ajastin = new Timer(20, this);
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		leveys = dim.width;
		korkeus = dim.height;
		pisteet = 0;
		hp = 3;
		shootingWait = 0;
		speed = 40;
		vaikeusValikko = true;
		vaikeusPisteet = 100;
		bulletSpeed = 8;
		rand = new Random(192837465);
		scores = new ArrayList<Integer>();
		JFrame frame = new JFrame("Galaga");
		this.gui = new JframeGraphics();
		frame.getContentPane().add(gui);
		frame.addKeyListener(new KeyListener() {
			public boolean ammuttu = false;
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(vaikeusValikko) {
					if(key == KeyEvent.VK_1) {
						speed = 50;
						vaikeusPisteet = 50;
						bulletSpeed = 7;
						vaikeusValikko = false;
					}
					else if(key == KeyEvent.VK_2) {
						speed = 40;
						vaikeusPisteet = 100;
						bulletSpeed = 8;
						vaikeusValikko = false;
					}
					else if(key == KeyEvent.VK_3) {
						speed = 30;
						vaikeusPisteet = 175;
						bulletSpeed = 10;
						vaikeusValikko = false;
					}
					else if(key == KeyEvent.VK_4) {
						speed = 20;
						vaikeusPisteet = 250;
						bulletSpeed = 12;
						vaikeusValikko = false;
					}
				}
				else {
					
				if (key == KeyEvent.VK_UP ) {
					Peli.pelaaja.setLiikeY(-8);
				}
				if (key == KeyEvent.VK_DOWN ) {
					Peli.pelaaja.setLiikeY(8);
				}
				if (key == KeyEvent.VK_LEFT ) {
					Peli.pelaaja.setLiikeX(-8);
				}
				if (key == KeyEvent.VK_RIGHT ) {
					Peli.pelaaja.setLiikeX(8);
				}
				if (key == KeyEvent.VK_SPACE && ammuttu == false) {
					Peli.ammu();
					ammuttu = true;
				}
				if (key == KeyEvent.VK_ENTER && pelaaja.getElossa()== false) {
					initialize();
					
				}
				
				}
			}
			
			public void keyReleased(KeyEvent e) {
				//pressed = false;
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
					Peli.pelaaja.setLiikeY(0);
				}
				if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
					Peli.pelaaja.setLiikeX(0);
				}
				if (key == KeyEvent.VK_SPACE) {
					ammuttu = false;
				}
			}
			
			public void keyTyped(KeyEvent e) {}
			
		});
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocation(dim.width/2-800/2,dim.height/2-800/2);
		
		this.genPelaaja();
		this.genAliens();
		
		ajastin.start();


		
		
	}
	/**
	 * alustaa pelaajan ja viholliset
	 */
	public void initialize() {
		//this.genPelaaja();
		pelaaja.setKoordinaatit(400, 400);
		ticks = 0;
		pisteet = 0;
		this.genAliens();
		hp = 3;
		pelaaja.setElossa(true);
		vaikeusValikko = true;
		ajastin.start();
		repaint();
	}
	
	/**
	 * kontrolloi pelaajan ampumisen nopeutta
	 */
	public static void ammu() {
		if(shootingWait==0) {
			objektiLista.add(new Ammus(pelaaja.getX(), pelaaja.getY(),0,-10));
		
		}
		shootingWait = 12;
	}
	
	/**
	 * generoi pelaajan
	 */
	public void genPelaaja() {
		pelaaja = new Pelaaja(400, 400);
		objektiLista.add(pelaaja);
	}
	
	/**
	 * generoi viholliset viidesta eri muodostelmasta
	 */
 	public void genAliens() {
 		int r = rand.nextInt(6);
 		int x;
 		int y;
 		switch(r) {
 		case(0):
 			x = 100;
 			for (int i = 0; i < 2; i++) {
 				objektiLista.add(new Vihollinen(x, 100));
 				x += 100;
 			}
 			for (int j = 0; j < 3; j++) {
 				objektiLista.add(new Vihollinen(x,50));
 				x += 100;
 			}
 			break;
 		case(1):
 			x = 100;
 			y = 50;
 			for (int i = 0; i < 5; i++) {
 				objektiLista.add(new Vihollinen(x, y));
 				x += 100;
 				y += 50;
 			}
 			break;
 		case(2):
 			objektiLista.add(new Vihollinen(100, 100));
 			objektiLista.add(new Vihollinen(500, 100));
 			objektiLista.add(new Vihollinen(150, 50));
 			objektiLista.add(new Vihollinen(450, 50));
 			objektiLista.add(new Vihollinen(250, 100));
 			break;
 		case(3):
 			x = 100;
 			for (int i = 0; i < 5; i++) {
 				objektiLista.add(new Vihollinen(x, 100));
 				x += 100;
 			}
 			break;
 		case(4):
 			x = 100;
 			for (int i = 0; i < 5; i++) {
 				objektiLista.add(new Vihollinen(x, 50));
 				x += 100;
 			}
 			break;
 		case(5):
 			x = 100;
 			y = 300;
 			for (int i = 0; i < 5; i++) {
 				objektiLista.add(new Vihollinen(x,y));
 				x += 100;
 				y -= 50;
 			}
 			break;
 		}
 		
 		
 		
 	}
 	
 	/**
 	 * suorittaa pelia ajastimella,
 	 * kutsuu gui:n paivitysta
 	 */
	public void actionPerformed(ActionEvent e) {
		if(vaikeusValikko) {
			this.gui.repaint();
		}
		else {
			
		ticks++;
		checkViholliset();
		vihollisetShooting();
		this.HitDetection();
		this.move();
		if(hp==0) {
			//objektiLista.remove(objektiLista.indexOf(pelaaja));
			pelaaja.setElossa(false);
		}
			
		if(vihollisMaara==0) {
			this.genAliens();
		}
		

		if(shootingWait>0) {
			shootingWait--;
		}
		this.gui.repaint();
		
		}
	}
	
	/**
	 * vihollisten ammuskelua saateleva metodi,
	 * ampuminen maaraytyy satunnaismuuttujan ja nopeus-olion avulla
	 */
	public void vihollisetShooting() {
		
		ArrayList<Integer> indeksit = new ArrayList<Integer>();
		
		for(int i = 0; i < objektiLista.size(); i++) {
			PeliObjekti o = objektiLista.get(i);
			
			if(o instanceof Vihollinen) {
				if(((Vihollinen)o).getAmpuminen()!=0 && ((Vihollinen)o).getAmmuskelu()==false) {
					((Vihollinen)o).setAmpuminen(rand.nextInt(speed) + speed*3/5);
					((Vihollinen)o).setAmmuskelu(true);
				}
				
				else if(((Vihollinen)o).getAmpuminen()!=0 && ((Vihollinen)o).getAmmuskelu()){
					((Vihollinen)o).setAmpuminen(((Vihollinen)o).getAmpuminen()-1);
				}
				else if(((Vihollinen)o).getAmpuminen()==0 && ((Vihollinen)o).getAmmuskelu()){
					//objektiLista.add(new AmmusV(o.getX(), o.getY(), 0, 7));
					indeksit.add(i);
					((Vihollinen)o).setAmmuskelu(false);
					((Vihollinen)o).setAmpuminen(-1);
					
				}
			}
		}
		for(int i : indeksit) {
			objektiLista.add(new AmmusV(objektiLista.get(i).getX(), objektiLista.get(i).getY(), 0, bulletSpeed));
		}
	}
	
	/**
	 * tarkistaa elossa olevien vihollisten maaran
	 */
	public void checkViholliset() {
		int a=0;
		for(PeliObjekti o : objektiLista) {
			if(o instanceof Vihollinen) {
				a++;
			}
			
		}
		vihollisMaara = a;
	}
	
	/**
	 * tarkistaa ovatko ammukset osuneet vihollisiin tai pelaajaan,
	 * poistaa osutut viholliset ja laskee osutun pelaajaan hp:ta 
	 */
	public void HitDetection() {
		
		ArrayList<Integer> tuhoutuvienIndeksit = new ArrayList<Integer>();
		
		A: for(int i = 0; i< objektiLista.size(); i++) {
			for(int j = 0; j< objektiLista.size(); j++) {
				PeliObjekti a = objektiLista.get(i);
				PeliObjekti b = objektiLista.get(j);
				if(i!=j && !tuhoutuvienIndeksit.contains(objektiLista.indexOf(b)) && !tuhoutuvienIndeksit.contains(objektiLista.indexOf(a))) {
					if(a instanceof Pelaaja && b instanceof Vihollinen) {
						if(new Rectangle(a.getX()+15, a.getY()+15, 30, 80).intersects(new Rectangle(b.getX(), b.getY(), 57, 57))){
							//a.tuhoa();
							b.tuhoa();
							//tuhoutuvienIndeksit.add(i);
							tuhoutuvienIndeksit.add(j);
							hp -= 1;
							break A;
						}
					}
						else if(a instanceof Ammus && b instanceof Vihollinen) {
							if(new Rectangle(a.getX()+10, a.getY()+5, 8, 19).intersects(new Rectangle(b.getX(), b.getY(), 57, 57))) {
								a.tuhoa();
								tuhoutuvienIndeksit.add(i);
								b.tuhoa();
								tuhoutuvienIndeksit.add(j);
								pisteet += vaikeusPisteet;
								break A;
							}
						}
						else if(a instanceof AmmusV && b instanceof Pelaaja) {
							if(new Rectangle(a.getX()+10, a.getY()+7, 8, 19).intersects(new Rectangle(b.getX() + 15, b.getY() + 15, 30, 80))) {
								a.tuhoa();
								tuhoutuvienIndeksit.add(i);
								hp -= 1;
								break A;
							}
						}
				}
			}
		}
		int a = 0;
		for(int i = 0; i < tuhoutuvienIndeksit.size();i++) {
			int b = tuhoutuvienIndeksit.get(i);
			objektiLista.remove(b-a);
			if(i==0 && tuhoutuvienIndeksit.size()==2) {
			if(tuhoutuvienIndeksit.get(i)<tuhoutuvienIndeksit.get(i+1)) {
				a=1;
			}
			}
			
		}

		
	}
	
	/**
	 * peliobjektien liikkeit kutsuva metodi
	 */
	public void move() {
		//for (PeliObjekti o : objektiLista) {
		ArrayList<Integer> tuhoutuvienIndeksit = new ArrayList<Integer>();
		B: for(int i = 0; i<objektiLista.size(); i++) {
			PeliObjekti o = objektiLista.get(i);
			if (o instanceof Vihollinen) {
				((Vihollinen)o).liikkuminen();
			}
			else if (o instanceof Pelaaja) {
				((Pelaaja)o).liikkuminen();
			}
			else if (o instanceof Ammus) {
				if (o.getY() + o.getLiikeY() < 0) {
					o.tuhoa();
					//objektiLista.remove(o);
					tuhoutuvienIndeksit.add(i);
					break B;
				}
				else {
					((Ammus)o).liikkuminen();
				}
			}
			else if(o instanceof AmmusV) {
				if(o.getY() + o.getLiikeY() > 800) {
					o.tuhoa();
					tuhoutuvienIndeksit.add(i);
					break B;
				}
				else {
					o.setY(o.getY() + o.getLiikeY());
				}
			}
		}
		for(int i : tuhoutuvienIndeksit) {
			objektiLista.remove(i);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
