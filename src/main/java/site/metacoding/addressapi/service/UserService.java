package site.metacoding.addressapi.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.addressapi.domain.user.User;
import site.metacoding.addressapi.domain.user.UserRepository;
import site.metacoding.addressapi.handler.ex.CustomException;
import site.metacoding.addressapi.web.dto.user.UpdateReqDto;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword(); // 1234
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); // 해쉬 알고리즘
        user.setPassword(encPassword);

        userRepository.save(user);
    }

    public User 회원상세보기(Integer userId) {
        Optional<User> userOp = userRepository.findById(userId);
        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            return userEntity;
        } else {
            throw new CustomException("해당 유저가 존재하지 않습니다.");
        }
    }

    @Transactional
    public User 회원수정하기(Integer userId, UpdateReqDto updateReqDto) {
        Optional<User> userOp = userRepository.findById(userId);

        if (userOp.isPresent()) {
            User userEntity = userOp.get();

            if (!userEntity.getAddress().equals(updateReqDto.getAddress())) {
                userEntity.setAddress(updateReqDto.getAddress());
            }

            return userEntity;
        } else {
            throw new CustomException("존재하지 않는 사용자입니다.");
        }
    }
}
