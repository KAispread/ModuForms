package com.modu.ModuForm.app.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@TestPropertySource(value = "classpath:application-test-db.properties")
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @DisplayName("save(): 같은 트랜잭션 안에서 User 저장 후 id 값을 불러오는데 성공한다.")
    @Test
    void save() {
        //given
        User user = User.builder()
                .gender(Gender.MAN)
                .email("adfsd@naver.com")
                .phone("01032491031")
                .birth(19980112L)
                .role(Role.USER)
                .name("예진")
                .nickName("Rosa")
                .build();

        //when
        User save = userRepository.save(user);

        //then
        assertThat(save.getId()).isGreaterThan(0);
    }

    @DisplayName("save(): nickname이 중복될 경우 저장에 저장에 실패한다.")
    @Test
    void saveFail() {
        //given
        User user = User.builder()
                .gender(Gender.WOMAN)
                .email("adfsd@naver.com")
                .phone("01032491031")
                .birth(19980112L)
                .role(Role.USER)
                .name("예진")
                .nickName("Rosa")
                .build();

        User user2 = User.builder()
                .gender(Gender.MAN)
                .email("pqwe@naver.com")
                .phone("0109991031")
                .birth(19980112L)
                .role(Role.USER)
                .name("기우")
                .nickName("Rosa")
                .build();


        //when
        userRepository.save(user);

        //then
        assertThatThrownBy(() -> userRepository.save(user2)).isInstanceOf(RuntimeException.class);
    }

    @DisplayName("findByEmail()에 email을 넘겨주면 해당 User 엔티티가 반환된다")
    @Test
    void findByEmailSuccess() {
        //given
        String email = "adfsd@naver.com";
        User user = User.builder()
                .gender(Gender.MAN)
                .email(email)
                .phone("01032491031")
                .birth(19980112L)
                .role(Role.USER)
                .name("예진")
                .nickName("Rosa")
                .build();
        userRepository.save(user);

        //when
        User findUser = userRepository.findByEmail(email).orElseThrow();

        //then
        assertThat(findUser.getEmail()).isEqualTo(email);
        assertThat(findUser.getPhone()).isEqualTo("01032491031");
        assertThat(findUser.getBirth()).isEqualTo(19980112L);
        assertThat(findUser.getName()).isEqualTo("예진");
        assertThat(findUser.getNickName()).isEqualTo("Rosa");
    }

    @DisplayName("findByEmail()에 존재하지 않는 User email을 넘겨주면 예외가 발생한다.")
    @Test
    void findByEmailFail() {
        //given
        String email = "adfsd@naver.com";
        User user = User.builder()
                .gender(Gender.MAN)
                .email(email)
                .phone("01032491031")
                .birth(19980112L)
                .role(Role.USER)
                .name("예진")
                .nickName("Rosa")
                .build();
        userRepository.save(user);

        //then
        assertThatThrownBy(() -> userRepository.findByEmail("adsfqewr@naver.com")
                .orElseThrow(IllegalArgumentException::new))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("findByNickName()에 nickname을 넘겨주면 해당 User 엔티티가 반환된다")
    @Test
    void findByNickNameSuccess() {
        //given
        String email = "adfsd@naver.com";
        String nickname = "Rosa";
        User user = User.builder()
                .gender(Gender.MAN)
                .email(email)
                .phone("01032491031")
                .birth(19980112L)
                .role(Role.USER)
                .name("예진")
                .nickName(nickname)
                .build();
        userRepository.save(user);

        //when
        User findUser = userRepository.findByNickName(nickname).orElseThrow();

        //then
        assertThat(findUser.getEmail()).isEqualTo(email);
        assertThat(findUser.getPhone()).isEqualTo("01032491031");
        assertThat(findUser.getBirth()).isEqualTo(19980112L);
        assertThat(findUser.getName()).isEqualTo("예진");
        assertThat(findUser.getNickName()).isEqualTo(nickname);
    }

    @DisplayName("findByNickName()에 존재하지 않는 User nickname을 넘겨주면 예외가 발생한다.")
    @Test
    void findByNickNameFail() {
        //given
        String email = "adfsd@naver.com";
        String nickname = "Rosa";
        User user = User.builder()
                .gender(Gender.MAN)
                .email(email)
                .phone("01032491031")
                .birth(19980112L)
                .role(Role.USER)
                .name("예진")
                .nickName(nickname)
                .build();
        userRepository.save(user);

        //then
        assertThatThrownBy(() -> userRepository.findByNickName("Alexander")
                .orElseThrow(IllegalArgumentException::new))
                .isInstanceOf(IllegalArgumentException.class);
    }


}