package ru.itis.kpfu.selyantsev.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.kpfu.selyantsev.dto.request.UserRequest;
import ru.itis.kpfu.selyantsev.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

@Tag(name = "User", description = "The User API")
@RequestMapping("api/user")
public interface UserApi {

    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created", content = @Content(
                    schema = @Schema(implementation = UUID.class)
            )),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    String createUser(@RequestBody UserRequest userRequest);

    @Operation(summary = "Update User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User updated", content = @Content(
                    schema = @Schema(implementation = UserResponse.class)
            )),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("update")
    @ResponseStatus(HttpStatus.OK)
    String updateUser(@RequestBody UserRequest entity);

    @Operation(summary = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted", content = @Content(
                    schema = @Schema(implementation = Void.class)
            )),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("delete/{userId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteUserById(@PathVariable UUID userId);

    @Operation(summary = "Find user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = @Content(
                    schema = @Schema(implementation = UserResponse.class)
            )),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("find/{userId}")
    @ResponseStatus(HttpStatus.OK)
    UserResponse findUserById(@PathVariable UUID userId);

    @Operation(summary = "Find all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found", content = @Content(
                    schema = @Schema(implementation = List.class)
            )),
            @ApiResponse(responseCode = "404", description = "Users not found")
    })
    @GetMapping("findAll")
    @ResponseStatus(HttpStatus.OK)
    List<UserResponse> findAll();
}
