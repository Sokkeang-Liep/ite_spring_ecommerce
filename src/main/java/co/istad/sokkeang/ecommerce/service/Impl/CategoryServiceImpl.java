package co.istad.sokkeang.ecommerce.service.Impl;

import co.istad.sokkeang.ecommerce.domain.Category;
import co.istad.sokkeang.ecommerce.dto.CategoryResponse;
import co.istad.sokkeang.ecommerce.dto.CreateCategoryRequest;
import co.istad.sokkeang.ecommerce.repository.CategoryRepository;
import co.istad.sokkeang.ecommerce.service.CategoryService;
import co.istad.sokkeang.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j  //for log
@RequiredArgsConstructor   //this annotation (shortcut for code injection dependency)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

//    //*** inject dependency (replace by @RequiredArgsConstructor)***
//    private final CategoryRepository categoryRepository;
//    public CategoryServiceImpl(CategoryRepository categoryRepository){
//        this.categoryRepository = categoryRepository;
//    }

    @Override
    public CategoryResponse createNew(CreateCategoryRequest createCategoryRequest) {
        log.info("createNew {}", createCategoryRequest);

        //validate category name
        boolean isExisting = categoryRepository.existsByName(createCategoryRequest.name());
        if (isExisting)
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category has already been used"
            );
        Category parentCategory = null;
        CategoryResponse parentCategoryResponse = null;

        //validate parent category (mean ot)
        if (createCategoryRequest.parentCategoryId() != null) {
            parentCategory = categoryRepository.findById(createCategoryRequest.parentCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Parent category has not been found"
                    ));
//            parentCategoryResponse = CategoryResponse.builder()
//                    .id(parentCategory.getId())
//                    .name(parentCategory.getName())
//                    .description(parentCategory.getDescription())
//                    .icon(parentCategory.getIcon())
//                    .is_deleted(parentCategory.getIsDeleted())
//                    .build();
        }

        //DTO don't exist so it is a System generated dat
        Category category = categoryMapper.mapCreateCategoryRequestToCategory(createCategoryRequest);
        category.setIsDeleted(false);
        category.setParentCategory(parentCategory);

        //insert if primary key is null
        //update if primary key has value
        category = categoryRepository.save(category);

        return categoryMapper.mapCategoryToCategoryResponse(category);

        //map from domain to dto (so we cut it by using annotation Mapping)
//        return CategoryResponse.builder()
//                .id(category.getId())
//                .description(category.getDescription())
//                .icon(category.getIcon())
//                .is_deleted(category.getIsDeleted())
//                .parentCategory(parentCategoryResponse)
//                .build();
    }

}
