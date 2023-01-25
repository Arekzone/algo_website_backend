package com.shop.api.controller.auth.Zadania;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.api.controller.auth.Zadania.kody.TaskDTO;
import com.shop.api.controller.auth.Zadania.kody.TaskWrapper;
import com.shop.api.model.StringKomentarz;
import com.shop.api.model.WynikModel;
import com.shop.model.*;
import com.shop.model.repository.KomentarzeRepository;
import com.shop.model.repository.LocalUserDao;
import com.shop.model.repository.ZadaniaDAO;

import com.shop.service.UserService;
import com.shop.service.ZadaniaService;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/zadania")
@CrossOrigin("*")
public class ZadaniaController {
    private final ZadaniaService zadaniaService;
    private final ZadaniaDAO zadaniaDAO;

    private final UserService userService;
    private final KomentarzeRepository komentarzeRepository;
    private final LocalUserDao localUserDao;
    private final UserZadanieRepository userZadanieRepository;
    private final UserZadanie userZadanie;


    public ZadaniaController(ZadaniaService zadaniaService, ZadaniaDAO zadaniaDAO, UserService userService, KomentarzeRepository komentarzeRepository, LocalUserDao localUserDao, UserZadanieRepository userZadanieRepository, UserZadanie userZadanie) {
        this.zadaniaService = zadaniaService;
        this.zadaniaDAO = zadaniaDAO;

        this.userService = userService;
        this.komentarzeRepository = komentarzeRepository;
        this.localUserDao = localUserDao;
        this.userZadanieRepository = userZadanieRepository;
        this.userZadanie = userZadanie;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Zadania>> getZadania(){
        List<Zadania> zadania= zadaniaDAO.findAll();
        return ResponseEntity.ok(zadania);
    }
//    @GetMapping("/zadania/uzytkownika")
//    public ResponseEntity<List<Zadania>> getZadania(@AuthenticationPrincipal LocalUser localUser){
//        return ResponseEntity.ok();
//    }
    @Transactional
    @PutMapping("{userId}/wykonane/{id}")
    public ResponseEntity<HttpStatus> sendWynik(@AuthenticationPrincipal LocalUser user, @RequestBody WynikModel wynik, @PathVariable
                                    Long id, @PathVariable Long userId){

        Optional<LocalUser>localUser = localUserDao.findById(userId);
        Optional<Zadania> zadania = zadaniaDAO.findById(id);
        System.out.println(wynik);
        if(localUser.isPresent()){
            LocalUser localUser1 = localUser.get();
            Zadania zadania1 = zadania.get();
            WynikModel wynikModel = new WynikModel();
            String wynik1 = wynik.getWynik();
            if(zadania1.getPoprawnyWynik().equals(wynik1)) {
                UserZadanie userZadanie1 = new UserZadanie();
                userZadanie1.setUser(localUser1);
                userZadanie1.setWynik_uzytkownika(wynik1);
                userZadanie1.setTask(zadania1);
                userZadanieRepository.save(userZadanie1);
                return ResponseEntity.ok(HttpStatus.ACCEPTED);
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    private boolean userHasPermission(LocalUser user, Long id){
        return user.getId()==id;
    }
    @CrossOrigin
    @GetMapping("/komentarze/{id}")
    public ResponseEntity<List<Komentarze>> getKomentarze(@PathVariable Long id){
        return ResponseEntity.ok(komentarzeRepository.findByZadania_Id(id));

    }
    @GetMapping("/mostpopular")
    public ResponseEntity<String> getMostPopular() {
        List<Object[]> listOfObjects = userZadanieRepository.find12MostDoneTasks();
        ObjectMapper mapper = new ObjectMapper();
        List<TaskDTO> taskDTOList = new ArrayList<>();
        for (Object[] task : listOfObjects) {
            taskDTOList.add(new TaskDTO((Long) task[0], (Long) task[1]));
        }
        TaskWrapper taskWrapper = new TaskWrapper();
        taskWrapper.setTasks(taskDTOList);
        String json;
        try {
            json = mapper.writeValueAsString(taskDTOList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(json);
    }

    @Transactional
    @PostMapping("/komentarze/{zadanieId}/{userId}")
    public ResponseEntity addKomentarz(@AuthenticationPrincipal LocalUser user, @RequestBody StringKomentarz komentarz, @PathVariable Long userId, @PathVariable Long zadanieId){

        Optional<Zadania> zadanie = zadaniaDAO.findById(zadanieId);
        Optional<LocalUser>localUser = localUserDao.findById(userId);
        if(zadanie.isPresent()){
            LocalUser localUser1 = localUser.get();
            System.out.println(localUser1.getId());
            List<Komentarze> komentarzes = new ArrayList<>();
            Komentarze komentarze = new Komentarze();
            String komentarz1 = komentarz.getKomentarz();
            komentarze.setKomentarz(komentarz1);
            komentarze.setLocalUser(localUser1);
            Zadania zadania = zadanie.get();
            komentarze.setZadania(zadania);
            komentarzes.add(komentarze);
            komentarzeRepository.save(komentarze);
            List<Komentarze> komentarzes1 = komentarzeRepository.findByZadania_Id(zadanieId);
            zadania.setKomentarzes(komentarzes1);
            zadaniaDAO.save(zadania);
        }
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
