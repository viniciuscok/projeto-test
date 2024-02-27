package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.ReviewReturnDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MovieRepository movieRepository;

	@Transactional
	public ReviewReturnDTO create(final ReviewDTO dto) {
		final String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		final var user = userRepository.findByEmail(email);
		final var movie = movieRepository.findById(dto.getMovieId()).get();

		final var review = repository.save(new Review(null, dto.getText(), movie, user));

		return new ReviewReturnDTO(review.getId(), review.getText(), review.getMovie().getId(), new UserDTO(review.getUser()));
	}
}
