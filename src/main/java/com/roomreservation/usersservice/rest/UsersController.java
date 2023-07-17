package com.roomreservation.usersservice.rest;

import com.roomreservation.usersservice.core.LoginRequestDto;
import com.roomreservation.usersservice.core.UserDto;
import com.roomreservation.usersservice.core.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Api(value = "Kontroler użytkowników")
public class UsersController {

    UsersService userService;

    UsersController(UsersService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(value = "Rejestracja nowego użytkownika")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Stworzono"),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto newUser) {
        userService.register(newUser);
//        return new ResponseEntity<>("Stworzono", HttpStatus.CREATED);
        return new ResponseEntity<>("Usługa jeszcze nie jest gotowa", HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Zwraca dane użytkownika o podanym ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces", response = UserDto.class),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        UserDto user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Aktualizuje dane użytkownika o danym ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody UserDto updatedUser) {
        userService.update(id, updatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Usuwa użytkownika o danym ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    @ApiOperation(value = "Loguje użytkownika do systemu")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDto loginRequest) {
        String token = userService.login(loginRequest);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
