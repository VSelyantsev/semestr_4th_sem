package ru.itis.kpfu.selyantsev.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.kpfu.selyantsev.dto.request.TaskRequest;
import ru.itis.kpfu.selyantsev.dto.response.TaskResponse;

import java.util.List;
import java.util.UUID;

@Tag(name = "Task", description = "The Task API")
@RequestMapping("api/task")
public interface TaskApi {

    @Operation(summary = "Create Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task created", content = @Content(
                    schema = @Schema(implementation = UUID.class)
            )),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest);

    @Operation(summary = "Update Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task updated", content = @Content(
                    schema = @Schema(implementation = TaskResponse.class)
            )),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PostMapping("update")
    @ResponseStatus(HttpStatus.OK)
    String updateTask(@RequestBody TaskRequest entity);

    @Operation(summary = "Delete task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted", content = @Content(
                    schema = @Schema(implementation = Void.class)
            )),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PostMapping("delete")
    @ResponseStatus(HttpStatus.OK)
    String deleteTaskByTitle(@RequestBody TaskRequest taskRequest);

    @Operation(summary = "Find task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found", content = @Content(
                    schema = @Schema(implementation = TaskResponse.class)
            )),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PostMapping("findTask")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> findTaskByTitle(String title);

    @Operation(summary = "Find all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks found", content = @Content(
                    schema = @Schema(implementation = List.class)
            )),
            @ApiResponse(responseCode = "404", description = "Tasks not found")
    })
    @GetMapping("findAll")
    @ResponseStatus(HttpStatus.OK)
    List<TaskResponse> findAll();
}
