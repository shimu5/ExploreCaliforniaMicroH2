package com.spring.microservice.ec;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.microservice.ec.MyRunner.TourFromFile;
import com.spring.microservice.ec.domain.Difficulty;
import com.spring.microservice.ec.domain.Region;
import com.spring.microservice.ec.services.TourPackageServices;
import com.spring.microservice.ec.services.TourService;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Component
public class MyRunner implements CommandLineRunner {
	//http://zetcode.com/springboot/commandlinerunner/

	private static final Logger logger = LoggerFactory.getLogger(ExplorecaliApplication.class);


	@Autowired
	private TourPackageServices tourPackageService;

	@Autowired
	private TourService tourService;


	@Override
	public void run(String... args) throws Exception {

		createTourPackages();
		long numOfPackages = tourPackageService.total();
		System.out.println("total packages"+numOfPackages);

	}


	public void createTourPackages() {

		//Create the default tour packages
		tourPackageService.createTourPackage("BC", "Backpack Cal");
		tourPackageService.createTourPackage("CC", "California Calm");
		tourPackageService.createTourPackage("CH", "California Hot springs");
		tourPackageService.createTourPackage("CY", "Cycle California");
		tourPackageService.createTourPackage("DS", "From Desert to Sea");
		tourPackageService.createTourPackage("KC", "Kids California");
		tourPackageService.createTourPackage("NW", "Nature Watch");
		tourPackageService.createTourPackage("SC", "Snowboard Cali");
		tourPackageService.createTourPackage("TC", "Taste of California");






		tourPackageService.lookup().forEach(tourPackage -> System.out.println(tourPackage));
		try {

			/*List<TourFromFile> tours = TourFromFile.importTours();
			for(TourFromFile tour : tours) {
				System.out.println("--"+ Region.findByLabel(tour.region));
				//System.out.println(tour.description);
			}*/
			TourFromFile.importTours().forEach(
					t -> tourService.createTour(
							t.title, t.description, t.blurb,
							Double.parseDouble(t.price), t.length, t.bullets,t.keywords,
							t.packageType,
							Difficulty.valueOf(t.difficulty),
							Region.findByLabel(t.region)
						));
		}catch(Exception e) {
			System.out.println("Exception in Input from json"+e);
		}
		System.out.println("Total tours " + tourService.total());

	}



	/**
	 * Helper class to import the records in the ExploreCalifornia.json
	 */
	static class TourFromFile {
		private static FileWriter file;
		//attributes as listed in the .json file
		private String packageType, title, description, blurb, price, length, bullets, keywords,  difficulty, region;

		@Override
		public String toString() {
			return "TourFromFile [packageType=" + packageType + ", title=" + title + ", description=" + description
					+ ", blurb=" + blurb + ", price=" + price + ", length=" + length + ", bullets=" + bullets
					+ ", keywords=" + keywords + ", difficulty=" + difficulty + ", region=" + region + "]";
		}

		/**
		 * Open the ExploreCalifornia.json, unmarshal every entry into a TourFromFile Object.
		 *
		 * @return a List of TourFromFile objects.
		 * @throws IOException if ObjectMapper unable to open file.
		 */
		static List<TourFromFile> importTours() {
			try {
				// URL resource = TourFromFile.class.getResource("/exploreCalifornia.json");
				// System.out.println(resource.getPath());
				//Paths.get(resource.toURI()).toFile();
				//TourFromFile.createAJsonFile();

				return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, 
						JsonAutoDetect.Visibility.ANY).readValue(
								TourFromFile.class.getResourceAsStream("/exploreCalfornia.json"),
								new TypeReference<List<TourFromFile>>(){});
			}catch(IOException e) {
				System.out.println("Exception in read file"+e);
			}
			return null;
		}

		public static void createAJsonFile() {
			// JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			JSONObject obj = new JSONObject();
			obj.put("Name", "Crunchify.com");
			obj.put("Author", "App Shah");

			JSONArray company = new JSONArray();
			company.add("Company: Facebook");
			company.add("Company: PayPal");
			company.add("Company: Google");
			obj.put("Company List", company);
			try {

				// Constructs a Fi/leWriter given a file name, using the platform's default charset
				file = new FileWriter("/crunchify.txt");

				file.write(obj.toJSONString());
				CrunchifyLog("Successfully Copied JSON Object to File...");
				CrunchifyLog("\nJSON Object: " + obj);

			} catch (IOException e) {
				e.printStackTrace();

			} finally {

				try {
					file.flush();
					file.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		static public void CrunchifyLog(String str) {
			System.out.println("str");
		}
	}

	

}
