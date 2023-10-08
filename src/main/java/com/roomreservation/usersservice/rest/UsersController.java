package com.roomreservation.usersservice.rest;

import com.roomreservation.usersservice.core.UsersService;
import com.roomreservation.usersservice.exceptions.UserAlreadyExistException;
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

    @PostMapping("/register")
    @ApiOperation(value = "Rejestracja nowego użytkownika")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Stworzono", response = JwtAuthenticationResponse.class),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
            @ApiResponse(code = 400, message = "Użytkownik z podanym adresem e-mail już istnieje"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
    })
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDto newUser) {
        try {
            JwtAuthenticationResponse response = userService.register(newUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<>("Użytkownik z podanym adresem e-mail już istnieje", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Zwraca dane użytkownika o podanym ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces", response = RegisterRequestDto.class),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
            @ApiResponse(code = 403, message = "Brak dostępu"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
    })
    public ResponseEntity<?> getUser(@PathVariable GetUserDataRequestDto userData) {
        RegisterRequestDto user = userService.getUser(userData);
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
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody RegisterRequestDto updatedUser) {
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
}
