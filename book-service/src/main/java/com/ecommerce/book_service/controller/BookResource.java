package com.ecommerce.book_service.controller;

import com.ecommerce.book_service.dto.RequestDto;
import com.ecommerce.book_service.dto.ResponseDto;
import com.ecommerce.book_service.entity.Book;
import com.ecommerce.book_service.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookResource {

    private final BookService bookService;

    // RequestDto in → RequestDto out
    @PostMapping
    public ResponseEntity<RequestDto> addBook(@RequestBody RequestDto requestDto) {
        System.out.println("controller reached to controller method");
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(requestDto));
    }

    // No input → List<ResponseDto> out
    @GetMapping
    public ResponseEntity<List<ResponseDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // bookId → ResponseDto out
    @GetMapping("/{bookId}")
    public ResponseEntity<ResponseDto> getById(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    // keyword → List<ResponseDto> out
    @GetMapping("/search")
    public ResponseEntity<List<ResponseDto>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(bookService.searchBooks(keyword));
    }

    // genre → List<ResponseDto> out
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<ResponseDto>> getByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(bookService.getByGenre(genre));
    }

    // author → List<ResponseDto> out
    @GetMapping("/author/{author}")
    public ResponseEntity<List<ResponseDto>> getByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(bookService.getByAuthor(author));
    }

    // No input → List<ResponseDto> out
    @GetMapping("/featured")
    public ResponseEntity<List<ResponseDto>> getFeatured() {
        return ResponseEntity.ok(bookService.getFeaturedBooks());
    }

    // bookId + Book body → ResponseDto out
    @PutMapping("/{bookId}")
    public ResponseEntity<ResponseDto> updateBook(@PathVariable Long bookId,
                                                  @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(bookId, book));
    }

    // bookId + stock param → ResponseDto out
    @PutMapping("/{bookId}/stock")
    public ResponseEntity<ResponseDto> updateStock(@PathVariable Long bookId,
                                                   @RequestParam Integer stock) {
        return ResponseEntity.ok(bookService.updateStock(bookId, stock));
    }

    // bookId → 204 No Content (cleaner than returning a plain string)
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
}