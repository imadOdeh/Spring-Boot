package com.example.shoppingCart;

import com.example.shoppingCart.Models.Register;
import com.example.shoppingCart.Entities.AppUser;
import com.example.shoppingCart.Entities.AppRole;
import com.example.shoppingCart.Repositories.UserRepository;
import com.example.shoppingCart.Repositories.UserRoleRepository;
import com.example.shoppingCart.Repositories.RoleRepository;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private final String INSERT_SQL = "INSERT INTO user_role(id,user_id, role_id) values(:id,:user_id,:role_id)";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("register", new Register());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerSubmit(@Valid Register register, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || !register.getPassword().equals(register.getRepeate_password())) {
            model.addAttribute("message", "There are some errors !");
            return "register";
        }
        String encryptedPassword = EncrytedPasswordUtils.encrytePassword(register.getPassword());
        AppUser user = new AppUser(register.getUsername(), encryptedPassword);

        userRepository.save(user);

        AppRole appRole = new AppRole("Registered");
        roleRepository.save(appRole);

//        UserRole userRole = new UserRole((long) 2,user.getUserId());
//        userRoleRepository.save(userRole);
        SqlParameterSource parameters;
        parameters = new MapSqlParameterSource()
                .addValue("id", userRoleRepository.count() + 1)
                .addValue("user_id", user.getUserId())
                .addValue("role_id", (long) 2);
        namedParameterJdbcTemplate.update(INSERT_SQL, parameters);

        //model.addAttribute("register",register);
        model.addAttribute("message", "You have been registered successfully");
        return "welcomePage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }

        return "403Page";
    }

}
