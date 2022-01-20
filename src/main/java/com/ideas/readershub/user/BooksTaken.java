package com.ideas.readershub.user;

import com.ideas.readershub.library.entity.Book;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class BooksTaken {
    private Book book;
    private LocalDate bookWithdrawDate;
    private LocalDate bookReturnDate;
}
