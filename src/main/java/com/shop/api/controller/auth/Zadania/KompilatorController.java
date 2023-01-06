package com.shop.api.controller.auth.Zadania;

import com.google.gson.Gson;
import com.shop.api.model.JdoodleBody;
import com.shop.api.model.JdoodleRespond;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@RestController
@CrossOrigin("*")
public class KompilatorController {

    @PostMapping(value = "/kompilator", produces = "application/json")
    public ResponseEntity<String> getResult(@AuthenticationPrincipal @RequestBody JdoodleBody jdoodleBody) throws ProtocolException {
        JdoodleRespond jdoodleRespond = null;
        String output = null;
        StringBuilder sb = null;
        String json = null;
        String s = null;
        Gson gson = null;
        try {
            URL url = new URL("https://api.jdoodle.com/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            String input = "{\"clientId\": \"" + jdoodleBody.getClientId() + "\",\"clientSecret\":\"" + jdoodleBody.getClientSecret() + "\",\"script\":\"" + jdoodleBody.getScript() +
                    "\",\"language\":\"" + jdoodleBody.getLanguage() + "\",\"versionIndex\":\"" + jdoodleBody.getVersionIndex() + "\"} ";
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Please check your inputs : HTTP error code : " + connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));
            jdoodleRespond = new JdoodleRespond();
            System.out.println("Output from JDoodle .... \n");
            output = bufferedReader.readLine();
            sb = new StringBuilder();
            sb.append(output);
            System.out.println(sb);
            gson = new Gson();
            s = gson.toJson(sb);
            jdoodleRespond.setOutput(output);
            JdoodleRespond jdoodleRespond1 = new JdoodleRespond();

            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(s);
    }
}
