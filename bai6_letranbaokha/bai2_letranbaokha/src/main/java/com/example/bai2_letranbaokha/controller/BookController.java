package com.example.bai2_letranbaokha.controller;

import com.example.bai2_letranbaokha.model.Book;
import com.example.bai2_letranbaokha.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Hiển thị danh sách sách
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        // Sửa từ "books" thành "book/books"
        return "book/books";
    }

    // Hiển thị form thêm sách mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        // Sửa từ "add-book" thành "book/add-book"
        return "book/add-book";
    }

    // Xử lý thêm sách mới có Validation
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            // Nếu có lỗi, trả về trang add trong thư mục book
            return "book/add-book";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }

    // Hiển thị form chỉnh sửa sách
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            // Sửa từ "edit-book" thành "book/edit-book"
            return "book/edit-book";
        } else {
            return "redirect:/books";
        }
    }

    // Xử lý cập nhật sách
    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable("id") Long id,
                             @Valid @ModelAttribute("book") Book book,
                             BindingResult result) {
        if (result.hasErrors()) {
            book.setId(id);
            // Sửa từ "edit-book" thành "book/edit-book"
            return "book/edit-book";
        }
        bookService.updateBook(book);
        return "redirect:/books";
    }

    // Xóa sách
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}