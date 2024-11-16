package mappers;

import POJO.Brand;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper {
    @Select("select * from brand;")
    List<Brand> selectAll();
}
