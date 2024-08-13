package restaurant.search.domain.search.dto.naver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchItem {

	private String name;			// 업체 이름
	private String phone;			// 업체 번호

	private String url;				// 업체 URL
	private String category;

	private String address;			// 주소
	private String road_address;	// 도로명 주소

	private String mapx;			// x좌표
	private String mapy;			// y좌표
}
