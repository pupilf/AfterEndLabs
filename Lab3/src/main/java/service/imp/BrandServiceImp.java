package service.imp;

import POJO.Brand;
import POJO.Page;
import mappers.BrandMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import service.BrandService;
import utils.sqlSessionFactoryUtil;

import java.io.IOException;
import java.util.List;

public class BrandServiceImp implements BrandService {
    private SqlSessionFactory factory;

    {
        try {
            factory = sqlSessionFactoryUtil.getSqlSessionFactory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Brand> selectAll() {
        SqlSession sqlSession = factory.openSession();
        List<Brand> brands = sqlSession.getMapper(BrandMapper.class).selectAll();
        sqlSession.close();
        return brands;
    }

    @Override
    public Page<Brand> selectByPage(int currentPage,int pageSize) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectByPage((currentPage - 1) * pageSize, pageSize);
        int total = mapper.selectTotal();
        Page<Brand> page = new Page<>();
        page.setRows(brands);
        page.setTotal(total);
        sqlSession.close();
        return page;
    }

    @Override
    public List<Brand> selectByCondition(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");  //模糊匹配brand name
        }
        List<Brand> brands = sqlSession.getMapper(BrandMapper.class).selectByCondition(brand);
        sqlSession.close();
        return brands;
    }

    @Override
    public void add(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.getMapper(BrandMapper.class).add(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.getMapper(BrandMapper.class).update(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void remove(Integer id) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.getMapper(BrandMapper.class).remove(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void batchRemove(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.getMapper(BrandMapper.class).batchRemove(ids);
        sqlSession.commit();
        sqlSession.close();
    }
}
