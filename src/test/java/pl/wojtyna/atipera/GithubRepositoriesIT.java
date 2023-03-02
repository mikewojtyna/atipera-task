package pl.wojtyna.atipera;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.wojtyna.atipera.github.GithubRepositories;
import pl.wojtyna.atipera.github.GithubUnirestClient;
import pl.wojtyna.atipera.repository.Repositories;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Github repositories integration tests")
    // Note: this test intentionally doesn't use any stubs or mocks
class GithubRepositoriesIT {

    // @formatter:off
    @DisplayName(
        """
         given Mike Wojtyna,
         when get Mike's public repositories,
         then list containing at least "domain-experiments" repo is returned
        """
    )
    // @formatter:on
    @Test
    void mikeWojtynaTest() {
        // given
        var repositories = createRepositories();
        var username = "mikewojtyna";

        // when
        var repositoriesList = repositories.allOf(username);

        // then
        assertThat(repositoriesList).isNotEmpty().anySatisfy(repository -> {
            assertThat(repository.name()).isEqualTo("domain-experiments");
            assertThat(repository.owner()).isEqualTo(username);
            assertThat(repository.branches()).isNotEmpty().anySatisfy(branch -> {
                assertThat(branch.name()).isEqualTo("master");
                assertThat(branch.lastCommitSha()).isNotEmpty();
            });
        });
    }

    private Repositories createRepositories() {
        return new GithubRepositories(new GithubUnirestClient(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                                                                                           false),
                                                              "https://api.github.com/users/{username}/repos"));
    }
}
