package ui;

import service.ProductService;
import model.Product;
import java.util.*;

public class Menu {
    private ProductService service;
    private Scanner scanner = new Scanner(System.in);

    public Menu(ProductService service) {
        this.service = service;
    }

    public void showMenu() {
        service.addSampleData();
        while (true) {
            System.out.println("\n--- Quản Lý Sản Phẩm ---");
            System.out.println("1. Cập nhật sản phẩm");
            System.out.println("2. Hiển thị sản phẩm");
            System.out.println("3. Tổng giá trị theo danh mục");
            System.out.println("4. Giảm giá sản phẩm");
            System.out.println("5. Đặt hàng");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> updateProduct();
                case 2 -> displayProducts();
                case 3 -> showTotalValue();
                case 4 -> discountProduct();
                case 5 -> orderProduct();
                case 0 -> { System.out.println("Thoát chương trình."); return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void updateProduct() {
        System.out.print("Nhập ID sản phẩm: ");
        int id = scanner.nextInt();
        System.out.print("Nhập giá mới: ");
        double price = scanner.nextDouble();
        System.out.print("Nhập số lượng mới: ");
        int qty = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập mô tả mới: ");
        String desc = scanner.nextLine();
        boolean ok = service.updateProduct(id, price, qty, desc);
        System.out.println(ok ? "Cập nhật thành công!" : "Không tìm thấy sản phẩm!");
    }

    private void displayProducts() {
        System.out.println("1. Theo giá | 2. Theo danh mục");
        int option = scanner.nextInt();
        List<Product> list = service.getProductsSorted(option);
        list.forEach(System.out::println);
    }

    private void showTotalValue() {
        Map<String, Double> totals = service.totalValueByCategory();
        totals.forEach((cat, total) -> System.out.printf("Danh mục:%s | Tổng:%.2f\n", cat, total));
    }

    private void discountProduct() {
        System.out.print("Nhập ID sản phẩm: ");
        int id = scanner.nextInt();
        System.out.print("Nhập % giảm giá: ");
        double discount = scanner.nextDouble();
        boolean ok = service.discountProduct(id, discount);
        System.out.println(ok ? "Giảm giá thành công!" : "Không tìm thấy sản phẩm!");
    }

    private void orderProduct() {
        System.out.print("Nhập ID sản phẩm: ");
        int id = scanner.nextInt();
        System.out.print("Nhập số lượng: ");
        int qty = scanner.nextInt();
        double total = service.orderProduct(id, qty);
        if (total >= 0) System.out.printf("Đặt hàng thành công! Tổng: %.2f\n", total);
        else System.out.println("Lỗi: Số lượng không hợp lệ hoặc sản phẩm không tồn tại!");
    }
}
