package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Photo;
import YenloBE.YenloBE.Repo.PhotoRepo;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService{
    @Autowired
    private PhotoRepo photoRepository;

    @Override
    public String addPhoto(String originalFilename, MultipartFile image) throws IOException {
        Photo photo
                = new Photo();
        photo.setTitle(originalFilename);
        photo.setData(new Binary(BsonBinarySubType.BINARY,image.getBytes()));
        return photoRepository.save(photo).getId();
    }

    @Override
    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    @Override
    public Photo getPhoto(String id) {
        return photoRepository.findById(id).get();
    }
}
