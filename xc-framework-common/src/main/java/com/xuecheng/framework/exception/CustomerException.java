package com.xuecheng.framework.exception;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomerException extends BaseException {

}
