package ru.itis.kpfu.selyantsev.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.dto.request.BlogRequest;
import ru.itis.kpfu.selyantsev.dto.response.BlogResponse;
import ru.itis.kpfu.selyantsev.model.Blog;
import ru.itis.kpfu.selyantsev.model.FollowerStatus;
import ru.itis.kpfu.selyantsev.model.Subscription;
import ru.itis.kpfu.selyantsev.model.User;
import ru.itis.kpfu.selyantsev.repository.BlogRepository;
import ru.itis.kpfu.selyantsev.repository.FollowerRepository;
import ru.itis.kpfu.selyantsev.service.BlogService;
import ru.itis.kpfu.selyantsev.service.UserService;
import ru.itis.kpfu.selyantsev.utils.mappers.BlogMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaseBlogService implements BlogService {

    private final BlogRepository blogRepository;
    private final FollowerRepository followerRepository;
    private final BlogMapper blogMapper;
    private final UserService userService;

    @Override
    public void createBlog(BlogRequest blogRequest) {
        User currentUser = userService.getCurrentUser();
        Blog blogToSave = blogMapper.toEntity(blogRequest);
        blogToSave.setUser(currentUser);
        blogRepository.save(blogToSave);
    }

    @Override
    public void updateBlog(BlogRequest blogRequest) {
        UUID currentUserUUID = userService.getCurrentUser().getId();
        Blog blog = blogMapper.toEntity(blogRequest);
        Blog currentBlogToSave = blogRepository.findBlogByTitle(blog.getTitle());
        if (currentBlogToSave.getUser().getId() == currentUserUUID) {
            currentBlogToSave.setContent(blog.getContent());
            blogRepository.save(currentBlogToSave);
        }
    }

    @Override
    public void deleteBlog(BlogRequest blogRequest) {
        blogRepository.deleteBlogByTitle(blogRequest.getTitle());
    }

    @Override
    public List<BlogResponse> findAllBlogs() {
        User currentUser = userService.getCurrentUser();
        List<BlogResponse> resultList = new ArrayList<>();

        // check if current user is follower
        List<Subscription> followers =  followerRepository.findAll()
                .stream()
                .filter(follower -> follower.getFollower().getId() == currentUser.getId()
                        & follower.getStatus() == FollowerStatus.ACCEPTED)
                .toList();

        // return blogs only user which myUser is following
        List<Blog> blogs = blogRepository.findAll();
        for (Subscription follower : followers) {
            for (Blog blog : blogs) {
                if (follower.getUser().getId() == blog.getUser().getId()) {
                    resultList.add(blogMapper.toResponse(blog));
                }
            }
        }
        return resultList;
    }
}
