package restaurant.search.domain.search.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import restaurant.search.domain.search.dto.kakao.DocDto;
import restaurant.search.domain.search.dto.kakao.MetaDto;
import restaurant.search.domain.search.dto.naver.SearchItem;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {

	private String lastBuildDate;	// 검색 시간
	private int total;				// 검색 결과 수
	private int start;				// 검색 결과 문서 중 문서의 시작점
	private int display;			// 검색 결과 개수
	private List<SearchItem> items;	// 검색 결과

	@JsonProperty("meta")
	private MetaDto metaDto;
	@JsonProperty("documents")
	private List<DocDto> docDtos;
}
