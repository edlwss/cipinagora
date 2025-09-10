package ru.itche.backend.controller.lookup;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itche.backend.entity.reference.Sport;
import ru.itche.backend.service.lookup.SportService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cipinagora/api/lookup/sports")
public class SportsLookupRestController {

    private final SportService sportService;

    @GetMapping
    public Iterable<Sport> getSports() {
        return sportService.getSports();
    }

}
