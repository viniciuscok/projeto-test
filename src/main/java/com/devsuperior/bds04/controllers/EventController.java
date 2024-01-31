package com.devsuperior.bds04.controllers;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;
//import com.devsuperior.dscatalog.dto.EventDTO;
//import com.devsuperior.dscatalog.services.EventService;


@RestController
@RequestMapping(value = "/events")
public class EventController {

	@Autowired
	private EventService service;
	
	@GetMapping
	public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable) {
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name"));
		Page<EventDTO> list = service.findAll(pageRequest);
		return ResponseEntity.ok().body(list);
	}

//	@GetMapping(value = "/{id}")
//	public ResponseEntity<EventDTO> findById(@PathVariable Long id) {
//		EventDTO dto = service.findById(id);
//		return ResponseEntity.ok().body(dto);
//	}
	
	@PostMapping
	public ResponseEntity<EventDTO> insert(@RequestBody @Valid EventDTO dto) throws Exception {
		try {
			dto = service.insert(dto);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
			return ResponseEntity.created(uri).body(dto);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

//	@PutMapping(value = "/{id}")
//	public ResponseEntity<EventDTO> update(@PathVariable Long id, @Valid @RequestBody EventDTO dto) {
//		dto = service.update(id, dto);
//		return ResponseEntity.ok().body(dto);
//	}
//
//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<Void> delete(@PathVariable Long id) {
//		service.delete(id);
//		return ResponseEntity.noContent().build();
//	}
} 
