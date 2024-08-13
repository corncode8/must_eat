package restaurant.search.domain.search.dto;

import lombok.Data;
import restaurant.search.domain.search.Search;

@Data
public class SearchKeywordDto {

	private String search;
	private Long searchCnt;

	public SearchKeywordDto(Search search) {
		this.search = search.getSearch();
		this.searchCnt = search.getSearchCnt();
	}
}
