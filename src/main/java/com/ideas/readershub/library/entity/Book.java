package com.ideas.readershub.library.entity;

import com.ideas.readershub.library.enums.Category;
import com.ideas.readershub.library.enums.Language;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
    private String bookName;
    private Category category;
    private Language language;
}
