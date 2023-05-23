package ru.itis.kpfu.selyantsev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.selyantsev.model.Blog;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface BlogRepository extends JpaRepository<Blog, UUID> {
    @Modifying
    @Transactional
    @Query("delete from t_blog b where b.title = :title")
    void deleteBlogByTitle(@Param("title") String title);

    @Query("select b from t_blog b where b.title = :title")
    Blog findBlogByTitle(@Param("title") String title);
}
