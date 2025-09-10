package ru.itche.backend.service.document;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itche.backend.controller.document.payload.NewDocumentPayload;
import ru.itche.backend.entity.Document;
import ru.itche.backend.entity.Instructor;
import ru.itche.backend.repository.document.DocRepository;
import ru.itche.backend.service.instructor.InstructorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceDef implements DocumentService {

    private final DocRepository docRepository;
    private final InstructorService instructorService;

    @Override
    public List<Document> findAllDocUser(Long id) {
        return null;
    }

    @Override
    public Document createDoc(Long userId,NewDocumentPayload payload) {

        Instructor instructor = instructorService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("instructor not found"));

        return docRepository.save(new Document(null,
                instructor,
                payload.doc()));
    }
}
