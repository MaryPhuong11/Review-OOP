package service;

import model.Product;
import java.util.*;

public class ProductService {
    private List<Product> products = new ArrayList<>();

    public void addSampleData() {
        products.add(new Product(1, "Laptop", "Electronics", 1500, 5, "Laptop gaming"));
        products.add(new Product(2, "iPhone", "Electronics", 1200, 3, "Điện thoại Apple"));
        products.add(new Product(3, "Áo Thun", "Clothes", 200, 20, "Áo cotton"));
        products.add(new Product(4, "Bánh", "Food", 50, 50, "Bánh quy"));
    }

    public Product findProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    // Cập nhật sản phẩm
    public boolean updateProduct(int id, double price, int quantity, String description) {
        Product p = findProductById(id);
        if (p != null) {
            p.setPrice(price);
            p.setQuantity(quantity);
            p.setDescription(description);
            return true;
        }
        return false;
    }

    // Hiển thị sản phẩm theo tiêu chí
    public List<Product> getProductsSorted(int option) {
        List<Product> sortedList = new ArrayList<>(products);
        if (option == 1) sortedList.sort(Comparator.comparing(Product::getDiscountedPrice));
        else if (option == 2) sortedList.sort(Comparator.comparing(Product::getCategory));
        return sortedList;
    }

    // Tổng giá trị theo danh mục
    public Map<String, Double> totalValueByCategory() {
        Map<String, Double> totals = new HashMap<>();
        for (Product p : products) {
            totals.put(p.getCategory(),
                    totals.getOrDefault(p.getCategory(), 0.0) + p.getDiscountedPrice() * p.getQuantity());
        }
        return totals;
    }

    // Giảm giá sản phẩm
    public boolean discountProduct(int id, double discount) {
        Product p = findProductById(id);
        if (p != null) {
            p.setDiscount(discount);
            return true;
        }
        return false;
    }

    // Đặt hàng
    public double orderProduct(int id, int qty) {
        Product p = findProductById(id);
        if (p != null && qty <= p.getQuantity()) {
            p.setQuantity(p.getQuantity() - qty);
            return p.getDiscountedPrice() * qty;
        }
        return -1;
    }

    public List<Product> getAllProducts() {
        return products;
    }
}
