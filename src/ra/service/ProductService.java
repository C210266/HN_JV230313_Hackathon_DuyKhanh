package ra.service;

import ra.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IService<Product, String> {
    private List<Product> products;

    public ProductService() {
        products = new ArrayList<>();
    }

    //    Methods
    public int getSize() {
        return products.size();
    }

    @Override
    public List<Product> getAll() {
        return products;
    }


    @Override
    public void save(Product product) {
        if (findById(product.getProductId()) == null) {
            products.add(product);
        } else {
            products.set(products.indexOf(findById(product.getProductId())), product);
        }
    }

    @Override
    public Product findById(String i) {
        for (Product product : products) {
            if (product.getProductId().equals(i)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void delete(String i) {
        if (findById(i) == null) {
            System.out.println("Ko tim thay id can xoa");
            return;
        }
        products.remove(findById(i));
        System.out.println("Da xoa san pham thanh cong");
    }
}
