package com.bridgelabz.imp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

public @Data class DateSearchDTO 
{
	@JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate startDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate endDate;
}
