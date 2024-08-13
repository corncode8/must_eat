package restaurant.search.domain.search;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Search {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String search;
	private Long searchCnt;

	public Search(String search, Long searchCnt) {
		this.search = search;
		this.searchCnt = searchCnt;
	}

	public void increaseSearchCnt() {
		searchCnt += 1;
	}

}
