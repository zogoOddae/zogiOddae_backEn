package com.zerobase.accommodation.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
	SUCCESS_CODE("성공적으로 작업을 수행 했습니다."),

	//숙박
	NOT_FOUND_ACCOMMODATION("등록된 숙박 시설이 아닙니다."),

	//블랙리스트
	ALREADY_REGISTERED_BLACKLIST("이미 등록된 회원입니다."),
	ALREADY_DELETED_BLACKLIST("이미 삭제된 블랙리스트 회원입니다."),
	NOT_HAD_WISHLIST("찜한 상품이 없습니다."),

	//리뷰
	ALREADY_REGISTERED_REVIEW("이미 등록된 리뷰가 있습니다."),
	NOT_MY_REVIEW("내가 작성한 리뷰가 아닙니다"),
	NOT_HAD_REVIEW("등록된 리뷰가 없습니다."),
	REVIEW_NOT_FOUND("리뷰를 찾을 수 없습니다."),

	//쿠폰
	NOT_REGISTERED_COUPON_GROUP("등록된 쿠폰이 아닙니다."),
	ALREADY_ISSUSED_COUPON("이미 발급된 쿠폰입니다."),
	EXPIRED_COUPON("만료된 쿠폰입니다."),
	EXCEEDED_COUNT_COUPON("발급 개수가 초과된 쿠폰입니다."),
	NOT_FOUND_COUPON("존재하지 않는 쿠폰입니다."),
	ALREADY_USED_COUPON("이미 사용된 쿠폰입니다."),


	//휴일
	NOT_FOUND_ACCOMMODATION_DAY_OFF("존재하지 않는 휴일 정보입니다."),

	//상품주문
	NOT_FOUND_ACCOMMODATION_ORDER_ITEM("존재하지 않는 상품 주문입니다."),
	NOT_FOUND_PAYMENT("존재하지 않는 결제 내역입니다."),
	//카트
	ALREADY_IN_CART("이미 장바구니에 존재합니다."),
	TOO_MANY_PERSON("숙박인원이 정원을 초과했습니다."),
	NOT_FOUND_CART("장바구니에 없는 상품입니다."),
	NOT_FOUND_ORDER_ITEM("상품을 찾을 수 없습니다."),
	NOT_HAD_ORDER_ITEM("장바구니에 등록된 상품이 없습니다."),
	NOT_MY_COUPON("발급 받은 쿠폰이 아닙니다."),
	ALREADY_ORDERED_PAYMENT("이미 예약된 결제 정보입니다."),
	NOT_PAYMENT_ORDER("결제가 되지않은 주문입니다."),
	NOT_FOUNT_ORDER("찾을 수 없는 주문입니다."),

	SERVER_INTERVAL_ERROR("서버 내부적 오류입니다."),
	NOT_FOUND_ORDER("주문 정보를 찾을 수 없습니다."),
	ALREADY_WISHED_ACCOMMODATION("이미 찜목록에 등록된 상품입니다."),
	NOT_WISHED_ACCOMMODATION("찜하지 않은 상품입니다.");

	private final String description;
}
