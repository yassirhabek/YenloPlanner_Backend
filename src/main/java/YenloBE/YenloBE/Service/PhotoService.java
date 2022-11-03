package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoService {
    String addPhoto(String originalFilename, MultipartFile image) throws IOException;
    List<Photo> findAll();
    Photo getPhoto(String id);
}
