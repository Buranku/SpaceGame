package peliObjektit;
/**
 * Luokka ammuksille
 */
public class Ammus extends PeliObjekti{
	
	public Ammus(int x, int y, int liikeX, int liikeY) {
		super(x, y, liikeX, liikeY);
	}
	

	public void liikkuminen() {
		//if (this.y > 0) {
			//this.y -= 4;
		//}
		this.y = this.y + this.getLiikeY();
		//this.setY(this.getY() + liikeY);
	}

}
