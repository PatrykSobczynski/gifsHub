package pl.akademiakodu.gifs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.gifs.model.Category;
import pl.akademiakodu.gifs.model.Gif;
import pl.akademiakodu.gifs.service.CategoryService;
import pl.akademiakodu.gifs.service.GifService;

import java.util.List;

@Controller
@RequestMapping("/")
public class GifController {
    private GifService gifService;
    private CategoryService categoryService;

    // Autowired - tworzy nowy obiekt zamiast słów new GifService().
    @Autowired
    public GifController(GifService gifService, CategoryService categoryService) {
        this.gifService = gifService;
        this.categoryService = categoryService;
    }

    // Odpowiada za całego home'a
    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) String q) {
        if (q == null) {
            model.addAttribute("gifs", gifService.getGifs());
        } else {
            model.addAttribute("gifs", gifService.findGif(q));
        }
        return "home";
    }

    // Odpowiada za stronę details
    @GetMapping("/gif/{name}")
    public String getGif(Model model, @PathVariable String name) {
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("editedGif", gifService.findGifByName(name));
        return "gif-details";
    }

    // Odpowiada za zmiane tagu
    @PostMapping("/gif/{name}")
    public String changeTag(@ModelAttribute Gif editedGif) {
      //  Gif gif = gifService.findGifByName(name);
        gifService.changeTag(editedGif);
        return "redirect:/gif/{name}";
    }

    // Odpowiada za zmiane kategorii
    @PostMapping("/gif/{name}/updateCategory")
    public String changeCategory(@ModelAttribute Gif editedGif) {
        //Gif gif = gifService.findGifByName(name);
       // gif.setCategoryId(editedGif.getCategoryId());
        gifService.changeCategory(editedGif);

        /**
            gdyby operować na {id} a nie na {name}, to wyglądało by to tak:
            gifService.changeCategory(editedGif);
         **/

        return "redirect:/gif/{name}";
    }

    // Odpowiada za dodawanie/usuwanie z favorite
    @GetMapping("/gif/{name}/favorite")
    public String toggleFav(@PathVariable String name, @RequestParam(required = false, defaultValue = "") String r) {
        Gif gif = gifService.findGifByName(name);
        gifService.toggleFavorite(gif);
        if (r.equals("details")) {
            return "redirect:/gif/{name}";
        } else if (r.equals("favorites")) {
            return "redirect:/favorites";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/favorites")
    public String getFavorites(Model model, @RequestParam(required = false) String q) {
        if (q == null) {
            model.addAttribute("favoritesGifs", gifService.findFavorites());
        } else {
            model.addAttribute("favoritesGifs", gifService.findFavoritesGifs(q));
        }
        return "favorites";
    }
}


