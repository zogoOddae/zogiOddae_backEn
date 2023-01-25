package com.zerobase.member.member.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InvalidArgumentsDto {
    private List<String> fieldNames;
}
