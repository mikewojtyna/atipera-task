package pl.wojtyna.atipera.github;

public record GithubRepository(String name, Owner owner, String branches_url, boolean fork) {
}
