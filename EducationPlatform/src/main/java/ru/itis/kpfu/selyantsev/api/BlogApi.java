package ru.itis.kpfu.selyantsev.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.kpfu.selyantsev.dto.request.BlogRequest;
import ru.itis.kpfu.selyantsev.dto.response.BlogResponse;

import java.util.List;

@RequestMapping("api/blog")
public interface BlogApi {

    @PostMapping("/create")
    String createBlog(@RequestBody BlogRequest blogRequest);

    @PostMapping("/updateBlog")
    String updateBlog(@RequestBody BlogRequest blogRequest);

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/deleteBlog")
    ResponseEntity<?> deleteBlog(@RequestBody BlogRequest blogRequest);

    @GetMapping("/findAll")
    List<BlogResponse> findAll();

}
