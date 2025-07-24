package com.example.BlogPlatform.mappers;


import com.example.BlogPlatform.domain.PostStatus;
import com.example.BlogPlatform.domain.dto.CategoryDto;
import com.example.BlogPlatform.domain.dto.CreateCategoryRequest;
import com.example.BlogPlatform.domain.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto (Category category);

    @Named("calculatePostCount")
     default long calculatePostCount(java.util.List<com.example.BlogPlatform.domain.entities.Post> posts){
        if(posts == null) return 0;
        return posts.stream()
                .filter(post -> PostStatus.Published.equals((post.getStatus())))
                .count();
    }

    Category toEntity(CreateCategoryRequest createCategoryRequest);
}
