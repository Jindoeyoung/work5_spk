package com.spk.api.error;

import lombok.Getter;

@Getter
public enum EResultCode {
    //==========================================================
    //< Success
    //==========================================================
    SUCCESS("1", "SUCCESS"),
    FAIL("0", "FAIL"),

    //==========================================================
    //< Parsing and Validation error (1000 ~ 1999)
    //==========================================================
    INVALID_INTERFACE_VERSION("1005", "유효하지 않은 인터페이스 버전 입니다."),
    INVALID_RESPONSE_FORMAT("1006", "유효하지 않은 response_format 입니다."),
    UNKNOWN_INTERFACE_ID("1007", "알 수 없는 인터페이스 아이디입니다."),
    INVALID_INTERFACE_MAIN_ID("1008", "일치하는 인터페이스 아이디가 없습니다.(MAIN_ID)"),
    INVALID_INTERFACE_ID("1009", "일치하는 인터페이스 아이디가 없습니다."),
    
    
    REQUEST_DEFICIENT("1001", "필수 요청 항목이 누락되었습니다."),
    REQUEST_NOT_SUPPORT("1002", "지원하지 않는 항목입니다."),
    REQUEST_VALUE_IS_EMPTY("1003", "필수 요청 항목 누락(빈스트링)되었습니다."),
    UNDEFINED_VALUE("1004", "정의되지 않은 구분(타입)값이 있습니다."),	//enum 체크시 사용
    INVALID_REQUEST_FORMAT("1000", "요청값이 유효하지 않습니다."),
    INVALID_DATE_FORMAT("1000", "요청값이 유효(날짜)하지 않습니다."),
    

    
	//==========================================================
    //< Service Common error (2100 ~ 2199)
    //==========================================================
    
	FAILED_BATCH("2100", "데이터 처리에 실패했습니다."),
	FAILED_INSERT("2101", "데이터 등록에 실패했습니다."),
	FAILED_UPDATE("2102", "데이터 수정에 실패했습니다."),
	FAILED_DELETE("2103", "데이터 삭제에 실패했습니다."),
    FAILED_DUPLICATE("2104", "중복된 데이터가 존재합니다."),
	CANNOT_BE_DELETED("2105", "삭제 불가"), 
	EXCEED_MAX_FILE_LIMIT("2106", "최대 파일 개수 초과"),
	EXCEED_MAX_FILE_SIZE("2107", "최대 파일 용량 초과"),
	NOT_FOUND_DATA("2108", "데이터를 찾을 수 없습니다."),
	NOT_FOUND_BATCH_DATA("2109", "처리할 데이터가 없습니다."),
	
	//==========================================================
    //< Service Sale error (2200 ~ ) : 증명서 발급 할 경우 사용
    //==========================================================
	NOT_ALLOWED_CANCEL_SALES("2200", "판매(결제) 취소 불가함."),
	EXPIRED_TIME_CANCEL_SALES("2201", "결제 취소 가능기간 초과."),
	CANNOT_BE_DELETED_OFFLINE("2202", "오프라인 내역 삭제 불가함."),
	ALREADY_COMPLETE_OFFLINE("2203", "이미 결제가 등록되었습니다."),
	FAILED_CANCEL_PAYMENT("2204", "결제 취소(PG사 연동)에 실패하였습니다."),
	FAILED_TO_CONVERT_RESPONSE("2205", "결제 응답 처리에 실패했습니다."),
	
    //==========================================================
    //< Database (3000 ~ 3999)
    //==========================================================
    BAD_SQL_GRAMMAR("3000", "잘못된 SQL 문법"),
    SQLEXCEPTION("3001", "SQL 오류"),
    DATA_IS_NULL("3002", "데이터 조회 후 응답값이 NULL인 경우"),
    DB_FAILED_BATCH("3003", "DB BATCH 실패"),
    DB_FAILED_INSERT("3004", "DB INSERT 실패"),
    DB_FAILED_UPDATE("3005", "DB UPDATE 실패"),
    DB_FAILED_DELETE("3006", "DB DELETE 실패"),
    DB_FAILED_DUPLICATE("3007", "DB 처리 실패. 중복된 데이터가 존재합니다."),

    //==========================================================
    //< Authentication (4000 ~ 4999)
    //==========================================================
    INVALID_API_KEY("4000", "API KEY가 일치하지 않습니다."),
    INVALID_USER("4001", "아이디나 비밀번호가 맞지 않습니다. 다시 확인해 주십시오"),
    ACCOUNT_DISABLED("4002", "계정이 비활성화 되었습니다. 관리자에게 문의하세요"),
    PASSWORD_ERROR_COUNT_EXCEEDED("4003", "비밀번호 오류 횟수가 5회를 넘었습니다"),
    INVALID_TOKEN("4004", "유효하지 않은 토큰"),
    CREDENTIAL_EXPIRED("4005", "토큰 유효기간이 만료 되었습니다"),
    BLACKLIST_TOKEN("4006", "사용할 수 없는 토큰입니다."),
    ACCESS_DENIED("4007", "접근권한이 없습니다"),
    BAD_USER_STATUS("4008", "사용이 불가능한 상태의 유저입니다."),
    FAILURE_USER_AUTH("4009", "사용자 인증 실패"),
    

	//==========================================================
    //< user message (5000~5999)
    //==========================================================
    USER_ALERT_MESSAGE("5000", "사용자 메세지"),
    USER_CONFIRM_MESSAGE("5001", "사용자 CONFIRM 메세지"),
    
    
    //==========================================================
    //< HTTP Communication (6000 ~ 6999)
    //==========================================================
    HTTP_STATUS_NOT_OK("6000", "HTTP 상태오류"),
    HTTP_HOST_CONNECTION_REFUSED("6001", "HTTP Host 연결이 실패하였습니다."),
    HTTP_UNEXPECTED_CONTENT_TYPE("6002", "예상치 못한 Content Type"),
    HTTP_CONNECTION_TIMED_OUT("6003", "HTTP Connection Timeout"),

    //==========================================================
    //< Extra (9000 ~ 9999)
    //==========================================================
    INTERNAL_ERROR("9000", "내부 처리 오류"),
    FAIL_TO_SEND_SMS("9001", "SMS 발송에 실패하였습니다"),
    FAIL_SAVE_FILE("9002", "파일 저장 중 오류가 발생했습니다."),
    INVALID_VALUE_TYPE("9003", "유효하지 않은 데이터 타입입니다."),
	;

    private final String resultCode;
    private final String resultMessage;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //< Constructor

    private EResultCode(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //< getter and setter
}
