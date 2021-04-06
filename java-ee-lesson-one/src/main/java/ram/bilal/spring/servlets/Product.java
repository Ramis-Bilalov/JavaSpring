package ram.bilal.spring.servlets;

public class Product {

    private int id;
    private String title;
    private String cost;

    public Product(int id, String title, String cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public String getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "<b>Product:</b> " +
                "id = " + id +
                ", title = " + title +
                ", cost = " + cost;
    }
}
