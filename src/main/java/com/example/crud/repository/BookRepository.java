package com.example.crud.repository;

import com.example.crud.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
    @Query("SELECT t FROM Book t WHERE " +
            "(LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(t.autor) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) " +
            "AND t.printYear > :printYear")
    Page<Book> findBySearchParams(
            @Param("searchTerm") String searchTerm,
            @Param("printYear") int printYear,
            Pageable pageRequest
    );

    @Query("SELECT t FROM Book t WHERE " +
            "(LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(t.autor) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) " +
            "AND t.printYear > :printYear AND t.readAlready = :readAlReady")
    Page<Book> findBySearchParamsAndReadAlready(
            @Param("searchTerm") String searchTerm,
            @Param("printYear") int printYear,
            @Param("readAlReady") boolean readAlready,
            Pageable pageRequest
    );
}
