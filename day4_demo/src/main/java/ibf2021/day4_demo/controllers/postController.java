package ibf2021.day4_demo.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import ibf2021.day4_demo.models.Post;
import ibf2021.day4_demo.repositories.PostRepository;

@Controller
@RequestMapping(path = "/post")
public class postController {

    @Autowired
    PostRepository postRepo;

    @GetMapping(path = "/{postId}")
    public String getPostById(@PathVariable int postId, Model model) {
        Optional<Post> opt = postRepo.getPostbyId(postId);
        // WE ARE NOT HANDLING 404
        model.addAttribute("post", opt.get());
        return "post";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postPost(
            @RequestParam MultipartFile image,
            @RequestPart String comment,
            @RequestPart String poster,
            Model model
    // @RequestBody MultiValueMap<String, String> values
    // @RequestParam(name = "comment") MultipartFile comment,
    // @RequestParam(name = "poster") MultipartFile poster
    ) {

        String imgName = image.getOriginalFilename();
        long imgSize = image.getSize();
        String imgType = image.getContentType();
        byte[] buff = new byte[0];

        try {
            buff = image.getBytes();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // String comment = values.getFirst("comment");

        // String commentName = comment.getOriginalFilename();
        // long commentSize = comment.getSize();
        // String commentType = comment.getContentType();

        System.out.printf("Image: filename=%s, size= %d, type = %s\n", imgName, imgSize, imgType);

        System.out.printf("Comment: %s\n", comment);

        System.out.printf("Poster: %s\n", poster);
        // System.out.printf("Comments: filename=%s, size= %d, type = %s\n",
        // commentName, commentSize, commentType);

        Post post = new Post();
        post.setComment(comment);
        post.setImage(buff);
        post.setPoster(poster);
        post.setImageType(imgType);

        int updateCount = postRepo.insertPost(post);
        model.addAttribute("updateCount", updateCount);

        return "result";
    }
}