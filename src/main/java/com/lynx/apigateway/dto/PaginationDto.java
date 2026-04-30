package com.lynx.apigateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaginationDto(
        @JsonProperty("total_records")
        Integer totalRecords,

        @JsonProperty("current_page")
        Integer currentPage,

        @JsonProperty("total_pages")
        Integer totalPages,

        @JsonProperty("limit")
        Integer limit
) {
}