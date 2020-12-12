package pl.akademiakodu.gifs.repository;

import pl.akademiakodu.gifs.model.Category;

import java.util.List;

public interface CategoryDao {
    void createCategory(Category category);

    List<Category> findAllCategories();

    void updateCategory(Long id);

    void deleteCategory(Long id);
}
