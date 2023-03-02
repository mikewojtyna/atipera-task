package pl.wojtyna.atipera.github;

import pl.wojtyna.atipera.repository.*;

import java.util.List;

public class GithubRepositories implements Repositories {

    private final GithubClient githubClient;

    public GithubRepositories(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @Override
    public List<Repository> allOf(String username) throws UserDoesNotExistException {
        try {
            return githubClient.allRepositoriesOf(username)
                               .stream()
                               .filter(githubRepository -> !githubRepository.fork())
                               .map(githubRepository -> new Repository(githubRepository.name(),
                                                                       githubRepository.owner().login(),
                                                                       fetchBranches(githubRepository)))
                               .toList();
        }
        catch (GithubException exception) {
            if (exception.getStatus() == 404) {
                throw new UserDoesNotExistException(exception.getMessage(), exception);
            }
            throw new RepositoriesException(exception.getMessage(), exception);
        }
    }

    private List<Branch> fetchBranches(GithubRepository githubRepository) {
        return githubClient.allBranchesOf(githubRepository)
                           .stream()
                           .map(githubBranch -> new Branch(githubBranch.name(), githubBranch.commit().sha()))
                           .toList();
    }
}
