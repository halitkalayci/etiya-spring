package com.etiya.ecommercedemo;

import com.etiya.ecommercedemo.core.util.exceptions.BusinessException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestControllerAdvice
public class EcommerceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceDemoApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}

	// Global Exception Handling-Handler
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Object handleValidationException(MethodArgumentNotValidException exception){
		//TODO: ErrorDataResult olarak ilgili hataları döndür.
		Map<String,String> errors = new HashMap<>();

		for(FieldError fieldError : exception.getBindingResult().getFieldErrors()){
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return errors;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBusinessException(BusinessException exception){
		// Result sistemi kullanılacak.
		return exception.getMessage();
	}

	// fonksiyon oluştur => @ExceptionHandler ile handler olduğunu söyle => Parametre olarak yakalayacağı exception türünü ver.
}
