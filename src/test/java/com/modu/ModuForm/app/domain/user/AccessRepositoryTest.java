package com.modu.ModuForm.app.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test-db.properties")
@DataJpaTest
class AccessRepositoryTest {
    @Autowired
    private AccessRepository accessRepository;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("올바른 엔티티를 넘겨주면 Access 데이터를 저장한다.")
    @Test
    void save() {
        //given
        String id = "pop123";
        String pwd = "abcdefgd1234";
        User user = User.builder()
                .gender(Gender.MAN)
                .email("adfsd@naver.com")
                .phone("01032491031")
                .birth(19980112L)
                .role(Role.USER)
                .name("예진")
                .nickName("Rosa")
                .build();
        userRepository.save(user);
        Access access = Access.builder()
                .user(user)
                .userId(id)
                .password(pwd)
                .build();

        //when
        Access save = accessRepository.save(access);

        //then
        assertThat(save.getUserId()).isEqualTo(id);
        assertThat(save.getPassword()).isEqualTo(pwd);
    }

    @DisplayName("User가 null이면 저장에 실패한다")
    @Test
    void saveNullUser() {
        //given
        String id = "pop123";
        String pwd = "abcdefgd1234";
        Access access = Access.builder()
                .user(null)
                .userId(id)
                .password(pwd)
                .build();

        //then
        assertThatThrownBy(() -> accessRepository.save(access)).isInstanceOf(RuntimeException.class);
    }


    @DisplayName("findByUser()에 User을 넘겨주면 해당 Access 가 반환된다")
    @Test
    void findByUserSuccess() {
        //given
        String id = "pop123";
        String pwd = "abcdefgd1234";
        User user = User.builder()
                .gender(Gender.MAN)
                .email("adfsd@naver.com")
                .phone("01032491031")
                .birth(19980112L)
                .role(Role.USER)
                .name("예진")
                .nickName("Rosa")
                .build();
        User saveUser = userRepository.save(user);
        Access testAccess = Access.builder()
                .user(user)
                .userId(id)
                .password(pwd)
                .build();
        accessRepository.save(testAccess);

        //when
        Access access = accessRepository.findByUser(saveUser).orElseThrow();

        //then
        assertThat(access.getUserId()).isEqualTo(id);
        assertThat(access.getPassword()).isEqualTo(pwd);
        assertThat(access.getUser().getName()).isEqualTo("예진");
        assertThat(access.getUser().getEmail()).isEqualTo("adfsd@naver.com");
    }

    @DisplayName("findByUser()에 DB에 저장되지 않은 User을 넘겨주면 예외가 발생한다")
    @Test
    void findByUserFail() {
        //given
        String id = "pop123";
        String pwd = "abcdefgd1234";
        User user = User.builder()
                .gender(Gender.MAN)
                .email("adfsd@naver.com")
                .phone("01032491031")
                .birth(19980112L)
                .role(Role.USER)
                .name("예진")
                .nickName("Rosa")
                .build();
        User save = userRepository.save(user);
        Access testAccess = Access.builder()
                .user(save)
                .userId(id)
                .password(pwd)
                .build();
        accessRepository.save(testAccess);

        //when
        User notSave = User.builder()
                .gender(Gender.MAN)
                .email("adfsd@naver.com")
                .phone("0112491031")
                .birth(19980112L)
                .role(Role.USER)
                .name("교환")
                .nickName("Tommy")
                .build();

        //then
        assertThatThrownBy(() -> accessRepository.findByUser(User.builder()
                .gender(Gender.MAN)
                .email("adfsd@naver.com")
                .phone("0112491031")
                .birth(19980112L)
                .role(Role.USER)
                .name("교환")
                .nickName("Tommy")
                .build())).isInstanceOf(InvalidDataAccessApiUsageException.class);
    }

    @DisplayName("findByUserId()에 유저의 아이디를 넘겨주면 해당 Access 가 반환된다")
    @Test
    void findByUserIdSuccess() {
        //given
        String id = "pop123";
        String pwd = "abcdefgd1234";
        User user = User.builder()
                .gender(Gender.MAN)
                .email("adfsd@naver.com")
                .phone("01032491031")
                .birth(19980112L)
                .role(Role.USER)
                .name("예진")
                .nickName("Rosa")
                .build();
        userRepository.save(user);
        Access testAccess = Access.builder()
                .user(user)
                .userId(id)
                .password(pwd)
                .build();
        accessRepository.save(testAccess);

        //when
        Access access = accessRepository.findByUserId(id).orElseThrow(IllegalArgumentException::new);

        //then
        assertThat(access.getUserId()).isEqualTo(id);
        assertThat(access.getPassword()).isEqualTo(pwd);
        assertThat(access.getUser().getName()).isEqualTo("예진");
        assertThat(access.getUser().getEmail()).isEqualTo("adfsd@naver.com");
    }

    @DisplayName("findByUserId()에 존재하지 않는 유저 아이디를 넘겨주면 예외가 발생한다")
    @Test
    void findByUserIdFail() {
        //given
        String id = "pop123";
        String pwd = "abcdefgd1234";
        User user = User.builder()
                .gender(Gender.MAN)
                .email("adfsd@naver.com")
                .phone("01032491031")
                .birth(19980112L)
                .role(Role.USER)
                .name("예진")
                .nickName("Rosa")
                .build();
        User saveUser = userRepository.save(user);
        Access testAccess = Access.builder()
                .user(user)
                .userId(id)
                .password(pwd)
                .build();
        accessRepository.save(testAccess);

        //then
        assertThatThrownBy(() -> accessRepository.findByUserId("wef123").orElseThrow(IllegalArgumentException::new))
                .isInstanceOf(IllegalArgumentException.class);
    }
}