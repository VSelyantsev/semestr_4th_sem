package ru.itis.kpfu.selyantsev.service;

import ru.itis.kpfu.selyantsev.dto.request.BlogRequest;
import ru.itis.kpfu.selyantsev.dto.response.BlogResponse;

import java.util.List;

public interface BlogService {

    void createBlog(BlogRequest blogRequest);

    void updateBlog(BlogRequest blogRequest);

    void deleteBlog(BlogRequest blogRequest);

    List<BlogResponse> findAllBlogs();
}
