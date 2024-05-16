package com.HealthCare.userservice.service.signup;

import com.HealthCare.userservice.Utitlity.EmailUtil;
import com.HealthCare.userservice.Utitlity.OtpUtil;
import com.HealthCare.userservice.client.UserClient;
import com.HealthCare.userservice.config.PasswordEncoderConfig;
import com.HealthCare.userservice.dto.*;
import com.HealthCare.userservice.entity.*;
import com.HealthCare.userservice.repository.AdminRepository;
import com.HealthCare.userservice.repository.DoctorRepository;
import com.HealthCare.userservice.repository.PharmaRepository;
import com.HealthCare.userservice.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SignupServiceImpl implements SignupService {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PharmaRepository pharmaRepository;
    @Autowired
    UserClient userClient;
    @Autowired
    PasswordEncoderConfig passwordEncoderConfig;
    @Autowired
    EmailUtil emailUtil;
    @Autowired
    OtpUtil otpUtil;

    @Override
    public Admin createAdmin(AdminDto adminDto) {

        if(userRepository.existsByEmail(adminDto.getEmail())){
            throw new DataIntegrityViolationException("Already have this Email");
        }
        Admin user = new Admin();
        BeanUtils.copyProperties(adminDto, user);
        String hashed=passwordEncoderConfig.passwordEncoder().encode(adminDto.getPassword());
        user.setPassword(hashed);
        user.setRole(Role.ADMIN);
        Admin createdUser = adminRepository.save(user);
        LoginDetails loginDetails = new LoginDetails(user.getId(),user.getEmail(),
                user.getRole().name(), adminDto.getPassword(),Role.ADMIN);
        userClient.signupUser(loginDetails);
        return user;
    }

//    @Override
//    public User createUser(UserDto userDto) {
//
//        if(userRepository.existsByEmail(userDto.getEmail())){
//            throw new DataIntegrityViolationException("Already have this Email");
//        }
//        User user = new User();
//        BeanUtils.copyProperties(userDto, user);
//        String hashed=passwordEncoderConfig.passwordEncoder().encode(userDto.getPassword());
//        user.setPassword(hashed);
//        user.setRole(Role.USER);
//        User createdUser = userRepository.save(user);
//        LoginDetails loginDetails = new LoginDetails(user.getId(),user.getFirstname(),user.getEmail(),
//                 userDto.getPassword(),user.getRole());
//        userClient.signupUser(loginDetails);
//        return user;
//    }

    @Override
    public User createUser(UserDto userDto) throws MessagingException {

        User user = register(userDto);
        String verificationOtp = otpUtil.generateOtp();
        emailUtil.sentOtpEmail(userDto.getEmail(),verificationOtp);
        user.setOtp(verificationOtp);
        user.setOtpGeneratedDateTime(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User register(UserDto userDto) {

        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new DataIntegrityViolationException("Already have this Email");
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        String hashed=passwordEncoderConfig.passwordEncoder().encode(userDto.getPassword());
        user.setPassword(hashed);
        user.setRole(Role.USER);
        if (userDto.getSex() != null ) {
            try {
                user.setSex(Sex.valueOf(userDto.getSex().name().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid gender");
            }
        }
//        User createdUser = userRepository.save(user);
//        LoginDetails loginDetails = new LoginDetails(user.getId(),user.getFirstname(),user.getEmail(),
//                userDto.getPassword(),user.getRole());
//        userClient.signupUser(loginDetails);
        return user;
    }

    @Override
    public boolean verifyAccount(OtpDto otpDto) {
        User user = userRepository.findByEmail(otpDto.getEmail()).orElseThrow(()->
                new RuntimeException("user not found"));
        if (user.getOtp().equals(otpDto.getOtp()) &&
                Duration.between(user.getOtpGeneratedDateTime(),
                        LocalDateTime.now()).getSeconds() < (10 * 60)){
            user.setEnabled(true);
            userRepository.save(user);

            LoginDetails loginDetails = new LoginDetails(user.getId(),user.getFirstname(),user.getEmail(),
            user.getPassword(),user.getRole());
            userClient.signupUser(loginDetails);

            return true;
        }
        return false;
    }


    @Override
    public Doctor createDoctor(DoctorDto doctorDto) throws MessagingException, IOException {

        if(doctorRepository.existsByEmail(doctorDto.getEmail())){
            throw new DataIntegrityViolationException("Already have this Email");
        }
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDto, doctor);
        String hashed=passwordEncoderConfig.passwordEncoder().encode(doctorDto.getPassword());
        doctor.setPassword(hashed);
        doctor.setRole(Role.DOCTOR);
        String verificationOtp = otpUtil.generateOtp();
        emailUtil.sentOtpEmail(doctorDto.getEmail(),verificationOtp);
        doctor.setOtp(verificationOtp);
        doctor.setOtpGeneratedDateTime(LocalDateTime.now());

//        String imagePath = saveImage(image);
//        doctor.setLicenceImg(imagePath);


        Doctor createdUser = doctorRepository.save(doctor);
//        LoginDetails loginDetails = new LoginDetails(doctor.getId(),doctor.getName(),doctor.getEmail(),
//                doctorDto.getPassword(),doctor.getRole());
//        userClient.signupUser(loginDetails);
        return doctor;
    }


//    @Override
//    public Doctor createDoctor(DoctorDto doctorDto, MultipartFile image) throws MessagingException, IOException {
//
//        if(doctorRepository.existsByEmail(doctorDto.getEmail())){
//            throw new DataIntegrityViolationException("Already have this Email");
//        }
//        Doctor doctor = new Doctor();
//        BeanUtils.copyProperties(doctorDto, doctor);
//        String hashed=passwordEncoderConfig.passwordEncoder().encode(doctorDto.getPassword());
//        doctor.setPassword(hashed);
//        doctor.setRole(Role.DOCTOR);
//        String verificationOtp = otpUtil.generateOtp();
//        emailUtil.sentOtpEmail(doctorDto.getEmail(),verificationOtp);
//        doctor.setOtp(verificationOtp);
//        doctor.setOtpGeneratedDateTime(LocalDateTime.now());
//
////        String imagePath = saveImage(image);
////        doctor.setLicenceImg(imagePath);
//
//
//        Doctor createdUser = doctorRepository.save(doctor);
////        LoginDetails loginDetails = new LoginDetails(doctor.getId(),doctor.getName(),doctor.getEmail(),
////                doctorDto.getPassword(),doctor.getRole());
////        userClient.signupUser(loginDetails);
//        return doctor;
//    }


    private String saveImage(MultipartFile image) throws IOException {
        // Validate the image file
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Image file is null or empty");
        }

        // Generate a unique file name
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String fileExtension = StringUtils.getFilenameExtension(fileName);
        String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;

        // Define the directory to save the image
        String rootPath = System.getProperty("user.dir");
        String uploadDir = rootPath + "/src/main/resources/static/uploads/"; // Change this to your desired upload directory

        // Create the directory if it does not exist
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        // Define the file path
        String filePath = uploadDir + "/" + uniqueFileName;

        try {
            // Save the image file
            byte[] bytes = image.getBytes();
            Path imagePath = Paths.get(filePath);
            Files.write(imagePath, bytes);
            return filePath; // Return the file path
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
            throw new IOException("Failed to save image file", e);
        }
    }


    @Override
    public Pharma createPharma(PharmaDto pharmaDto) throws MessagingException {
        if(pharmaRepository.existsByEmail(pharmaDto.getEmail())){
            throw new DataIntegrityViolationException("Already have this Email");
        }
        Pharma pharma = new Pharma();
        BeanUtils.copyProperties(pharmaDto,pharma);
        String hashed=passwordEncoderConfig.passwordEncoder().encode(pharmaDto.getPassword());
        pharma.setPassword(hashed);
        pharma.setRole(Role.PHARMACY);
        String verificationOtp = otpUtil.generateOtp();
        emailUtil.sentOtpEmail(pharmaDto.getEmail(),verificationOtp);
        pharma.setOtp(verificationOtp);
        pharma.setOtpGeneratedDateTime(LocalDateTime.now());
        Pharma createPharma= pharmaRepository.save(pharma);
//        LoginDetails loginDetails = new LoginDetails(pharma.getId(),pharma.getOwnername(),
//                pharma.getEmail(),pharma.getPassword(),pharma.getRole());
//        userClient.signupUser(loginDetails);
        return pharma;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }


}
