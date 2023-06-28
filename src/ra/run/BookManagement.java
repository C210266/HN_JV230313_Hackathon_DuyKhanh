package ra.run;

import com.sun.security.jgss.GSSUtil;
import ra.config.InputMethods;
import ra.model.Catalog;
import ra.model.Product;
import ra.service.CatalogService;
import ra.service.ProductService;

import java.awt.im.spi.InputMethod;
import java.util.Collections;
import java.util.Comparator;

public class BookManagement {
    private static CatalogService catalogService = new CatalogService();
    private static ProductService productService = new ProductService();

    public static void main(String[] args) {
        while (true) {

            System.out.println("**************************BASIC-MENU**************************\n" +
                    "1. Quản lý danh mục [5 điểm]\n" +
                    "2. Quản lý sản phẩm [5 điểm]\n" +
                    "3. Thoát [5 điểm]");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    catelogMenu();
                    break;
                case 2:
                    productMenu();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nhap gia tri tu 1 den 3");
                    break;
            }
        }
    }


    public static void catelogMenu() {
        byte choice = 0;
        while (choice != 5) {

            System.out.println("********************CATALOG-MANAGEMENT********************\n" +
                    "1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục [5 điểm]\n" +
                    "2. Hiển thị thông tin tất cả các danh mục [5 điểm]\n" +
                    "3. Sửa tên danh mục theo mã danh mục [5 điểm]\n" +
                    "4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm) [5 điểm]\n" +
                    "5. Quay lại");
            System.out.println("Nhap lua chon cua ban");
            choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    createCatalog();
                    break;
                case 2:
                    displayCatalog();
                    break;
                case 3:
                    EditCatalog();
                    break;
                case 4:
                    DeleteCatalog();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Nhap gia tri tu 1 den 5");
                    break;
            }
        }
    }


    public static void createCatalog() {
        System.out.println("Nhap so danh muc muon them");
        int n = InputMethods.getInteger();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap thong tin cua catalog thu " + (i + 1));
            Catalog newCatalog = new Catalog();
            newCatalog.inputData();
            catalogService.save(newCatalog);
        }
    }

    public static void displayCatalog() {
        if (catalogService.getSize() == 0) {
            System.out.println("Chua co catalog nao");
            return;
        }
        System.out.println("-----------Danh sach catalog--------------");
        for (Catalog catalog : catalogService.getAll()) {

            System.out.println(catalog.toString());
        }
    }

    public static void EditCatalog() {
        if (catalogService.getSize() == 0) {
            System.out.println("Chua co catalog nao");
            return;
        }
        System.out.println("Nhap vao id cua catalog ma ban muon thay doi");
        int idEdit = InputMethods.getInteger();
        Catalog editCatalog = catalogService.findById(idEdit);
        if (editCatalog == null) {
            System.out.println("Khong tim thay id");
            return;
        }
        System.out.println("Doi tuong can sua la");
        editCatalog.toString();
        editCatalog.inputData();
        catalogService.save(editCatalog);
        System.out.println("Da update thanh cong");
    }

    public static void DeleteCatalog() {
        if (catalogService.getSize() == 0) {
            System.out.println("Chua co catalog nao");
            return;
        }
        System.out.println("Nhap id catalog ban muon xoa");
        int idDel = InputMethods.getInteger();
        catalogService.delete(idDel);
    }

    public static void productMenu() {
        byte choice = 0;
        while (choice != 7) {

            System.out.println("********************PRODUCT-MANAGEMENT*******************\n" +
                    "1. Nhập số sản  phẩm và nhập thông tin sản phẩm [5 điểm]\n" +
                    "2. Hiển thị thông tin các sản phẩm [5 điểm]\n" +
                    "3. Sắp xếp sản phẩm theo giá giảm dần [5 điểm]\n" +
                    "4. Xóa sản phẩm theo mã [5 điểm]\n" +
                    "5. Tìm kiếm sách theo tên sách [10 điểm]\n" +
                    "6. Thay đổi thông tin của sách theo mã sách [10 điểm]\n" +
                    "7. Quay lại\n");
            System.out.println("Nhap lua chon cua ban");
            choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    createProduct();
                    break;
                case 2:
                    displayProduct();
                    break;
                case 3:
                    sortProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    searchProduct();
                    break;
                case 6:
                    updateProduct();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Nhap gia tru tu 1 den 7");
                    break;


            }

        }

    }

    public static void createProduct() {
        if (catalogService.getSize() == 0) {
            System.out.println("Chua co catalog nao nen ko the tao san pham duoc, vui long them catalog truoc");
            return;
        }
        System.out.println("Nhap so san pham muon them");
        int n = InputMethods.getInteger();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap thong tin cua san pham thu " + (i + 1));
            Product newProduct = new Product();
            newProduct.inputData(productService.getAll(), catalogService.getAll());
            productService.save(newProduct);
        }
    }

    public static void displayProduct() {
        if (productService.getSize() == 0) {
            System.out.println("Chua co san pham nao");
            return;
        }
        System.out.println("-----------Danh sach San pham--------------");
        for (Product product : productService.getAll()) {
            System.out.println(product.toString());
        }
    }

    public static void sortProduct() {
        if (productService.getSize() == 0) {
            System.out.println("Chua co san pham nao nen ko the sap xep dc");
            return;
        }
        Collections.sort(productService.getAll(), Comparator.comparing(Product::getProductPrice));
    }

    public static void deleteProduct() {
        if (productService.getSize() == 0) {
            System.out.println("Chua co san pham nao nen ko the xoa dc");
            return;
        }
        System.out.println("Nhap id san pham ban muon xoa");
        String idDel = InputMethods.getString();
        productService.delete(idDel);
    }

    public static void searchProduct() {
        if (productService.getSize() == 0) {
            System.out.println("Chua co san pham nao nen ko the tim kiem  dc");
            return;
        }
        System.out.println("Nhap ten sash ma ban muon tim kiem");
        String productName = InputMethods.getString();
        for (Product product : productService.getAll()) {
            if (product.getProductName().contains(productName)) {
                System.out.println("San pham ban muon tim kiem la : " + product);
            }
        }
    }

    public static void updateProduct() {
        if (productService.getSize() == 0) {
            System.out.println("Chua co san pham nao nen ko the update dc");
            return;
        }
        System.out.println("Nhap ma sach ma ban muon thay doi");
        String productId = InputMethods.getString();
        for (Product product : productService.getAll()) {
            if (product.getProductId() != productId) {
                System.out.println("San pham ko ton tai, nhap lai");
                break;
            } else {
                System.out.println("San pham dc tim thay la " + product.toString());
                System.out.println("Noi dung thay doi");
                product.inputData(productService.getAll(), catalogService.getAll());
                productService.save(product);
                System.out.println("San pham da duoc thay doi");
            }

        }
    }

}
