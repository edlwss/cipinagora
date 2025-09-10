package ru.itche.backend.controller.document;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.itche.backend.controller.document.payload.NewDocumentPayload;
import ru.itche.backend.entity.Document;
import ru.itche.backend.service.document.DocumentService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cipinagora/api/doc/{userId:\\d+}")
public class DocRestController {

    private final DocumentService docService;

    @GetMapping
    public List<Document> getDocUser(@PathVariable Long userId) {
        return docService.findAllDocUser(userId);
    }

    @PostMapping("/add")
    public ResponseEntity<Document> addDoc(@PathVariable Long userId, @RequestBody NewDocumentPayload payload,
                           UriComponentsBuilder uriBuilder) {

        Document doc = docService.createDoc(userId, payload);

        return ResponseEntity.created(uriBuilder
                        .replacePath("/cipinagora/api/instructors/{id}")
                        .build(Map.of("id", doc.getId())))
                .body(doc);
    }


}
