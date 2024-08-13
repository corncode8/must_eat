package restaurant.search.domain.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restaurant.search.domain.search.Search;

public interface SearchRepository extends JpaRepository<Search, String> {
}
