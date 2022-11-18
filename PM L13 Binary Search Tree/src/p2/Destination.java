package p2;

public class Destination {
	private String country;
	private int riskFactor;

	public Destination(String country, int riskFactor) {
		super();
		this.country = country;
		this.riskFactor = riskFactor;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getRiskFactor() {
		return riskFactor;
	}

	public void setRiskFactor(int riskFactor) {
		this.riskFactor = riskFactor;
	}

	@Override
	public String toString() {
		return "Destination [country=" + country + ", riskFactor=" + riskFactor + "]";
	}

}
