package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Enums.Status;
import YenloBE.YenloBE.Exception.ApiRequestException;
import YenloBE.YenloBE.Model.Availability;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Repo.AvailabilityRepo;
import YenloBE.YenloBE.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AvailabilityServiceImpl implements AvailabilityService{

    @Autowired
    private AvailabilityRepo availabilityRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public String addAvailabilityOneDay(Availability availability)
    {
        availabilityRepo.save(availability);
        return "availability created.";
    }

    @Override
    public List<Availability> getAvailabilityOneDay(Date date, Integer user_id) {
        List<Availability> availabilities = availabilityRepo.findAllByDateTimeAndUserId(date, user_id);
        if (availabilities.size() == 0){
            availabilities.add(new Availability((int)0, userRepo.findById(user_id).get(), Status.NIKS, date, true));
            availabilities.add(new Availability((int)0, userRepo.findById(user_id).get(), Status.NIKS, date, false));
            return availabilities;
        } else if (availabilities.size() == 2) {
            return availabilities;
        }

        return availabilities;
        //return Optional.ofNullable(availabilityRepo.findAllByDateTimeAndUserId(date, user_id));
    }

    @Override
    public Optional<List<Availability>> getAvailabilityOneWeek(Date begin_date, Integer user_id) {
        List<Availability> availabilities = new ArrayList<>();
        Date weekDate = begin_date;
        for (int i = 0; i < 5; i++){
            for (Availability a:this.getAvailabilityOneDay(weekDate, user_id)) {
                availabilities.add(a);
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(weekDate);
            cal.add(Calendar.DATE, 1);
            weekDate = cal.getTime();
        }
        return Optional.ofNullable(availabilities);
//        List<Availability> availabilities = new ArrayList<>();
//        Date weekDate = begin_date;
//        for (int i = 0; i < 5; i++) {
//            for (Availability a:availabilityRepo.findAllByDateTimeAndUserId(weekDate, user_id)) {
//                availabilities.add(a);
//            };
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(weekDate);
//            cal.add(Calendar.DATE, 1);
//            weekDate = cal.getTime();
//        }
//        return Optional.ofNullable(availabilities);
    }

    @Override
    public Optional<List<Availability>> getAvailabilityBetween(Integer user_id, Date start_date, Date end_date) {
        List<Availability> availabilities = new ArrayList<>();
        Date date = start_date;
        for(int i = 0; i < 1;date = this.datePlusOne(date)){
            for (Availability a:this.getAvailabilityOneDay(date, user_id)) {
                availabilities.add(a);
            }
            if (date.toString().equals(end_date.toString())){
                i++;
            }
        }
        return Optional.ofNullable(availabilities);


//        List<Availability> availabilities = new ArrayList<>();
//        for (Availability a:availabilityRepo.findByUserIdAndDateTimeBetween(user_id, start_date, end_date)) {
//            availabilities.add(a);
//        }
//        return Optional.ofNullable(availabilities);
    }

    private Date datePlusOne(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        date = cal.getTime();
        return date;
    }

    @Override
    public String updateAvailabilityDay(Availability availability) {

        availabilityRepo.save(availability);
        return "";
    }

    @Override
    public Integer getOfficeStatus() {
        List<Availability> availabilities = availabilityRepo.findAllByStatus(Status.OFFICE);
        return availabilities.size();
    }

    @Override
    public Optional<Availability> findById(Integer id) {
        return availabilityRepo.findById(id);
    }

    @Override
    public Boolean getOfficeStatus(Integer userId, Date date) {
        Boolean available = false;
        for (Availability a:availabilityRepo.findAllByDateTimeAndUserId(date, userId)) {
            if (a.getStatus() == Status.OFFICE || a.getStatus() == Status.HOME){
                available = true;
            }
        }
        return available;
    }
}
