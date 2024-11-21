package umc.spring.apiPayload.code.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.exception.GeneralException;

public class StoreHandler extends GeneralException {
  public StoreHandler(BaseErrorCode errorCode) {
    super(errorCode);
  }
}