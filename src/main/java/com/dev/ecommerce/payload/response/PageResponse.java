package com.dev.ecommerce.payload.response;

import lombok.Data;

@Data
public class PageResponse {

    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean lastPage;
}
