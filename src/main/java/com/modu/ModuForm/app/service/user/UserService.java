package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.web.dto.user.*;

public interface UserService {
    public Access login(LoginRequestDto loginRequestDto);
    public Long register(UserRegisterDto userRequestDto);
    public UserSubDetailsDto getUserSubDetails(Long id);
    public Long updateUser(Long id, UserRegisterDto userRequestDto);
    public UserDetailsDto getUserDetails(Long id);
    public Long getUserPk(String nickName);
}
