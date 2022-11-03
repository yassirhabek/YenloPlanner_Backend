package YenloBE.YenloBE.Repo;

import YenloBE.YenloBE.Model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepo extends MongoRepository<Photo, String> {
}
