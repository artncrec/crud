package com.example.crud.controller;

import com.example.crud.entity.Book;
import com.example.crud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping(path = "/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(path = "")
    public String viewBooksList(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ask") String order,
            @RequestParam(required = false, defaultValue = "") String term,
            @RequestParam(required = false, defaultValue = "0") int afterYear,
            @RequestParam(required = false, defaultValue = "") String ready,
            Model model
    ) {
        Sort sort;
        if (order.equals("desc")) sort = new Sort(Sort.Direction.DESC, sortBy);
        else sort = new Sort(Sort.Direction.ASC, sortBy);
        Integer pageNumber = (page > 0) ? page - 1 : 0;
        PageRequest pageRequest = new PageRequest(pageNumber, 8, sort);
        Page<Book> books;

        if (!ready.equals("") && (ready.equals("true") || ready.equals("false")))
            books = bookService.search(term, afterYear, Boolean.parseBoolean(ready), pageRequest);
        else
            books = bookService.search(term, afterYear, pageRequest);
        model.addAttribute("books", books);
        model.addAttribute("current", pageNumber);
        model.addAttribute("term", term);
        model.addAttribute("year", afterYear);
        model.addAttribute("ready", ready);
        return "books/list";
    }

    @GetMapping(path = "/{id}")
    public String viewBook(@PathVariable int id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "books/view";
    }

    @PostMapping(path = "/delete/{id}")
    public String deleteBook(@PathVariable int id){
        Book book = bookService.findById(id);
        bookService.delete(book);
        return "redirect:/books";
    }

    @PostMapping(path = "/ready/{id}")
    public String isReadyBook(@PathVariable int id, RedirectAttributes redirectAttributes){
        Book book = bookService.findById(id);
        book.setReadAlready(true);
        bookService.save(book);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/books/{id}";
    }

    @GetMapping(path = "/edition/{id}")
    public String newEditionBook(@PathVariable int id, Model model){
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "books/edition";
    }

    @PostMapping(path = "/edition/{id}")
    public String editionSubmit(
            @ModelAttribute Book book,
            @PathVariable int id,
            @RequestParam MultipartFile file,
            RedirectAttributes redirectAttributes
    ){
        if(!file.isEmpty()){
            String fileName = file.getOriginalFilename();
            try {
                book.setImageData(file.getBytes());
                book.setImageStr(fileName);
            } catch (IOException e) {}
        }
        bookService.update(book, id);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/books/{id}";
    }

    @GetMapping(path = "/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "books/newBook";
    }

    @PostMapping(path = "/add")
    public String addSubmit(
            @ModelAttribute Book book,
            @RequestParam MultipartFile file
    ){
        if(!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            try {
                book.setImageData(file.getBytes());
                book.setImageStr(fileName);
            } catch (IOException e) {
            }
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping(path = "/{id}/image")
    @ResponseBody
    public ResponseEntity<byte[]> getImageData(@PathVariable int id){

        byte[] imageData = bookService.findById(id).getImageData();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                .header(HttpHeaders.CACHE_CONTROL, CacheControl.noCache().getHeaderValue())
                .body(imageData);
    }
}
