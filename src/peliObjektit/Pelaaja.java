package peliObjektit;

/**
 * pelaajan luokka, aliluokka peliobjektille
 */
public class Pelaaja extends PeliObjekti {


	public Pelaaja(int x, int y) {
		super(x, y);
	}

	public void liikkuminen() {
		if(this.getX() + this.getLiikeX() < 700 && this.getX() + this.getLiikeX() > 20) {
			this.setX(this.getX() + this.getLiikeX());
		}

		if(this.getY() + this.getLiikeY() > 20 && this.getY() + this.getLiikeY() < 660) {
			this.setY(this.getY() + this.getLiikeY());
		}
	}

}