package Model;

public class City {

	private String cityName;
	private String countryCode;
	
	@Override
	public String toString() {
		return cityName + ", "+ countryCode;
	}
	public City(String cityName, String countryCode) {
		super();
		this.cityName = cityName;
		this.countryCode = countryCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public City() {
		
	}
	
	
}
