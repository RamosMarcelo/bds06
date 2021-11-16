package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findReviewsOfMovie(Long id) throws ResourceNotFoundException {
		List<Review> list = reviewRepository.findReviewsOfMovie(id);
		if (list.isEmpty()) throw new ResourceNotFoundException("Entity not found");
		return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
	}

	@Transactional
	public ReviewDTO insert(@Valid ReviewDTO dto) {
		Review entity = new Review();
		copyDtoToEntity(dto, entity);
		entity.setUser(authService.authenticated());
		entity = reviewRepository.save(entity);
		return new ReviewDTO(entity);
	}
	
	private void copyDtoToEntity(ReviewDTO dto, Review entity) {
	   entity.setText(dto.getText());
	   MovieMinDTO movieDto = new MovieMinDTO();
	   movieDto.setId(dto.getMovieId());
	   Movie movie = movieRepository.getOne(movieDto.getId());
	   entity.setMovie(movie);
	}
	
}
