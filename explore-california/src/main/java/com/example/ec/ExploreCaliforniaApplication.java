package com.example.ec;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ec.domain.Difficulty;
import com.example.ec.domain.Region;
import com.example.ec.services.TourPackageService;
import com.example.ec.services.TourService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ExploreCaliforniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExploreCaliforniaApplication.class, args);
	}

	@Autowired
	private TourPackageService tourPackageService;

	@Autowired
	private TourService tourService;

	@Bean
	public CommandLineRunner runner() {
		return (args) -> {
			toursFromFile();
		};
	}

	private void toursFromFile() throws IOException {

		// Create default Tour Packages
		tourPackageService.createTourPackage("BC", "Backpack Cal");
		tourPackageService.createTourPackage("CC", "California Calm");
		tourPackageService.createTourPackage("CH", "California Hot springs");
		tourPackageService.createTourPackage("CY", "Cycle California");
		tourPackageService.createTourPackage("DS", "From Desert to Sea");
		tourPackageService.createTourPackage("KC", "Kids California");
		tourPackageService.createTourPackage("NW", "Nature Watch");
		tourPackageService.createTourPackage("SC", "Snowboard Cali");
		tourPackageService.createTourPackage("TC", "Taste of California");

		tourPackageService.lookupTourPackages().forEach(System.out::println);

		log.info("Number of imported Tour Packages = {}", tourPackageService.totalTourPackages());

		// Load Tours from File
		List<TourFromFile> toursFromFile = TourFromFile.importTours();

		toursFromFile.forEach(System.out::println);

		toursFromFile.forEach(t -> {
			log.debug("region: " + Region.findByLabel(t.region) + ", " + t.region);
			log.debug("difficulty: " + Difficulty.valueOf(t.difficulty));

			tourService.createTour(t.title, t.description, t.blurb, Integer.parseInt(t.price), t.length, t.bullets,
					t.keywords, t.packageType, Difficulty.valueOf(t.difficulty), Region.findByLabel(t.region));
		});

		log.info("Number of imported Tours = {}", tourService.totalTours());
	}

	@ToString(of = { "packageType", "title", "price", "difficulty", "region" })
	static class TourFromFile {
		// attributes as listed in the .json file
		private String packageType, title, description, blurb, price, length, bullets, keywords, difficulty, region;

		/**
		 * Open the ExploreCalifornia.json, unmarshal every entry into a TourFromFile
		 * Object.
		 *
		 * @return a List of TourFromFile objects.
		 * @throws IOException if ObjectMapper unable to open file.
		 */
		static List<TourFromFile> importTours() throws IOException {
			return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).readValue(
					TourFromFile.class.getResourceAsStream("/ExploreCalifornia.json"),
					new TypeReference<List<TourFromFile>>() {
					});
		}
	}
}
