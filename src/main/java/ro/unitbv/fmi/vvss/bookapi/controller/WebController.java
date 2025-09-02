package ro.unitbv.fmi.vvss.bookapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.unitbv.fmi.vvss.bookapi.service.BookService;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final BookService bookService;

    @GetMapping("/books")
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books"; // Numele fisierului HTML din templates
    }
}