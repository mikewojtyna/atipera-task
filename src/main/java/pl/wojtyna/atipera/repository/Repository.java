package pl.wojtyna.atipera.repository;

import java.util.List;

public record Repository(String name, String owner, List<Branch> branches) {
}
