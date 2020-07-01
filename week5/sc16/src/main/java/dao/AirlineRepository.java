package dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import flights.Airline;

public interface AirlineRepository extends Repository<Airline, Integer> {

	// TODO : find airline by ID
	Airline findById(Integer id);
	
	// TODO : find all in a page
	Page<Airline> findAll( Pageable pageable );
	
	// TODO : find all by country
	Page<Airline> findByCountry( String country, Pageable pageable );
	
	// TODO : find all existing countries	
	@Query("Select distinct(al.country) from Airline al Order By al.country")
	List<String> selectDistinctCountries();
	
}
