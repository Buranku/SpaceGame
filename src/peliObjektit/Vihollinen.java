package peliObjektit;

/**
 * Vihollisten luokka, aliluokka Peliobjektille,
 * Sisaltaa attribuuttina alustavan x-koordinaatin, jota kaytetaan liikeratojen laskemiseen,
 * Mukana myos ampumisen saatelyyn tarvittavat metodit
 */
public class Vihollinen extends PeliObjekti{
	
	private final int SET_X;
	//private final int SET_Y;
	private boolean VAS;

	
	public Vihollinen(int x, int y) {
		super(x, y);
		SET_X = x;
		//SET_Y = y;
		ampuminen = -1;

	}
	private int ampuminen;
	private boolean ammuskelu;
	/**
	 * getteri ammuskelulle
	 * @return true/false
	 */
	public boolean getAmmuskelu() {
		return this.ammuskelu;
	}
	
	/**
	 * setteri ammukselun saatelemiseen
	 * @param a=true kun ampuminen-muuttujalla asetetaan arvo, muuten false
	 */
	public void setAmmuskelu(boolean a) {
		this.ammuskelu = a;
	}
	
	/**
	 * getteri ampumisen aikavalille
	 * @return frame lkm minka jlkeen ammutaan
	 */
	public int getAmpuminen() {
		return this.ampuminen;
	}
	
	/**
	 * setteri ampumisen aikavalille
	 * @param a=frame lkm minka jlkeen ammutaan
	 */
	public void setAmpuminen(int a) {
		this.ampuminen = a;
	}
	
	public void liikkuminen() {
		int liike = 0;
		int max = this.SET_X +140;
		int min = this.SET_X;
		if (this.getX()==max) {
			VAS = true;
		}
		if (this.getX()==min) {
			VAS = false;
		}
		if (VAS) {
			liike = this.getX()-2;
		}
		else  {
			liike = this.getX()+2;
		}
		
		this.x = liike;
	}
	
}
