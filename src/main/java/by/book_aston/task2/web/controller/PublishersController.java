package by.book_aston.task2.web.controller;

import by.book_aston.task2.model.dto.book.BookDto;
import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/publishers")
public class PublishersController {

    private final PublisherService publisherService;

    @Autowired
    public PublishersController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }


    @GetMapping("/{id}")
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("publisher",publisherService.get(id));
        return "publisherPage";
    }

    @PostMapping("/add")
    public long save(Model model){
        return publisherService.add((PublisherDto) model.getAttribute("publisher"));
    }

    @PostMapping("/editName/{id}")
    public void editName(@PathVariable long id, Model model){
        publisherService.editName(id, (String) model.getAttribute("name"));
    }
}
