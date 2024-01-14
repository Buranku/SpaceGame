package peliObjektit;

/**
 * Yliluokka kaikille peliobjekteille,
 * Sisaltaa koordinaatit, liikkeet ja elossa olon
 */
public class PeliObjekti{

	protected boolean elossa = true;
	protected int x;
	protected int y;
	protected int liikeX, liikeY;
	public PeliObjekti(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public PeliObjekti(int x, int y, int liikeX, int liikeY) {
		this.x = x;
		this.y = y;
		this.liikeX = liikeX;
		this.liikeY = liikeY;
	}
	/**
	 * getteri elossaolon tarkistukseen,
	 * @return true jos elossa, muuten false
	 */
	public boolean getElossa() {
	return elossa;
	}

	/**
	 * setteri elossaolon muokkaukseen,
	 * @param t, true jos elossa
	 */
	public void setElossa(boolean t) {
		this.elossa = t;
	}

	/**
	 * getteri x-koordinaatille
	 * @return x-koordinaatti
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * getteri y-koordinaatille
	 * @return y-koordinaatti
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * setteri x-koordinaatille
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * setteri y-koordinatille
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * getteri objektin liikkeelle x-suuntaisesti
	 * @return
	 */
	public int getLiikeX() {
		return this.liikeX;
	}

	/**
	 * getteri objektin liikkeelle y-suuntaisesti
	 * @return
	 */
	public int getLiikeY() {
		return this.liikeY;
	}

	/**
	 * setteri liikkeelle x-suuntaisesti
	 * @param a
	 */
	public void setLiikeX(int a) {
		this.liikeX = a;
	}

	/**
	 * setteri liikkeelle y-suuntaisesti
	 * @param b
	 */
	public void setLiikeY(int b) {
		this.liikeY = b;
	}

	/**
	 * setteri liikkeelle
	 * @param a
	 * @param b
	 */
	public void setLiikkeet(int a, int b) {
		this.liikeX = a;
		this.liikeY = b;
	}

	/**
	 * setteri molemmille koordinaateille
	 * @param x-koordinaatti
	 * @param y-koordinaatti
	 */
	public void setKoordinaatit(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * muuttaa objektin elossa-arvon falseksi
	 */
	public void tuhoa() {
		this.elossa = false;
	}

	/**
	 * peliobjektin liikkeet
	 */
	public void liikkuminen() {}

}
