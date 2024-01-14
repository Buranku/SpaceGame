package peliObjektit;

/**
 * luokka vihollisten ammuksille
 */
public class AmmusV extends PeliObjekti{
	public AmmusV(int x, int y, int liikeX, int liikeY) {
		super(x, y, liikeX, liikeY);
	}

	public void liikkuminen() {
		this.y = this.y + this.getLiikeY();
	}
}
