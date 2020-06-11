package org.claudomiro.interview.ibm.movies;

import org.claudomiro.interview.ibm.movies.domain.MoviePage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class MoviesApplication {


	private static final String BASE_URL = "https://jsonmock.hackerrank.com/api/movies/";

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate)  {
		return args -> runMovies(restTemplate);
	}

	private void runMovies(RestTemplate restTemplate) {
		String searchString = readStringFromStdIn();

		List<String> titleList = new ArrayList<>();

		MoviePage page1 = getPage(restTemplate, searchString, 1);
		addTitles(titleList, page1);
		for(int pageNumber =2; pageNumber <= page1.getTotalPages(); pageNumber++) {
			addTitles(titleList, getPage(restTemplate, searchString, pageNumber));
		}
		Collections.sort(titleList);
		System.out.println(titleList);
	}

	private String readStringFromStdIn() {
		try (Scanner scanner = new Scanner(System.in)) {
			return scanner.next();
		}
	}

	private void addTitles(List<String> titleList, MoviePage moviePage) {
		moviePage.getData().forEach(movie -> titleList.add(movie.getTitle()));
	}

	private MoviePage getPage(RestTemplate restTemplate, String searchString, int page) {
		String url = String.format(BASE_URL + "search/?Title=%s&page=%d", searchString, page);
		return restTemplate.getForObject(url, MoviePage.class);
	}
}
