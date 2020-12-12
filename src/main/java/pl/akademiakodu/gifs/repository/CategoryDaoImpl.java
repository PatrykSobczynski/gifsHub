package pl.akademiakodu.gifs.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.akademiakodu.gifs.model.Category;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void createCategory(Category category) {
        String sql = "INSERT INTO gifs VALUES(null, ?, ?) ";
        jdbcTemplate.update(sql, category.getId(), category.getName());
    }

    @Override
    public List<Category> findAllCategories() {
        String sql = "SELECT * FROM gifs.category";

        List<Category> categories = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Category(rs.getLong("id"),
                        rs.getString("name"))
        );
        return categories;
    }

    @Override
    public void updateCategory(Long id) {

    }

    @Override
    public void deleteCategory(Long id) {

    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void init() {
//        List<Category> categories = new ArrayList<>();
//
//        categories.add(new Category(1L, "PEOPLE"));
//        categories.add(new Category(2L, "ROBOTS"));
//        categories.add(new Category(3L, "ILLUSION"));
//        categories.add(new Category(4L, "ANIMALS"));
//
//        for (Category category : categories) {
//            String sql = "INSERT INTO category VALUES (null, ?)";
//            jdbcTemplate.update(sql, category.getName());
//        }
//
//    }
}
