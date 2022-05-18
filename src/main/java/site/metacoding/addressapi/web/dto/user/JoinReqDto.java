package site.metacoding.addressapi.web.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.addressapi.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoinReqDto {

    @Pattern(regexp = "[a-zA-Z1-9]{3,20}", message = "유저네임은 한글이 들어갈 수 없습니다.")
    @Size(min = 3, max = 20)
    @NotBlank
    private String username;

    @Size(min = 4, max = 20)
    @NotBlank
    private String password;

    @NotBlank
    private String address;

    public User toEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAddress(address);
        return user;
    }
}
