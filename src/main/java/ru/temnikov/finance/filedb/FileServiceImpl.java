package ru.temnikov.finance.filedb;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import ru.temnikov.finance.user.entity.User;
import ru.temnikov.finance.user.service.UserService;

import java.io.*;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final static String FILENAME = "C:\\naumen\\finance-app\\src\\main\\resources\\filedb\\filedb.txt";

    private final UserService userService;

    public FileServiceImpl(final UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void load() {
        try (FileOutputStream fileOut = new FileOutputStream(FILENAME);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(userService.getUsers());
            System.out.println("Object serialized to file: " + FILENAME);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void save() {
        try (FileInputStream fileIn = new FileInputStream(FILENAME);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            List<User> deserializedUsers = (List<User>) in.readObject();
            userService.setUsers(deserializedUsers);
            System.out.println("Deserialized object: " + deserializedUsers);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
