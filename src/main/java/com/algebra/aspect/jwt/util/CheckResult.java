package com.algebra.aspect.jwt.util;

import io.jsonwebtoken.Claims;
import lombok.Data;

/**
 * @author al
 * @date 2020/1/9 16:45
 * @description
 */
@Data
public class CheckResult {
    private boolean success;
    private Claims claims;
    private String errCode;
}
