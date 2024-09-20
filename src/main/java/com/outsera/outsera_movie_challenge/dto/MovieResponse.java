package com.outsera.outsera_movie_challenge.dto;

import java.util.List;

public record MovieResponse(List<BaseResponse> min, List<BaseResponse> max) {}
