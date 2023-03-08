package pl.wojtyna.atipera.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.wojtyna.atipera.repository.Repositories;
import pl.wojtyna.atipera.repository.Repository;

import java.util.List;

@RestController
@RequestMapping("/api/v0/repositories")
public class RepositoriesRestApi {

    private final Repositories repositories;

    public RepositoriesRestApi(Repositories repositories) {
        this.repositories = repositories;
    }

    @GetMapping
    public ResponseEntity<List<Repository>> allRepositoriesOf(@RequestParam("user") String username) {
        return ResponseEntity.ok(repositories.allOf(username));
    }
}
