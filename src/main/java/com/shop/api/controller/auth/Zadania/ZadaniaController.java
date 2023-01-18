package com.shop.api.controller.auth.Zadania;

import com.shop.api.model.StringKomentarz;
import com.shop.model.Komentarze;
import com.shop.model.LocalUser;
import com.shop.model.Zadania;
import com.shop.model.repository.KomentarzeRepository;
import com.shop.model.repository.LocalUserDao;
import com.shop.model.repository.ZadaniaDAO;
import com.shop.model.repository.ZadaniaUserDao;
import com.shop.service.UserService;
import com.shop.service.ZadaniaService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/zadania")
@CrossOrigin("*")
public class ZadaniaController {
    private final ZadaniaService zadaniaService;
    private final ZadaniaDAO zadaniaDAO;
    private final ZadaniaUserDao zadaniaUserDao;
    private final UserService userService;
    private final KomentarzeRepository komentarzeRepository;
    private final LocalUserDao localUserDao;

    public ZadaniaController(ZadaniaService zadaniaService, ZadaniaDAO zadaniaDAO, ZadaniaUserDao zadaniaUserDao, UserService userService, KomentarzeRepository komentarzeRepository, LocalUserDao localUserDao) {
        this.zadaniaService = zadaniaService;
        this.zadaniaDAO = zadaniaDAO;
        this.zadaniaUserDao = zadaniaUserDao;
        this.userService = userService;
        this.komentarzeRepository = komentarzeRepository;
        this.localUserDao = localUserDao;
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
    public void sendWynik(@AuthenticationPrincipal LocalUser user,@RequestBody String wynik,@PathVariable
                                    Long id, @PathVariable Long userId){

        zadaniaDAO.updateWynikuzytkownika(userId,id,wynik);

    }
    private boolean userHasPermission(LocalUser user, Long id){
        return user.getId()==id;
    }
    @CrossOrigin
    @GetMapping("/komentarze/{id}")
    public ResponseEntity<List<Komentarze>> getKomentarze(@PathVariable Long id){
        return ResponseEntity.ok(komentarzeRepository.findByZadania_Id(id));

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
