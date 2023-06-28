package ra.model;

import ra.config.InputMethods;

import java.util.List;

public class Product {
    private String productId;
    private String productName;
    private double productPrice;
    private String description;
    private int stock;
    private Catalog catalog;
    private boolean status = true;

    public Product() {
    }

    public Product(String productId, String productName, double productPrice, String description, int stock, Catalog catalog, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.stock = stock;
        this.catalog = catalog;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(List<Product> products, List<Catalog> catalogs) {
        if (catalogs.size() == 0) {
            System.out.println("Vui long nhap catalog truoc");
            return;
        }
        System.out.println("Nhap id san pham");
        while (true) {
            String productId = InputMethods.getProductId();
            boolean flag = true;
            for (Product product : products) {
                if (product != null && product.getProductId().equals(productId)) {
                    System.out.println("Id san pham da ton tai, Nhap lai");
                    flag = false;
                    break;
                }
            }
            if (flag) {
                this.productId = productId;
                System.out.println("Da them san pham thanh cong");
                break;
            }

        }
        System.out.println("Nhap ten san pham");
        this.productName = InputMethods.getString();
        while (true) {
            System.out.println("Nhap gia san pham:");
            boolean flag = true;
            double productPrice = InputMethods.getDouble();
            if (productPrice > 0) {
                this.productPrice = productPrice;
                flag = false;
            }
            if (!flag) {
                break;
            } else {
                System.err.println("Gia san pham phai lon hon 0");
            }
        }
        System.out.println("Nhap mo ta san pham");
        this.description = InputMethods.getString();
        while (true) {
            System.out.println("Nhap so luong ton kho");
            boolean flag = true;
            int stock = InputMethods.getInteger();
            if (stock < 10) {
                System.err.println("So luong phai it nhat 10 san pham");
                flag = false;
            }
            if (flag) {
                this.stock = stock;
                break;
            }

        }
//        Hien thi dan hsach catalog
        for (Catalog catalog : catalogs) {
            System.out.println("-----------Danh sach catalogs----------------");
            System.out.println("Id : " + catalog.getCatalogId() + "CatalogName : " + catalog.getCatalogName());
        }
//        Chon id
        System.out.println("Nhap catalog id cua san pham");
        while (true) {
            int id = InputMethods.getInteger();
            boolean flag = true;
            for (Catalog catalog : catalogs) {
                if (catalog != null && catalog.getCatalogId() == id) {
                    flag = false;
                    this.catalog = catalog;
                }
            }
            if (flag) {
                System.out.println("Id ko ton tai");

            } else {
                break;
            }
        }
        System.out.println("Nhap trang thai cua san pham");
        this.status = InputMethods.getBoolean();
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                catalog +
                (status ? " Dang duoc ban" : " Ko duoc  ban") +
                '}';
    }
}
