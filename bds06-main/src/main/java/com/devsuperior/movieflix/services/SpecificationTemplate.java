package com.devsuperior.movieflix.services;

import org.springframework.data.jpa.domain.Specification;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import com.devsuperior.movieflix.entities.Movie;

public class SpecificationTemplate {

    @And({
            @Spec(path = "title", spec = Equal.class)
    })
    public interface MovieSpec extends Specification<Movie> {}

    public static Specification<Movie> byGenreId(Long genreId) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("genre"), genreId);

    }
}
