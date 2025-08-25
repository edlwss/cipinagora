package ru.itche.backend.controller.lookup;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.itche.backend.entity.reference.AgeCategories;
import ru.itche.backend.service.lookup.AgeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cipinagora/api/lookup/age")
public class AgeCategoriesLookupRestController {

    private final AgeService ageService;

    @GetMapping()
    public Iterable<AgeCategories> getAgeCategories() {
        return ageService.getAges();
    }

    @GetMapping("/{id:\\d+}")
    public AgeCategories getAgeCategories(@PathVariable("id") Long id) {
        return ageService.setAge(id).orElseThrow(()
                -> new AgeCategoriesLookupRestController.AgeCategoriesNotFoundException(id));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private static class AgeCategoriesNotFoundException extends RuntimeException {
        public AgeCategoriesNotFoundException(Long id) {
            super("Age categories with id " + id + " not found");
        }
    }
}
