package ru.itche.backend.controller.center;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itche.backend.entity.SportsCenter;
import ru.itche.backend.service.center.SportsCenterService;
import ru.itche.backend.controller.center.payload.UpdateSportsCenterPayload;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cipinagora/api/centers/center/{id:\\d+}")
public class SportsCenterRestController {

    private final SportsCenterService centerService;

    @ModelAttribute("center")
    public SportsCenter getCenter(@PathVariable("id") Long id) {
        return centerService.findById(id)
                .orElseThrow(() -> new RuntimeException("Sports center not found"));
    }

    @GetMapping
    public SportsCenter get(@ModelAttribute("center") SportsCenter center) {
        return center;
    }

    @PatchMapping
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestBody UpdateSportsCenterPayload payload) {
        centerService.update(id, payload);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        centerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
