package org.rockyJie;


import org.junit.jupiter.api.Test;
import org.rockyJie.v2.pojo.Blog;
import org.rockyJie.v2.mappers.BlogMapper;
import org.rockyJie.v2.session.SqlSession;
import org.rockyJie.v2.session.SqlSessionFactory;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testV2(){
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.bulid().openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        /*Blog blog = mapper.selectAuthorById(1);
        System.out.println(blog);*/
        List<Blog> blogs = mapper.selectAll();
        System.out.println(blogs);
    }
}
