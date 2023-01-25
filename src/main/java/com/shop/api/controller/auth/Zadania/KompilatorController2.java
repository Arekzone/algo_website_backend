package com.shop.api.controller.auth.Zadania;





import com.shop.model.Zadania;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

@CrossOrigin("*")
@RestController

public class KompilatorController2 extends Object {


    private static Stack<String> sciezkaPliku = new Stack<>();
    static Class<?> clazz;
    static URL url;
    static boolean propagate = false;
    private static String wynik;
    @Autowired
    RestTemplate restTemplate;


    public record WynikUzytkownika(String string) {
        public String getWynik() {
            return string;
        }
    }

    public static final Resource code_dir = new FileSystemResource("src/main/java/com/shop/api/controller/auth/Zadania/kody");


    @PostMapping("/wynik")
    public ResponseEntity<String> getResult(@RequestBody WynikUzytkownika wynikUzytkownika) {

        String url = "http://localhost:8081/wynik";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, null, String.class);
        String responseBody = response.getBody();

        return ResponseEntity.ok(responseBody);


    }


    public void kompilator() throws
            ClassNotFoundException,
            IllegalAccessException,
            InstantiationException, NoSuchMethodException {
        String fileToCompile = "src/main/java/com/shop/api/controller/auth/Zadania/kody/Zadanie.java";
        sciezkaPliku.push(fileToCompile);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int compilationResult = compiler.run(null, null, null, sciezkaPliku.peek());
        if (compilationResult == 0) {
            System.out.println("Compilation is successful");
        } else {
            System.out.println("Compilation Failed");
        }
        Path source = Paths.get("C:\\Users\\aeksz\\IdeaProjects\\webshopSpring\\src\\main\\java\\com\\shop\\api\\controller\\auth\\Zadania\\kody\\Zadanie.java");
        Path destination = Paths.get("C:\\Users\\aeksz\\IdeaProjects\\KompilatorApplication\\src\\main\\java\\com\\example\\kompilatorapplication\\plikdokompilacji");
        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        String url = "http://localhost:8081/wynik";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, null, String.class);
        String responseBody = response.getBody();
        System.out.println(response.getBody());
    }


}





