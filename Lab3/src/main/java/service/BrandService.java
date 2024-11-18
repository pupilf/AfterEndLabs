package service;

import POJO.Brand;
import POJO.Page;

import java.util.List;

public interface BrandService {
    List<Brand> selectAll();
    Page<Brand> selectByPage(int begin,int size);

    List<Brand> selectByCondition(Brand brand);
    void add(Brand brand);
    void update(Brand brand);
    void remove(Integer id);

    void batchRemove(int[] ids);

}
