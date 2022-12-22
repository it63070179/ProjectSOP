package com.example.userservice2.command.rest;

import com.example.userservice2.command.AddAdminCommand;
import com.example.userservice2.command.AddDoctorCommand;
import com.example.userservice2.command.UpdateDoctorCommand;
import com.example.userservice2.command.UpdateUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
@RestController
@RequestMapping("/users")
public class UserCommandController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private final CommandGateway commandGateway;

    @Autowired
    public UserCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping("/update")
    public String updateUser(@RequestParam("id") String id,@RequestParam("name") String name,
                             @RequestParam("email") String email, @RequestParam("gender") String gender,
                             @RequestParam("file") MultipartFile file) throws IOException{
        UpdateUserCommand command = UpdateUserCommand.builder().id(id).name(name).email(email).gender(gender).picture(file.getOriginalFilename()).build();
        File saveFile = new File("C:\\Users\\user\\OneDrive\\เดสก์ท็อป\\ProjectSOP\\ProjectSOP\\user-service-2\\src\\main\\resources\\static", file.getOriginalFilename());
        saveFile.createNewFile();
        try(FileOutputStream fout = new FileOutputStream(saveFile)){
            fout.write(file.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
        Object users = rabbitTemplate.convertSendAndReceive("UserExchange", "updateUser", command);
        return (String) users;
    }


    @PutMapping("/adddoctor")
    public String addDoctor(@RequestBody AddDoctorRestModel model){
        AddDoctorCommand command = AddDoctorCommand.builder()
//                .id(model.getId())
                .email(model.getEmail())
                .role(model.getRole())
                .build();
        Object users = rabbitTemplate.convertSendAndReceive("UserExchange", "addDoctor", command);
        return (String) users;
    }

    @PutMapping("/addadmin")
    public String addAdmin(@RequestBody AddAdminRestModel model){
        AddAdminCommand command = AddAdminCommand.builder()
//                .id(model.getId())
                .email(model.getEmail())
                .role(model.getRole())
                .build();
        System.out.println("Command : " + command);
//        System.out.println("ID :  "+ model.getId());
        System.out.println("Email : " + model.getEmail());
        System.out.println("Role : " + model.getRole());
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        System.out.println("result : " + result);
        return result;
    }

    @PutMapping("/update/doctor")
    public String updateDoctor(@RequestParam("id") String id,@RequestParam("name") String name,
                               @RequestParam("email") String email, @RequestParam("gender") String gender,
                               @RequestParam("file") MultipartFile file,@RequestParam("branch") String branch,@RequestParam("description") String description) throws IOException{
        UpdateDoctorCommand command = UpdateDoctorCommand.builder().id(id).name(name).email(email).gender(gender).picture(file.getOriginalFilename()).branch(branch).description(description).build();
        File saveFile = new File("C:\\Users\\user\\OneDrive\\เดสก์ท็อป\\ProjectSOP\\ProjectSOP\\user-service-2\\src\\main\\resources\\static", file.getOriginalFilename());
        saveFile.createNewFile();
        try(FileOutputStream fout = new FileOutputStream(saveFile)){
            fout.write(file.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        System.out.println("result : " + result);
        return result;
    }
}
