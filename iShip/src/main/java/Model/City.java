package Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

	private String cityName;
	private String countryCode;
	
	@Override
	public String toString() {
		return cityName + ", "+ countryCode;
	}
	
}