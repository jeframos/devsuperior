package com.devsuperior.movieflix.projections;

import com.devsuperior.movieflix.entities.Movie;

public interface MovieProjection extends IdProjection<Long> {

    String getName();
}
