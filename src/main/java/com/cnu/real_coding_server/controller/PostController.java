package com.cnu.real_coding_server.controller;

import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.model.request.PostRequest;
import com.cnu.real_coding_server.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(postService.createPost(postRequest));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        log.info("get posts");
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(postService.getPost(postId).orElse(null));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable("postId")Integer postId,
                                           @RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(postService.updatePost(postId, postRequest).orElse(null));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") Integer postId) {
        postService.deletePost(postId);

        return ResponseEntity.noContent().build();
    }
}
