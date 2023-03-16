package com.lf.community.dao.elasticsearch;

import com.lf.community.entity.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/11 0011 14:48
 * @Version 1.0
 */
@Repository
public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost,Integer> {
    
}
