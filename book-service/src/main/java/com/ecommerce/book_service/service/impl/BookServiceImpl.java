package com.ecommerce.book_service.service.impl;

import com.ecommerce.book_service.dto.RequestDto;
import com.ecommerce.book_service.dto.ResponseDto;
import com.ecommerce.book_service.entity.Book;
import com.ecommerce.book_service.mapper.impl.SignUpRequestMapper;
import com.ecommerce.book_service.mapper.impl.UserResponseMapper;
import com.ecommerce.book_service.repository.BookRepository;
import com.ecommerce.book_service.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final SignUpRequestMapper signUpRequestMapper;
    private final UserResponseMapper userResponseMapper;

    @Override
    public RequestDto addBook(RequestDto requestDto) {
        Book book = signUpRequestMapper.mapTo(requestDto);  // RequestDto → Book
        Book saved = bookRepository.save(book);
        System.out.println("Controller reached to service method");
        return signUpRequestMapper.mapFrom(saved);          // Book → RequestDto
    }

    @Override
    public List<ResponseDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return userResponseMapper.mapToList(books);         // List<Book> → List<ResponseDto>
    }

    @Override
    public ResponseDto getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        return userResponseMapper.mapTo(book);              // Book → ResponseDto
    }

    @Override
    public List<ResponseDto> searchBooks(String keyword) {
        List<Book> books = bookRepository.searchByKeyword(keyword);
        return userResponseMapper.mapToList(books);
    }

    @Override
    public List<ResponseDto> getByGenre(String genre) {
        List<Book> books = bookRepository.findByGenreIgnoreCase(genre);
        return userResponseMapper.mapToList(books);
    }

    @Override
    public List<ResponseDto> getByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);
        ; // fixed typo
        return userResponseMapper.mapToList(books);
    }

    @Override
    public ResponseDto updateBook(Long bookId, Book updated) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        // Do NOT set bookId — keep the original ID
        book.setAuthor(updated.getAuthor());
        book.setPrice(updated.getPrice());
        book.setStock(updated.getStock());
        book.setGenre(updated.getGenre());
        book.setDescription(updated.getDescription());
        book.setCoverImageUrl(updated.getCoverImageUrl());

        Book saved = bookRepository.save(book);
        return userResponseMapper.mapTo(saved);             // Book → ResponseDto
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public ResponseDto updateStock(Long bookId, Integer stock) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        book.setStock(stock);
        Book saved = bookRepository.save(book);
        return userResponseMapper.mapTo(saved);             // Book → ResponseDto
    }

    @Override
    public List<ResponseDto> getFeaturedBooks() {
        List<Book> books = bookRepository.findByFeaturedTrue();
        return userResponseMapper.mapToList(books);
    }

}
