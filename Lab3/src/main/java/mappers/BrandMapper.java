package mappers;

import POJO.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    @ResultMap("brandResultMap")
    @Select("select *from tb_brand;")
    List<Brand> selectAll();

    @ResultMap("brandResultMap")
    @Select("select * from tb_brand limit #{begin},#{size};")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);

    @Select("select count(*) from tb_brand;")
    int selectTotal();

    List<Brand> selectByCondition(Brand brand);

    @Insert("insert into tb_brand values(NULL,#{brandName},#{companyName},#{ordered},#{description},#{status});")
    @ResultMap("brandResultMap")
    void add(Brand brand);

    @Update("update tb_brand set company_name=#{companyName},ordered=#{ordered},description=#{description}," +
            "status=#{status} where brand_name=#{brandName}")
    @ResultMap("brandResultMap")
    void update(Brand brand);

    @Delete("delete from tb_brand where id=#{id};")
    void remove(int id);

    void batchRemove(@Param("ids") int[] ids);

}
