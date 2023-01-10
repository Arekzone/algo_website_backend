package com.shop.api.controller.auth.user;

import com.shop.model.Address;
import com.shop.model.LocalUser;
import com.shop.model.repository.AddressDAO;
import com.shop.model.repository.LocalUserDao;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
@CrossOrigin(origins="*")
public class UserController {

    private AddressDAO addressDAO;
    private LocalUserDao localUserDao;

    public UserController(AddressDAO addressDAO, LocalUserDao localUserDao) {
        this.addressDAO = addressDAO;
        this.localUserDao = localUserDao;
    }
    @GetMapping("/admin/users")
    public ResponseEntity<List<LocalUser>> getAll(){
        return ResponseEntity.ok(localUserDao.findAll());
    }
    @GetMapping("/address/{userId}")
    public ResponseEntity<List<Address>> getAddress(@AuthenticationPrincipal LocalUser user, @PathVariable Long userId){
        if(!userHasPermission(user,userId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(addressDAO.findByLocalUser_Id(userId));
    }

    @PutMapping("/address/{userId}")
    public ResponseEntity<Address> putAddress(@AuthenticationPrincipal LocalUser user,@PathVariable Long userId,
                                              @RequestBody Address address){
        if(!userHasPermission(user,userId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        address.setId(null);
        LocalUser refUser = new LocalUser();
        refUser.setId(userId);
        address.setUser(refUser);
        return ResponseEntity.ok(addressDAO.save(address));
    }
    @PatchMapping("{userId}/address/{addressId}")
    public ResponseEntity<Address> patchAddress(@AuthenticationPrincipal LocalUser user, @PathVariable Long userId,
                                                @PathVariable Long addressId, @RequestBody Address address){
        if(!userHasPermission(user,userId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if(address.getId()==addressId){
            Optional<Address> opOriginalAddress = addressDAO.findById(addressId);
            if(opOriginalAddress.isPresent()){
                LocalUser user2 = opOriginalAddress.get().getLocalUser();
                if(user2.getId()== userId){
                    address.setUser(user2);
                    return ResponseEntity.ok(addressDAO.save(address));
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }
    private boolean userHasPermission(LocalUser user, Long id){
        return user.getId()==id;
    }
}
