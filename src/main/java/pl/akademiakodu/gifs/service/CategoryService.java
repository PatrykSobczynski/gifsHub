package pl.akademiakodu.gifs.service;

import pl.akademiakodu.gifs.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();

    // Metoda pokazujące kategorie za zmienną name.
    List<Category> findCategory(String name);

    List<Category> findGifsByCategories(Long id);
}

