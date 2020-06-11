package org.claudomiro.interview.ibm.movies;

import org.claudomiro.interview.ibm.movies.domain.MoviePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		Scanner scanner = new Scanner(System.in);
		String searchString = scanner.next();
		scanner.close();

		List<String> titleList = new ArrayList<>();

		MoviePage page1 = getPage(restTemplate, searchString, 1);
		addTitles(titleList, page1);
		for(int pageNumber =2; pageNumber <= page1.getTotalPages(); pageNumber++) {
			addTitles(titleList, getPage(restTemplate, searchString, pageNumber));
		}
		Collections.sort(titleList);
		System.out.println(titleList);
	}

	private void addTitles(List<String> titleList, MoviePage moviePage) {
		moviePage.getData().forEach(movie -> titleList.add(movie.getTitle()));
	}

	private MoviePage getPage(RestTemplate restTemplate, String searchString, int page) {
		return restTemplate.getForObject(
				"https://jsonmock.hackerrank.com/api/movies/search/?Title=" +
						searchString +
						"&page=" +
						page, MoviePage.class);
	}
}
