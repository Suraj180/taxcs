package com.example.tcs.Controller;

import com.example.tcs.CRUD.entity.Posts;
import com.example.tcs.CRUD.repository.postRepository;
import com.example.tcs.UserDtls;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class dashboardController {

    @Autowired
    private AdminRepository repo;

    private static final String DIR_TO_UPLOAD = "D:\\tcss\\FileUpload\\";

    @Autowired
    private postRepository postRepo;

    @GetMapping("/dashboard")
    public String dashboard( Principal p, Model m){

//        Optional <Posts> posts = postRepo.findById(id);
//        if (posts.isPresent()) {
//
//            String image = posts.get().getPhotos();
//
//            StreamUtils.copy(image.getBytes(), response.getOutputStream());
//        }

        String em= p.getName();

       UserDtls u= repo.findByUsername(em);
        m.addAttribute("username",u.getUsername());

        List<Posts> list = postRepo.findAll();
        m.addAttribute("all_posts",list);


        return "dashboard";
    }

    @GetMapping("/loadForm")
    public String loadForm(){
        return"add";
    }

    @GetMapping("/editForm/{id}")
    public String editForm(@PathVariable(value = "id")Long id,Model m) throws UnsupportedEncodingException {
        Optional<Posts> posts = postRepo.findById(id);
        Posts pos = posts.get();
        m.addAttribute("posts",pos);


        return"edit";
    }

    @PostMapping("/save_post")
    public String savePost(@ModelAttribute Posts post, HttpSession session, @RequestParam("image") MultipartFile file) throws IOException {

        // Set the form data into entity
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        post.setPhotos(fileName, file.getContentType(), file.getBytes());


        postRepo.save(post);
        session.setAttribute("msg","Post Added Successfully.");

        byte[] bytes = file.getBytes();
        Path path = Paths.get(DIR_TO_UPLOAD + file.getOriginalFilename());
        Files.write(path, bytes);


        return "redirect:./dashboard";
    }


    @RequestMapping(value = "/update_post",  method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET})
    public String updatePost(@ModelAttribute Posts post, HttpSession session){
        postRepo.save(post);
        session.setAttribute("msg","Post Updated Successfully.");



        return"redirect:./dashboard";
    }

    @RequestMapping(value = "/delete/{id}",  method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET})
    public String deletePost(@PathVariable(value = "id")Long id, HttpSession session){
        postRepo.deleteById(id);
        session.setAttribute("msg","Post deleted Successfully.");
        return"redirect:../dashboard";
    }

}
