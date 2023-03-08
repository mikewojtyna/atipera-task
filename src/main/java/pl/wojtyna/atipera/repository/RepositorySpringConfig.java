package pl.wojtyna.atipera.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.atipera.github.GithubClient;
import pl.wojtyna.atipera.github.GithubRepositories;
import pl.wojtyna.atipera.github.GithubUnirestClient;

@Configuration
public class RepositorySpringConfig {

    @Bean
    public GithubClient githubClient(ObjectMapper objectMapper,
                                     @Value("${github.reposTemplate}") String userReposTemplate) {
        return new GithubUnirestClient(objectMapper, userReposTemplate);
    }

    @Bean
    public Repositories repositories(GithubClient githubClient) {
        return new GithubRepositories(githubClient);
    }
}
