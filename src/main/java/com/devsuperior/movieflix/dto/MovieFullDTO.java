package com.devsuperior.movieflix.dto;

import java.util.ArrayList;
import java.util.List;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;

public class MovieFullDTO extends MovieMinDTO {
	private static final long serialVersionUID = 1L;

	private String synopsis;
	private GenreDTO genre;
	private List<ReviewDTO> reviews = new ArrayList<>();
	
	public MovieFullDTO() {
	}

	public MovieFullDTO(Long id, String title, String subTitle, Integer year,
			String imgUrl, String synopsis, GenreDTO genre) {
		super(id, title, subTitle, year, imgUrl);
		this.synopsis = synopsis;
		this.genre = genre;
	}

	public MovieFullDTO(Movie entity) {
		super(entity);
		synopsis = entity.getSynopsis();
		genre = new GenreDTO(entity.getGenre());
		
		for (Review review : entity.getReviews()) {
			reviews.add(new ReviewDTO(review));
		}
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public GenreDTO getGenre() {
		return genre;
	}

	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}

	public List<ReviewDTO> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewDTO> reviews) {
		this.reviews = reviews;
	}

}
