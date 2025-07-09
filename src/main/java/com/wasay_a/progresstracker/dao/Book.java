package com.wasay_a.progresstracker.dao;

import java.util.ArrayList;
import java.util.List;

public class Book {
    public static enum Genre {
		ADVENTURE, BIOGRAPHY, YOUNG_ADULT, COOKING_FOOD, CRIME_FICTION, DETECTIVE, DYSTOPIAN, 
		ESSAYS, FANTASY, HISTORICAL_FICTION, HISTORY, HORROR, HUMOR, LEGAL, LITERARY_FICTION, 
		MEMOIR, MYSTERY, NON_FICTION, PARANORMAL, PHILOSOPHY, PICTURE_BOOKS, POLITICAL, PSYCHOLOGICAL_THRILLER, 
		ROMANCE, SCIENCE, SCIENCE_FICTION, SELF_HELP, SPORTS, SUSPENSE, THRILLER, TRUE_CRIME, FICTION, GOTHIC_FICTION, 
		POETRY, DRAMA, COMEDY, MYTHOLOGY, DIARY, NOVEL, SOUTHERN_GOTHIC, TRAGEDY, COMING_OF_AGE, CLASSIC, GRAPHIC_NOVEL,
        CHILDREN_LITERATURE, SHORT_STORIES, FABLES, FAIRY_TALES, FANTASY_FICTION, PARANORMAL_ROMANCE, URBAN_FANTASY,
        HISTORICAL_ROMANCE, SCIENCE_FICTION_FANTASY, STEAMPUNK, PHILOSOPHICAL, PLAY
	}

    private int id;
    private String title;
    private String status;
    private String isbn;
    private int pageCount;
    private List<Author> authors = new ArrayList<>();
    private List<Genre>  genres  = new ArrayList<>();


    public Book(int id, String title) {
        this(id, title, "not started");
    }

    public Book(int id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }
    

    public Book(int id, String title, String status, String isbn, int pageCount, List<Author> authors, List<Genre> genres) {
        super();
        this.id = id;
        this.title = title;
        this.status = status;
        this.isbn = isbn;
        this.authors = authors;
        this.genres = genres;
        this.pageCount = pageCount;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = new ArrayList<>(authors);
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = new ArrayList<>(genres);
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", status=" + status + ", isbn=" + isbn + ", pageCount=" + pageCount + ", authors=" + authors + ", genres=" + genres + "]";
    }

}
