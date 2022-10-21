package Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
	public City() {}
}
