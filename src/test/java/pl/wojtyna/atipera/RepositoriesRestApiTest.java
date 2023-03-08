package pl.wojtyna.atipera;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.wojtyna.atipera.repository.Branch;
import pl.wojtyna.atipera.repository.Repositories;
import pl.wojtyna.atipera.repository.Repository;
import pl.wojtyna.atipera.repository.UserDoesNotExistException;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Repositories REST API")
@SpringBootTest
@AutoConfigureMockMvc
class RepositoriesRestApiTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Repositories repositories;

    // @formatter:off
    @DisplayName(
        """
         given Mike has only one repository,
         when GET on /api/v0/repositories with param user=mike,
         then this repository is returned
        """
    )
    // @formatter:on
    @Test
    void fetchTest() throws Exception {
        // given
        var firstRepo = new Repository("first repository",
                                       "mike",
                                       List.of(new Branch("master", "312382290f4f71e7fb7f00449fb529fce3b8ec95")));
        when(repositories.allOf("mike")).thenReturn(List.of(firstRepo));

        // when
        mockMvc.perform(get("/api/v0/repositories").accept(MediaType.APPLICATION_JSON).param("user", "mike"))

               // then
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].name", is("first repository")))
               .andExpect(jsonPath("$[0].owner", is("mike")))
               .andExpect(jsonPath("$[0].branches", hasSize(1)))
               .andExpect(jsonPath("$[0].branches[0].lastCommitSha", is("312382290f4f71e7fb7f00449fb529fce3b8ec95")));
    }

    // @formatter:off
    @DisplayName(
        """
         given Mike doesn't exist :(,
         when GET on /api/v0/repositories with param user=mike,
         then 404 error response is returned
        """
    )
    // @formatter:on
    @Test
    void userDoesntExistTest() throws Exception {
        // given
        when(repositories.allOf("mike")).thenThrow(new UserDoesNotExistException("user doesn't exist", "mike"));

        // when
        mockMvc.perform(get("/api/v0/repositories").accept(MediaType.APPLICATION_JSON).param("user", "mike"))

               // then
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.status", is(404)))
               .andExpect(jsonPath("$.message", is("User mike doesn't exist")));
    }

    // @formatter:off
    @DisplayName(
        """
         given Accept: application/xml header,
         when GET on /api/v0/repositories with param user=mike,
         then 406 error response is returned
        """
    )
    // @formatter:on
    @Test
    void xmlNotSupportedTest() throws Exception {
        // when
        mockMvc.perform(get("/api/v0/repositories").accept("application/xml").param("user", "mike"))

               // then
               .andExpect(status().isNotAcceptable())
               .andExpect(jsonPath("$.status", is(406)))
               .andExpect(jsonPath("$.message", is("No acceptable representation")));
    }
}
