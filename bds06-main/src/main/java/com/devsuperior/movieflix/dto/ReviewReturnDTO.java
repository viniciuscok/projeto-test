package com.devsuperior.movieflix.dto;

public class ReviewReturnDTO {

    private Long id;
    private String text;
    private Long movieId;
    private UserDTO user;

    public ReviewReturnDTO(Long id, String text, Long movieId, UserDTO user) {
        this.id = id;
        this.text = text;
        this.movieId = movieId;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Long getMovieId() {
        return movieId;
    }

    public UserDTO getUser() {
        return user;
    }
}
