package com.ecommerce.book_service.service;

import com.ecommerce.book_service.dto.RequestDto;
import com.ecommerce.book_service.dto.ResponseDto;
import com.ecommerce.book_service.entity.Book;

import java.util.List;


public interface BookService {

    RequestDto addBook(RequestDto book);
    List<ResponseDto> getAllBooks();
    ResponseDto getBookById(Long bookId);
    List<ResponseDto> searchBooks(String keyword);
    List<ResponseDto> getByGenre(String genre);
    List<ResponseDto> getByAuthor(String author);
    ResponseDto updateBook(Long bookId, Book book);
    void deleteBook(Long bookId);
    ResponseDto updateStock(Long bookId, Integer stock);
    List<ResponseDto> getFeaturedBooks();

}
