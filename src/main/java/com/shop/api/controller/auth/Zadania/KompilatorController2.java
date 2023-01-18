package com.shop.api.controller.auth.Zadania;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

@CrossOrigin("*")
@RestController
public class KompilatorController2 {
    public record WynikUzytkownika(String string){
        public String getWynik(){
            return string;
        }
    }
    public static final Resource code_dir=new FileSystemResource("src/main/java/com/shop/api/controller/auth/Zadania/kody");
    @PostMapping("/kompilator2")
    public ResponseEntity<String> getResult(@RequestBody WynikUzytkownika wynikUzytkownika){
        try {
            FileWriter writer = new FileWriter("src/main/java/com/shop/api/controller/auth/Zadania/kody/Example.java");
            writer.write(wynikUzytkownika.getWynik());
            writer.close();
            System.out.println("String written to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
        kompilator();
        String wynikZapytania = "";
        return ResponseEntity.ok(wynikZapytania);
    }
    @PostConstruct
    @SuppressWarnings("")
    public String kompilator(){
        Stack<String> sciezkaPliku = new Stack<>();
        String fileToCompile = "src/main/java/com/shop/api/controller/auth/Zadania/kody/Example.java";
        sciezkaPliku.push(fileToCompile);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int compilationResult = compiler.run(null, null, null, sciezkaPliku.peek());

        if(compilationResult == 0) {
            System.out.println("Compilation is successful");
        } else {
            System.out.println("Compilation Failed");
        }
        return "chuj";
    }
    }

