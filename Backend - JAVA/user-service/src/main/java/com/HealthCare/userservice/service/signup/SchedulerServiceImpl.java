package com.HealthCare.userservice.service.signup;

import com.HealthCare.userservice.entity.User;
import com.HealthCare.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class SchedulerServiceImpl implements SchedulerService{
    @Autowired
    private UserRepository userRepository;
    private final Duration inactiveTimePeriod = Duration.ofMinutes(3);

    @Override
    @Scheduled(cron = "0 0/3 * * * *")
    public void deleteUnverifiedUser() {

        LocalDateTime currentTime = LocalDateTime.now();
        List<User> unVerifiedUsers = userRepository.findByEnabled(false);

        for (User user : unVerifiedUsers) {
            LocalDateTime registrationTimeOfUser = user.getOtpGeneratedDateTime();
            Duration timeElapsed = Duration.between(registrationTimeOfUser, currentTime);
            if (timeElapsed.compareTo(inactiveTimePeriod) > 0) {
                userRepository.delete(user);
            }
        }
    }

    @Override
    public void changeStatus() {

    }


}
