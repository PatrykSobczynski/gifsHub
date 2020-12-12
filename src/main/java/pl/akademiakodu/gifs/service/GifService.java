package pl.akademiakodu.gifs.service;

import pl.akademiakodu.gifs.model.Gif;

import java.util.List;

public interface GifService {
    List<Gif> getGifs();

    List<Gif> findGif(String name);

    Gif findGifByName(String name);

    void changeTag(Gif gif, String tag);

    void toggleFavorite(Gif gif);

    List<Gif> findFavorites();

    List<Gif> findFavoritesGifs(String name);

    // Szukanie gifów po kategoriach
    List<Gif> findGifsByCategory(Long id);

    void changeCategory(Gif editedGif);
}

