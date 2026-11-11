package sn.ucad.biblioapi.commun;

import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GestionnaireErreurs extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GestionnaireErreurs.class);

    @ExceptionHandler(RessourceIntrouvableException.class)
    public ProblemDetail introuvable(RessourceIntrouvableException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(ConflitException.class)
    public ProblemDetail conflit(ConflitException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }

    @Override                                              // échec de @Valid
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
            HttpHeaders headers, HttpStatusCode status, WebRequest requete) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "Les données envoyées sont invalides");
        Map<String, String> erreurs = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors()                 // un message par champ (le premier)
                .forEach(f -> erreurs.putIfAbsent(f.getField(), f.getDefaultMessage()));
        pb.setProperty("erreurs", erreurs);
        return handleExceptionInternal(e, pb, headers, status, requete);
    }

    @ExceptionHandler(Exception.class)                     // le filet de sécurité, en dernier recours
    public ProblemDetail inattendue(Exception e) {
        log.error("Erreur inattendue", e);                     // le détail va dans le journal...
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                "Erreur interne");                             // ... pas au client
    }
}
