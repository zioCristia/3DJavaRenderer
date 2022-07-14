package renderer.utility;

public class PackageCordinate {
	private boolean fragile = false;
	private double initialX;
	private double initialY;
	private double initialZ;
	private double finalX;
	private double finalY;
	private double finalZ;
	
	public PackageCordinate(int fragile, double initialX, double initialY, double initialZ,
			double finalX, double finalY, double finalZ) {
		if(fragile == 1)
			this.fragile = true;
		
		this.initialX = initialX;
		this.initialY = initialY;
		this.initialZ = initialZ;
		this.finalX = finalX;
		this.finalY = finalY;
		this.finalZ = finalZ;
	}
	public PackageCordinate(double initialX, double initialY, double initialZ,
			double finalX, double finalY, double finalZ) {
		this.initialX = initialX;
		this.initialY = initialY;
		this.initialZ = initialZ;
		this.finalX = finalX;
		this.finalY = finalY;
		this.finalZ = finalZ;
	}

	public boolean isFragile() {
		return fragile;
	}

	public void setFragile(boolean fragile) {
		this.fragile = fragile;
	}

	public double getInitialX() {
		return initialX;
	}

	public void setInitialX(double initialX) {
		this.initialX = initialX;
	}

	public double getInitialY() {
		return initialY;
	}

	public void setInitialY(double initialY) {
		this.initialY = initialY;
	}

	public double getInitialZ() {
		return initialZ;
	}

	public void setInitialZ(double initialZ) {
		this.initialZ = initialZ;
	}

	public double getFinalX() {
		return finalX;
	}

	public void setFinalX(double finalX) {
		this.finalX = finalX;
	}

	public double getFinalY() {
		return finalY;
	}

	public void setFinalY(double finalY) {
		this.finalY = finalY;
	}

	public double getFinalZ() {
		return finalZ;
	}

	public void setFinalZ(double finalZ) {
		this.finalZ = finalZ;
	}
}
