package by.book_aston.task2.web.controller;

import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.model.dto.book.BookDto;
import by.book_aston.task2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public String get(@PathVariable long id, Model model) {
        model.addAttribute("book",bookService.get(id));
        return "bookPage";
    }

    @PostMapping("/add")
    public long save(Model model){
        return bookService.add((BookDto) model.getAttribute("book"));
    }

    @PostMapping("/editName/{id}")
    public void editName(@PathVariable long id, Model model){
        bookService.editName(id, (String) model.getAttribute("name"));
    }
}
