package com.example.music.serviceImp;

import com.example.music.exception.NotFoundException;
import com.example.music.model.dto.AuthResponse;
import com.example.music.model.dto.SigninDTO;
import com.example.music.model.dto.SignupDTO;
import com.example.music.model.entity.Album;
import com.example.music.model.entity.User;
import com.example.music.repository.UserRepository;
import com.example.music.security.JwtService;
import com.example.music.service.AuthService;
import com.example.music.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;

    public AuthServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userService = userService;
    }
    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public AuthResponse register(SignupDTO signupDTO) {
        // Kiểm tra xem email đã tồn tại hay chưa
        int temp = userService.isUserNameValid(signupDTO.getUsername(),signupDTO.getEmail());
        if (temp == 1 || temp == 2 || temp == 3 || temp == 4 || temp == 5  ) {
            if(temp == 1){
                throw new NotFoundException("Username đang để trống");
            }else if(temp == 2){
                throw new NotFoundException("Eamil đang để trống");
            } else if (temp == 3) {
                throw new NotFoundException("Username và Email đã tồn tại");
            } else if (temp == 4){
                throw new NotFoundException("Username đã tồn tại");
            }else{
                throw new NotFoundException("Email đã tồn tại");
            }
            // Email đã tồn tại, trả về lỗi

        }else {
            User user = new User();
            user.setUsername(signupDTO.getUsername());
            user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
            user.setEmail(signupDTO.getEmail());
            Album album = new Album();
            user.setAlbum(album);
            album.setUser(user);
            userRepository.save(user);
            var jwt = jwtService.generateToken(user);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(jwt);
            return authResponse;
        }
    }

    public AuthResponse login(SigninDTO signinDTO) {
        // Kiểm tra xem người dùng có tồn tại không
        Optional<User> user = userRepository.findByEmail(signinDTO.getEmail());
        if (!user.isPresent()) {
            throw new NotFoundException("Người dùng không tồn tại.");
        }else {
            // Kiểm tra tính đúng đắn của mật khẩu
            if (!passwordEncoder.matches(signinDTO.getPassword(), user.get().getPassword())) {
                throw new NotFoundException("Sai tên đăng nhập hoặc mật khẩu.");
            }else {
                // Tạo mã token
                String jwt = jwtService.generateToken(user.get());

                AuthResponse authResponse = new AuthResponse();
                authResponse.setToken(jwt);
                // Có thể thêm thông tin người dùng khác vào đây nếu cần
                return authResponse;
            }
        }
    }

    @Override
    public AuthResponse resetPassword(SigninDTO signinDTO) {
        // Tìm người dùng theo email
        Optional<User> user = userRepository.findByEmail(signinDTO.getEmail());

        if (user.isEmpty()) {
            throw new NotFoundException("Không tìm thấy người dùng với địa chỉ email: " + signinDTO.getEmail());
        }else {
            User userTemp = user.orElse(null);
            // Kiểm tra xem mật khẩu đã cho (password) khớp với mật khẩu cũ trong cơ sở dữ liệu
            if (passwordEncoder.matches(signinDTO.getPassword(), userTemp.getPassword())) {
                throw new NotFoundException("Mật khẩu bị trùng với mật khẩu cũ.");
            }

            // Mã hóa mật khẩu mới
            String encodedNewPassword = passwordEncoder.encode(signinDTO.getPassword());

            // Cập nhật mật khẩu mới cho người dùng
            userTemp.setPassword(encodedNewPassword);

            // Lưu thông tin người dùng đã cập nhật vào cơ sở dữ liệu
            userRepository.save(userTemp);
            // Tạo mã token
            String jwt = jwtService.generateToken(userTemp);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(jwt);
            // Có thể thêm thông tin người dùng khác vào đây nếu cần
            return authResponse;
        }
    }


}
