package pl.akademiakodu.gifs.model;

public class Category {
    private Long id;
    private String name;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getUrl() {
        return "/categories/" + name + ".jpg";
    }

    public String getDetailsUrl() {
        return "/category/" + id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
