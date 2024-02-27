package com.devsuperior.movieflix.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.SpecificationTemplate;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

	@Autowired
	private MovieService service;

	@GetMapping
	public ResponseEntity<Page<?>> findAll(SpecificationTemplate.MovieSpec spec, @PageableDefault(page = 0, size = 10, sort = "title", direction = Sort.Direction.ASC)
													  Pageable pageable,
										   @RequestParam(value = "genreId", required = false) Long genreId) {
		if (genreId != null) {
			return ResponseEntity.ok().body(service.findAllPaged(SpecificationTemplate.byGenreId(genreId).and(spec), pageable));
		} else
		{
			return ResponseEntity.ok().body(service.findAllPaged(spec, pageable));
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		final var dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
}
