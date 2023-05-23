package ru.itis.kpfu.selyantsev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.kpfu.selyantsev.dto.request.BlogRequest;
import ru.itis.kpfu.selyantsev.dto.request.TaskRequest;
import ru.itis.kpfu.selyantsev.dto.request.UserRequest;
import ru.itis.kpfu.selyantsev.service.BlogService;
import ru.itis.kpfu.selyantsev.service.SubscriptionService;
import ru.itis.kpfu.selyantsev.service.TaskService;
import ru.itis.kpfu.selyantsev.service.UserService;
import ru.itis.kpfu.selyantsev.utils.mappers.UserMapper;


@Controller
@RequiredArgsConstructor
public class PageController {

    private final TaskService taskService;
    private final UserService userService;
    private final SubscriptionService followerService;
    private final BlogService blogService;
    private final UserMapper userMapper;
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "registration";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "login";
    }

    @GetMapping("/logout")
    public String loginPage(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "login";
    }

    @GetMapping("/tasks")
    public String task(Model model) {
        model.addAttribute("taskRequest", new TaskRequest());
        return "task";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("taskRequest", new TaskRequest());
        model.addAttribute("tasks", taskService.findAll());
        return "dashboard";
    }

    @GetMapping("/perform")
    public String perform() {
        return "register_success";
    }

    @GetMapping("/update")
    public String updateTask(Model model) {
        model.addAttribute("taskRequest", new TaskRequest());
        return "task_updating";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("user", userMapper.toResponse(userService.getCurrentUser()));
        model.addAttribute("friends", followerService.countFollowers());
        model.addAttribute("tasks", taskService.findAll());
        return "profile";
    }

    @PostMapping("/updateUser")
    public String updateUser() {
        return "profile";
    }

    @GetMapping("/subscription")
    public String subscriptionPage(Model model) {
        model.addAttribute("follower", new UserRequest());
        model.addAttribute("markedFollower", new UserRequest());
        model.addAttribute("followerRequests", followerService.findFollowerRequests());
        return "subscription";
    }

    @GetMapping("/blog")
    public String blogging(Model model) {
        model.addAttribute("blog", new BlogRequest());
        return "blog";
    }

    @GetMapping("/readBlog")
    public String readBlog(Model model) {
        model.addAttribute("blogs", blogService.findAllBlogs());
        return "read_blog";
    }
}
