package cohort22.ByteBuilder.controller;

import cohort22.ByteBuilder.dto.request.BlockNumberRequestDTO;
import cohort22.ByteBuilder.dto.request.UnblockNumberRequestDTO;
import cohort22.ByteBuilder.dto.response.BlockedNumbersResponseDTO;
import cohort22.ByteBuilder.services.UserSevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/block")
@CrossOrigin(origins = "http://localhost:5173")
public class BlockController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> blockNumber(@RequestBody BlockNumberRequestDTO request) {
        try {
            userService.blockNumber(request);
            return ResponseEntity.ok("Number blocked successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<?> unblockNumber(@RequestBody UnblockNumberRequestDTO request) {
        try {
            userService.unblockNumber(request);
            return ResponseEntity.ok("Number unblocked successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/list/{userEmail}")
    public ResponseEntity<?> getBlockedNumbers(@PathVariable("userEmail") String userEmail) {
        try {
            BlockedNumbersResponseDTO response = userService.getUserBlockedNumbers(userEmail);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
