package com.example.buensaborback.domain.dtos.error;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorDto {
    private String errorMsg;
}
