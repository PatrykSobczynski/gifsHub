package pl.akademiakodu.gifs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.akademiakodu.gifs.model.Category;
import pl.akademiakodu.gifs.model.Gif;
import pl.akademiakodu.gifs.repository.GifDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GifServiceImpl implements GifService {

    private GifDao gifDao;
    public List<Category> categories;

    @Autowired
    public GifServiceImpl(GifDao gifDao) {
        this.gifDao = gifDao;
    }

    public GifServiceImpl() {
    }

    @Override
    public List<Gif> getGifs() {
        return gifDao.findAllGifs();
    }

    @Override
    public List<Gif> findGif(String name) {
        return getGifs().stream().filter(gif ->
                gif.getTag() != null ? gif.getTag().equals(name) : gif.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public Gif findGifByName(String name) {
        return gifDao.findGifByNameSQL(name);
//        return gifs.stream().filter(gif -> gif.getName().equals(name)).findFirst().get();
    }

    @Override
    public void changeTag(Gif gif, String tag) {
        gif.setTag(tag);

        gif.setFavorite(null);
        gif.setName(null);
        gif.setCategoryId(null);

        gifDao.updateGif(gif);
    }

    @Override
    public void changeCategory(Gif gif) {

        gif.setFavorite(null);
        gif.setTag(null);

        gifDao.updateGif(gif);
    }

    @Override
    public void toggleFavorite(Gif gif) {
        gif.setFavorite(!gif.isFavorite()); // Ustawiamy wartość przeciwną do tej która jest akutalnie

        gif.setCategoryId(null);
        gif.setTag(null);

        gifDao.updateGif(gif);
    }

    @Override
    public List<Gif> findFavorites() {
        // return gifs.stream().filter(gif -> gif.isFavorite()).collect(Collectors.toList());
        // To jest to samo, tylko że aplikacja zużywa mniej pamięci! :)
        if (getGifs() != null) {
            return getGifs().stream().filter(Gif::isFavorite).collect(Collectors.toList());
        }
        return new ArrayList<Gif>();
    }

    //odpowiada za search w favorites
    @Override
    public List<Gif> findFavoritesGifs(String name) {
        return findFavorites().stream().filter(gif -> gif.getTag().equals(name)).collect(Collectors.toList());
    }

//    //odpowiada za search w favorites
//    @Override
//    public List<Gif> findFavoritesGifs(String name) {
//        return findFavorites().stream().filter(gif -> gif.getTag() != null ? gif.getTag().equals(name) : gif.getName().equals(name)).collect(Collectors.toList());
//    }

    @Override
    public List<Gif> findGifsByCategory(Long id) {
        return getGifs().stream().filter(gif -> gif.getCategoryId().equals(id)).collect(Collectors.toList());
    }




}
