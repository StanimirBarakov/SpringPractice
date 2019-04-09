package springprojdect.stunodemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springprojdect.stunodemo.model.pojos.User;
import springprojdect.stunodemo.model.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private static ArrayList<User> users = new ArrayList<>();
//    static {
//        users.add(new User("Pesho",13,"asdasd@asd.sd","cskarlz"));
//        users.add(new User("Sasho",14,"asdasdadsasdasd@asd.sd","csk@rlz"));
//    }

    @GetMapping(value = "/users/all")
    public List<User> getUsers() {
        return users;
    }

    @PostMapping(value = "/users/add")
    public void saveUser(@RequestBody User u){
        System.out.println(u.toString());
    }

    @GetMapping(value = "/building/{str_id}/block/{bl_id}/floor/{floor_id}")
    public String getInfo(@PathVariable("str_id") int street,
                          @PathVariable("bl_id") int block,
                          @PathVariable("floor_id") int floor){
        return "Bravo ti si na " + street + " ulica, " + block + " blok, " + floor + " etaj.";

    }
    @GetMapping(value = "users/{name}")
    public String getByName(@PathVariable("name") String name){
        return "Hi " + name;
    }
    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/users")
    public List<User> getAll(){

        return userDao.getAllUsers();
    }




}
