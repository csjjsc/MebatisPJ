package org.rockyJie.v2.mappers;

import org.rockyJie.v2.pojo.Blog;

import java.util.List;

/**
 * RockeyJie
 * 2020/7/12
 */
public interface BlogMapper {

    Blog selectAuthorById(Integer bid);

    List<Blog> selectAll();
}
