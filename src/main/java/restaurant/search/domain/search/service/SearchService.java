package restaurant.search.domain.search.service;

import java.util.List;

import restaurant.search.domain.search.Search;
import restaurant.search.domain.search.dto.SearchKeywordDto;
import restaurant.search.domain.search.dto.SearchResponse;

public interface SearchService {

	SearchResponse searchData(String query, String sort, int page);
	List<Search> ListWithCnt();
	SearchKeywordDto saveSearchKeyword(String query);

}
