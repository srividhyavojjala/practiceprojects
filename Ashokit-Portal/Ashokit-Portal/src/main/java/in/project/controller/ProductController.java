package in.project.controller;

import in.project.entity.Product;
import in.project.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepo repo;

    @GetMapping("/form")
    public String loadForm(Model model){
        model.addAttribute("product",new Product());
        return "index";
    }

    @PostMapping("/product")
    public String saveData(@Validated @ModelAttribute("product") Product p, BindingResult result, Model model){

        if(result.hasErrors()){
            return "index";
        }
        repo.save(p);
        model.addAttribute("msg","Data saved");
        model.addAttribute("product",new Product());
        return "index";
    }

    @GetMapping("/products")
    public String getAllTheData(Model model){
        List<Product> list = repo.findAll();
        model.addAttribute("alldata",list);
        return "data";
    }

    @GetMapping("/delete")
    public String deleteData(@RequestParam("id") Integer id, Model model){
        repo.deleteById(id);
        model.addAttribute("msg","data deleted");
        model.addAttribute("products",repo.findAll());
        return "data";
    }
}
