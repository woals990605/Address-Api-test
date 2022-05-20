package site.metacoding.addressapi.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.addressapi.handler.ex.CustomException;
import site.metacoding.addressapi.service.UserService;
import site.metacoding.addressapi.web.dto.user.JoinReqDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/login-form")
    public String loginForm() {
        return "/user/loginForm";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "/user/joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid JoinReqDto joinReqDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors() == true) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fe : bindingResult.getFieldErrors()) {
                errorMap.put(fe.getField(), fe.getDefaultMessage());
            }

            throw new CustomException(errorMap.toString());
        }

        userService.회원가입(joinReqDto.toEntity());

        return "redirect:/login-form";
    }

    @GetMapping("/user/juso-popup")
    public String jusoPopup() {
        System.out.println("gdgdgdgdgdg");
        return "/user/jusoPopup";
    }

    @PostMapping("/user/juso-popup")
    public String jusoCallBack(String inputYn, String roadFullAddr, Model model) {

        model.addAttribute("inputYn", inputYn);
        model.addAttribute("roadFullAddr", roadFullAddr);
        System.out.println(inputYn);
        System.out.println(roadFullAddr);
        return "/user/jusoPopup";
    }
}