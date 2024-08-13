package restaurant.search.domain.search.service.impl;

import java.util.List;

import org.springframework.http.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import restaurant.search.domain.search.Search;
import restaurant.search.domain.search.dto.SearchKeywordDto;
import restaurant.search.domain.search.dto.SearchResponse;
import restaurant.search.domain.search.repository.SearchRepository;
import restaurant.search.domain.search.service.SearchService;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverSearchClient implements SearchService {

	private final SearchRepository searchRepository;
	private final KakaoSearchClient kakaoSearchClient;

	@Value(value = "${naver.client.id}")
	private String naverClientId;
	@Value(value = "${naver.client.secret}")
	private String naverClientSecret;
	@Value("${naver.url.search.local}")
	private String naverLocalSearchUrl;


	@Override
	public SearchResponse searchData(String query, String sort, int page) {

		UriComponentsBuilder uriBuilder = getUriComponentsBuilder(query, sort);
		var responseEntity = getSearchResponseResponseEntity(uriBuilder);
		SearchResponse sr = responseEntity.getBody();
		return sr;
	}

	@NotNull
	private UriComponentsBuilder getUriComponentsBuilder(String query, String sort) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(naverLocalSearchUrl);
		uriBuilder.queryParam("query", query);
		uriBuilder.queryParam("display", "5");
		uriBuilder.queryParam("start", "1");
		uriBuilder.queryParam("sort", sort);
		return uriBuilder;
	}

	@NotNull
	private ResponseEntity<SearchResponse> getSearchResponseResponseEntity(UriComponentsBuilder uriBuilder) {
		var headers = new HttpHeaders();
		headers.set("X-Naver-Client-Id", naverClientId);
		headers.set("X-Naver-Client-Secret", naverClientSecret);
		headers.setContentType(MediaType.APPLICATION_JSON);

		var httpEntity = new HttpEntity<>(headers);

		var responseType = new ParameterizedTypeReference<SearchResponse>() {
		};

		var responseEntity = new RestTemplate()
			.exchange(
				uriBuilder.build().encode().toUri(),
				HttpMethod.GET,
				httpEntity,
				responseType
			);
		return responseEntity;
	}

	@Override
	public List<Search> ListWithCnt() {
		return searchRepository.findAll();
	}

	@Override
	public SearchKeywordDto saveSearchKeyword(String query) {
		Search search = searchRepository.findById(query).orElse(new Search(query, 0L));
		search.increaseSearchCnt();

		return new SearchKeywordDto(searchRepository.save(search));
	}
}
