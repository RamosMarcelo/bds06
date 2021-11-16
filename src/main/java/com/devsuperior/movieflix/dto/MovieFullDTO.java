package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;

public class MovieFullDTO extends MovieMinDTO {
	private static final long serialVersionUID = 1L;

	private String synopsis;
	private GenreDTO genre;
	
	public MovieFullDTO() {
	}

	public MovieFullDTO(Long id, String title, String subTitle, Integer year,
			String imgUrl, String synopsis, GenreDTO genre) {
		super(id, title, subTitle, year, imgUrl);
		this.synopsis = synopsis;
		this.genre = genre;
	}
	
	public MovieFullDTO(Movie entity, Genre genre) {
		super(entity);
		synopsis = entity.getSynopsis();
		this.genre = new GenreDTO(genre);
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

}
