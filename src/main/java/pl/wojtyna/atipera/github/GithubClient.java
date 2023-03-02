package pl.wojtyna.atipera.github;

import java.util.List;

public interface GithubClient {

    List<GithubRepository> allRepositoriesOf(String username) throws GithubException;

    List<GithubBranch> allBranchesOf(GithubRepository repository) throws GithubException;
}
