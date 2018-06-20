package com.example.crud.controller;

import com.example.crud.entity.Book;
import com.example.crud.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/books/api")
public class BookRestController {
    final Logger logger = LoggerFactory.getLogger(BookRestController.class);

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/all")
    public @ResponseBody
    List<Book> getAllBook() {
        return bookService.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Book findBookById(@PathVariable int id) {
        return bookService.findById(id);
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Book create(@RequestBody Book book) {
        logger.info("Creating book: " + book);
        bookService.save(book);
        logger.info("Book created successfully with info: " + book);

        return book;
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody
    Book update(@RequestBody Book book, @PathVariable int id) {
        logger.info("Updating book: " + book);
        Book updBook = bookService.update(book, id);
        logger.info("Book update successfully with info: " + book);
        return updBook;
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity<String> delete(@PathVariable int id) {
        logger.info("Deleting book with id: " + id);
        Book book = bookService.findById(id);
        bookService.delete(book);
        logger.info("book deleted successfully");
        return ResponseEntity.ok("deleted Book #" + id);
    }

    @GetMapping(path = "")
    public @ResponseBody
    Page<Book> getPageBooks(@RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "id") String sortBy,
                            @RequestParam(required = false, defaultValue = "ask") String order) {
        Sort sort;
        if (order.equals("desc")) sort = new Sort(Sort.Direction.DESC, sortBy);
        else sort = new Sort(Sort.Direction.ASC, sortBy);
        Integer pageNumber = (page > 0) ? page - 1 : 0;
        return bookService.findAllByPage(new PageRequest(pageNumber, 10, sort));
    }

    @GetMapping(path = "/search")
    public @ResponseBody
    Page<Book> search (
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ask") String order,
            @RequestParam(required = false, defaultValue = "") String term,
            @RequestParam(required = false, defaultValue = "0") int afterYear,
            @RequestParam(required = false, defaultValue = "") String ready
    ){
        Sort sort;
        if (order.equals("desc")) sort = new Sort(Sort.Direction.DESC, sortBy);
        else sort = new Sort(Sort.Direction.ASC, sortBy);
        Integer pageNumber = (page > 0) ? page - 1 : 0;
        PageRequest pageRequest = new PageRequest(pageNumber, 10, sort);
        if (!ready.equals("") && (ready.equals("true") || ready.equals("false"))){
            return bookService.search(term, afterYear, Boolean.parseBoolean(ready), pageRequest);
        }
        return bookService.search(term, afterYear, pageRequest);
    }
}
