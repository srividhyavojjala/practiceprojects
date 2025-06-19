package in.project.controller;

import in.project.entity.Book;
import in.project.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepo repo;

    @GetMapping("/searchBook")
    public ModelAndView LoadForm(@RequestParam(name = "id", required = false) Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");

        if (id == null) {
            mav.addObject("error", "Book ID is required.");
            return mav;
        }

        Optional<Book> bookid = repo.findById(id);
        if (bookid.isPresent()) {
            Book bookdata = bookid.get();
            mav.addObject("book", bookdata);
        } else {
            mav.addObject("error", "Book not found with ID: " + id);
        }

        return mav;
    }

}
