package pl.akademiakodu.gifs.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.akademiakodu.gifs.model.Gif;

import java.util.List;

@Repository
public class GifDaoImpl implements GifDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GifDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createGif(Gif gif) {
        String sql = "INSERT INTO gifs VALUES(null, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, gif.getName(), gif.getTag(), gif.isFavorite(), gif.getCategoryId());
    }

    @Override
    public List<Gif> findAllGifs() {
        String sql = "SELECT * FROM gifs";

        List<Gif> gifs = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Gif(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("tag"),
                        rs.getBoolean("favorite"),
                        rs.getLong("category_id"))
        );
        return gifs;
    }

    @Override
    public void updateGif(Gif gif) {
        if (gif.getTag() != null) {
            String sql = "UPDATE gifs g SET g.TAG = '" + gif.getTag() + "' WHERE g.ID = ?";
            jdbcTemplate.update(sql, gif.getId());
        }
        if (gif.isFavorite() != null) {
            String sql = "UPDATE gifs g SET g.FAVORITE = " + gif.isFavorite() + " WHERE g.ID = ?";
            jdbcTemplate.update(sql, gif.getId());
        }
        if (gif.getCategoryId() != null) {
            String sql = "UPDATE gifs g SET g.CATEGORY_ID = " + gif.getCategoryId() + " WHERE g.ID = ?";
            jdbcTemplate.update(sql, gif.getId());
        }
    }


    @Override
    public void deleteGif(long id) {
        String sql = "DELETE FROM gifs WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Gif findGifByNameSQL(String name) {
        String sql = "SELECT * FROM gifs q WHERE q.NAME = '" + name + "'";

        Gif gif = jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Gif(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("tag"),
                        rs.getBoolean("favorite"),
                        rs.getLong("category_id"))
        );
        return gif;
    }


//    @EventListener(ApplicationReadyEvent.class)
//    public void init() {
//        List<Gif> gifs = new ArrayList<>();
//
//        // ID1 - People
//        // ID2 - Robots
//        // ID3 - Illusion
//        // ID4 - Animals
//        gifs.add(new Gif("android-explosion","explode",false, 2L));
//        gifs.add(new Gif("ben-and-mike","explode",true, 1L));
//        gifs.add(new Gif("book-dominos","domino",true, 3L));
//        gifs.add(new Gif("compiler-bot","compiler",true,2L));
//        gifs.add(new Gif("cowboy-coder","cowboy",false,1L));
//        gifs.add(new Gif("infinite-andrew","infinite",true,3L));
//        gifs.add(new Gif("0e9b8ad60c9e93bd35fc86936fe9ad6c","karate",false,1L));
//
//        for (Gif gif : gifs) {
//            String sql = "INSERT INTO gifs VALUES (null, ?, ?, ?, ?)";
//            jdbcTemplate.update(sql, gif.getName(), gif.getTag(), gif.isFavorite(), gif.getCategoryId());
//        }
//    }

}
