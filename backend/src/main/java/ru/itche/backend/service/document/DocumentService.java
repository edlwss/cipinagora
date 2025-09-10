package ru.itche.backend.service.document;

import ru.itche.backend.controller.document.payload.NewDocumentPayload;
import ru.itche.backend.entity.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    List<Document> findAllDocUser(Long id);
    Document createDoc(Long userId, NewDocumentPayload payload);

}
