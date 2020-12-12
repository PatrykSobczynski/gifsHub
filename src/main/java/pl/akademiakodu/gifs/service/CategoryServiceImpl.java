package pl.akademiakodu.gifs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.akademiakodu.gifs.model.Category;
import pl.akademiakodu.gifs.repository.CategoryDao;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;
    private List<Category> categories;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
        categories = categoryDao.findAllCategories();
    }

    public CategoryServiceImpl() {
    }

    @Override
    public List<Category> getCategories() {
        return categoryDao.findAllCategories();
    }

    // Metoda pokazujące kategorie za zmienną name.
    @Override
    public List<Category> findCategory(String name) {
        return categories.stream().filter(category -> category.getName().equals(name.toUpperCase())).collect(Collectors.toList());
    }

    @Override
    public List<Category> findGifsByCategories(Long id) {
        return categories.stream()
                .filter(category -> category.getId().equals(id)).collect(Collectors.toList());
    }
//  ^^^^^^^^^^^^^^^^^^^^^^^^^ Poprawiona wersja tego poniżej ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//    @Override
//    public List<Category> findGifsByCategories(Long id) {
//        return categories.stream()
//                .filter(category -> category.getId() != null ? category.getId().equals(id) : category.getId().equals(id))
//                .collect(Collectors.toList());
//    }

    // ---------------------------------------------------------------------------------------------

    /*
    albo jestem głupi albo czegoś nie widzę :D
    category.getId() != null ? category.getId().equals(id) : category.getId().equals(id)
    to to samo co
    if (category.getId() != null) {
        category.getId().equals(id)
    } else {
        category.getId().equals(id)
    }
    to po co ten if? :-)
    Chyba powinno być tak:
    .filter(category -> category.getId().equals(id))
    ewentualnie zwróci pustą listę i dobrze.
    */

}
