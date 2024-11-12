package com.apisample.address.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ZipCodeDto {
    /** ステータス */
	int status;

	/** メッセージ */
	String message;

    /** 郵便番号情報リスト */
	List<ZipCodeDataDto> results = new ArrayList<>();
}
