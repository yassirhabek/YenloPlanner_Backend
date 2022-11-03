package YenloBE.YenloBE.Controller;


import YenloBE.YenloBE.Model.Photo;
import YenloBE.YenloBE.Service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/photo")
@CrossOrigin
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping
    public String addPhoto(@RequestParam("image") MultipartFile image) throws IOException {
        String id = photoService.addPhoto(image.getOriginalFilename(),image);
        return  id;
    }

    @GetMapping("/{id}")
    public String getPhoto(@PathVariable String id) {
        Photo photo = photoService.getPhoto(id);
        byte[] bytes = photo.getData().getData();
        String s = Base64.getEncoder().encodeToString(bytes);
        return "data:image/png;base64, " + s;
    }

    @GetMapping
    public List<String> getPhotos() {
        List<Photo> photos = photoService.findAll();
        ArrayList<String> photoStrings = new ArrayList<>();
        for (var photo : photos) {
            byte[] bytes = photo.getData().getData();
            String s = Base64.getEncoder().encodeToString(bytes);
            photoStrings.add("data:image/png;base64, " + s);
        }
        return photoStrings;
    }
}