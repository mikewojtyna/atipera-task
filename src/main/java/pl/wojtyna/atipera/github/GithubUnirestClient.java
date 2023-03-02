package pl.wojtyna.atipera.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;

import java.util.List;

public class GithubUnirestClient implements GithubClient {

    private final ObjectMapper objectMapper;
    private final String reposUrlTemplate;

    public GithubUnirestClient(ObjectMapper objectMapper, String reposUrlTemplate) {
        this.objectMapper = objectMapper;
        this.reposUrlTemplate = reposUrlTemplate;
    }

    @Override
    public List<GithubRepository> allRepositoriesOf(String username) {
        try {
            var response = Unirest.get(reposUrlTemplate)
                                  .routeParam("username", username)
                                  .asString();
            if (response.getStatus() == 404) {
                throw new GithubException("User %s not found".formatted(username), 404);
            }
            return objectMapper.readValue(response.getBody(), new TypeReference<>() {});
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GithubBranch> allBranchesOf(GithubRepository repository) {
        var branchesPath = repository.branches_url().replace("{/branch}", "");
        try {
            return objectMapper.readValue(Unirest.get(branchesPath).asString().getBody(), new TypeReference<>() {});
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
