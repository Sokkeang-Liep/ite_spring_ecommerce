package co.istad.sokkeang.ecommerce.service;

import co.istad.sokkeang.ecommerce.dto.CategoryResponse;
import co.istad.sokkeang.ecommerce.dto.CreateCategoryRequest;

public interface CategoryService {

    CategoryResponse createNew(CreateCategoryRequest createCategoryRequest);



}
