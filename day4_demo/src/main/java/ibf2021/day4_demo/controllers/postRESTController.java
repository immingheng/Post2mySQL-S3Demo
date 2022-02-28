package ibf2021.day4_demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.day4_demo.models.Post;
import ibf2021.day4_demo.repositories.PostRepository;

@RestController
@RequestMapping(path = "/post")
public class postRESTController {

    @Autowired
    private PostRepository postRepo;

    @GetMapping(path = "/{post_id}/image")
    public ResponseEntity<byte[]> getPostImage(@PathVariable int post_id) {
        Optional<Post> opt = postRepo.getPostbyId(post_id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            final Post post = opt.get();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", post.getImageType());
            return ResponseEntity.ok().headers(headers).body(post.getImage());
        }
    }
}
