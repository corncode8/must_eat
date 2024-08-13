package restaurant.search.domain.search.dto;

import lombok.Data;

@Data
public class SearchInputDto {

	private String search;
	private String sort;
	private String page;
}
