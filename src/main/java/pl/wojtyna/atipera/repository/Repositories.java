package pl.wojtyna.atipera.repository;

import java.util.List;

public interface Repositories {

    List<Repository> allOf(String username) throws UserDoesNotExistException;
}
