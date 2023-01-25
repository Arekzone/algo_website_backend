package com.shop.api.controller.auth.Zadania;

import com.google.gson.Gson;
import com.shop.api.model.JdoodleBody;
import com.shop.api.model.JdoodleRespond;
import com.shop.model.LocalUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Pattern;

@RestController
@CrossOrigin("*")
public class KompilatorController {

    @PostMapping(value = "/kompilator", produces = "application/json")
    public ResponseEntity<JdoodleRespond> getResult(@RequestBody JdoodleBody jdoodleBody) throws ProtocolException {

        JdoodleRespond jdoodleRespond = null;
        try {
            URL url = new URL("https://api.jdoodle.com/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            JdoodleBody jdoodleBody1 = new JdoodleBody();
            jdoodleBody.getScript();
            String inputBody = jdoodleBody.getScript().toString();
            Pattern pattern = Pattern.compile("\\r?\\n");
            String sanitized = pattern.matcher(inputBody).replaceAll(" ");
            jdoodleBody1.setScript(sanitized);
            String input = "{\"clientId\": \"" + jdoodleBody.getClientId() + "\",\"clientSecret\":\"" + jdoodleBody.getClientSecret() + "\",\"script\":\"" + jdoodleBody1.getScript() +
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
            String output;
            StringBuilder sb = null;
            output=bufferedReader.readLine();
            System.out.println(output);
            String[] dataArray = output.split(",");
            String output2 = output.split("output\":\"")[1].split("\",")[0];
//            for (int i = 0; i < dataArray.length; i++) {
//                dataArray[i] = dataArray[i].replace("\"", "").replace("\\n","")
//                        .replace("{","").replace("}","");
//                dataArray[i] = dataArray[i].split(":")[1];
//            }
//            System.out.println(Arrays.toString(dataArray));
            String output3 = output2.replace("\"","").replace("\\n","");
            //walidacja po stronie backendu
            if(output3.length()>50){
                output3="Błąd kompilacji";
            }
            jdoodleRespond.setOutput(output3);
            jdoodleRespond.setStatusCode("0");
            jdoodleRespond.setMemory("0");
            jdoodleRespond.setCpuTime("0");
            JdoodleRespond jdoodleRespond1 = new JdoodleRespond();
            System.out.println(jdoodleRespond.toString());
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(jdoodleRespond);
    }
}
