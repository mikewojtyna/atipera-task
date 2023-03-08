package pl.wojtyna.atipera;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wojtyna.atipera.repository.Repositories;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ContextStartsSuccessfullyTest {

    @Autowired
    private ObjectProvider<Repositories> repositoriesObjectProvider;

    @DisplayName(
        """
         context loads and repositories bean is available
        """
    )
    // @formatter:on
    @Test
    void test() {
        assertThat(repositoriesObjectProvider.stream().findAny()).isPresent();
    }
}
