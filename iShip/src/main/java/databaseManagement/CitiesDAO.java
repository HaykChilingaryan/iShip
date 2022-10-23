package databaseManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.City;

public class CitiesDAO {

	public List<City> getAllCities() {
		List<City> cities = new ArrayList<City>();
		try {
			Connection connection = DBConnection.getConnectionToDatabaseWorld();
			String getQueryString = "SELECT * FROM city WHERE Population > 1000000 ORDER BY Name";
			PreparedStatement statement = connection.prepareStatement(getQueryString);
			ResultSet results = statement.executeQuery();		
			while(results.next()) {
				City city = City.builder()
						.cityName(results.getString("Name"))
						.countryCode(results.getString("CountryCode"))
						.build();
				cities.add(city);
			}					
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return cities;		
	}
}