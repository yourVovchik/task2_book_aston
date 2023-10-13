package by.book_aston.task2.web.controller;

import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorController {


    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/{id}")
    public String get(@PathVariable long id, Model model) {
        model.addAttribute("author",authorService.get(id));
        return "authorPage";
    }

    @PostMapping("/add")
    public long save(Model model){
        return authorService.add((AuthorDto) model.getAttribute("author"));
    }

    @PostMapping("/editName/{id}")
    public void editName(@PathVariable long id, Model model){
        authorService.editName(id, (String) model.getAttribute("name"));
    }

}
