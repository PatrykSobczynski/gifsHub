package pl.akademiakodu.gifs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.akademiakodu.gifs.model.Category;
import pl.akademiakodu.gifs.model.Gif;
import pl.akademiakodu.gifs.service.CategoryService;
import pl.akademiakodu.gifs.service.GifService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CategoryController {
    private CategoryService categoryService;
    private GifService gifService;

    @Autowired
    public CategoryController(CategoryService categoryService, GifService gifService) {
        this.categoryService = categoryService;
        this.gifService = gifService;
    }

    @GetMapping("/categories")
    public String getCategories(Model model, String q) {
        if (q == null) {
            model.addAttribute("category", categoryService.getCategories());
        } else {
            model.addAttribute("category", categoryService.findCategory(q));
        }
        return "categories";
    }

    @GetMapping("/category/{id}")
    public String gifGifsByCategoryId(Model model, @PathVariable Long id) {
        model.addAttribute("categoryGifs", gifService.findGifsByCategory(id));
        return "categoryGifs";
    }
}

