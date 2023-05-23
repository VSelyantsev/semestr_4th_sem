package ru.itis.kpfu.selyantsev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itis.kpfu.selyantsev.api.BlogApi;
import ru.itis.kpfu.selyantsev.aspect.annotation.CollectData;
import ru.itis.kpfu.selyantsev.dto.request.BlogRequest;
import ru.itis.kpfu.selyantsev.dto.response.BlogResponse;
import ru.itis.kpfu.selyantsev.service.BlogService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogController implements BlogApi {

    private final BlogService blogService;

    @CollectData
    @Override
    public String createBlog(@ModelAttribute("blog") BlogRequest blogRequest) {
        blogService.createBlog(blogRequest);
        return "dashboard";
    }

    @CollectData
    @Override
    public String updateBlog(@ModelAttribute("blog") BlogRequest blogRequest) {
        blogService.updateBlog(blogRequest);
        return "dashboard";
    }

    @CollectData
    @Override
    public ResponseEntity<Void> deleteBlog(@ModelAttribute("blog") BlogRequest blogRequest) {
        blogService.deleteBlog(blogRequest);
        return ResponseEntity.ok().build();
    }

    @CollectData
    @Override
    public List<BlogResponse> findAll() {
        return blogService.findAllBlogs();
    }
}
