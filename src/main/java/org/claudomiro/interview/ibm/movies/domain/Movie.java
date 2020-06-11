package org.claudomiro.interview.ibm.movies.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Value;

@Value
@JsonPropertyOrder({ "title", "year", "imdbID"})
public class Movie {
    @JsonProperty("Title")
    String title;
    @JsonProperty("Year")
    int year;
    String imdbID;
}
