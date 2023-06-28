package ra.service;

import ra.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements IService<Catalog, Integer> {
    private List<Catalog> catalogs;

    public CatalogService() {
        catalogs = new ArrayList<>();
    }

    //    Methods
    public int getSize() {
        return catalogs.size();
    }

    public int getNewId() {
        int max = 0;
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogId() > max) {
                max = catalog.getCatalogId();
            }
        }
        return max + 1;
    }

    @Override
    public List<Catalog> getAll() {
        return catalogs;
    }

    @Override
    public void save(Catalog catalog) {
        if (findById(catalog.getCatalogId()) == null) {
            catalogs.add(catalog);
        } else {
            catalogs.set(catalogs.indexOf(findById(catalog.getCatalogId())), catalog);
        }
    }

    @Override
    public Catalog findById(Integer i) {
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogId() == i) {
                return catalog;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer i) {
        if (findById(i) == null) {
            System.out.println("Ko tim thay id can xoa");
            return;
        }
        catalogs.remove(findById(i));
        System.out.println("Da xoa catalog thanh cong");
    }


}
