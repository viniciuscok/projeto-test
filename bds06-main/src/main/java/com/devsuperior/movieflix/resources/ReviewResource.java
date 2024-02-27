package com.devsuperior.movieflix.resources;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.ReviewReturnDTO;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewResource {

	@Autowired
	private ReviewService service;

	@PostMapping
	public ResponseEntity<ReviewReturnDTO> create(@Valid @RequestBody final ReviewDTO dto) {
		final var reviewReturnDTO = service.create(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(reviewReturnDTO);
	}
} 
