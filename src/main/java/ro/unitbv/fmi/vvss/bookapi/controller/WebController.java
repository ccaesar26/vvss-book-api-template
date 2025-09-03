package ro.unitbv.fmi.vvss.bookapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.unitbv.fmi.vvss.bookapi.model.Book;
import ro.unitbv.fmi.vvss.bookapi.service.BookService;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final BookService bookService;

    @GetMapping("/books")
    public String showBooks(@RequestParam(required = false) String title,
                            @RequestParam(required = false) String author,
                            Model model) {
        model.addAttribute("books", bookService.searchBooks(title, author));
        model.addAttribute("titleParam", title);
        model.addAttribute("authorParam", author);
        return "books";
    }

    @GetMapping("/books/new")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/books")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books"; // Redirect dupa POST pentru a evita re-trimiterea
    }
}