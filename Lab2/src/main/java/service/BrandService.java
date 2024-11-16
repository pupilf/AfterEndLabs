package service;

import POJO.Brand;
import mappers.BrandMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import utils.sqlSessionUtil;

import java.io.IOException;
import java.util.List;

public class BrandService {
    private SqlSessionFactory factory= sqlSessionUtil.getSqlSessionFactory();

    public BrandService() throws IOException {
    }
    public List<Brand> selectAll(){
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> list=mapper.selectAll();
        sqlSession.close();
        return list;
    }
}
