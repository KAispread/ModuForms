package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.AccessRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserAccountService {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final AccessRepository accessRepository;

    @Transactional
    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        answerRepository.deleteAllByUser(user);
        accessRepository.deleteByUser(user);
        userRepository.delete(user);
    }
}
